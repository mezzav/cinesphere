package com.example.cinesphere.di

import com.example.cinesphere.BuildConfig
import com.example.cinesphere.data.remote.service.TMDBService
import com.example.cinesphere.data.repository.paging.NowPlayingMoviesPagingSource
import com.example.cinesphere.data.repository.paging.PopularMoviesPagingSource
import com.example.cinesphere.data.repository.paging.UpcomingMoviesPagingSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
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
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): TMDBService {
        return retrofit.create(TMDBService::class.java)
    }

    @Singleton
    @Provides
    fun provideUpcomingMoviesPagingSource(api: TMDBService): UpcomingMoviesPagingSource {
        return UpcomingMoviesPagingSource(api)
    }

    @Singleton
    @Provides
    fun providePopularMoviesPagingSource(api: TMDBService): PopularMoviesPagingSource {
        return PopularMoviesPagingSource(api)
    }

    @Singleton
    @Provides
    fun provideNowPlayingMoviesPagingSource(api: TMDBService): NowPlayingMoviesPagingSource {
        return NowPlayingMoviesPagingSource(api)
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
