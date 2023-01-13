package me.bigad.shoestore.screens.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {


    lateinit var binding: FragmentInstructionsBinding
    private lateinit var viewModel: InstructionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)

        viewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)
        binding.shoeListButton.setOnClickListener {
            it.findNavController()
                .navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoesListFragment())
        }

        return binding.root
    }


}