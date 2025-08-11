package park.sangeun.aysnctest.domain

import org.springframework.beans.factory.annotation.Autowired
import park.sangeun.aysnctest.AysncTestApplicationTests
import park.sangeun.aysnctest.common.component.RedisService
import park.sangeun.aysnctest.domain.common.GlobalConstant.Companion.REDIS_MEMBER_INFO
import park.sangeun.aysnctest.domain.member.repository.MemberRepository
import kotlin.test.Test

class MemberControllerTest(
    @Autowired
    private val redisService: RedisService,

    @Autowired
    private val memberRepository: MemberRepository
): AysncTestApplicationTests() {
    @Test
    fun test() {
        println(redisService.getHashEntries("$REDIS_MEMBER_INFO:1"))
        println(memberRepository.findById(1))
    }
}