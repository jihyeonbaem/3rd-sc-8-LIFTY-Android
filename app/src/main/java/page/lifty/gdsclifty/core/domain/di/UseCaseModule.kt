package page.lifty.gdsclifty.core.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import page.lifty.gdsclifty.core.domain.usecase.chat.DefaultPostChatUseCase
import page.lifty.gdsclifty.core.domain.usecase.chat.PostChatUseCase
import page.lifty.gdsclifty.core.domain.usecase.diary.DefaultGetDiaryUseCase
import page.lifty.gdsclifty.core.domain.usecase.userinfo.DefaultGetUserInfoUseCase
import page.lifty.gdsclifty.core.domain.usecase.diary.GetDiaryUseCase
import page.lifty.gdsclifty.core.domain.usecase.userinfo.GetUserInfoUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun bindGetUserinfoUseCase(default: DefaultGetUserInfoUseCase): GetUserInfoUseCase

    @Singleton
    @Binds
    abstract fun bindGetDiaryUseCase(default: DefaultGetDiaryUseCase): GetDiaryUseCase

    @Singleton
    @Binds
    abstract fun bindPostChatUseCase(default: DefaultPostChatUseCase): PostChatUseCase
}