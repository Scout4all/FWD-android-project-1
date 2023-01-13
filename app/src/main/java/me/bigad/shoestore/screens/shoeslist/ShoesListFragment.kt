package me.bigad.shoestore.screens.shoeslist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentShoesListBinding

class ShoesListFragment : Fragment() {


    private lateinit var binding: FragmentShoesListBinding
    private lateinit var viewModel: ShoesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)

        viewModel = ViewModelProvider(this).get(ShoesListViewModel::class.java)

        binding.addShoeFab.setOnClickListener {
            it.findNavController()
                .navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailsFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.user_menu, menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)
    }

}