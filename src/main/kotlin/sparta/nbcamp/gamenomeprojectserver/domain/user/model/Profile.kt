package sparta.nbcamp.gamenomeprojectserver.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Profile(
    @Column(name = "nickname", unique = true, nullable = false)
    val nickname: String,

    @Column(name = "about_summary", nullable = false)
    val aboutSummanry: String = "",

    @Column(name = "profile_image_url", nullable = true)
    val profileImageUrl: String? = null
)
