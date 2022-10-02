package com.example.contacts.model

class UserCardsListRepository(
    private val userCardListLocalData: UserCardListLocalData,
    private val userCardListRemoteData: UserCardListRemoteData
) {

    suspend fun fetchUserCardList() : List<UserContactItem> {
        val contacts =  userCardListRemoteData.getContactsList()
        userCardListLocalData.saveRemoteData(contacts)
        return contacts
    }

    suspend fun loadLocalData() : List<UserContactItem> =
        userCardListLocalData.loadAllData().map {
            UserContactItem(
                id = it.id,
                name = it.name,
                phone = it.phone,
                height = it.height,
                biography = it.biography,
                temperament = it.temperament,
                educationPeriodStart = it.educationPeriod.split(" ").first(),
                educationPeriodEnd = it.educationPeriod.split(" ").drop(1).first()
            )
        }

}