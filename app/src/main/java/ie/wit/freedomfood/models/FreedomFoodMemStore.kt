package ie.wit.freedomfood.models

import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class FreedomFoodMemStore : FreedomFoodStore {

    val donations = ArrayList<FreedomFoodModel>()

    override fun findAll(): List<FreedomFoodModel> {
        return donations
    }

    override fun findById(id:Long) : FreedomFoodModel? {
        val foundDonation: FreedomFoodModel? = donations.find { it.id == id }
        return foundDonation
    }

    override fun create(donation: FreedomFoodModel) {
        donation.id = getId()
        donations.add(donation)
        logAll()
    }

    fun logAll() {
        Timber.v("** Donations List **")
        donations.forEach { Timber.v("Donate ${it}") }
    }
}