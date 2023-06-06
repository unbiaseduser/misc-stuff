package com.sixtyninefourtwenty.kotlinjavacompat.coroutines

import kotlinx.coroutines.CoroutineScope
import java.util.function.Consumer
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

@Suppress("unused")
class ContinuationCompat<T> @JvmOverloads constructor(
    private val scope: CoroutineScope,
    private val onSuccess: Consumer<T>? = null,
    private val onFailure: Consumer<Exception>? = null
) : Continuation<T> {

    override val context: CoroutineContext
        get() = scope.coroutineContext

    override fun resumeWith(result: Result<T>) {
        result.onSuccess {
            onSuccess?.accept(it)
        }.onFailure {
            if (it is Exception && onFailure != null) {
                onFailure.accept(it)
            } else throw it
        }
    }
}