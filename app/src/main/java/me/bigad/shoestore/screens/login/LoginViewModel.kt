package me.bigad.shoestore.screens.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bigad.shoestore.model.ModelUserList
import me.bigad.shoestore.model.User
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private val _userList =   ModelUserList.getInstance().userList
    val userList: LiveData<List<User>>
        get() = _userList
    private val _stateMessage = MutableLiveData<ErrorState>()
    val stateMessage: LiveData<ErrorState>
        get() = _stateMessage
    var stateObject = ErrorState(true)

//validate user input data and check credentials
private fun validateData(email: String, password: String, isLogin: Boolean = true)  {
        val isUserExists: Boolean = _userList.value?.any { user ->
            user.email == email
        } == true
        val isUserCredentialTrue: Boolean = _userList.value?.any { user ->
            user.email == email && user.password == password
        } == true
        if (!email.isValidEmail()) {
            stateObject.hasError = true
            stateObject.message = "email is empty or not correct"
            stateObject.input =  "email"


        } else if (!password.isValidPassword()) {
            stateObject.hasError = true
            stateObject.message = "password is empty"
            stateObject.input =  "password"

        } else if (isLogin) {
            if (!isUserExists) {
                stateObject.hasError = true
                stateObject.message = "user dose not exists create new user"
                stateObject.input =  "email"

            } else if(!isUserCredentialTrue){
                stateObject.hasError = true
            stateObject.message = "password you have entered is not correct"
            stateObject.input = "password"
            _stateMessage.value = stateObject
            }else{
                stateObject.hasError = false

            }
        }else if(!isLogin){
            if (isUserExists) {
                stateObject.hasError = true
                stateObject.message = "email is exists try to login or use different email"
                stateObject.input =  "email"

            }else{
                stateObject.hasError = false

            }
        }else {
            stateObject.hasError = false

        }
        _stateMessage.value = stateObject

    }
    //login method
    fun login(email: String, password: String) :Boolean{
        validateData(email, password)
        if(_stateMessage.value?.hasError==false){
            return true
        }

        return false
    }
    //create new user to login to app
    fun createUser(email: String, password: String): Boolean {
        validateData(email, password, false)
        Timber.w(stateMessage.value.toString())

        if(stateMessage.value?.hasError==false){
            val newUser = User(email, password)

            _userList.value = _userList.value?.plus(newUser) ?: listOf(newUser)
            return true
        }



        return false
    }

    fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun CharSequence?.isValidPassword() = !isNullOrEmpty()

}

data class ErrorState(
    var hasError: Boolean = true,
    var message: String = "",
    var input: String = ""
)
