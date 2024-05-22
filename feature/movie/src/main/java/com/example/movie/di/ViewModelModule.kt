package com.example.movie.di

import com.example.movie.details.MovieDetailsViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {
    fun movieDetailsFactory(): com.example.movie.details.MovieDetailsViewModel.Factory
}