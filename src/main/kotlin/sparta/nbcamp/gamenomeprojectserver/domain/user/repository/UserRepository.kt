package sparta.nbcamp.gamenomeprojectserver.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

interface UserRepository : JpaRepository<User, Long>