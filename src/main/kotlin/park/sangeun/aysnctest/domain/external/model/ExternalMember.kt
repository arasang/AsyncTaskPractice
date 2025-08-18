package park.sangeun.aysnctest.domain.external.model

data class ExternalMember(
    val memberId: Long = 99999,
    val name: String = "External Member Name",
    val age: Int = 43,
    val sex: String = "MEN",
    val email: String = "test@test.com"
)
