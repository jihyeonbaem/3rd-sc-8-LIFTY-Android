package page.lifty.gdsclifty.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import page.lifty.gdsclifty.app.model.UserData
import javax.inject.Inject

class UserDataRepository @Inject constructor(

) {
    val userData: Flow<UserData> = flow {
        emit(UserData(isLogin = true))
    }
}