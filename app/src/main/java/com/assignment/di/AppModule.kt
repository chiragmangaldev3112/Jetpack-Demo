package com.assignment.di

import android.content.Context
import com.assignment.data.datastore.AppDataStore
import com.assignment.data.network.client.AppApiClient
import com.assignment.data.network.service.AppService
import com.assignment.domain.repository.users.UsersRepository
import com.assignment.domain.repository.users.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

// help to create singleton instance of classes which required through out the app
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // used to create instance of http client
    @Provides
    fun provideHttpClient(): OkHttpClient = AppApiClient.createHttpClient()

    // used to create instance of app client
    @Provides
    @Singleton
    fun provideAppService(
        client: OkHttpClient
    ): AppService = AppApiClient.createAppService(client)

    // used to create instance of data store
    @Provides
    @Singleton
    fun provideAppDataStore(
        @ApplicationContext context: Context
    ): AppDataStore = AppDataStore(context)

    // used to create instance of user repo
    @Provides
    @Singleton
    fun provideUsersRepository(
        service: AppService,
    ): UsersRepository = UsersRepositoryImpl(service)
}