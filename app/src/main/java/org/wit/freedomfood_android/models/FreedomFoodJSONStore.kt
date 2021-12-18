package org.wit.freedomfood_android.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.freedomfood_android.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "freedomfoods.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<FreedomFoodModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class FreedomFoodJSONStore(private val context: Context) : FreedomFoodStore {

    var freedomfoods = mutableListOf<FreedomFoodModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<FreedomFoodModel> {
        logAll()
        return freedomfoods
    }

    override fun create(freedomfood: FreedomFoodModel) {
        freedomfood.id = generateRandomId()
        freedomfoods.add(freedomfood)
        serialize()
    }

    override fun update(freedomfood: FreedomFoodModel) {
        val freedomfoodList = findAll() as ArrayList<FreedomFoodModel>
        var foundFreedomFood: FreedomFoodModel? = freedomfoodList.find { p -> p.id == freedomfood.id }
        if (foundFreedomFood != null) {
            foundFreedomFood.title = freedomfood.title
            foundFreedomFood.description = freedomfood.description
            foundFreedomFood.rating = freedomfood.rating
            foundFreedomFood.meal = freedomfood.meal
            foundFreedomFood.allergen = freedomfood.allergen
            foundFreedomFood.image = freedomfood.image
            foundFreedomFood.lat = freedomfood.lat
            foundFreedomFood.lng = freedomfood.lng
            foundFreedomFood.zoom = freedomfood.zoom
        }
        serialize()
    }

    override fun delete(freedomfood: FreedomFoodModel) {
        freedomfoods.remove(freedomfood)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(freedomfoods, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        freedomfoods = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        freedomfoods.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}