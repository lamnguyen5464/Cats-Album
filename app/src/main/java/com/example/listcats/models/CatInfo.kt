package com.example.listcats.models
import com.example.listcats.helpers.JsonHandler
import org.json.JSONObject

class CatInfo() {
    var description: String? = null
    var name: String? = null
    var wikipedia_url: String? = null
    var url: String? = null
    var width: Int? = null
    var height: Int? = null


    constructor(json: JSONObject?) : this() {
        description = JsonHandler.getFieldSafely(json, CatInfoField.description)
        name = JsonHandler.getFieldSafely(json, CatInfoField.name)
        wikipedia_url = JsonHandler.getFieldSafely(json, CatInfoField.wikipedia_url)

        val fieldImage =
            JsonHandler.getJsonObjFromString(JsonHandler.getFieldSafely(json, CatInfoField.image))
        url = JsonHandler.getFieldSafely(fieldImage, CatInfoField.url)
        width = JsonHandler.getFieldSafely(fieldImage, CatInfoField.width).toInt()
        height = JsonHandler.getFieldSafely(fieldImage, CatInfoField.height).toInt()
    }
}

class CatInfoField() {
    companion object {
        val description = "description"
        val name = "name"
        val wikipedia_url = "wikipedia_url"
        val image = "image"
        val url = "url"
        val width = "width"
        val height = "height"
    }
}
