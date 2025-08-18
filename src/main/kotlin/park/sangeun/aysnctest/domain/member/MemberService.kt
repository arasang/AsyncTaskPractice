package park.sangeun.aysnctest.domain.member

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import park.sangeun.aysnctest.common.component.RedisService
import park.sangeun.aysnctest.domain.member.model.Member
import park.sangeun.aysnctest.domain.member.model.RedisMember
import park.sangeun.aysnctest.domain.member.repository.MemberRepository
import park.sangeun.aysnctest.domain.common.GlobalConstant.Companion.REDIS_MEMBER_INFO

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val redisService: RedisService,
    private val objectMapper: ObjectMapper
) {

    fun getMemberInfo() {
        // 1. 레디스 조회
        getMemberInfoFromRedis()
        // 2. 회원 테이블 조회
        // 3. 외부사 api호출

    }

    fun getMemberInfoFromRedis() {
        val keys = redisService.getKeys("$REDIS_MEMBER_INFO:*")
        val keyList = keys.toList()
        val chunkSize = 500

        for (i in keyList.indices step chunkSize) {
            val end = minOf(i + chunkSize, keyList.size)
            val batch = keyList.subList(i, end)
            batch.forEach {
                val redisMember = redisService.getHashEntries(it)
                val convertMember = objectMapper.convertValue(redisMember, RedisMember::class.java)
                val member = Member(convertMember)

                println("Amber test : $member")
            }
        }


    }
}