package me.bigad.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import me.bigad.shoestore.MainActivityViewModel
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentShoeDetailsBinding
import me.bigad.shoestore.model.Shoe

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var viewModel: ShoeDetailsViewModel
    val activityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)

        binding.saveButton.setOnClickListener {
            val shoeName = binding.shoeNameEt.text.toString()
            var shoeSize: Double = 0.0
            if (!binding.shoeSizeEt.text.isNullOrEmpty()) {

                shoeSize = binding.shoeSizeEt.text.toString().toDouble()
            }
            val shoeCompany = binding.shoeCompanyEt.text.toString()
            val shoeDesctription = binding.shoeDescriptionEt.text.toString()
            val shoe = Shoe(shoeName, listOf(shoeSize), shoeCompany, shoeDesctription)
            if (activityViewModel.onAddShoe(shoe)) {
                it.findNavController()
                    .navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoesListFragment())
            }

        }

        binding.cancelButton.setOnClickListener {
            it.findNavController()
                .navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoesListFragment())

        }

        activityViewModel.stateMessage.observe(viewLifecycleOwner, Observer { errorState ->
            if (errorState.hasError) {
                if (!errorState.errors.get(activityViewModel.shoeNameInput).isNullOrEmpty()) {
                    binding.shoeNameEt.error =
                        errorState.errors.get(activityViewModel.shoeNameInput).toString()
                }
                if (!errorState.errors.get(activityViewModel.shoeCompanyInput).isNullOrEmpty()) {
                    binding.shoeCompanyEt.error =
                        errorState.errors.get(activityViewModel.shoeSizeInput).toString()
                }
                if (!errorState.errors.get(activityViewModel.shoeSizeInput).isNullOrEmpty()) {
                    binding.shoeSizeEt.error =
                        errorState.errors.get(activityViewModel.shoeSizeInput).toString()
                }

            } else {
                binding.shoeNameEt.error = null
                binding.shoeCompanyEt.error = null
                binding.shoeSizeEt.error = null
            }
        })
        return binding.root
    }


}