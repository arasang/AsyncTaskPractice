package park.sangeun.aysnctest.domain.member

import org.springframework.stereotype.Service
import park.sangeun.aysnctest.domain.member.repository.MemberRepository

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun getMemberInfo() {
        memberRepository.findById(1)
    }
}