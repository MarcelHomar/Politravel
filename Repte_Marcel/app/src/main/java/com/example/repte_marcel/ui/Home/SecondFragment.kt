package com.example.repte_marcel.ui.Home

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.repte_marcel.R
import com.example.repte_marcel.databinding.FragmentSecondBinding
import com.google.android.material.transition.MaterialContainerTransform
import util.loadImageFromFilesDir
import kotlin.LazyThreadSafetyMode.NONE
import util.themeColor

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val args : SecondFragmentArgs by navArgs()
    private val travelPackageId : Long by lazy(NONE) { args.packageId }

    private val sharedViewModel : HomeViewModel by activityViewModels()

    private var _binding: FragmentSecondBinding? = null

    object PackageConstants {
        const val travelPackage = "PACKAGE"
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getSerializable(PackageConstants.travelPackage)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.nav_host_fragment_content_main
            duration = 300.toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(Color.parseColor("#FFFBFE"))
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val travelPackage = sharedViewModel.getFromId(travelPackageId)
        if (travelPackage == null) {
            showError()
            return
        }
        binding.run {
            binding.travelPackage = travelPackage

//
//            val path = requireContext().filesDir.absolutePath.toString() + "/img/" + travelPackage.image + ".jpg"
//            val bitmap = BitmapFactory.decodeFile(path)
//            binding.imageView.setImageBitmap(bitmap)

        }

//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showError(){
        //
    }
}