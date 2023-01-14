package me.bigad.shoestore.screens.shoeslist

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentShoesListBinding


class ShoesListFragment : Fragment() {


    private lateinit var binding: FragmentShoesListBinding
    private lateinit var viewModel: ShoesListViewModel
    private lateinit var mContext : Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)

        viewModel = ViewModelProvider(this).get(ShoesListViewModel::class.java)
        mContext = requireContext()
        binding.addShoeFab.setOnClickListener {
            it.findNavController()
                .navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailsFragment())
        }
        setHasOptionsMenu(true)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoes->
            for(shoe in shoes){
                binding.shoesListHolder.addView(createViews(shoe.name,shoe.company,shoe.size.toString(),shoe.description))

            }
        })


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

fun createViews(shoeName : String , shoeCompany : String , shoeSize:String,shoeDescription : String) : View{
    val params = RelativeLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)
    params.setMargins(16,16,16,16)


    val cardView = CardView(requireContext())
    cardView.radius = 15f
    cardView.setContentPadding(36,36,36,36)
    cardView.layoutParams = params
    cardView.cardElevation = 8f

    val constraintContainer = ConstraintLayout(requireContext())
    constraintContainer.id = View.generateViewId()
    val set = ConstraintSet()

    val width = 120
    val height = 120
    val imageParams = LinearLayout.LayoutParams(width, height)


    val shoeImage = ImageView(requireContext())
shoeImage.id = View.generateViewId()
    shoeImage.setLayoutParams(imageParams)
    shoeImage.setImageResource(R.drawable.shoes_icon)
    constraintContainer.addView(shoeImage,0)
    set.clone(constraintContainer);
    set.connect(shoeImage.getId(), ConstraintSet.TOP, constraintContainer.getId(), ConstraintSet.TOP, 8)
    set.connect(shoeImage.getId(), ConstraintSet.END, constraintContainer.getId(), ConstraintSet.END, 8)
    set.connect(shoeImage.getId(), ConstraintSet.START, constraintContainer.getId(), ConstraintSet.START, 8)
    set.applyTo(constraintContainer)

    val shoeNameTv = TextView(requireContext())
    shoeNameTv.id=View.generateViewId()
    shoeNameTv.setText(shoeName)
    constraintContainer.addView(shoeNameTv,1)
    set.clone(constraintContainer)
    set.connect(shoeNameTv.getId(), ConstraintSet.TOP, shoeImage.getId(), ConstraintSet.BOTTOM, 8)
    set.connect(shoeNameTv.getId(), ConstraintSet.END, constraintContainer.getId(), ConstraintSet.END, 8)
    set.connect(shoeNameTv.getId(), ConstraintSet.START, constraintContainer.getId(), ConstraintSet.START, 8)
    set.applyTo(constraintContainer)

    val shoeCompanyTv = TextView(requireContext())
    shoeCompanyTv.id=View.generateViewId()
    shoeCompanyTv.setText(shoeCompany)
    constraintContainer.addView(shoeCompanyTv,2)
    set.clone(constraintContainer)
    set.connect(shoeCompanyTv.getId(), ConstraintSet.TOP, shoeNameTv.getId(), ConstraintSet.BOTTOM, 8)
    set.connect(shoeCompanyTv.getId(), ConstraintSet.END, constraintContainer.getId(), ConstraintSet.END, 8)
    set.connect(shoeCompanyTv.getId(), ConstraintSet.START, constraintContainer.getId(), ConstraintSet.START, 8)
    set.applyTo(constraintContainer)

    val shoeDescriptionTv = TextView(requireContext())
    shoeDescriptionTv.id=View.generateViewId()
    shoeDescriptionTv.setText(shoeDescription)
    constraintContainer.addView(shoeDescriptionTv,3)
    set.clone(constraintContainer)
    set.connect(shoeDescriptionTv.getId(), ConstraintSet.TOP, shoeCompanyTv.getId(), ConstraintSet.BOTTOM, 8)
    set.connect(shoeDescriptionTv.getId(), ConstraintSet.END, constraintContainer.getId(), ConstraintSet.END, 8)
    set.connect(shoeDescriptionTv.getId(), ConstraintSet.START, constraintContainer.getId(), ConstraintSet.START, 8)
    set.applyTo(constraintContainer)

    val shoeSizeTv = TextView(requireContext())
    shoeSizeTv.id=View.generateViewId()
    shoeSizeTv.setText(shoeSize)
    constraintContainer.addView(shoeSizeTv,4)
    set.clone(constraintContainer)
    set.connect(shoeSizeTv.getId(), ConstraintSet.TOP, shoeImage.getId(), ConstraintSet.BOTTOM, 8)
    set.connect(shoeSizeTv.getId(), ConstraintSet.START, shoeNameTv.getId(), ConstraintSet.END, 8)
    set.connect(shoeSizeTv.getId(), ConstraintSet.END, constraintContainer.getId(), ConstraintSet.END, 8)
    set.applyTo(constraintContainer)
    cardView.addView(constraintContainer)
    return cardView
}

}