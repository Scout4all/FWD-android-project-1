package me.bigad.shoestore.screens.shoeslist

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import me.bigad.shoestore.MainActivityViewModel
import me.bigad.shoestore.R
import me.bigad.shoestore.databinding.FragmentShoesListBinding
import me.bigad.shoestore.model.Shoe
import me.bigad.shoestore.model.UserSessionModel


class ShoesListFragment : Fragment() {


    private lateinit var binding: FragmentShoesListBinding
    private lateinit var viewModel: ShoesListViewModel

    lateinit var deleteButton: MaterialButton

    //load shared view model
    val activityViewModel: MainActivityViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)

        viewModel = ViewModelProvider(this).get(ShoesListViewModel::class.java)

        binding.addShoeFab.setOnClickListener {
            it.findNavController()
                .navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailsFragment())
        }
        setHasOptionsMenu(true)

        //observe shared activity view model to create views
        activityViewModel.shoesList.observe(viewLifecycleOwner, Observer { shoes ->
            if (shoes.size == 0 || shoes == null) {
                binding.notShoesText.visibility = View.VISIBLE
            } else {
                binding.notShoesText.visibility = View.GONE
                binding.shoesListHolder.removeAllViews()
                for (shoe in shoes) {
                    val containerCardView = createViews(shoe)
                    binding.shoesListHolder.addView(containerCardView)
                    deleteButton.setOnClickListener {
                        activityViewModel.onDelete(shoe)
                        binding.shoesListHolder.removeView(containerCardView)
                    }
                }

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
            R.id.menu_logout -> {
                Toast.makeText(
                    context,
                    "see you again ${UserSessionModel.getInstance().loggedUser}",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun createViews(shoe: Shoe): View {


        val typeface = ResourcesCompat.getFont(requireContext(), R.font.mitr)

        val params = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(16, 16, 16, 16)

        val leftViewsParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)

        val buttonParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val matchParentParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        val imageParams = LinearLayout.LayoutParams(300, 300)

        val cardView = CardView(requireContext())
        cardView.radius = 15f
        cardView.setContentPadding(16, 16, 16, 16)
        cardView.layoutParams = params
        cardView.cardElevation = 8f

        val constraintContainer = ConstraintLayout(requireContext())
        constraintContainer.id = View.generateViewId()
        val set = ConstraintSet()
        constraintContainer.layoutParams = matchParentParams




        deleteButton = MaterialButton(requireContext())
        deleteButton.id = View.generateViewId()
        deleteButton.text = "x"
        deleteButton.layoutParams = buttonParams
        deleteButton.setBackgroundColor(resources.getColor(R.color.color_red))

        constraintContainer.addView(deleteButton)

        set.clone(constraintContainer)
        set.connect(
            deleteButton.id,
            ConstraintSet.TOP,
            constraintContainer.id,
            ConstraintSet.TOP,
            8
        )

        set.connect(
            deleteButton.id,
            ConstraintSet.START,
            constraintContainer.id,
            ConstraintSet.START,
            8
        )
        set.applyTo(constraintContainer)

        val shoeImage = ImageView(requireContext())
        shoeImage.id = View.generateViewId()
        shoeImage.layoutParams = imageParams
        shoeImage.setImageResource(R.drawable.shoes_icon)
        constraintContainer.addView(shoeImage)
        set.clone(constraintContainer)
        set.connect(
            shoeImage.id, ConstraintSet.TOP, deleteButton.id, ConstraintSet.BOTTOM, 8
        )
        set.connect(
            shoeImage.id,
            ConstraintSet.START,
            constraintContainer.id,
            ConstraintSet.START,
            8
        )
        set.applyTo(constraintContainer)


        val shoeCompanyTv = TextView(requireContext())
        shoeCompanyTv.id = View.generateViewId()
        shoeCompanyTv.layoutParams = leftViewsParams
        shoeCompanyTv.typeface = typeface
        shoeCompanyTv.textSize = 16f
        shoeCompanyTv.setBackgroundColor(resources.getColor(R.color.color_primary_dark))
        shoeCompanyTv.setTextColor(resources.getColor(R.color.white))
        shoeCompanyTv.setPadding(20)
        shoeCompanyTv.text = shoe.company
        constraintContainer.addView(shoeCompanyTv)
        set.clone(constraintContainer)
        set.connect(
            shoeCompanyTv.id,
            ConstraintSet.TOP,
            constraintContainer.id,
            ConstraintSet.TOP,
            8
        )
        set.connect(
            shoeCompanyTv.id,
            ConstraintSet.END,
            constraintContainer.id,
            ConstraintSet.END,
            8
        )
        set.connect(
            shoeCompanyTv.id,
            ConstraintSet.BOTTOM,
            deleteButton.id,
            ConstraintSet.BOTTOM

        )
        set.applyTo(constraintContainer)


        val shoeNameTv = TextView(requireContext())
        shoeNameTv.id = View.generateViewId()
        shoeNameTv.typeface = typeface
        shoeNameTv.textSize = 18f
        shoeNameTv.layoutParams = leftViewsParams
        shoeNameTv.text = shoe.name
        constraintContainer.addView(shoeNameTv)
        set.clone(constraintContainer)
        set.connect(
            shoeNameTv.id, ConstraintSet.TOP, shoeImage.id, ConstraintSet.TOP, 8
        )
        set.connect(
            shoeNameTv.id, ConstraintSet.END, constraintContainer.id, ConstraintSet.END, 8
        )
        set.connect(
            shoeNameTv.id, ConstraintSet.START, shoeImage.id, ConstraintSet.END, 8
        )
        set.applyTo(constraintContainer)


        val shoeSizeTv = TextView(requireContext())
        shoeSizeTv.id = View.generateViewId()
        shoeSizeTv.layoutParams = leftViewsParams
        shoeSizeTv.setPadding(16)
        shoeSizeTv.text = "Sizes: ${shoe.size}"
        constraintContainer.addView(shoeSizeTv)
        set.clone(constraintContainer)
        set.connect(
            shoeSizeTv.id, ConstraintSet.TOP, shoeNameTv.id, ConstraintSet.BOTTOM, 8
        )
        set.connect(
            shoeSizeTv.id, ConstraintSet.START, shoeImage.id, ConstraintSet.END, 8
        )
        set.connect(
            shoeSizeTv.id, ConstraintSet.END, constraintContainer.id, ConstraintSet.END, 8
        )
        set.connect(
            shoeSizeTv.id, ConstraintSet.BOTTOM, shoeImage.id, ConstraintSet.BOTTOM, 8
        )
        set.applyTo(constraintContainer)


        val shoeDescriptionTv = TextView(requireContext())
        shoeDescriptionTv.id = View.generateViewId()
        shoeDescriptionTv.layoutParams = leftViewsParams
        shoeDescriptionTv.text = shoe.description
        constraintContainer.addView(shoeDescriptionTv)
        set.clone(constraintContainer)
        set.connect(
            shoeDescriptionTv.id,
            ConstraintSet.TOP,
            shoeImage.id,
            ConstraintSet.BOTTOM,
            16
        )

        set.connect(
            shoeDescriptionTv.id,
            ConstraintSet.START,
            constraintContainer.id,
            ConstraintSet.START,
            8
        )
        set.applyTo(constraintContainer)

        cardView.addView(constraintContainer)

        return cardView
    }


}


