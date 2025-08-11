package park.sangeun.aysnctest.domain.exchange.model

import java.math.BigDecimal

data class ExchangeRateResponse(
    val to: String,
    val from: String,
    val amount: Long,
    val rate: BigDecimal,
    val result: Float,
)
