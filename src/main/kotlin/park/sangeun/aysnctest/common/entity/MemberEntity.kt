package park.sangeun.aysnctest.common.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "member")
@EntityListeners(AuditingEntityListener::class)
data class MemberEntity(
    @Column(length = 8)
    val mCode: String,

    @Column(length = 100)
    val email: String,

    @Column(length = 255)
    var profileImg: String?,

    @Column(length = 25)
    val name: String,

    var birth: Int,

    @Column(columnDefinition = "bit(1)")
    var isActive: Boolean = true,

    @Column(columnDefinition = "DATETIME")
    var deleteDate: LocalDateTime?


) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @CreatedDate
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    var createDate: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    var updateDate: LocalDateTime = LocalDateTime.now()
}
