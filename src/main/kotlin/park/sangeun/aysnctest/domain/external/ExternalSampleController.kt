package park.sangeun.aysnctest.domain.external

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import park.sangeun.aysnctest.domain.external.model.ExternalMember

@RestController
@RequestMapping("/external/sample")
class ExternalSampleController {
    @PostMapping("/member")
    fun getMemberInfo(): ExternalMember {
        Thread.sleep(1500)
        return ExternalMember()
    }
}