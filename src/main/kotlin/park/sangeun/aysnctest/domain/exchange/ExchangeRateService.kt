package park.sangeun.aysnctest.domain.exchange

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import park.sangeun.aysnctest.domain.exchange.model.CurrencyNationEnum
import park.sangeun.aysnctest.domain.exchange.model.ExchangeRateRequest

@Service
class ExchangeRateService(
    private val webClient: WebClient,

    @Value("\${exchange-rate.url}")
    private val exchangeUrl: String,

    @Value("\${exchange-rate.key}")
    private val exchangeKey: String
) {
    fun getRateList(request: ExchangeRateRequest) {
        val baseCurrency = if (request.baseCurrency == null) CurrencyNationEnum.KOREA
        else request.baseCurrency

        CurrencyNationEnum.entries.forEach { target ->
            webClient.get()
                .uri { builder ->
                    builder.host(exchangeUrl)
                        .path("/convert")
                        .queryParam("access_key", exchangeKey)
                        .queryParam("from", baseCurrency!!.value)
                        .queryParam("to", target)
                        .build()
                }
        }
    }
}