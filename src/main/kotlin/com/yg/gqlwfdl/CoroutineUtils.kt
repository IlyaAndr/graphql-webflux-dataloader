package com.yg.gqlwfdl

import reactor.core.publisher.Mono
import kotlin.coroutines.experimental.suspendCoroutine

// Miscellaneous utility methods related to coroutines

/**
 * Wraps `this` (a [Mono]) into a coroutine. Subscribes to the Mono then suspends the coroutine while awaiting for the
 * Mono to supply its value. Then resumes the coroutine with the result or with the exception, if an error occurred.
 */
suspend inline fun <T> Mono<T>.awaitResult(): T {
    return suspendCoroutine { cont ->
        try {
            this
                    .doOnError { cont.resumeWithException(it) }
                    .subscribe { cont.resume(it) }
        } catch (e: Exception) {
            cont.resumeWithException(e)
        }
    }
}