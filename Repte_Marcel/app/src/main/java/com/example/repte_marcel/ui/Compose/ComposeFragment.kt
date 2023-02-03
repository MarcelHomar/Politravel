package com.example.repte_marcel.ui.Compose


import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.repte_marcel.R
import com.example.repte_marcel.databinding.FragmentComposeBinding
import com.example.repte_marcel.ui.Home.HomeViewModel
import com.google.android.material.transition.MaterialContainerTransform
import util.themeColor
import java.io.File


class ComposeFragment : Fragment() {


    private var _binding: FragmentComposeBinding? = null

    private val binding get() = _binding!!

    private val sharedViewModel : HomeViewModel by activityViewModels()

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
            this.startView!!.visibility = View.INVISIBLE
        }


        returnTransition = androidx.transition.Slide().apply {
            duration = 225
            addTarget(R.id.package_card_view)

        }

        binding.buttonNext.setOnClickListener(){
            sharedViewModel.addTravelPackage()
            findNavController().popBackStack()
        }

        val dialog = Dialog(requireActivity())

        binding.buttonSelectImage.setOnClickListener(){



        }

        binding.buttonNext.setOnClickListener(){

        }

        /*
        binding.buttonSelectImage.setOnClickListener(){
            dialog.setContentView(com.example.repte_marcel.R.layout.dialog_layout)
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.setCancelable(false)
            dialog.window!!.attributes.windowAnimations = R.style.animation

            okay_text = dialog.findViewById(R.id.okay_text)
            cancel_text = dialog.findViewById(R.id.cancel_text)

            okay_text.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
                Toast.makeText(this@MainActivity, "okay clicked", Toast.LENGTH_SHORT).show()
            })

            cancel_text.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
                Toast.makeText(this@MainActivity, "Cancel clicked", Toast.LENGTH_SHORT).show()
            })

            dialog.show()

        }
        */



        return binding.root

    }

    override fun onStart() {
        super.onStart()


    }

    private fun imageReader(root : File):ArrayList<File>{
        val a : ArrayList<File> ? = null
        val files = root.listFiles()
        for (i in 0..files.size){
            if (files[i].name.endsWith(".jpg")){
                a?.add(files[i])
            }
        }
        return a!!
    }


}