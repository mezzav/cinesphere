package com.example.cinesphere.domain

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FormatDateUseCase @Inject constructor() {
    operator fun invoke(
        date: String,
        inputFormat: String,
        outputFormat: String,
    ): String {
        val inputFormatter = DateTimeFormatter.ofPattern(inputFormat)
        val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)

        return LocalDate.parse(date, inputFormatter).format(outputFormatter)

    }
}
