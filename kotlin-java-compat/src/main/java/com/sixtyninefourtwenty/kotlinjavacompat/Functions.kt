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

    companion object {
        /**
         * Convenience method that allows to avoid casting your lambda expression.
         *
         * Usage: Just replace occurrences of
         *
         * ```
         * method((Function0Compat) () -> doStuff());
         * ```
         *
         * with
         *
         * ```
         * // shorten() is static imported
         * method(shorten(() -> doStuff()));
         * ```
         */
        @JvmStatic
        fun shorten(block: Function0Compat): () -> Unit = block
    }
}

/**
 * A functional interface that can be used in place of Kotlin's `(T) -> Unit` to get rid of the awkward `return Unit.INSTANCE` return statement.
 */
fun interface Function1Compat<in T> : Function1<T, Unit> {
    override fun invoke(t: T) {
        invokeCustom(t)
    }

    fun invokeCustom(t: T)

    companion object {
        /**
         * Convenience method that allows to avoid casting your lambda expression.
         *
         * Usage: Just replace occurrences of
         *
         * ```
         * method((Function1Compat<MyClass>) myObj -> doStuff(myObj));
         * ```
         *
         * with
         *
         * ```
         * // shorten() is static imported
         * method(shorten(myObj -> doStuff(myObj)));
         * ```
         */
        @JvmStatic
        fun <T> shorten(block: Function1Compat<T>): (T) -> Unit = block
    }
}

/**
 * A functional interface that can be used in place of Kotlin's `(T, U) -> Unit` to get rid of the awkward `return Unit.INSTANCE` return statement.
 */
fun interface Function2Compat<in T, in U> : Function2<T, U, Unit> {
    override fun invoke(t: T, u: U) {
        invokeCustom(t, u)
    }

    fun invokeCustom(t: T, u: U)

    companion object {
        /**
         * Convenience method that allows to avoid casting your lambda expression.
         *
         * Usage: Just replace occurrences of
         *
         * ```
         * method((Function2Compat<MyClassOne, MyClassTwo>) (myObjOne, myObjTwo) -> doStuff(myObjOne, myObjTwo));
         * ```
         *
         * with
         *
         * ```
         * // shorten() is static imported
         * method(shorten((myObjOne, myObjTwo) -> doStuff(myObjOne, myObjTwo)));
         * ```
         */
        @JvmStatic
        fun <T, U> shorten(block: Function2Compat<T, U>): (T, U) -> Unit = block
    }
}
