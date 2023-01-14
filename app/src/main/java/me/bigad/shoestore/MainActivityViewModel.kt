package me.bigad.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bigad.shoestore.model.Shoe
import timber.log.Timber

class MainActivityViewModel : ViewModel() {
    //ToDo make activity view model hold shoes
    val shoesList = MutableLiveData<List<Shoe>>()
    var shoesListArray = ArrayList<Shoe>()
    private val _stateMessage = MutableLiveData<ErrorStateNew>()
    val stateMessage: LiveData<ErrorStateNew>
        get() = _stateMessage
    var stateObject = ErrorStateNew(true)
    val shoeNameInput = "shoe"
    val shoeNameInputErrors: ArrayList<String> = ArrayList()
    val shoeSizeInput = "size"
    val shoeSizeInputErrors: ArrayList<String> = ArrayList()
    val shoeCompanyInput = "company"
    val shoeCompanyInputErrors: ArrayList<String> = ArrayList()

    init {
        shoesListArray = arrayListOf(
            Shoe(
                "Nature Three Mahogany Leather",
                listOf(08.00, 09.00, 09.50, 10.00, 10.30),
                "Clarks",
                "This classic silhouette last is brought right up-to-date with the addition of state-of-the-art breathable Active Air underfoot cushioning. The last word in comfort is complemented by a rich leather upper and sheepskin lining. A rubber sole adds grip and durability."
            ),
            Shoe(
                "Court Lite Wally Dark Olive Suede",
                listOf(08.00, 09.00, 09.50, 10.00, 10.30),
                "Clarks",
                "Our instantly recognisable Wallabee silhouette gets a sports injection with an athletic-inspired sole for casual style. This super-lightweight profile is built on the original Wallabee last for a feel-good fit, boosted with innovative cushioning for 21st century comfort.\n" +
                        "Responsibly sourced premium dark olive suede upper\n" +
                        "Innovative MI-X technology combines a high rebound, lightweight part-recycled EVA midsole with a multi-density, contoured, breathable footbed for ultimate support\n" +
                        "Footbed is removable for customisable comfort\n" +
                        "Durable crepe-effect part-recycled rubber sole gives grip"
            ),
        )

        //load view with dummy data
        shoesList.value = shoesListArray

    }

    fun onAddShoe(shoe: Shoe): Boolean {
        validateAddShoe(shoe)
        if (!stateObject.hasError) {
            shoesListArray.add(shoe)
            shoesList.value = shoesListArray
            return true
        }

        Timber.w(shoesList.value.toString())
        return false
    }

    fun onDelete(shoe: Shoe): Boolean {

        shoesListArray.remove(shoe)
        shoesList.value = shoesListArray


        return true
    }

    fun validateAddShoe(shoe: Shoe) {
        shoeNameInputErrors.clear()
        shoeCompanyInputErrors.clear()
        shoeSizeInputErrors.clear()
        if (shoe.name.isEntered()) {
            stateObject.hasError = true
            shoeNameInputErrors.add("shoe name should not be empty")

            stateObject.errors.put(shoeNameInput, shoeNameInputErrors)

        } else {
            stateObject.hasError = false
        }
        if (shoe.company.isEntered()) {
            stateObject.hasError = true
            shoeCompanyInputErrors.add("shoe company should not be empty")
            stateObject.errors.put(shoeCompanyInput, shoeCompanyInputErrors)

        } else {
            stateObject.hasError = false
        }
        if (shoe.size.isEmpty() || shoe.size.get(0) == 0.0) {
            stateObject.hasError = true
            shoeSizeInputErrors.add("shoe size should not be empty")
            stateObject.errors.put(shoeSizeInput, shoeSizeInputErrors)
//        stateObject.message = "shoe size should not be empty"
//        stateObject.input =  "size"
        } else {
            stateObject.hasError = false
        }
        _stateMessage.value = stateObject

    }

    fun CharSequence?.isEntered() = isNullOrEmpty()
}

data class ErrorStateNew(
    var hasError: Boolean = true,
    var errors: HashMap<String, ArrayList<String>> = HashMap()
)
