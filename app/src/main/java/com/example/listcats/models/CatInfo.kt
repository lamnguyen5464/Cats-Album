package com.example.listcats.models
import com.example.listcats.helpers.JsonHelper
import org.json.JSONObject

class CatInfo() {
    var description: String? = null
    var name: String? = null
    var wikipedia_url: String? = null
    var url: String? = null
    var width: Int? = null
    var height: Int? = null

    constructor(json: JSONObject?) : this() {
        description = JsonHelper.getFieldSafely(json, CatInfoField.description)
        name = JsonHelper.getFieldSafely(json, CatInfoField.name)
        wikipedia_url = JsonHelper.getFieldSafely(json, CatInfoField.wikipedia_url)

        val fieldImage =
            JsonHelper.getJsonObjFromString(JsonHelper.getFieldSafely(json, CatInfoField.image))
        url = JsonHelper.getFieldSafely(fieldImage, CatInfoField.url)
        width = JsonHelper.getFieldSafely(fieldImage, CatInfoField.width).toInt()
        height = JsonHelper.getFieldSafely(fieldImage, CatInfoField.height).toInt()
    }
}

class CatInfoField {
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
