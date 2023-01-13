package me.bigad.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentLoginBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.emailEt.setText("a@b.me")
        binding.passwordEt.setText("a@b.me")

        binding.loginButton.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            if (viewModel.login(email, password)) {
                it.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(email))
            }

        }
        binding.newAccountButton.setOnClickListener {
            var email = binding.emailEt.text.toString()
            var password = binding.passwordEt.text.toString()
            if (viewModel.createUser(email, password)) {
                it.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(email))
            }

        }

        //observe error states passed from view model to update binding
        viewModel.stateMessage.observe(viewLifecycleOwner, Observer { errorState ->
            Timber.w(errorState.toString())
            if (errorState.hasError) {
                if (errorState.input == "email") {
                    binding.emailEt.setError(errorState.message)
                }
                if (errorState.input == "password") {
                    binding.passwordEt.setError(errorState.message)
                }

            } else {
                binding.emailEt.setError(null)
                binding.passwordEt.setError(null)

            }

        })

        viewModel.userList.observe(viewLifecycleOwner, Observer { it ->
            Timber.w(it.toString())
        })
        return binding.root
    }

}