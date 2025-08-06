package park.sangeun.aysnctest

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(args = ["--spring.profiles.active=local", "--jasypt.encryptor.password=testKey"])
class AysncTestApplicationTests {

}
