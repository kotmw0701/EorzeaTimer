package com.kotmw.eorzeatimer

import javafx.animation.*
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
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
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

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
    @FXML lateinit var command: TextArea
    @FXML lateinit var adjust: Label

    private lateinit var vBox: VBox

    private val alarmList = mutableListOf<AlarmData>()

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy / MM / dd HH:mm:ss")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    private var adjustProperty: StringProperty = SimpleStringProperty("0")

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
                val alarmTempData = it
                setOnAction { addAlarm(alarmTempData) }
            })
        }

        adjust.textProperty().bind(adjustProperty)

        initSpinner(hourInput, 0, 23)
        initSpinner(minuteInput, 0, 59)
        initSpinner(agoMinute, 0, 60)

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
                EventHandler {
                    val date = EorzeaDateTime.now(adjustProperty.get().toInt())
                    val realDateTime = date.convertToRealTime()
                    realTime.text = "${dateTimeFormatter.format(realDateTime)} (JST)"
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

    private fun initSpinner(spinner: Spinner<Int>, minValue: Int, maxValue: Int) {
        spinner.valueFactory = SpinnerValueFactory.IntegerSpinnerValueFactory(minValue, maxValue)
        spinner.editor.textProperty().addListener { _, _, newValue ->
            if (newValue.isEmpty()) return@addListener
            if (!isNumber(newValue) || newValue.substring(0, if (newValue.length > 2) 3 else newValue.length).toInt() > maxValue) {
                println("規定範囲内ではありません")
                spinner.editor.text = maxValue.toString()
            }
        }
        //フォーカス外した時に中身が無い場合に0を表示させる
        spinner.focusedProperty().addListener { _, _, newValue ->
            if (spinner.editor.text.isNullOrEmpty()) {
                println("NullOrEmpty1")
                spinner.editor.text = minValue.toString()
            }
            if (!newValue && (spinner.value == null || spinner.value < minValue)) {
                println("規定範囲内ではありません②")
                spinner.valueFactory.value = minValue
            }
        }
        //Enter押したときに中身が無いとNPE吐くための対処
        spinner.editor.setOnAction {
            if (spinner.editor.text.isNullOrEmpty()) spinner.editor.text = minValue.toString()
        }
    }

    fun onConfirm(actionEvent: ActionEvent) {
        val title = if (alarmTitle.text.isNullOrEmpty()) "アラーム" else alarmTitle.text
        val type = (timeType.selectedToggle as RadioButton).id.toUpperCase()
        addAlarm(title, type, hourInput.value, minuteInput.value, agoMinute.value)
    }

    fun addAlarm(alarmTempData: AlarmTempData) {
        addAlarm(alarmTempData.title, "ET", alarmTempData.hour)
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
        val contextMenu = ContextMenu(MenuItem("削除").apply {
            setOnAction {
                alarmList.remove(alarmData)
                alarmContainer.children.remove(gridPane)
            }
        })
        gridPane.setOnContextMenuRequested {
            contextMenu.show(gridPane, it.screenX, it.screenY)
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
            label.layoutX = it.sceneX+10
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
            padding = Insets(0.0, 7.5, 0.0, 7.5)
            style = "-fx-font-size: 24px; -fx-text-fill: white; -fx-background-color: #0000FF70; -fx-background-radius: 50px"
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

    private fun isNumber(text: String): Boolean {
        for (char in text.toCharArray())
            if (!char.isDigit()) return false
        return true
    }

    /*
    /alarm  "アラーム テスト"  lt  1428
    /alarm  "アラーム テスト"  lt  rp      28
    /alarm  "アラーム テスト"  lt  1428    0
    /alarm  "アラーム テスト"  lt  rp      28      0
    /alarm  "アラーム テスト"  et  1428    0
    /alarm  "アラーム テスト"  et  rp      1428    0

    /alarm "アラーム テスト" et rp 1428 0
     */
    fun onRead(actionEvent: ActionEvent) {
        for (line in command.text.split("\n")) {
            if (!line.startsWith("/alarm")) continue
            addAlarmCommand(line)
        }
    }

    fun addAlarmCommand(command: String) {
        val matches = Regex("([^\"]\\S*|\".+?\")\\s*").findAll(command).map { it.value.trim() }.toList()
        val type = matches[2]
        val isRepeat = matches.contains("rp")
        val timeID = if (isRepeat) 4 else 3
        val agoID = timeID + 1
        if (matches.size < 4
            || matches.size > 6
            || !Regex("(et|lt)").matches(type)
            || !isNumber(matches[timeID])
            || (matches.size > agoID && !isNumber(matches[agoID]))) {
            println("入力値がおかしいです")
            return
        }
        val time = matches[timeID].toInt()
        val agoMinute = if (matches.size > agoID) matches[agoID].toInt() else 0
        val hour = if (time >= 100) time / 100 else 0
        val minute = time % 100

        addAlarm(matches[1], type.toUpperCase(), hour, minute, agoMinute)
    }

    fun onAdjust(actionEvent: ActionEvent) {
        val param = this.adjustProperty.get().toInt() + (actionEvent.source as Button).text.toInt()
        this.adjustProperty.set(param.toString())
    }
}
