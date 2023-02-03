package com.example.repte_marcel.ui.Home


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repte_marcel.R
import com.example.repte_marcel.data.TravelPackage
import com.example.repte_marcel.databinding.FragmentFirstBinding
import com.example.repte_marcel.ui.PackageAdapter
import com.example.repte_marcel.util.JsonUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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

        lifecycleScope.launch(){
            val list = JsonUtil.getTravelPackages(requireContext())
            sharedViewModel.initTravelPackages(list)
        }

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }


        binding.listPackages.apply {
            adapter = packageAdapter
        }

        binding.listPackages.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.listPackages.adapter = packageAdapter

        sharedViewModel.travelPackageListMutable.observe(viewLifecycleOwner){
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

        val activity = requireActivity() as MainActivity
        activity.setActionBarTitle(travelPackage.title)


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


        val fab = activity.findViewById<FloatingActionButton>(R.id.fab)

        fab.visibility = View.INVISIBLE




    }

    override fun onPackageLongPressed(travelPackage: TravelPackage): Boolean {
        //TODO: Delete this package from the list



        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Title")
            .setMessage("Your message goes here. Keep it short but clear.")
            .setPositiveButton(
                "EDIT"
            ) { _, _ ->

            }
            .setNeutralButton(
                "DELETE"
            ) { _, _ ->

            }
            .show()

        return true
    }

    override fun onResume() {
        super.onResume()
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        if (fab.visibility == View.INVISIBLE){
            fab.visibility = View.VISIBLE
        }

    }
}