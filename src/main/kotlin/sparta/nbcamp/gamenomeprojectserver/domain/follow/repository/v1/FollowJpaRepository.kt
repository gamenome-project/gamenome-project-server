package sparta.nbcamp.gamenomeprojectserver.domain.follow.repository.v1

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1.Follow


interface FollowJpaRepository: JpaRepository<Follow, Long> {
    fun findByFollowingUserId(followingUserId: Long): Follow?
    fun findAllByUserId(userId: Long): List<Follow>
    fun deleteByUserIdAndFollowingUserId(userId: Long, followingUserId: Long)
}