package park.sangeun.aysnctest.domain.member

data class RedisMember(
    var nickname: String,
    var birth: String,
    val name: String,
    val phone: String,
    val gender: String
)
