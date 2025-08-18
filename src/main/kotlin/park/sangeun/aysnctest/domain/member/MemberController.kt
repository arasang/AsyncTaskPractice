package park.sangeun.aysnctest.domain.member

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController (
    private val memberService: MemberService
) {
    @GetMapping
    fun getMemberInfo() {
        return memberService.getMemberInfo()
    }
}