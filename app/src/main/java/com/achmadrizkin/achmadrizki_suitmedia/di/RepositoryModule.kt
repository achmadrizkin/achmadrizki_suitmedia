package com.achmadrizkin.achmadrizki_suitmedia.di

import com.achmadrizkin.achmadrizki_suitmedia.data.network.RetrofitService
import com.achmadrizkin.achmadrizki_suitmedia.data.network.network_mapper.UserResponseNetworkMapper
import com.achmadrizkin.achmadrizki_suitmedia.repo.ThirdScreenRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeMainRepo(
        retrofitService: RetrofitService,
        userResponseNetworkMapper: UserResponseNetworkMapper
    ): ThirdScreenRepo {
        return ThirdScreenRepo(retrofitService, userResponseNetworkMapper)
    }
}