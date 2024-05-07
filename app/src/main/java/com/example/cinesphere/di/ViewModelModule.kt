package com.example.cinesphere.di

import com.example.cinesphere.ui.movie.details.MovieDetailsViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {
    fun movieDetailsFactory(): MovieDetailsViewModel.Factory
}