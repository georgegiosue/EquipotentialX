package com.nmrc.equipotentialx.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.FragmentQ1Binding
import com.nmrc.equipotentialx.databinding.FragmentQ2Binding
import com.nmrc.equipotentialx.viewmodel.EquipotentialsViewModel

class Q2Fragment : Fragment(R.layout.fragment_q2) {

    private var _binding: FragmentQ2Binding? = null
    private val binding get() = _binding!!
    private val vm: EquipotentialsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQ2Binding.bind(view)

        toResultPreview()
        backQ1()
    }

    private fun backQ1() {
        binding.ibBack.setOnClickListener {
            findNavController().navigate(R.id.action_q2Fragment_to_q1Fragment)
        }
    }

    private fun toResultPreview() {
        binding.ibFinalizeQ2.setOnClickListener {
            findNavController().navigate(R.id.action_q2Fragment_to_electricFieldFragment)
        }
    }
}