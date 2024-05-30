package sparta.nbcamp.gamenomeprojectserver.domain.follow.repository.v1

import sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1.Follow

interface FollowRepository {
    fun findByFollowingUserId(followingUserId: Long): Follow?
    fun findAllByUserId(userId: Long): List<Follow>
    fun find(userId: Long): Follow?
    fun deleteAllById(id: Long)
    fun deleteByUserIdAndFollowingUserId(userId: Long, followingUserId: Long)
    fun delete(follow: Follow)
    fun save(follow: Follow): Follow
}