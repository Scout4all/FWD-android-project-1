package me.bigad.shoestore.screens.shoedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentShoeDetailsBinding
import me.bigad.shoestore.screens.instructions.InstructionsViewModel

class ShoeDetailsFragment : Fragment() {

 private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var viewModel: ShoeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_details,container,false)

        viewModel = ViewModelProvider(this).get(ShoeDetailsViewModel::class.java)
    return binding.root
    }


}