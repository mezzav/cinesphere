package com.example.cinesphere.domain

import javax.inject.Inject
import javax.inject.Named

class FormatTMDBUrlUseCase @Inject constructor(
    @Named("TMDB_IMAGE_BASE_URL") val imageUrl: String
) {
    operator fun invoke(width: Int, path: String): String {
        return imageUrl + "w${width}${path}"
    }
}
