package park.sangeun.aysnctest.domain.exchange

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import park.sangeun.aysnctest.domain.exchange.model.ExchangeRateRequest
import park.sangeun.aysnctest.domain.exchange.model.ExchangeRateResponse

@RestController
@RequestMapping("/exchange/rate")
class ExchangeRateController(
    private val service: ExchangeRateService
) {
    @GetMapping
    fun getExchangeRate(
        @ModelAttribute @Valid request: ExchangeRateRequest?,
    ): ExchangeRateResponse {
        return ExchangeRateResponse()
    }
}