package page.lifty.gdsclifty.core.domain.usecase.userinfo

import kotlinx.coroutines.flow.Flow
import page.lifty.gdsclifty.core.network.model.response.UserInfoResponse

interface GetUserInfoUseCase {
    operator fun invoke(): Flow<UserInfoResponse>
}