package org.wit.freedomfood_android.models

import timber.log.Timber.i

class FreedomFoodMemStore {

    val freedomfoods = ArrayList<FreedomFoodModel>()

    override fun findAll(): List<FreedomFoodModel> {
        return freedomfoods
    }

    override fun create(freedomfood: FreedomFoodModel) {
        freedomfoods.add(freedomfood)
        logAll()
    }

    fun logAll() {
        freedomfoods.forEach{ i("${it}") }
    }
}