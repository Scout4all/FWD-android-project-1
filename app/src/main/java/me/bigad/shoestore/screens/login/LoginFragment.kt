package me.bigad.shoestore.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.text.setOnClickListener{
            it.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())

        }


        return binding.root
    }

}