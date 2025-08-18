package park.sangeun.aysnctest.domain.member.model

data class RedisMember(
    var nickname: String,
    var birth: String,
    val name: String,
    val phone: String,
    val gender: String
)
