package sparta.nbcamp.gamenomeprojectserver.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Profile(
    @Column(name = "nickname", length = 255, unique = true, nullable = false)
    val nickname: String,

    @Column(name = "about_summary", length = 255, nullable = true)
    val aboutSummary: String? = null,

    @Column(name = "profile_image_url", columnDefinition = "text", nullable = true)
    val profileImageUrl: String? = null
)
