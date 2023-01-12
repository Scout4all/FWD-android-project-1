package me.bigad.shoestore.screens.shoeslist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentShoesListBinding
import me.bigad.shoestore.screens.login.LoginViewModel

class ShoesListFragment : Fragment() {

    companion object {
        fun newInstance() = ShoesListFragment()
    }
private lateinit var binding : FragmentShoesListBinding
    private lateinit var viewModel: ShoesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoes_list,container,false)

        viewModel = ViewModelProvider(this).get(ShoesListViewModel::class.java)


        return binding.root    }


}