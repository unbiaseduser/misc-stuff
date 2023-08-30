import com.sixtyninefourtwenty.conflictresolver.BaseListImportConflictResolver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ListImportConflictResolverTest {

    @Test
    fun keepElementsInFirstList() {
        val firstList = listOf(10, 0, 1, 2)
        val secondList = listOf(1, 2, 42)
        val conflicts = listOf(1, 2)
        assertTrue(firstList.containsAll(conflicts))
        assertTrue(secondList.containsAll(conflicts))
        // Keep conflicting elements in first list, discard those in second list
        BaseListImportConflictResolver.create(
            existingList = firstList,
            importedList = secondList,
            conflictPredicate = (Any::equals),
            conflictResolution = { resolution ->
                // Any.equals() is used as conflict predicate.
                assertEquals(resolution.existingElement, resolution.importedElement)
                assertTrue(resolution.existingElement in conflicts)
                resolution.importedList.remove(resolution.importedElement)
                resolution.retry()
            },
            onResolved = { list ->
                /* By default, resultCombiner simply adds first list and second list after resolve.
                Using current resolve strategy:
                Iteration 1: firstList: [10, 0, 1, 2], secondList: [2, 42] (Conflicting element is 1, which is removed from second list.)
                Iteration 2: firstList: [10, 0, 1, 2], secondList: [42] (Conflicting element is 2, which is removed from second list.)
                Iteration 3: No more conflicts. Final list is firstList + secondList = [10, 0, 1, 2] + [42].
                */
                assertIterableEquals(listOf(10, 0, 1, 2, 42), list)
            }
        ).resolve()
    }

}