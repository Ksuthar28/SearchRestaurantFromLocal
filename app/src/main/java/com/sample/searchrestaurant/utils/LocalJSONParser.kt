package com.sample.searchrestaurant.utils

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class LocalJSONParser {

    companion object {
        fun inputStreamToString(inputStream: InputStream): String {
            return try {
                val bytes = ByteArray(inputStream.available())
                inputStream.read(bytes, 0, bytes.size)
                String(bytes)
            } catch (e: IOException) {
                "Invalid JSON file"
            }
        }
    }
}
/*
Covert local json file to model class
 */
inline fun <reified T> Context.getObjectFromJson(jsonFileName: String): T {
    val myJson = LocalJSONParser.inputStreamToString(this.assets.open(jsonFileName))
    return Gson().fromJson(myJson, T::class.java)
}