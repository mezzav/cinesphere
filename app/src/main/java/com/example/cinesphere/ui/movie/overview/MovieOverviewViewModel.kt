package com.example.cinesphere.ui.movie.overview

import androidx.lifecycle.ViewModel
import com.example.cinesphere.data.repository.NetworkTMDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieOverviewViewModel @Inject constructor(
    private val repository: NetworkTMDBRepository
) : ViewModel() {
    init {

    }
}