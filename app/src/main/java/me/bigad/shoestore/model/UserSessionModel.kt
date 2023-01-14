package me.bigad.shoestore.model

import androidx.lifecycle.MutableLiveData

class UserSessionModel {
    val userList = MutableLiveData<List<User>>()
    var loggedUser: String = ""

    init {
        val list = listOf<User>(
            User("a@b.c", "123"),
            User("a@b.com", "123")
        )
        userList.value = list
    }

    companion object {

        @Volatile
        var INSTANCE: UserSessionModel? = null

        fun getInstance(): UserSessionModel {
            return INSTANCE ?: synchronized(this) {
                val instance = UserSessionModel()
                INSTANCE = instance
                instance
            }
        }

    }

}