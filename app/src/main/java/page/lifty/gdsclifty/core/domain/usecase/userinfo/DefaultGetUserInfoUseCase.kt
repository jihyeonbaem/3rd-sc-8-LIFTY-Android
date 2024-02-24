package page.lifty.gdsclifty.core.domain.usecase.userinfo

import kotlinx.coroutines.flow.Flow
import page.lifty.gdsclifty.core.domain.repository.UserInfoRepository
import page.lifty.gdsclifty.core.network.model.response.UserInfoResponse
import javax.inject.Inject

class DefaultGetUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
) : GetUserInfoUseCase {
    override operator fun invoke(): Flow<UserInfoResponse> = userInfoRepository.getUserInfo
}