package com.nmrc.equipotentialx.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.FragmentElectricFieldBinding
import com.nmrc.equipotentialx.databinding.FragmentQ2Binding
import com.nmrc.equipotentialx.viewmodel.EquipotentialsViewModel

class ElectricFieldFragment : Fragment(R.layout.fragment_electric_field) {

    private var _binding: FragmentElectricFieldBinding? = null
    private val binding get() = _binding!!
    private val vm: EquipotentialsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentElectricFieldBinding.bind(view)

        backQ2()
    }

    private fun backQ2() {
        binding.ibBack.setOnClickListener {
            findNavController().navigate(R.id.action_electricFieldFragment_to_q2Fragment)
        }
    }
}