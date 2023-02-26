package asmaa.khb.usersproject.di

import android.app.Application
import androidx.room.Room
import asmaa.khb.usersproject.api.BASE_URL
import asmaa.khb.usersproject.api.UserApi
import asmaa.khb.usersproject.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideDataBases(app: Application): UserDatabase =
        Room.databaseBuilder(app, UserDatabase::class.java, "users_database").build()
}