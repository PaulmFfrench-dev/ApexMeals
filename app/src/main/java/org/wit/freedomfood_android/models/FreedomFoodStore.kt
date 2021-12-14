package org.wit.freedomfood_android.models

interface FreedomFoodStore {
    fun findAll(): List<FreedomFoodModel>
    fun create(freedomfood: FreedomFoodModel)
    fun update(freedomfood: FreedomFoodModel)
}