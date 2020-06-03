package com.kotmw.eorzeatimer

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.util.*
import kotlin.system.exitProcess

class Main : Application() {

    private val appName = "ErozeaTimer"
    private val jsonFactory = JacksonFactory.getDefaultInstance()
    private val tokensDirectoryPath = "tokens"

    private val scopes = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)
    private val credentials = "credentials.json"

    fun getCredentials(HTTP_TRANSPORT: NetHttpTransport): Credential {
        val inputStream = ClassLoader.getSystemResourceAsStream(credentials)
            ?: throw FileNotFoundException("Resource not found: $credentials")
        val clientSecrets = GoogleClientSecrets.load(jsonFactory, InputStreamReader(inputStream))
        val flow = GoogleAuthorizationCodeFlow.Builder(
            HTTP_TRANSPORT, jsonFactory, clientSecrets, scopes)
            .setDataStoreFactory(FileDataStoreFactory(File(tokensDirectoryPath)))
            .setAccessType("offline")
            .build()
        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }


    override fun start(stage: Stage?) {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val spreadSheetId = "1A079Qt8gpcEr4mXACPokVAoZ3_00Nh4dtqJzifYgl1Y"
        val range = "sheet1"
        val service = Sheets.Builder(httpTransport, jsonFactory, getCredentials(httpTransport))
            .setApplicationName(appName)
            .build()
        val response = service.spreadsheets().values()//json
            .get(spreadSheetId, range)
            .execute()
        val values = response.getValues()
        if (values == null || values.isEmpty()) println("No data found.")
        else {
            for (row in values) {
                println("${row[0]}, ${row[1]}, ${row[2]}, ${row[3]}, ${row[4]}, ${row[5]}")
            }
        }
        val scene = Scene(FXMLLoader.load(ClassLoader.getSystemResource("Main.fxml")))
        stage?.scene = scene
        stage?.setOnCloseRequest { exitProcess(0) }
        stage?.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}
