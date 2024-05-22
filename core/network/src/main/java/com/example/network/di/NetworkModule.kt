package com.example.network.di

import com.example.network.BuildConfig
import com.example.network.TMDBService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Named("TMDB_BASE_URL")
    fun provideBaseUrl(): String {
        return "https://api.themoviedb.org/3/"
    }

    @Provides
    @Named("TMDB_IMAGE_BASE_URL")
    fun provideImageBaseUrl(): String {
        return "https://image.tmdb.org/t/p/"
    }

    @Provides
    @Named("TMDB_API_TOKEN")
    fun provideTMDBApiToken() : String {
        return BuildConfig.TMDB_API_TOKEN
    }

    @Provides
    fun provideAuthInterceptor(
        @Named("TMDB_API_TOKEN") token: String
    ): AuthInterceptor {
        return AuthInterceptor(token)
    }

    @Provides
    fun provideOkHttpClient(
        interceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("TMDB_BASE_URL") baseUrl: String,
        okHttpClient: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): TMDBService {
        return retrofit.create(TMDBService::class.java)
    }
}

class AuthInterceptor(private val authToken: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()

        val requestBuilder = originalRequest.newBuilder()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer $authToken")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
