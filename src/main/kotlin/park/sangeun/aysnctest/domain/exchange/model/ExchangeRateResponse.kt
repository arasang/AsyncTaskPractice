package park.sangeun.aysnctest.domain.exchange.model

data class ExchangeRateResponse(
    val to: String,
    val from: String,
    val amount: Long,
    val rate: Float,
    val result: Float,
)
