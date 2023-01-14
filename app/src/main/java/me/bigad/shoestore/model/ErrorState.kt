package me.bigad.shoestore.model

//data class ErrorState(
//    var hasError: Boolean = true,
//    var message: String = "",
//    var input: String = ""
//)
data class ErrorState(
    var hasError: Boolean = true,
    var errors: HashMap<String, ArrayList<String>> = HashMap()
)