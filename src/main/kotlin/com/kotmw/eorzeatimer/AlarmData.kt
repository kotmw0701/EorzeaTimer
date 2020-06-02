package com.kotmw.eorzeatimer

import java.time.LocalDateTime

data class AlarmData(
    val title: String,
    val targetType: String,
    val agoMinute: Int,
    val eorzeaDateTime: EorzeaDateTime,
    var localDateTime: LocalDateTime
)

data class AlarmTempData(
    val title: String,
    val category: String,
    val gatherer: String,
    val area: String,
    val patch: String,
    val hour: Int
)


