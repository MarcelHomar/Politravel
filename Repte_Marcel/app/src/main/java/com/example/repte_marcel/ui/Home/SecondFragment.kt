package com.example.repte_marcel.ui.Home

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repte_marcel.R
import com.example.repte_marcel.data.TravelPackage
import com.example.repte_marcel.databinding.FragmentSecondBinding
import com.example.repte_marcel.ui.CustomGridLayoutManager
import com.example.repte_marcel.ui.ItineraryAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

    private lateinit var travelPackage: TravelPackage

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


        travelPackage = sharedViewModel.getFromId(travelPackageId)!!
        val travelPackageBinding = travelPackage
        if (travelPackage == null) {
            showError()
            return
        }
        binding.run {

            binding.travelPackage = travelPackageBinding
            binding.travelPackageLastIndex = travelPackageBinding.itinerary.size - 1



//
//            val path = requireContext().filesDir.absolutePath.toString() + "/img/" + travelPackage.image + ".jpg"
//            val bitmap = BitmapFactory.decodeFile(path)
//            binding.imageView.setImageBitmap(bitmap)

        }



        val adapter : ItineraryAdapter = ItineraryAdapter(requireContext(), travelPackage.itinerary)
        val layoutManager = CustomGridLayoutManager(requireContext())
        layoutManager.setScrollEnabled(false)
        binding.ListViewItinerary.layoutManager = layoutManager

        binding.ListViewItinerary.adapter = adapter


        val mapFragment = childFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showError(){
        //
    }

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val location = LatLng(travelPackage.latitude.toDouble(), travelPackage.longitude.toDouble())
        googleMap.addMarker(MarkerOptions().position(location).title(travelPackage.title))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, travelPackage.zoom.toFloat()))
//        val sydney = LatLng(-34.0, 151.0)
//        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 11f))
    }
}