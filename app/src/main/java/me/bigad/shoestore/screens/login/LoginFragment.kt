/*
 * Copyright (c) 2023.
 * Developed by : Bigad Aboubakr
 * Developer website : http://bigad.me
 * Developer github : https://github.com/Scout4all
 * Developer Email : bigad@bigad.me
 */

package me.bigad.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    //    private lateinit var viewModel: LoginViewModel
    private val viewModel by viewModels<LoginViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.loginViewModel = viewModel
        binding.emailEt.requestFocus()


        viewModelObservers()
        return binding.root
    }

    private fun viewModelObservers() {
        viewModel.resetUserDetailsData()
        viewModel.toWelcomeEvent.observe(viewLifecycleOwner) { toWelcomeEvent ->
            if (toWelcomeEvent) {
                binding.loginContainer.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(viewModel.mUser.value!!.email))
            }
        }


        //observe error states passed from view model to update binding
        viewModel.validationErrorsState.observe(viewLifecycleOwner) { errorState ->
            Timber.w(errorState.toString())
            binding.emailEt.error = null
            binding.passwordEt.error = null
            if (errorState.hasError) {
                if (!errorState.errors.get(viewModel.emailHasError).isNullOrEmpty()) {
                    binding.apply {
                        emailEt.error =
                            errorState.errors.get(viewModel.emailHasError).toString()
                        emailEt.requestFocus()
                    }

                }
                if (!errorState.errors.get(viewModel.passwordHasError).isNullOrEmpty()) {
                    binding.apply {
                        passwordEt.error =
                            errorState.errors.get(viewModel.passwordHasError).toString()
                        passwordEt.requestFocus()
                    }

                }

            } else {
                binding.apply {
                    emailEt.error = null
                    emailEt.clearFocus()
                    passwordEt.error = null
                    passwordEt.clearFocus()
                }

            }

        }


    }

}