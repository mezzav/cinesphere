package com.example.tv.di

import com.example.tv.ui.details.TVShowDetailsViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {
    fun tvShowDetailsFactory(): TVShowDetailsViewModel.Factory
}