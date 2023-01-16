/*
 * Copyright (c) 2023.
 * Developed by : Bigad Aboubakr
 * Developer website : http://bigad.me
 * Developer github : https://github.com/Scout4all
 * Developer Email : bigad@bigad.me
 */

package me.bigad.shoestore.utils


import androidx.databinding.InverseMethod

 object Convertor {


    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: List<Double>): String {
        return value.toString()
    }
     @JvmStatic
    fun stringToDouble(value : String): List<Double> {
     return listOf( value.toDouble())
    }


}