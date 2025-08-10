package park.sangeun.aysnctest.domain.exchange

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import park.sangeun.aysnctest.domain.exchange.model.CurrencyNationEnum
import park.sangeun.aysnctest.domain.exchange.model.CurrencyResultResponse
import park.sangeun.aysnctest.domain.exchange.model.ExchangeRateRequest
import park.sangeun.aysnctest.domain.exchange.model.ExchangeRateResponse

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
                    getRate(t, base!!)
                }
            }

        currency.awaitAll()
    }

    private suspend fun getRate(target: CurrencyNationEnum, baseCurrency: CurrencyNationEnum): ExchangeRateResponse {
        return try {
            val response =  webClient.get()
                .uri { builder ->
                    builder
                        .path("/convert")
                        .queryParam("access_key", exchangeKey)
                        .queryParam("from", baseCurrency.value)
                        .queryParam("to", target.value)
                        .build()
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
        } catch (e: Exception) {
            e.printStackTrace()
            ExchangeRateResponse(
                to = target.value,
                from = baseCurrency.value,
                amount = 0,
                rate = 0.0f,
                result = 0.0f
            )
        }
    }

}