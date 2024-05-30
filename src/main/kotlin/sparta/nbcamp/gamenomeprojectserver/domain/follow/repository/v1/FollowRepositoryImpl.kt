package sparta.nbcamp.gamenomeprojectserver.domain.follow.repository.v1

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1.Follow

@Repository
class FollowRepositoryImpl(
    val followJpaRepository: FollowJpaRepository
) : FollowRepository {
    override fun findByFollowingUserId(followingUserId: Long): Follow? {
        return followJpaRepository.findByFollowingUserId(followingUserId)
    }

    override fun findAllByUserId(userId: Long): List<Follow> {
        return followJpaRepository.findAllByUserId(userId)
    }

    override fun find(userId: Long): Follow? {
        return followJpaRepository.findByIdOrNull(userId)
    }

    override fun deleteAllById(id: Long) {
        followJpaRepository.deleteById(id)
    }

    override fun deleteByUserIdAndFollowingUserId(userId: Long, followingUserId: Long) {
        followJpaRepository.deleteByUserIdAndFollowingUserId(userId, followingUserId)
    }

    override fun delete(follow: Follow) {
        followJpaRepository.delete(follow)
    }

    override fun save(follow: Follow): Follow {
        return followJpaRepository.save(follow)
    }
}