package page.lifty.gdsclifty.core.domain.usecase

import page.lifty.gdsclifty.core.data.repository.DefaultTokenRepository
import javax.inject.Inject

class DefaultGetAccessTokenUseCase @Inject constructor(
    private val defaultTokenRepository: DefaultTokenRepository,
) {
    operator fun invoke() = defaultTokenRepository.getAccessToken
}

class DefaultSaveAccessTokenUseCase @Inject constructor(
    private val defaultTokenRepository: DefaultTokenRepository,
) {
    suspend operator fun invoke(accessToken: String) =
        defaultTokenRepository.saveAccessToken(accessToken)
}

class DefaultGetRefreshTokenUseCase @Inject constructor(
    private val defaultTokenRepository: DefaultTokenRepository,
) {
    operator fun invoke() = defaultTokenRepository.getRefreshToken
}

class DefaultSaveRefreshTokenUseCase @Inject constructor(
    private val defaultTokenRepository: DefaultTokenRepository,
) {
    suspend operator fun invoke(refreshToken: String) =
        defaultTokenRepository.saveRefreshToken(refreshToken)
}