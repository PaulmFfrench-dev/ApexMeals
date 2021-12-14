package org.wit.freedomfood_android.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class FreedomFoodMemStore {

    val freedomfoods = ArrayList<FreedomFoodModel>()

    override fun findAll(): List<FreedomFoodModel> {
        return freedomfoods
    }

    override fun create(freedomfood: FreedomFoodModel) {
        freedomfood.id = getId()
        freedomfoods.add(freedomfood)
        logAll()
    }

    override fun update(freedomfood: FreedomFoodModel) {
        var foundFreedomFood: FreedomFoodModel? = freedomfoods.find { p -> p.id == freedomfood.id }
        if (foundFreedomFood != null) {
            foundFreedomFood.title = freedomfood.title
            foundFreedomFood.description = freedomfood.description
            logAll()
        }
    }

    fun logAll() {
        freedomfoods.forEach{ i("${it}") }
    }
}