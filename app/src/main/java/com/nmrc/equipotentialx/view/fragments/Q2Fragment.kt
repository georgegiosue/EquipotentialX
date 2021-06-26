package com.nmrc.equipotentialx.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.FragmentQ2Binding
import com.nmrc.equipotentialx.model.ECharge
import com.nmrc.equipotentialx.viewmodel.EquipotentialsViewModel

class Q2Fragment : Fragment(R.layout.fragment_q2) {

    private var _binding: FragmentQ2Binding? = null
    private val binding get() = _binding!!
    private val vm: EquipotentialsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQ2Binding.bind(view)

        toResultPreview()
        backQ1()
        slidersListener()
    }

    @SuppressLint("SetTextI18n")
    private fun slidersListener() {
        with(binding) {
            sliderCharger.addOnChangeListener { _, value, _ ->
                tvCharger.text = "${value.toInt()} nC"
            }
            sliderXPosition.addOnChangeListener { _, value, _ ->
                tvDx.text = "${value.toInt()}i"
            }
        }
    }

    private fun backQ1() {
        binding.ibBack.setOnClickListener {
            findNavController().navigate(R.id.action_q2Fragment_to_q1Fragment)
        }
    }

    private fun toResultPreview() {
        binding.ibFinalizeQ2.setOnClickListener {
            vm.addCharge(data())
            findNavController().navigate(R.id.action_q2Fragment_to_electricFieldFragment)
        }
    }

    private fun data(): ECharge {
        with(binding) {
            val value = sliderCharger.value.toInt()
            val dx  = sliderXPosition.value.toInt()
            return ECharge(value, dx)
        }
    }
}