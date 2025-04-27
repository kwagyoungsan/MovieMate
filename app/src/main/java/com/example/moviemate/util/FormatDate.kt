package com.example.moviemate.util

import java.text.SimpleDateFormat
import java.util.Locale

fun formatDate(dateString: String): String {
    return try {
        val parser = when {
            dateString.contains("-") -> SimpleDateFormat("yyyy-MM-dd", Locale.KOREA) // 2024-04-16
            else -> SimpleDateFormat("yyyyMMdd", Locale.KOREA) // 20240416
        }
        val formatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
        val date = parser.parse(dateString)
        if (date != null) {
            formatter.format(date)
        } else {
            "날짜 정보 없음"
        }
    } catch (e: Exception) {
        "날짜 정보 없음"
    }
}
