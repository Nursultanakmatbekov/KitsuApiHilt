package com.example.data.network.remote.di

import android.content.Context
import com.example.data.local.prefs.TokenPreferenceHelper
import com.example.data.network.remote.apiservices.*
import com.example.data.network.repostories.*
import com.example.domain.repostories.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideTokenPreferenceHelper(context: Context): TokenPreferenceHelper {
        return TokenPreferenceHelper(context)
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(tokenPreferenceHelper: TokenPreferenceHelper): TokenInterceptor {
        return TokenInterceptor(tokenPreferenceHelper)
    }

    @Provides
    @Singleton
    fun provideRetrofit1(): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val clientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
        }

        return Retrofit.Builder()
            .baseUrl("https://kitsu.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
    }

    @Retrofit2
    @Provides
    @Singleton
    fun provideAuthenticatedRetrofit(tokenInterceptor: TokenInterceptor): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val clientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(tokenInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl("https://kitsu.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
    }

    @Provides
    @Singleton
    fun providePostRepository(apiService: PostApiService): PostRepository {
        return PostRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePostApiService(@Retrofit2 retrofit: Retrofit): PostApiService =
        retrofit.create(PostApiService::class.java)

    @Provides
    @Singleton
    fun provideAnimeApiService(retrofit: Retrofit): AnimeApiService =
        retrofit.create(AnimeApiService::class.java)

    @Provides
    @Singleton
    fun provideMangaApiService(retrofit: Retrofit): MangaApiService =
        retrofit.create(MangaApiService::class.java)

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideSignInApiService(retrofit: Retrofit): SignInApiService =
        retrofit.create(SignInApiService::class.java)

    @Provides
    @Singleton
    fun provideCategoryApiService(retrofit: Retrofit): CategoriesApiService =
        retrofit.create(CategoriesApiService::class.java)

    @Provides
    @Singleton
    fun provideAnimeRepository(apiService: AnimeApiService): AnimeRepository {
        return AnimeRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMangaRepository(apiService: MangaApiService): MangaRepository {
        return MangaRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(apiService: CategoriesApiService): CategoriesRepository {
        return CategoriesRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideSignInRepository(apiService: SignInApiService): SingInRepository {
        return SingInRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: UserApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }
}
