package park.sangeun.aysnctest.domain.exchange.model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class ExchangeRateRequest(
    var baseCurrency: String? = null
)