package com.example.repte_marcel.ui.Home

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repte_marcel.ui.PackageAdapter
import com.example.repte_marcel.R
import com.example.repte_marcel.data.TravelPackage
import com.example.repte_marcel.databinding.FragmentFirstBinding
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), PackageAdapter.PackageAdapterListener {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val sharedViewModel : HomeViewModel by activityViewModels()

    private val packageAdapter = PackageAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment_content_main
            duration = 5000
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.parseColor("#FFFBFE"))

        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }


        binding.listPackages.apply {
            adapter = packageAdapter
        }

        binding.listPackages.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.listPackages.adapter = packageAdapter

        sharedViewModel.TravelPackageListMutable.observe(viewLifecycleOwner){
            packageAdapter.submitList(it)
        }


//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPackageClicked(cardView: View, travelPackage: TravelPackage) {


        exitTransition = MaterialElevationScale(false).apply {
            duration = 300
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = 300
        }



        val transitionName = getString(R.string.package_card_detail_transition_name)
        val extras = FragmentNavigatorExtras(cardView to transitionName)

        val directions = FirstFragmentDirections.actionFirstFragmentToSecondFragment(travelPackage.id)
        findNavController().navigate(directions, extras)




    }

    override fun onPackageLongPressed(travelPackage: TravelPackage): Boolean {
        //TODO: Delete this package from the list
        return true
    }
}