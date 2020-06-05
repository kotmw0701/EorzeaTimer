package com.kotmw.eorzeatimer

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.system.exitProcess

class Main : Application() {

    override fun start(stage: Stage?) {
        val scene = Scene(FXMLLoader.load(ClassLoader.getSystemResource("Main.fxml")))
        stage?.scene = scene
        stage?.setOnCloseRequest { exitProcess(0) }
        stage?.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}
