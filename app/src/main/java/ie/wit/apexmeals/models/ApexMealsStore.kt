package ie.wit.apexmeals.models

interface ApexMealsStore {
    fun findAll() : List<ApexMealsModel>
    fun findById(id: Long) : ApexMealsModel?
    fun create(donation: ApexMealsModel)
}