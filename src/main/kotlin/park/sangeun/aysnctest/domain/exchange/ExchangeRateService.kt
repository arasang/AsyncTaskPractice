package park.sangeun.aysnctest.domain.exchange

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.awaitBody
import park.sangeun.aysnctest.common.retry429
import park.sangeun.aysnctest.common.withLimit
import park.sangeun.aysnctest.domain.exchange.model.CurrencyNationEnum
import park.sangeun.aysnctest.domain.exchange.model.CurrencyResultResponse
import park.sangeun.aysnctest.domain.exchange.model.ExchangeRateRequest
import park.sangeun.aysnctest.domain.exchange.model.ExchangeRateResponse
import java.math.BigDecimal

@Service
class ExchangeRateService(
    @Qualifier("exchangeRateWebClient")
    private val webClient: WebClient,
    @Value("\${exchange-rate.key}")
    private val exchangeKey: String
) {
    suspend fun getRateList(request: ExchangeRateRequest): List<ExchangeRateResponse> = coroutineScope {
        val base = if (request.baseCurrency == null) CurrencyNationEnum.KOREA
        else request.baseCurrency

        val currency =  CurrencyNationEnum.entries
            .filter { it != base }
            .map { t ->
                async {
                    withLimit {
                        retry429 {
                            getRate(request.amount, t, base!!)
                        }
                    }
                }
            }

        currency.awaitAll()
    }

    private suspend fun getRate(amount: Long, target: CurrencyNationEnum, baseCurrency: CurrencyNationEnum): ExchangeRateResponse {
        return try {
            val response =  webClient.get()
                .uri { builder ->
                    val build = builder
                        .path("/convert")
                        .queryParam("access_key", exchangeKey)
                        .queryParam("from", baseCurrency.value)
                        .queryParam("to", target.value)
                        .queryParam("amount", amount)
                        .build()
                    println(builder.toUriString())
                    build
                }
                .retrieve()
                .awaitBody<CurrencyResultResponse>()

            ExchangeRateResponse(
                to = response.query.to,
                from = response.query.from,
                amount = response.query.amount,
                rate = response.info.quote,
                result = response.result
            )
        } catch(e: WebClientResponseException.TooManyRequests) {
          throw e
        } catch (e1: Exception) {
            println(e1.javaClass.name)
            e1.printStackTrace()
            ExchangeRateResponse(
                to = target.value,
                from = baseCurrency.value,
                amount = 0,
                rate = BigDecimal.valueOf(0.0),
                result = 0.0f
            )
        }
    }

}