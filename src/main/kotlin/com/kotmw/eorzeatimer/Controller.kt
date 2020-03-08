package com.kotmw.eorzeatimer

import javafx.animation.*
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.text.TextAlignment
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.util.Duration
import java.net.URL
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.ResolverStyle
import java.util.*
import kotlin.math.floor
import kotlin.math.round

class Controller : Initializable{

    @FXML lateinit var realTime: Label
    @FXML lateinit var eorzeaTime: Label
    @FXML lateinit var timeType: ToggleGroup
    @FXML lateinit var alarmTitle: TextField
    @FXML lateinit var hourInput: Spinner<Int>
    @FXML lateinit var minuteInput: Spinner<Int>
    @FXML lateinit var agoMinute: Spinner<Int>
    @FXML lateinit var alarmContainer: VBox
    @FXML lateinit var root: AnchorPane
    @FXML lateinit var classSelect: ChoiceBox<String>
    @FXML lateinit var areaSelect: ChoiceBox<String>
    @FXML lateinit var patchSelect: ChoiceBox<String>
    @FXML lateinit var timerTempList: VBox

    private lateinit var vBox: VBox

    private val alarmList = mutableListOf<AlarmData>()

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy / MM / dd HH:mm:ss")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        classSelect.items.addAll("全て", "採掘師", "園芸師")
        classSelect.value = classSelect.items[0]
        areaSelect.items.addAll("全て", "ラノシア", "ザナラーン", "クルザス", "モードゥナ", "ドラヴァニア", "アバラシア", "ギラバニア", "オサード", "ノルヴラント")
        areaSelect.value = areaSelect.items[0]
        patchSelect.items.addAll("全て", "新生(2.x)", "蒼天(3.x)", "紅蓮(4.x)", "漆黒(5.x)")
        patchSelect.value = areaSelect.items[0]
        AlarmDataStore.getList().forEach {
            timerTempList.children.add(Button().apply {
                prefWidth = 264.0
                graphic = HBox(Label(it.title).apply { prefWidth = 170.0 }, Label(it.gatherer), Label("  ${"%02d".format(it.hour)}:00"))
            })
        }
        hourInput.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23)
        hourInput.focusedProperty().addListener { _, _, newValue ->
            if (!newValue && hourInput.value > 23) hourInput.valueFactory.value = 23
        }
        minuteInput.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59)
        minuteInput.focusedProperty().addListener { _, _, newValue ->
            if (!newValue && minuteInput.value > 59) minuteInput.valueFactory.value = 59
        }
        agoMinute.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60)
        agoMinute.focusedProperty().addListener { _, _, newValue ->
            if (!newValue && agoMinute.value > 60) agoMinute.valueFactory.value = 60
        }

        val screen = Screen.getPrimary().bounds
        vBox = VBox().apply {
            alignment = Pos.CENTER
            style = "-fx-background-color: transparent"
            spacing = 5.0
            setPrefSize(screen.width, screen.height)
        }
        val primary = Stage().apply {
            initStyle(StageStyle.UTILITY)
            maxWidth = 0.0
            maxHeight = 0.0
            opacity = 0.0
            setOnCloseRequest { event -> event.consume() }
        }
        primary.show()
        Stage().apply {
            initOwner(primary)
            initStyle(StageStyle.TRANSPARENT)
            scene = Scene(vBox)
            scene.fill = null
            isAlwaysOnTop = true
            setOnCloseRequest { event -> event.consume() }
        }.show()
        val timeline = Timeline(
            KeyFrame(
                Duration.seconds(0.05),
                EventHandler<ActionEvent> {
                    val date = EorzeaDateTime.now()
                    val realDateTime = date.convertToRealTime()
                    realTime.text = dateTimeFormatter.format(realDateTime)
                    eorzeaTime.text = date.format("%04d / %02d / %02d %02d:%02d:%02d")
                    alarmList.forEach {
                        if (it.localDateTime.hour == realDateTime.hour
                            && it.localDateTime.minute == realDateTime.minute
                            && it.localDateTime.second == realDateTime.second) {
                            println("${it.title} 指定時刻です")
                            onNotify(it)
                            it.eorzeaDateTime.day++
                            it.localDateTime = it.eorzeaDateTime.convertToRealTime()
                        }
                    }
                }
            )
        )
        timeline.cycleCount = Timeline.INDEFINITE
        timeline.play()
    }

    fun onConfirm(actionEvent: ActionEvent) {
        val title = if (alarmTitle.text.isNullOrEmpty()) "アラーム" else alarmTitle.text
        val type = (timeType.selectedToggle as RadioButton).id.toUpperCase()
        addAlarm(title, type, hourInput.value, minuteInput.value, agoMinute.value)
    }

    fun addAlarm(title: String, type: String, hour: Int, minute: Int = 0, agoMinute: Int = 0) {
        val eorzeaDateTime: EorzeaDateTime
        val localDateTime: LocalDateTime
        if (type == "ET") {
            eorzeaDateTime = EorzeaDateTime.now().apply {
                this.hour = hour.toByte()
                this.minute = minute.toByte()
                this.second = 0
                checkPassAndIncrease()
            }
            localDateTime = eorzeaDateTime.convertToRealTime()
        } else {
            eorzeaDateTime = EorzeaDateTime.now()
            localDateTime = LocalDateTime.now().run {
                if (this.hour >= hour && this.minute >= minute) withDayOfMonth(dayOfMonth + 1)
                with(LocalTime.of(hour, minute, 0))
            }
        }
        localDateTime.minusMinutes(agoMinute.toLong())
        val alarmData = AlarmData(
            title,
            type,
            agoMinute,
            eorzeaDateTime,
            localDateTime
        )
        alarmList.add(alarmData)
        val gridPane = GridPane()
        gridPane.addRow(0, Label(title), Label(type), Label("%02d:%02d".format(hour, minute)))
        gridPane.columnConstraints.addAll(ColumnConstraints(290.0), ColumnConstraints(30.0), ColumnConstraints(50.0))
        if (agoMinute > 0) {
            gridPane.addRow(0, Label("(%d)".format(agoMinute)))
            gridPane.columnConstraints.add(ColumnConstraints(30.0))
        }
        alarmContainer.children.add(gridPane)
        val label = Label().apply {
            textAlignment = TextAlignment.CENTER
            alignment = Pos.CENTER
            style = "-fx-background-color: #00000060; -fx-background-radius: 10px;"
            padding = Insets(2.0, 10.0, 2.0, 10.0)
        }
        gridPane.setOnMouseEntered {
            label.text = "通知時刻\n(LT) ${timeFormatter.format(alarmData.localDateTime)}"
            label.layoutX = it.sceneX
            label.layoutY = it.sceneY
            root.children.add(label)
        }
        gridPane.setOnMouseExited { root.children.remove(label) }
    }

    fun onNotify(alarmData: AlarmData) {
        val time =
            if (alarmData.targetType == "ET") "%02d:%02d:%02d".format(
                alarmData.eorzeaDateTime.hour,
                alarmData.eorzeaDateTime.minute,
                alarmData.eorzeaDateTime.second)
            else timeFormatter.format(alarmData.localDateTime)
        val label = Label().apply {
            text = "\uD83D\uDD14 ${alarmData.title} - (${alarmData.targetType}) $time"
            style = "-fx-font-size: 24px; -fx-text-fill: white; -fx-background-color: #0000FF60; -fx-background-radius: 10px"
        }
        vBox.children.add(label)
        SequentialTransition(
            FadeTransition(Duration.seconds(0.5), label).apply {
                fromValue = 0.0
                toValue = 1.0
                interpolator = Interpolator.EASE_BOTH
            },
            PauseTransition(Duration.seconds(2.0)),
            FadeTransition(Duration.seconds(0.5), label).apply {
                fromValue = 1.0
                toValue = 0.0
                interpolator = Interpolator.EASE_BOTH
            }
        ).apply {
            setOnFinished { vBox.children.remove(label) }
        }.play()
    }
}
