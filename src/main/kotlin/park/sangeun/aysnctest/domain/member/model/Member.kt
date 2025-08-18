package park.sangeun.aysnctest.domain.member.model

data class Member (
    val name: String,
    val birth: String,
    val phone: String,
    val gender: String,
    val from: MemberFromEnum,
    val email: String,
) {
    constructor(redis: RedisMember) : this(
        redis.name,
        redis.birth,
        redis.phone,
        redis.gender,
        MemberFromEnum.REDIS,
        ""
    )
}