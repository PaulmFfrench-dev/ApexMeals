package ie.wit.freedomfood.models

interface FreedomFoodStore {
    fun findAll() : List<FreedomFoodModel>
    fun findById(id: Long) : FreedomFoodModel?
    fun create(donation: FreedomFoodModel)
}