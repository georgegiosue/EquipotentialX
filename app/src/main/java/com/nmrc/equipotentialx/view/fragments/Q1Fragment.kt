package com.nmrc.equipotentialx.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.Slider
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.FragmentQ1Binding
import com.nmrc.equipotentialx.model.ECharge
import com.nmrc.equipotentialx.viewmodel.EquipotentialsViewModel

class Q1Fragment : Fragment(R.layout.fragment_q1) {

    private var _binding: FragmentQ1Binding? = null
    private val binding get() = _binding!!
    private val vm: EquipotentialsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQ1Binding.bind(view)

        toQ2()
        backData()
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

    private fun backData() {
        binding.ibBack.setOnClickListener {
            findNavController().navigate(R.id.action_q1Fragment_to_dataFragment)
        }
    }

    private fun toQ2() {
        binding.ibNextQ2.setOnClickListener {
            vm.addCharge(data())
            findNavController().navigate(R.id.action_q1Fragment_to_q2Fragment)
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