package page.lifty.gdsclifty.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import page.lifty.gdsclifty.core.network.datasource.ChatRemoteDataSource
import page.lifty.gdsclifty.core.network.datasource.DefaultChatRemoteDataSource
import page.lifty.gdsclifty.core.network.datasource.DefaultDiaryRemoteDataSource
import page.lifty.gdsclifty.core.network.datasource.DefaultUserInfoRemoteDataSource
import page.lifty.gdsclifty.core.network.datasource.DiaryRemoteDataSource
import page.lifty.gdsclifty.core.network.datasource.UserRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindUserInfoRemoteDataSource(default: DefaultUserInfoRemoteDataSource): UserRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindDiaryRemoteDataSource(default: DefaultDiaryRemoteDataSource): DiaryRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindChatRemoteDataSource(default: DefaultChatRemoteDataSource): ChatRemoteDataSource
}