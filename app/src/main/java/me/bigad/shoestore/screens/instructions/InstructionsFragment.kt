package me.bigad.shoestore.screens.instructions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentInstructionsBinding
import me.bigad.shoestore.databinding.FragmentLoginBinding
import me.bigad.shoestore.screens.login.LoginViewModel

class InstructionsFragment : Fragment() {


    lateinit var binding: FragmentInstructionsBinding
    private lateinit var viewModel: InstructionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_instructions,container,false)

        viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)


        return binding.root
     }



}