package me.bigad.shoestore.model

import androidx.lifecycle.MutableLiveData

 class ModelUserList  {
    val userList = MutableLiveData<List<User>>()



    init {
        val list = listOf<User>(
            User("a@b.c", "123"),
            User("a@b.com", "123")
        )
        userList.value = list
    }

     companion object{

         @Volatile
         var INSTANCE : ModelUserList? = null

         fun getInstance(): ModelUserList {
             return INSTANCE?: synchronized(this){
                 val instance = ModelUserList()
                 INSTANCE = instance
                 instance
             }
         }

     }

 }