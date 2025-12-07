package com.example.jetpackcomposeui.di

import android.content.Context
import android.content.SharedPreferences
import com.example.jetpackcomposeui.network.api.AppApi
import com.example.jetpackcomposeui.network.api.AuthApi
import com.example.jetpackcomposeui.network.repository.IAuthRepository
import com.example.jetpackcomposeui.network.repository_impl.AuthRepositoryImpl
import com.example.jetpackcomposeui.network.use_case.AuthUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


/*@Provides
@Singleton
fun provideAuthApi(retrofit: Retrofit): AuthApi =
    retrofit.create(AuthApi::class.java)


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepo(impl: AuthRepositoryImpl): IAuthRepository
}*/


@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepo(loginRepositoryImpl: AuthRepositoryImpl): IAuthRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AuthUseCaseModule{

    @Provides
    fun provideLoginApi(client: Retrofit): AuthApi {
        return client.create(AuthApi::class.java)
    }

    @Provides
    fun provideLoginUseCase(loginRepositoryImpl: AuthRepositoryImpl): AuthUseCase {
        return AuthUseCase(loginRepositoryImpl)
    }
}
