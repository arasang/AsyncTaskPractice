package park.sangeun.aysnctest.domain.member.repository

import org.springframework.stereotype.Service
import park.sangeun.aysnctest.common.component.RedisService
import park.sangeun.aysnctest.domain.common.GlobalConstant.Companion.REDIS_MEMBER_INFO

@Service
class RedisMemberRepository(
    private val redisService: RedisService,
) {
    fun getProfile(memberId: Long) {
        val entries = redisService.getHashEntries(REDIS_MEMBER_INFO)
    }
}