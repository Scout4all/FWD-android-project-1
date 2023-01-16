/*
 * Copyright (c) 2023.
 * Developed by : Bigad Aboubakr
 * Developer website : http://bigad.me
 * Developer github : https://github.com/Scout4all
 * Developer Email : bigad@bigad.me
 */

package me.bigad.shoestore.screens.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.bigad.shoestore.utils.ValidationErrors
import me.bigad.shoestore.model.User
import me.bigad.shoestore.model.UserSessionModel


class LoginViewModel : ViewModel() {

    //retrieve users from users model
    val userSessionModel = UserSessionModel.getInstance()
    val mUser = MutableLiveData<User?>()

    // input error checking
    private val _validationErrorsState = MutableLiveData<ValidationErrors>()
    val validationErrorsState: LiveData<ValidationErrors> = _validationErrorsState
    //Init error State
    var validationErrors = ValidationErrors(true)
    //error names and messages
    val emailHasError: String = "email"
    val emailErrors: ArrayList<String> = ArrayList()
    val passwordHasError: String = "password"
    val passwordErrors: ArrayList<String> = ArrayList()

    //validate user input data and check credentials
    private fun validateData(vEmail: String , vPassword: String, isLogin: Boolean = true) {
        val email = vEmail.trim()
        val password =  vPassword.trim()
        //reset errors
        validationErrors.hasError = true
        validationErrors.errors.get(emailHasError)?.clear()
        validationErrors.errors.get(passwordHasError)?.clear()
        validationErrors.errors.clear()



        val isUserExists: Boolean = userSessionModel.userList.value?.any { user ->
            user.email == email
        } == true
        val isUserCredentialTrue: Boolean = userSessionModel.userList.value?.any { user ->
            user.email == email && user.password == password
        } == true
        if (!email.isValidEmail()) {
            emailErrors.add("email is empty or not correct")
            validationErrors.errors.put(emailHasError, emailErrors)
        }
        if (!password.isValidPassword()) {
            passwordErrors.add("password is empty")
            validationErrors.errors.put(passwordHasError, passwordErrors)
        }
        if (isLogin) {
           if(email.isValidEmail() &&password.isValidPassword() ) {
               if (!isUserExists) {
                   emailErrors.add("user dose not exists create new user")
                   validationErrors.errors.put(emailHasError, emailErrors)
               } else {
                   if (!isUserCredentialTrue) {
                       passwordErrors.add("password you have entered is not correct")
                       validationErrors.errors.put(passwordHasError, passwordErrors)
                   }
               }
           }

        }else{
            if ( email.isValidEmail() &&password.isValidPassword() ) {
                if (isUserExists) {
                    emailErrors.add("email is exists try to login or use different email")
                    validationErrors.errors.put(emailHasError, emailErrors)
                }
            }
        }


        if(validationErrors.errors.size == 0){
            validationErrors.hasError=false
        }
        _validationErrorsState.value = validationErrors
    }

    //login method
    fun login() {
        mUser.value?.let { validateData(it.email, it.password) }
        if (_validationErrorsState.value?.hasError == false) {
            userSessionModel.loggedUser = mUser.value?.email ?: ""
            toWelcomeEvent.value = true
        }
    }

    //create new user to login to app
    fun createUser()  {
        mUser.value?.let { validateData(it.email, it.password,false) }


        if (validationErrorsState.value?.hasError == false) {
            val newUser :User = mUser.value!!

            userSessionModel.userList.value =  userSessionModel.userList.value?.plus(newUser) ?: listOf(newUser)
            toWelcomeEvent.value = true
        }
    }

    fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun CharSequence?.isValidPassword() = !isNullOrEmpty()
    fun resetUserDetailsData() {
        validationErrors.hasError = true
        validationErrors.errors.clear()
        mUser.value = User()
    }
    val toWelcomeEvent = MutableLiveData(false)

}


