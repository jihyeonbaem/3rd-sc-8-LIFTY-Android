package page.lifty.gdsclifty.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import page.lifty.gdsclifty.core.data.repository.DefaultUserRepository
import page.lifty.gdsclifty.core.network.model.response.UserResponse
import javax.inject.Inject

class DefaultUserUseCase @Inject constructor(
    private val defaultUserRepository: DefaultUserRepository,
) {
    fun invoke(): Flow<UserResponse> = defaultUserRepository.getUser
}