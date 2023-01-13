package me.bigad.shoestore.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentWelcomeBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)

//ToDo pass logged in data to view model factory with safe args

        //navigate to instructions screen
        binding.instructionsButton.setOnClickListener {
            it.findNavController()
                .navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment2())
        }

        return binding.root
    }

}