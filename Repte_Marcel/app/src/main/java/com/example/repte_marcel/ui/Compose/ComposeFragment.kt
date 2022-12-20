package Compose

import android.graphics.Color
import android.os.Bundle
import android.transition.Slide
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.example.repte_marcel.R
import com.example.repte_marcel.databinding.FragmentComposeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialContainerTransform
import util.themeColor


class ComposeFragment : Fragment() {


    private var _binding: FragmentComposeBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentComposeBinding.inflate(inflater, container, false)


        enterTransition = MaterialContainerTransform().apply {
            startView = requireActivity().findViewById(R.id.fab)
            endView = binding.packageCardView
            duration = 300
            scrimColor = Color.TRANSPARENT
            containerColor = requireContext().themeColor(com.google.android.material.R.attr.colorSurface)
            startContainerColor = requireContext().themeColor(com.google.android.material.R.attr.colorSecondary)
            endContainerColor = requireContext().themeColor(com.google.android.material.R.attr.colorSurface)
        }

        returnTransition = androidx.transition.Slide().apply {
            duration = 225
            addTarget(R.id.package_card_view)
        }





        return binding.root




    }

    override fun onStart() {
        super.onStart()


    }
}