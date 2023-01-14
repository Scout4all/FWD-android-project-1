package me.bigad.shoestore.screens.shoeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import me.bigad.shoestore.model.ModelShoesList
import me.bigad.shoestore.model.Shoe

class ShoesListViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val shoeListModel = ModelShoesList.getInstance()
    private val _shoeList = shoeListModel.shoesList
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList
}