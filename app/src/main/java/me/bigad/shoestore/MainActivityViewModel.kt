/*
 * Copyright (c) 2023.
 * Developed by : Bigad Aboubakr
 * Developer website : http://bigad.me
 * Developer github : https://github.com/Scout4all
 * Developer Email : bigad@bigad.me
 */

package me.bigad.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bigad.shoestore.model.Shoe
import me.bigad.shoestore.utils.ValidationErrors
import timber.log.Timber

class MainActivityViewModel : ViewModel() {

    val shoesList = MutableLiveData<List<Shoe>>()
    private var shoesListArray = ArrayList<Shoe>()
    private val _validationErrorsState = MutableLiveData<ValidationErrors>()
    val validationErrorsState: LiveData<ValidationErrors> = _validationErrorsState


    val mShoe = MutableLiveData<Shoe>()


    private var validationErrors = ValidationErrors(true)
    val shoeNameInput = "shoe"
    private val shoeNameInputErrors: ArrayList<String> = ArrayList()
    val shoeSizeInput = "size"
    private val shoeSizeInputErrors: ArrayList<String> = ArrayList()
    val shoeCompanyInput = "company"
    private val shoeCompanyInputErrors: ArrayList<String> = ArrayList()

    init {

        //load dummy list uncomment next line
        shoesListArray  = inflateShoeList()

        shoesList.value = this.shoesListArray

    }

    //dummy list
    private fun inflateShoeList(): ArrayList<Shoe> {
        return arrayListOf(
            Shoe(
                "Nature Three Mahogany Leather",
                08.00,
                "Clarks",
                "This classic silhouette last is brought right up-to-date with the addition of state-of-the-art breathable Active Air underfoot cushioning. The last word in comfort is complemented by a rich leather upper and sheepskin lining. A rubber sole adds grip and durability."
            ),
            Shoe(
                "Court Lite Wally Dark Olive Suede",
                09.50,
                "Clarks",
                "Our instantly recognisable Wallabee silhouette gets a sports injection with an athletic-inspired sole for casual style. This super-lightweight profile is built on the original Wallabee last for a feel-good fit, boosted with innovative cushioning for 21st century comfort.\n" + "Responsibly sourced premium dark olive suede upper\n" + "Innovative MI-X technology combines a high rebound, lightweight part-recycled EVA midsole with a multi-density, contoured, breathable footbed for ultimate support\n" + "Footbed is removable for customisable comfort\n" + "Durable crepe-effect part-recycled rubber sole gives grip"
            ),
        )
    }

    fun onDelete(shoe: Shoe): Boolean {
        shoesListArray.remove(shoe)
        shoesList.value = shoesListArray
        return true
    }
    private fun CharSequence?.isEntered() = isNullOrEmpty()
    private fun validateAddShoe(shoe: Shoe) {

        validationErrors.errors.clear()
        validationErrors.hasError = true
        if (shoe.name.isEntered()) {
            shoeNameInputErrors.add("shoe name should not be empty")
            validationErrors.errors.put(shoeNameInput, shoeNameInputErrors)
        }

        if (shoe.company.isEntered()) {
            shoeCompanyInputErrors.add("shoe company should not be empty")
            validationErrors.errors.put(shoeCompanyInput, shoeCompanyInputErrors)
        }
        if (shoe.size.toString().isEntered() || shoe.size == 0.0) {
            shoeSizeInputErrors.add("shoe size should not be empty")
            validationErrors.errors.put(shoeSizeInput, shoeSizeInputErrors)
        }

        if(validationErrors.errors.size == 0){
            validationErrors.hasError=false
        }
        _validationErrorsState.value = validationErrors

    }





    val backEvent = MutableLiveData(false)
    fun onBack() {
        backEvent.value = true
    }

    fun onSaveShoe(): Boolean {
        mShoe.value?.let { validateAddShoe(it) }
        if (!validationErrors.hasError) {
            mShoe.value?.let { shoesListArray.add(it) }
            shoesList.value = shoesListArray
            backEvent.value = true
        }
        Timber.w(mShoe.value.toString())
        return validationErrors.hasError
    }

    fun resetShoeDetailsData() {
        validationErrors.hasError = true
        validationErrors.errors.clear()
        mShoe.value = Shoe()
        backEvent.value = false
    }
}



