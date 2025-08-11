package park.sangeun.aysnctest.domain

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import park.sangeun.aysnctest.AysncTestApplicationTests
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import park.sangeun.aysnctest.common.toMultiValueMap
import park.sangeun.aysnctest.domain.exchange.model.CurrencyNationEnum
import park.sangeun.aysnctest.domain.exchange.model.ExchangeRateRequest

class ExchangeRateControllerTest: AysncTestApplicationTests() {
    @Test
    fun getExchangeRateList() {
        val request = ExchangeRateRequest(amount = 1000,)
        request.baseCurrency = CurrencyNationEnum.KOREA

        val response = mockMvc?.perform (
            get("$baseUrl/exchange/rate")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .params(
                    request.toMultiValueMap()
                )
        )
            ?.andDo(MockMvcResultHandlers.print())
            ?.andExpect(status().isOk)
            ?.andReturn()
            ?.response
    }
}