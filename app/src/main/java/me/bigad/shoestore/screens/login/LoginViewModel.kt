package me.bigad.shoestore.screens.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bigad.shoestore.model.ErrorState
import me.bigad.shoestore.model.User
import me.bigad.shoestore.model.UserSessionModel
import timber.log.Timber

class LoginViewModel : ViewModel() {

    //retrive users from users model
    val userSessionModel = UserSessionModel.getInstance()
    private val _userList = userSessionModel.userList
    val userList: LiveData<List<User>>
        get() = _userList

    // input error checking
    private val _stateMessage = MutableLiveData<ErrorState>()
    val stateMessage: LiveData<ErrorState>
        get() = _stateMessage

    //Init error State
    var stateObject = ErrorState(true)

    //error names and messages
    val emailHasError: String = "email"
    val emailErrors: ArrayList<String> = ArrayList()
    val passwordHasError: String = "password"
    val passwordErrors: ArrayList<String> = ArrayList()

    //validate user input data and check credentials
    private fun validateData(email: String, password: String, isLogin: Boolean = true) {
        passwordErrors.clear()
        emailErrors.clear()
        val isUserExists: Boolean = _userList.value?.any { user ->
            user.email == email
        } == true
        val isUserCredentialTrue: Boolean = _userList.value?.any { user ->
            user.email == email && user.password == password
        } == true
        if (!email.isValidEmail()) {
            stateObject.hasError = true
            emailErrors.add("email is empty or not correct")
            stateObject.errors.put(emailHasError, emailErrors)
        } else {
            stateObject.hasError = false
        }

        if (!password.isValidPassword()) {
            stateObject.hasError = true
            passwordErrors.add("password is empty")
            stateObject.errors.put(passwordHasError, passwordErrors)
        } else {
            stateObject.hasError = false
        }
        if (isLogin) {
            if (!isUserExists) {
                stateObject.hasError = true
                emailErrors.add("user dose not exists create new user")
                stateObject.errors.put(emailHasError, emailErrors)


            } else {
                if (!isUserCredentialTrue) {
                    stateObject.hasError = true
                    passwordErrors.add("password you have entered is not correct")
                    stateObject.errors.put(passwordHasError, passwordErrors)

                    _stateMessage.value = stateObject
                } else {
                    stateObject.hasError = false

                }

            }

        }
        if (!isLogin) {
            if (isUserExists) {
                stateObject.hasError = true
                emailErrors.add("email is exists try to login or use different email")
                stateObject.errors.put(emailHasError, emailErrors)


            } else {
                stateObject.hasError = false

            }
        }
        _stateMessage.value = stateObject

    }

    //login method
    fun login(email: String, password: String): Boolean {
        validateData(email, password)
        if (_stateMessage.value?.hasError == false) {
            userSessionModel.loggedUser = email
            return true
        }

        return false
    }

    //create new user to login to app
    fun createUser(email: String, password: String): Boolean {
        validateData(email, password, false)
        Timber.w(stateMessage.value.toString())

        if (stateMessage.value?.hasError == false) {
            val newUser = User(email, password)
            userSessionModel.loggedUser = email
            _userList.value = _userList.value?.plus(newUser) ?: listOf(newUser)
            return true
        }



        return false
    }

    fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun CharSequence?.isValidPassword() = !isNullOrEmpty()

}


