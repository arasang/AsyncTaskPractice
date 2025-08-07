package park.sangeun.aysnctest.domain.exchange.model

import com.fasterxml.jackson.annotation.JsonInclude
import park.sangeun.aysnctest.common.ValidEnum

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class ExchangeRateRequest(
    @field:ValidEnum(enumClass = CurrencyNationEnum::class)
    var baseCurrency: CurrencyNationEnum? = null
)