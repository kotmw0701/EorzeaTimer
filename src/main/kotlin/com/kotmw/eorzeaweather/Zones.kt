package com.kotmw.eorzeaweather

enum class Zones(val str: String, val ja: String) {
    AMH_ARAENG("Amh Araeng", "アム・アレーン") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 45) WeatherType.FAIR_SKIES else if (chance < 60) WeatherType.CLOUDS else if (chance < 70) WeatherType.DUST_STORMS else if (chance < 80) WeatherType.HEAT_WAVES else WeatherType.CLEAR_SKIES
        }
    },
    AZYS_LLA("Azys Lla", "アジス・ラー") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 35) WeatherType.FAIR_SKIES else if (chance < 70) WeatherType.CLOUDS else WeatherType.THUNDER
        }
    },
    CENTRAL_SHROUD("Central Shuroud", "黒衣森:中央森林") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.THUNDER else if (chance < 20) WeatherType.RAIN else if (chance < 30) WeatherType.FOG else if (chance < 40) WeatherType.CLOUDS else if (chance < 55) WeatherType.FAIR_SKIES else if (chance < 85) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    CENTRAL_THANALAN("Central Thanalan", "中央ザナラーン") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 15) WeatherType.DUST_STORMS else if (chance < 55) WeatherType.CLEAR_SKIES else if (chance < 75) WeatherType.FAIR_SKIES else if (chance < 85) WeatherType.CLOUDS else if (chance < 95) WeatherType.FOG else WeatherType.RAIN
        }
    },
    COERTHAS_CENTRAL_HIGHLANDS("Coerthas Central HighLands", "クルザス中央高地") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.BLIZZARDS else if (chance < 60) WeatherType.SNOW else if (chance < 70) WeatherType.FAIR_SKIES else if (chance < 75) WeatherType.CLEAR_SKIES else if (chance < 90) WeatherType.CLOUDS else WeatherType.FOG
        }
    },
    COERTHAS_WESTERN_HIGHLANDS("Coerthas Western HighLands", "クルザス西部高地") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.BLIZZARDS else if (chance < 60) WeatherType.SNOW else if (chance < 70) WeatherType.FAIR_SKIES else if (chance < 75) WeatherType.CLEAR_SKIES else if (chance < 90) WeatherType.CLOUDS else WeatherType.FOG
        }
    },
    EAST_SHROUD("East Shroud", "黒衣森:東部森林") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.THUNDER else if (chance < 20) WeatherType.RAIN else if (chance < 30) WeatherType.FOG else if (chance < 40) WeatherType.CLOUDS else if (chance < 55) WeatherType.FAIR_SKIES else if (chance < 85) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    EASTERN_LA_NOSCEA("Eastern La Noscea", "東ラノシア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.FOG else if (chance < 50) WeatherType.CLEAR_SKIES else if (chance < 80) WeatherType.FAIR_SKIES else if (chance < 90) WeatherType.CLOUDS else if (chance < 95) WeatherType.RAIN else WeatherType.SHOWERS
        }
    },
    EASTERN_THANALAN("Eastern Thanalan", "東ザナラーン") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 40) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 70) WeatherType.CLOUDS else if (chance < 80) WeatherType.FOG else if (chance < 85) WeatherType.RAIN else WeatherType.SHOWERS
        }
    },
    EULMORE("Eulmore", "ユールモア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.GALES else if (chance < 20) WeatherType.RAIN else if (chance < 30) WeatherType.FOG else if (chance < 45) WeatherType.CLOUDS else if (chance < 85) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    },
    EUREKA_ANEMOS("Eureka Anemos","エウレカ:アネモス帯") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 30) WeatherType.FAIR_SKIES else if (chance < 60) WeatherType.GALES else if (chance < 90) WeatherType.SHOWERS else WeatherType.SNOW
        }
    },
    EUREKA_HYDATOS("Eureka Hydatos","エウレカ:ヒュダトス") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 12) WeatherType.FAIR_SKIES else if (chance < 34) WeatherType.SHOWERS else if (chance < 56) WeatherType.GLOOM else if (chance < 78) WeatherType.THUNDERSTORMS else WeatherType.SNOW
        }
    },
    EUREKA_PAGOS("Eureka Pagos","エウレカ:パゴス") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.FAIR_SKIES else if (chance < 28) WeatherType.FOG else if (chance < 46) WeatherType.HEAT_WAVES else if (chance < 64) WeatherType.SNOW else if (chance < 82) WeatherType.THUNDER else WeatherType.BLIZZARDS
        }
    },
    EUREKA_PYROS("Eureka Pyros","エウレカ:ピューロス") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.FAIR_SKIES else if (chance < 28) WeatherType.HEAT_WAVES else if (chance < 46) WeatherType.THUNDER else if (chance < 64) WeatherType.BLIZZARDS else if (chance < 82) WeatherType.UMBRAL_WIND else WeatherType.SNOW
        }
    },
    GRIDANIA("Gridania", "グリダニア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.RAIN else if (chance < 20) WeatherType.RAIN else if (chance < 30) WeatherType.FOG else if (chance < 40) WeatherType.CLOUDS else if (chance < 55) WeatherType.FAIR_SKIES else if (chance < 85) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    IDYLLSHIRE("Idyllshire", "イディルシャイア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.CLOUDS else if (chance < 20) WeatherType.FOG else if (chance < 30) WeatherType.RAIN else if (chance < 40) WeatherType.SHOWERS else if (chance < 70) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    IL_MHEG("IlMheg", "イル・メグ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.RAIN else if (chance < 20) WeatherType.FOG else if (chance < 35) WeatherType.CLOUDS else if (chance < 45) WeatherType.THUNDERSTORMS else if (chance < 60) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    ISHGARD("Ishgard", "イシュガルド") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 60) WeatherType.SNOW else if (chance < 70) WeatherType.FAIR_SKIES else if (chance < 75) WeatherType.CLEAR_SKIES else if (chance < 90) WeatherType.CLOUDS else WeatherType.FOG
        }
    },
    KHOLUSIA("Kholusia", "コルシア島") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.GALES else if (chance < 20) WeatherType.RAIN else if (chance < 30) WeatherType.FOG else if (chance < 45) WeatherType.CLOUDS else if (chance < 85) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    },
    KUGANE("Kugane", "クガネ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.RAIN else if (chance < 20) WeatherType.FOG else if (chance < 40) WeatherType.CLOUDS else if (chance < 80) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    },
    LAKELAND("Lakeland", "レイクランド") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 75) WeatherType.CLOUDS else if (chance < 85) WeatherType.FOG else if (chance < 95) WeatherType.RAIN else WeatherType.THUNDERSTORMS
        }
    },
    LIMSA_LOMINSA("Limsa Lominsa", "リムサ・ロミンサ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.CLOUDS else if (chance < 50) WeatherType.CLEAR_SKIES else if (chance < 80) WeatherType.FAIR_SKIES else if (chance < 90) WeatherType.FOG else WeatherType.RAIN
        }
    },
    LOWER_LA_NOSCEA("Lower La Noscea", "低地ラノシア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.CLOUDS else if (chance < 50) WeatherType.CLEAR_SKIES else if (chance < 70) WeatherType.FAIR_SKIES else if (chance < 80) WeatherType.WIND else if (chance < 90) WeatherType.FOG else WeatherType.RAIN
        }
    },
    MIDDLE_LA_NOSCEA("Middle La Noscea", "中央ラノシア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.CLOUDS else if (chance < 50) WeatherType.CLEAR_SKIES else if (chance < 70) WeatherType.FAIR_SKIES else if (chance < 80) WeatherType.WIND else if (chance < 90) WeatherType.FOG else WeatherType.RAIN
        }
    },
    MIST("Mist", "ミスト・ヴィレッジ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.CLOUDS else if (chance < 50) WeatherType.CLEAR_SKIES else if (chance < 70) WeatherType.FAIR_SKIES else if (chance < 80) WeatherType.FAIR_SKIES else if (chance < 90) WeatherType.FOG else WeatherType.RAIN
        }
    },
    MOR_DHONA("Mor Dhona", "モードゥナ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 15) WeatherType.CLOUDS else if (chance < 30) WeatherType.FOG else if (chance < 60) WeatherType.GLOOM else if (chance < 75) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    NORTH_SHROUD("North Shroud", "黒衣森:北部森林") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.FOG else if (chance < 10) WeatherType.SHOWERS else if (chance < 25) WeatherType.RAIN else if (chance < 30) WeatherType.FOG else if (chance < 40) WeatherType.CLOUDS else if (chance < 70) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    },
    NORTHERN_THANALAN("Northern Thanalan", "北ザナラーン") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.CLEAR_SKIES else if (chance < 20) WeatherType.FAIR_SKIES else if (chance < 50) WeatherType.CLOUDS else WeatherType.FOG
        }
    },
    OUTER_LA_NOSCEA("Outer La Noscea", "外地ラノシア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 30) WeatherType.CLEAR_SKIES else if (chance < 50) WeatherType.FAIR_SKIES else if (chance < 70) WeatherType.CLOUDS else if (chance < 85) WeatherType.FOG else WeatherType.RAIN
        }
    },
    RHALGRS_REACH("Rhalgr's Reach", "ラールガーズリーチ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 15) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 80) WeatherType.CLOUDS else if (chance < 90) WeatherType.FOG else WeatherType.THUNDER
        }
    },
    SHIROGANE("Shirogane", "シロガネ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.RAIN else if (chance < 20) WeatherType.FOG else if (chance < 40) WeatherType.CLOUDS else if (chance < 80) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    },
    SOUTH_SHROUD("South Shroud", "黒衣森:南部森林" ) {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.FOG else if (chance < 10) WeatherType.THUNDERSTORMS else if (chance < 25) WeatherType.THUNDER else if (chance < 30) WeatherType.FOG else if (chance < 40) WeatherType.CLOUDS else if (chance < 70) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    },
    SOUTHERN_THANALAN("Southern Thanalan", "南ザナラーン") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.HEAT_WAVES else if (chance < 60) WeatherType.CLEAR_SKIES else if (chance < 80) WeatherType.FAIR_SKIES else if (chance < 90) WeatherType.CLOUDS else WeatherType.FOG
        }
    },
    THE_AZIM_STEPPE("The Azim Steppe", "アジムステップ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.GALES else if (chance < 10) WeatherType.WIND else if (chance < 17) WeatherType.RAIN else if (chance < 25) WeatherType.FOG else if (chance < 35) WeatherType.CLOUDS else if (chance < 75) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    },
    THE_CHURNING_MISTS("The Churning Mists", "ドラヴァニア雲海") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.CLOUDS else if (chance < 20) WeatherType.GALES else if (chance < 40) WeatherType.UMBRAL_STATIC else if (chance < 70) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    THE_CRYSTARIUM("The Crystarium", "クリスタリウム") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 75) WeatherType.CLOUDS else if (chance < 85) WeatherType.FOG else if (chance < 95) WeatherType.RAIN else WeatherType.THUNDERSTORMS
        }
    },
    THE_DIADEM("The Diadem", "ディアデム諸島") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 30) WeatherType.FAIR_SKIES else if (chance < 60) WeatherType.FOG else if (chance < 90) WeatherType.WIND else WeatherType.UMBRAL_WIND
        }
    },
    THE_DRAVANIAN_FORELANDS("The Dravanian Forelands", "高地ドラヴァニア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.CLOUDS else if (chance < 20) WeatherType.FOG else if (chance < 30) WeatherType.THUNDER else if (chance < 40) WeatherType.DUST_STORMS else if (chance < 70) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    THE_DRAVANIAN_HINTERLANDS("The Dravanian Hinterlands", "低地ドラヴァニア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.CLOUDS else if (chance < 20) WeatherType.FOG else if (chance < 30) WeatherType.RAIN else if (chance < 40) WeatherType.SHOWERS else if (chance < 70) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    THE_FRINGES("The Fringes", "ギラバニア辺境地帯") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 15) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 80) WeatherType.CLOUDS else if (chance < 90) WeatherType.FOG else WeatherType.THUNDER
        }
    },
    THE_GOBLET("The Goblet", "ゴブレットビュート") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 40) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 85) WeatherType.CLOUDS else if (chance < 95) WeatherType.FOG else WeatherType.RAIN
        }
    },
    THE_LAVENDER_BEDS("The Lavender Beds", "ラベンダーベッド") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.CLOUDS else if (chance < 20) WeatherType.RAIN else if (chance < 30) WeatherType.FOG else if (chance < 40) WeatherType.CLOUDS else if (chance < 55) WeatherType.FAIR_SKIES else if (chance < 85) WeatherType.CLEAR_SKIES else WeatherType.FAIR_SKIES
        }
    },
    THE_LOCHS("The Lochs", "ギラバニア湖畔地帯") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 80) WeatherType.CLOUDS else if (chance < 90) WeatherType.FOG else WeatherType.THUNDERSTORMS
        }
    },
    THE_PEAKS("The Peaks", "ギラバニア山岳地帯") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 75) WeatherType.CLOUDS else if (chance < 85) WeatherType.FOG else if (chance < 95) WeatherType.WIND else WeatherType.DUST_STORMS
        }
    },
    THE_RAKTIKA_GREATWOOD("The Raktika Greatwood", "ラケティカ大森林") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.FOG else if (chance < 20) WeatherType.RAIN else if (chance < 30) WeatherType.UMBRAL_WIND else if (chance < 45) WeatherType.CLEAR_SKIES else if (chance < 85) WeatherType.FAIR_SKIES else WeatherType.CLOUDS
        }
    },
    THE_RUBY_SEA("The Ruby Sea", "紅玉海") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.THUNDER else if (chance < 20) WeatherType.WIND else if (chance < 35) WeatherType.CLOUDS else if (chance < 75) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    },
    THE_SEA_OF_CLOUDS("The Sea Of Clouds", "アバラシア雲海") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 30) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 70) WeatherType.CLOUDS else if (chance < 80) WeatherType.FOG else if (chance < 90) WeatherType.WIND else WeatherType.UMBRAL_WIND
        }
    },
    THE_TEMPEST("The Tempest", "テンペスト") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.CLOUDS else if (chance < 80) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    },
    ULDAH("Ul'dah", "ウルダハ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 40) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 85) WeatherType.CLOUDS else if (chance < 95) WeatherType.FOG else WeatherType.RAIN
        }
    },
    UPPER_LA_NOSCEA("Upper La Noscea", "高地ラノシア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 30) WeatherType.CLEAR_SKIES else if (chance < 50) WeatherType.FAIR_SKIES else if (chance < 70) WeatherType.CLOUDS else if (chance < 80) WeatherType.FOG else if (chance < 90) WeatherType.THUNDER else WeatherType.THUNDERSTORMS
        }
    },
    WESTERN_LA_NOSCEA("Western La Noscea", "西ラノシア") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 10) WeatherType.FOG else if (chance < 40) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 80) WeatherType.CLOUDS else if (chance < 90) WeatherType.WIND else WeatherType.GALES
        }
    },
    WESTERN_THANALAN("Western Thanalan", "西ザナラーン") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 40) WeatherType.CLEAR_SKIES else if (chance < 60) WeatherType.FAIR_SKIES else if (chance < 85) WeatherType.CLOUDS else if (chance < 95) WeatherType.FOG else WeatherType.RAIN
        }
    },
    WOLVES_DEN_PIER("Wolves Den Pier", "ウルヴズジェイル係船場") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 20) WeatherType.CLOUDS else if (chance < 50) WeatherType.CLEAR_SKIES else if (chance < 80) WeatherType.FAIR_SKIES else if (chance < 90) WeatherType.FOG else WeatherType.THUNDERSTORMS
        }
    },
    YANXIA("Yanxia", "ヤンサ") {
        override fun weather(chance: Int): WeatherType {
            return if (chance < 5) WeatherType.SHOWERS else if (chance < 15) WeatherType.RAIN else if (chance < 25) WeatherType.FOG else if (chance < 40) WeatherType.CLOUDS else if (chance < 80) WeatherType.FAIR_SKIES else WeatherType.CLEAR_SKIES
        }
    };


    abstract fun weather(chance: Int): WeatherType

}
