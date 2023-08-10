package com.sixtyninefourtwenty.conflictresolver

import java.util.function.BiPredicate
import java.util.function.BinaryOperator
import java.util.function.Consumer

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ListImportConflictResolver<out T> @JvmOverloads constructor(
    existingList: List<T>,
    importedList: List<T>,
    private val conflictPredicate: BiPredicate<in T, in T>,
    private val conflictResolution: Consumer<in ConflictResolution<T>>,
    private val resultCombiner: BinaryOperator<List<T>> = BinaryOperator { first, second -> first + second },
    private val onResolved: Consumer<in List<T>>
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
        onResolved.accept(resultCombiner.apply(existingList, importedList))
    }

    class ConflictResolution<T> internal constructor(
        val existingList: MutableList<T>,
        val existingElement: T,
        val importedList: MutableList<T>,
        val importedElement: T,
        private val retryFunction: () -> Unit
    ) {
        fun retry() = retryFunction()
    }
}