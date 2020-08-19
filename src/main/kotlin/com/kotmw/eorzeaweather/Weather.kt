package com.kotmw.eorzeaweather

import com.kotmw.eorzeatimer.EorzeaDateTime
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlin.math.floor

class Weather {

    init {
        val vBox = VBox()
        vBox.padding = Insets(10.0)
        println(System.currentTimeMillis())
        val startTime = getWeatherTime(System.currentTimeMillis())
        val date = EorzeaDateTime.now()
        println(date.format("%04d / %02d / %02d %02d:%02d:%02d"))
        val header = HBox(
            Label().apply {
                prefWidth = 170.0
            },
            Label(getEorzeaHour(startTime).toString()).apply {
                prefHeight = 30.0
                prefWidth = 30.0
                alignment = Pos.CENTER
            })
        for (i in 1..10) {
            header.children.addAll(
                Label("➡").apply {
                    prefHeight = 30.0
                    prefWidth = 20.0
                    alignment = Pos.CENTER
                },
                Label(getEorzeaHour(startTime + 8 * i * 175 * 1000).toString()).apply {
                    prefHeight = 30.0
                    prefWidth = 30.0
                    alignment = Pos.CENTER
                })
        }
        vBox.children.add(header)
        for (zone in Zones.values()) {
            val hBox = HBox(
                Label(zone.ja).apply {
                    prefWidth = 170.0
                },
                ImageView(zone.weather(calculateForecastTarget(startTime)).path + ".png").apply {
                    fitHeight = 30.0
                    fitWidth = 30.0
                })
            for (i in 1..10) {
                val weather = zone.weather(calculateForecastTarget(startTime + 8 * i * 175 * 1000))
                hBox.children.addAll(
                    Label("➡").apply {
                        prefHeight = 30.0
                        prefWidth = 20.0
                        alignment = Pos.CENTER
                    },
                    ImageView(weather.path + ".png").apply {
                        fitHeight = 30.0
                        fitWidth = 30.0
                    })
            }
            vBox.children.add(hBox)
        }
        val scene = Scene(ScrollPane(vBox))
        val stage = Stage()
        stage.scene = scene
        stage.show()
    }

    private fun calculateForecastTarget(milliSec: Long): Int {
        val unixSeconds = (milliSec / 1000).toInt()
        val bell = unixSeconds / 175
        val increment = (bell + 8 - (bell % 8)) % 24
        var totalDays = unixSeconds / 4200
        totalDays = (totalDays shl 32) ushr 0
        val calcBase = totalDays * 100 + increment
        val step1 = ((calcBase shl 11) xor calcBase) ushr 0
        val step2 = ((step1 ushr 8) xor step1) ushr 0
        return step2 % 100
    }

    private fun getWeatherTime(milliSec: Long): Long {
        val unixSeconds = (milliSec / 1000)
        val bell = (unixSeconds / 175) % 24
        val startBell = bell - (bell % 8)
        val startUnixSeconds = unixSeconds - (175 * (bell - startBell))
        return (startUnixSeconds * 1000)
    }

    private fun getEorzeaHour(milliSec: Long): Double {
        val unixSeconds = (milliSec / 1000).toInt()
        val bell = (unixSeconds / 175) % 24
        return floor(bell.toDouble())
    }
}

enum class WeatherType(val path: String) {
    BLIZZARDS("Blizzards"),
    CLEAR_SKIES("Clear_Skies"),
    CLOUDS("Clouds"),
    DUST_STORMS("Dust_Storms"),
    FAIR_SKIES("Fair_Skies"),
    FOG("Fog"),
    GALES("Gales"),
    GLOOM("Gloom"),
    HEAT_WAVES("Heat_Waves"),
    RAIN("Rain"),
    SHOWERS("Showers"),
    SNOW("Snow"),
    THUNDER("Thunder"),
    THUNDERSTORMS("ThunderStorms"),
    UMBRAL_STATIC("Umbral_Static"),
    UMBRAL_WIND("Umbral_Wind"),
    WIND("Wind")
}
