package park.sangeun.aysnctest.common.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Semaphore
import org.springframework.web.reactive.function.client.WebClientResponseException

private val limiter = Semaphore(2)
suspend fun <T> withLimit(block: suspend() -> T): T {
    limiter.acquire()
    try {
        return block()
    } finally {
        limiter.release()
    }
}

suspend fun <T> retry429(maxRetries: Int = 3, block: suspend() -> T): T {
    var attempt = 0
    var delayMs = 500L

    while(true) {
        try {
            return block()
        } catch (e: WebClientResponseException.TooManyRequests) {
            attempt++
            if (attempt > maxRetries) throw e

            val retryAfterSec = e.headers.getFirst("Retry-After")?.toLongOrNull()
            val waitTime = retryAfterSec?.times(1000) ?: delayMs

            delay(waitTime)
            delayMs = (delayMs * 2).coerceAtMost(4000)
        }
    }
}