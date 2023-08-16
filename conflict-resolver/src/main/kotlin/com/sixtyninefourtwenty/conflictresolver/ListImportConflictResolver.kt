@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.sixtyninefourtwenty.conflictresolver

import java.util.function.BiConsumer
import java.util.function.BiPredicate
import java.util.function.BinaryOperator
import java.util.function.Consumer

class ConflictResolution<T> internal constructor(
    val existingList: MutableList<T>,
    val existingElement: T,
    val importedList: MutableList<T>,
    val importedElement: T,
    private val retryFunction: () -> Unit
) {
    fun retry() = retryFunction()
}

abstract class BaseListImportConflictResolver<T>(
    existingList: List<T>,
    importedList: List<T>,
    private val conflictPredicate: BiPredicate<in T, in T>,
    private val conflictResolution: Consumer<in ConflictResolution<T>>
) {
    private val existingList = ArrayList(existingList)
    private val importedList = ArrayList(importedList)
    private val retryFunction: () -> Unit = (::resolve)

    fun resolve() {
        for (existingElement in existingList) {
            for (importedElement in importedList) {
                if (conflictPredicate.test(existingElement, importedElement)) {
                    conflictResolution.accept(ConflictResolution(existingList, existingElement, importedList, importedElement, retryFunction))
                    return
                }
            }
        }
        processResults(existingList, importedList)
    }

    abstract fun processResults(resolvedExistingList: List<T>, resolvedImportedList: List<T>)

    companion object {
        @JvmStatic
        @JvmOverloads
        fun <T> create(
            existingList: List<T>,
            importedList: List<T>,
            conflictPredicate: BiPredicate<in T, in T>,
            conflictResolution: Consumer<in ConflictResolution<T>>,
            resultCombiner: BinaryOperator<List<T>> = BinaryOperator { first, second -> first + second },
            onResolved: Consumer<in List<T>>
        ): BaseListImportConflictResolver<T> =
            ListImportConflictResolver(existingList, importedList, conflictPredicate, conflictResolution, resultCombiner, onResolved)

        @JvmStatic
        fun <T> create(
            existingList: List<T>,
            importedList: List<T>,
            conflictPredicate: BiPredicate<in T, in T>,
            conflictResolution: Consumer<in ConflictResolution<T>>,
            onResolved: BiConsumer<in List<T>, in List<T>>
        ): BaseListImportConflictResolver<T> =
            ListImportConflictResolver2(existingList, importedList, conflictPredicate, conflictResolution, onResolved)
    }

}

class ListImportConflictResolver<T> @JvmOverloads constructor(
    existingList: List<T>,
    importedList: List<T>,
    conflictPredicate: BiPredicate<in T, in T>,
    conflictResolution: Consumer<in ConflictResolution<T>>,
    private val resultCombiner: BinaryOperator<List<T>> = BinaryOperator { first, second -> first + second },
    private val onResolved: Consumer<in List<T>>
) : BaseListImportConflictResolver<T>(
    existingList, importedList, conflictPredicate, conflictResolution
) {

    override fun processResults(resolvedExistingList: List<T>, resolvedImportedList: List<T>) {
        onResolved.accept(resultCombiner.apply(resolvedExistingList, resolvedImportedList))
    }

}

internal class ListImportConflictResolver2<T>(
    existingList: List<T>,
    importedList: List<T>,
    conflictPredicate: BiPredicate<in T, in T>,
    conflictResolution: Consumer<in ConflictResolution<T>>,
    private val onResolved: BiConsumer<in List<T>, in List<T>>
) : BaseListImportConflictResolver<T>(
    existingList, importedList, conflictPredicate, conflictResolution
) {

    override fun processResults(resolvedExistingList: List<T>, resolvedImportedList: List<T>) {
        onResolved.accept(resolvedExistingList, resolvedImportedList)
    }

}
