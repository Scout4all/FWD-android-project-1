/*
 * Copyright (c) 2023.
 * Developed by : Bigad Aboubakr
 * Developer website : http://bigad.me
 * Developer github : https://github.com/Scout4all
 * Developer Email : bigad@bigad.me
 */

package me.bigad.shoestore.utils

//data class ErrorState(
//    var hasError: Boolean = true,
//    var message: String = "",
//    var input: String = ""
//)
data class ValidationErrors(
    var hasError: Boolean = true,
    var errors: HashMap<String, ArrayList<String>> = HashMap()
)