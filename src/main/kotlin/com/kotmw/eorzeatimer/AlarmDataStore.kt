package com.kotmw.eorzeatimer

import java.time.LocalTime

class AlarmDataStore {

    companion object {

        private val alarmList = listOf(
            AlarmTempData("レガリスゼンマイ", "伝説", "園芸師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(0, 0)),
            AlarmTempData("レガリスゼンマイ", "伝説", "園芸師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(12, 0)),
            AlarmTempData("輝コバルト鉱", "伝説", "採掘師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(2, 0)),
            AlarmTempData("輝コバルト鉱", "伝説", "採掘師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(14, 0)),
            AlarmTempData("紫根", "伝説", "園芸師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(4, 0)),
            AlarmTempData("紫根", "伝説", "園芸師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(16, 0)),
            AlarmTempData("プルプラシェルチップ", "伝説", "採掘師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(6, 0)),
            AlarmTempData("プルプラシェルチップ", "伝説", "採掘師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(18, 0)),
            AlarmTempData("メルバウ原木", "伝説", "園芸師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(8, 0)),
            AlarmTempData("メルバウ原木", "伝説", "園芸師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(20, 0)),
            AlarmTempData("アッシュアルメン", "伝説", "採掘師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(10, 0)),
            AlarmTempData("アッシュアルメン", "伝説", "採掘師", "ノルヴラント", "漆黒(5.x)", LocalTime.of(22, 0))
        )

        fun getList(): List<AlarmTempData> {
            return alarmList
        }
    }
}
