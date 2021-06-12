package com.example.listcats.helpers

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class JsonHandler {
    companion object {
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun getJsonObjFromString(string: String): JSONObject {
            return JSONObject(string)
        }

        fun getJsonArrayromString(string: String): JSONArray {
            try {
                return JSONArray(string)
            } catch (ioException: IOException) {
                return JSONArray("[]")
            }
        }

        fun getFieldSafely(obj: JSONObject?, fieldName: String): String {
            if (obj == null) return ""
            return if (obj.has(fieldName)) obj.getString(fieldName) else "";
        }
    }
}