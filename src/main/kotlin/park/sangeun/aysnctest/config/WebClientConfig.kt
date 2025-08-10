package park.sangeun.aysnctest.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpHeaders.ACCEPT
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
class WebClientConfig(
    @Value("\${exchange-rate.url}")
    private val exchangeUrl: String,
) {
    private val timeout = 10000L
    private val baseUrl = ""

    @Bean
    @Primary
    fun webClient(): WebClient {
        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(getHttpClient()))
            .baseUrl(baseUrl)
            .defaultHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultStatusHandler(HttpStatusCode::isError) {
                it.createException().flatMap {
                    Mono.error {
                        throw Exception(it.message)
                    }
                }
            }
            .build()
    }

    @Bean
    fun exchangeRateWebClient(): WebClient {
        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(getHttpClient()))
            .baseUrl(exchangeUrl)
            .defaultHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultStatusHandler(HttpStatusCode::isError) {
                it.createException().flatMap {
                    Mono.error {
                        throw Exception(it.message)
                    }
                }
            }
            .build()
    }

    private fun getHttpClient(): HttpClient {
        return HttpClient.create()
            .followRedirect(true)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout.toInt())
            .responseTimeout(Duration.ofMillis(timeout))
            .doOnConnected { conn ->
                conn.addHandlerLast(ReadTimeoutHandler(timeout, TimeUnit.MILLISECONDS))
                    .addHandlerLast(WriteTimeoutHandler(timeout, TimeUnit.MILLISECONDS))
            }
    }
}