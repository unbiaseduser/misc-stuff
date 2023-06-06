@file:Suppress("unused")
package com.sixtyninefourtwenty.kotlinjavacompat

/**
 * A functional interface that can be used in place of Kotlin's `() -> Unit` to get rid of the awkward `return Unit.INSTANCE` return statement.
 */
fun interface Function0Compat : () -> Unit {
    override fun invoke() {
        invokeCustom()
    }

    fun invokeCustom()
}

/**
 * A functional interface that can be used in place of Kotlin's `(T) -> Unit` to get rid of the awkward `return Unit.INSTANCE` return statement.
 */
fun interface Function1Compat<T> : Function1<T, Unit> {
    override fun invoke(t: T) {
        invokeCustom(t)
    }

    fun invokeCustom(t: T)
}

/**
 * A functional interface that can be used in place of Kotlin's `(T, U) -> Unit` to get rid of the awkward `return Unit.INSTANCE` return statement.
 */
fun interface Function2Compat<T, U> : Function2<T, U, Unit> {
    override fun invoke(t: T, u: U) {
        invokeCustom(t, u)
    }

    fun invokeCustom(t: T, u: U)
}
