package me.bigad.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentShoeDetailsBinding

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var viewModel: ShoeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)

        viewModel = ViewModelProvider(this).get(ShoeDetailsViewModel::class.java)
        binding.saveButton.setOnClickListener {
            it.findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoesListFragment())
        }
        return binding.root
    }


}