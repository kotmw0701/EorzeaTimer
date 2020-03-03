package com.kotmw.eorzeatimer

class AlarmDataStore {

    companion object {

        private val alarmList = listOf(
            AlarmTempData("レガリスゼンマイ", "園芸師", "ノルヴラント", "漆黒(5.x)", 0),
            AlarmTempData("レガリスゼンマイ", "園芸師", "ノルヴラント", "漆黒(5.x)", 12),
            AlarmTempData("輝コバルト鉱", "採掘師", "ノルヴラント", "漆黒(5.x)", 2),
            AlarmTempData("輝コバルト鉱", "採掘師", "ノルヴラント", "漆黒(5.x)", 14),
            AlarmTempData("紫根", "園芸師", "ノルヴラント", "漆黒(5.x)", 4),
            AlarmTempData("紫根", "園芸師", "ノルヴラント", "漆黒(5.x)", 16),
            AlarmTempData("プルプラシェルチップ", "採掘師", "ノルヴラント", "漆黒(5.x)", 6),
            AlarmTempData("プルプラシェルチップ", "採掘師", "ノルヴラント", "漆黒(5.x)", 18),
            AlarmTempData("メルバウ原木", "園芸師", "ノルヴラント", "漆黒(5.x)", 8),
            AlarmTempData("メルバウ原木", "園芸師", "ノルヴラント", "漆黒(5.x)", 20),
            AlarmTempData("アッシュアルメン", "採掘師", "ノルヴラント", "漆黒(5.x)", 10),
            AlarmTempData("アッシュアルメン", "採掘師", "ノルヴラント", "漆黒(5.x)", 22)
        )

        fun getList(): List<AlarmTempData> {
            return alarmList
        }
    }
}