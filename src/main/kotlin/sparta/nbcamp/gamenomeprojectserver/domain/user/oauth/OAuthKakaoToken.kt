package sparta.nbcamp.gamenomeprojectserver.domain.user.oauth

data class OAuthToken(
    val accessToken: String?,
    val tokenType: String?,
    val refreshToken: String?,
    val scope: String?,
    val expiresIn: Int,
    val refreshTokenExpiresIn: Int,
)
