package park.sangeun.aysnctest

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class)
@SpringBootTest(args = ["--spring.profiles.active=local", "--jasypt.encryptor.password=testKey"])
@ActiveProfiles("local")
class AysncTestApplicationTests {
    protected var mockMvc: MockMvc?= null

    protected val baseUrl = "http://localhost:8082"

    @BeforeEach
    fun set(
        webApplication: WebApplicationContext
    ) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplication)
            .build()
    }
}
