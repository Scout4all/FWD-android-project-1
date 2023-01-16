/*
 * Copyright (c) 2023.
 * Developed by : Bigad Aboubakr
 * Developer website : http://bigad.me
 * Developer github : https://github.com/Scout4all
 * Developer Email : bigad@bigad.me
 */

package me.bigad.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import me.bigad.shoestore.MainActivityViewModel
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentShoeDetailsBinding

class ShoeDetailsFragment : Fragment() {

     private lateinit var binding: FragmentShoeDetailsBinding

    private val appSharedViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)

        binding.shoeDetailsViewModel = appSharedViewModel

        viewModelObservers()

        return binding.root
    }


    private fun viewModelObservers() {
        //reset shoe data when open fragment !Important
        appSharedViewModel.resetShoeDetailsData()

        //observe form validation error state
        appSharedViewModel.validationErrorsState.observe(viewLifecycleOwner) { errorState ->
            if (errorState.hasError) {
                if (!errorState.errors[appSharedViewModel.shoeNameInput].isNullOrEmpty()) {
                    binding.shoeNameEt.error =
                        errorState.errors[appSharedViewModel.shoeNameInput].toString()
                    binding.shoeCompanyEt.requestFocus()
                }
                if (!errorState.errors[appSharedViewModel.shoeCompanyInput].isNullOrEmpty()) {
                    binding.shoeCompanyEt.error =
                        errorState.errors[appSharedViewModel.shoeCompanyInput].toString()
                    binding.shoeCompanyEt.requestFocus()
                }
                if (!errorState.errors[appSharedViewModel.shoeSizeInput].isNullOrEmpty()) {
                    binding.shoeSizeEt.error =
                        errorState.errors[appSharedViewModel.shoeSizeInput].toString()
                    binding.shoeSizeEt.requestFocus()
                }

            } else {
                binding.apply {
                    shoeNameEt.error = null
                    shoeNameEt.clearFocus()
                    shoeCompanyEt.error = null
                    shoeCompanyEt.clearFocus()
                    shoeSizeEt.error = null
                    shoeSizeEt.clearFocus()
                }

            }
        }

        //observe on back event to return to store listing
        appSharedViewModel.backEvent.observe(viewLifecycleOwner) { back ->
            if (back) {
                binding.shoeDetailsContainer.findNavController()
                    .navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoesListFragment())
            }
        }
    }
}