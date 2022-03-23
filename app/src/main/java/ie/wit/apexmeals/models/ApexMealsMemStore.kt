package ie.wit.apexmeals.models

import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ApexMealsMemStore : ApexMealsStore {

    val apexmeals = ArrayList<ApexMealsModel>()

    override fun findAll(): List<ApexMealsModel> {
        return apexmeals
    }

    override fun findById(id:Long) : ApexMealsModel? {
        val foundDonation: ApexMealsModel? = apexmeals.find { it.id == id }
        return foundDonation
    }

    override fun create(apexmeal: ApexMealsModel) {
        apexmeal.id = getId()
        apexmeals.add(apexmeal)
        logAll()
    }

    fun logAll() {
        Timber.v("** Donations List **")
        apexmeals.forEach { Timber.v("Donate ${it}") }
    }
}