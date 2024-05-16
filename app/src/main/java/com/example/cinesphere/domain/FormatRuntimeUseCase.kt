package com.example.cinesphere.domain

import javax.inject.Inject

class FormatRuntimeUseCase @Inject constructor() {
    operator fun invoke(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime % 60

        return "${hours}h${minutes}m"
    }
}
