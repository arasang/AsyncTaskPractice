package park.sangeun.aysnctest.domain

import org.springframework.beans.factory.annotation.Autowired
import park.sangeun.aysnctest.AysncTestApplicationTests
import park.sangeun.aysnctest.common.component.RedisService
import park.sangeun.aysnctest.domain.common.GlobalConstant.Companion.REDIS_MEMBER_INFO
import kotlin.test.Test

class MemberControllerTest(
    @Autowired
    private val redisService: RedisService
): AysncTestApplicationTests() {
    @Test
    fun test() {
        println(redisService.getHashEntries("$REDIS_MEMBER_INFO:1"))
    }
}