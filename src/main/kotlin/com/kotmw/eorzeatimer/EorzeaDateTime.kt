package com.kotmw.eorzeatimer

import java.time.*
import kotlin.math.floor

class EorzeaDateTime private constructor(
    var year: Int,
    var month: Short,
    var day: Short,
    var hour: Byte,
    var minute: Byte,
    var second: Byte
) {
    fun format(formatter: String): String = formatter.format(year, month, day, hour, minute, second)

    fun convertToRealTime(): LocalDateTime {
        val utc = (((year - 1) * YEAR.toLong() +
                (month - 1) * MONTH +
                (day - 1) * DAY +
                hour * HOUR +
                minute * MINUTE +
                second) / EORZEA_MULTIPLIER).toLong() * 1000
        return Instant.ofEpochMilli(utc).atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

    fun checkPassAndIncrease() {
        val now = now()
        if (now.hour >= hour && now.minute >= minute) day++
    }

    companion object {
        const val YEAR   = 33177600
        const val MONTH  = 2764800
        const val DAY    = 86400
        const val HOUR   = 3600
        const val MINUTE = 60
        const val SECOND = 1

        const val EORZEA_MULTIPLIER = 3600.toDouble() / 175

        private fun convert(epochMilli: Long): EorzeaDateTime {
            val date = epochMilli / 1000.toDouble()
            val eorzeaTime = floor(date * EORZEA_MULTIPLIER).toLong()

            return EorzeaDateTime(
                (eorzeaTime / YEAR + 1).toInt(),
                (eorzeaTime / MONTH % 12 + 1).toShort(),
                (eorzeaTime / DAY % 32 + 1).toShort(),
                (eorzeaTime / HOUR % 24).toByte(),
                (eorzeaTime / MINUTE % 60).toByte(),
                (eorzeaTime / SECOND % 60).toByte())
        }

        fun now(): EorzeaDateTime = convert(OffsetDateTime.now().toInstant().toEpochMilli())

        fun convertLTtoET(localDateTime: LocalDateTime) = convert(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli())
    }
}
