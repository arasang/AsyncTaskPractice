package park.sangeun.aysnctest.domain.exchange.model

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.PositiveOrZero
import park.sangeun.aysnctest.common.ValidEnum

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class ExchangeRateRequest(
    @field:ValidEnum(enumClass = CurrencyNationEnum::class)
    var baseCurrency: CurrencyNationEnum? = null,

    @field: PositiveOrZero(message = "조회할 금액을 입력하세요.")
    val amount: Long
)