package park.sangeun.aysnctest.domain.exchange.model

data class CurrencyResultResponse(
    val success: Boolean,
    val terms: String,
    val privacy: String,
    val query: Query,
    val info: Info,
    val result: Float
) {
    data class Query (
        val from: String,
        val to: String,
        val amount: Long
    )

    data class Info (
        val timestamp: Long,
        val quote: Float
    )
}
