package me.bigad.shoestore.screens.welcome

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentWelcomeBinding
import me.bigad.shoestore.screens.login.LoginViewModel

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    private lateinit var viewModel: WelcomeViewModel
private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome,container,false)

        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)


        return binding.root
    }

}