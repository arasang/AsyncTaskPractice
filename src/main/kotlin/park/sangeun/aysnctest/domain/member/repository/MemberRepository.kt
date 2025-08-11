package park.sangeun.aysnctest.domain.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import park.sangeun.aysnctest.common.entity.MemberEntity

@Repository
interface MemberRepository: JpaRepository<MemberEntity, Long>, MemberRepositoryImpl {
}