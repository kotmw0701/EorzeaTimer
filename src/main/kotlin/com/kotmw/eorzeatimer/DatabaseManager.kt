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
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class DatabaseManager {

    private val appName = "ErozeaTimer"
    private val jsonFactory = JacksonFactory.getDefaultInstance()
    private val tokensDirectoryPath = "tokens"

    private val scopes = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY)
    private val credentials = "credentials.json"

    private fun getCredentials(HTTP_TRANSPORT: NetHttpTransport): Credential {
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

    fun getTempData(): List<AlarmTempData> {
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
        val spreadSheetId = "1A079Qt8gpcEr4mXACPokVAoZ3_00Nh4dtqJzifYgl1Y"
        val range = "sheet1"
        val service = Sheets.Builder(httpTransport, jsonFactory, getCredentials(httpTransport))
            .setApplicationName(appName)
            .build()
        val response = service.spreadsheets().values()
            .get(spreadSheetId, range)
            .execute()
        val values = response.getValues()
        val list = mutableListOf<AlarmTempData>()
        if (values == null || values.isEmpty()) println("No data found.")
        else {
            val formatter = DateTimeFormatter.ofPattern("H:mm")
            for (row in values) {
                list.add(AlarmTempData(row[0].toString(), row[1].toString(), row[2].toString(), row[3].toString(), row[4].toString(), LocalTime.parse(row[5].toString(), formatter)))
//                println("${row[0]}, ${row[1]}, ${row[2]}, ${row[3]}, ${row[4]}, ${row[5]}")
//                println(LocalTime.parse(row[5].toString(), formatter))
            }
        }
        return list.toList()
    }
}
