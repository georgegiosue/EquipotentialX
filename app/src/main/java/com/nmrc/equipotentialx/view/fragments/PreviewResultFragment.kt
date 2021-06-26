package com.nmrc.equipotentialx.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.FragmentPreviewResultBinding
import com.nmrc.equipotentialx.model.ECharge
import com.nmrc.equipotentialx.viewmodel.EquipotentialsViewModel

class PreviewResultFragment : Fragment(R.layout.fragment_preview_result) {

    private var _binding: FragmentPreviewResultBinding? = null
    private val binding get() = _binding!!
    private val vm: EquipotentialsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPreviewResultBinding.bind(view)

        backQ2()
        chargersListener()
        toElectricField()
        toElectricCurves()
    }

    private fun toElectricCurves() {
        binding.cvCampoElectrico.setOnClickListener {
            PreviewResultFragmentDirections.actionPreviewResultFragmentToARCoreActivity(arrayOf(
                vm.getChargers().value!![vm.getChargers().value!!.lastIndex-1],
                vm.getChargers().value!!.last()
            )).also { action ->
                findNavController().navigate(action)
            }
        }
    }

    private fun toElectricField() {
        binding.cvCurvasE.setOnClickListener {
            PreviewResultFragmentDirections.actionPreviewResultFragmentToARCoreActivity(arrayOf(
                vm.getChargers().value!![vm.getChargers().value!!.lastIndex-1],
                vm.getChargers().value!!.last()
            )).also { action ->
                findNavController().navigate(action)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun chargersListener() {
        vm.getChargers().observe(viewLifecycleOwner){ chargers ->
            with(binding) {
                tvQ2.text = "Q1 = ${chargers.last()}"
                tvQ1.text = "Q2 = ${chargers[(chargers.lastIndex)-1]}"
            }
        }
    }

    private fun backQ2() {
        binding.ibBack.setOnClickListener {
            findNavController().navigate(R.id.action_electricFieldFragment_to_q2Fragment)
        }
    }
}