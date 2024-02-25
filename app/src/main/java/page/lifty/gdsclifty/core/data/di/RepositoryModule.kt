package page.lifty.gdsclifty.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import page.lifty.gdsclifty.core.data.repository.DefaultChatRepository
import page.lifty.gdsclifty.core.data.repository.DefaultDiaryRepository
import page.lifty.gdsclifty.core.data.repository.DefaultUserInfoRepository
import page.lifty.gdsclifty.core.domain.repository.ChatRepository
import page.lifty.gdsclifty.core.domain.repository.DiaryRepository
import page.lifty.gdsclifty.core.domain.repository.UserInfoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindUserInfoRepository(default: DefaultUserInfoRepository): UserInfoRepository

    @Singleton
    @Binds
    abstract fun bindDiaryRepository(default: DefaultDiaryRepository): DiaryRepository

    @Singleton
    @Binds
    abstract fun bindChatRepository(default: DefaultChatRepository): ChatRepository
}