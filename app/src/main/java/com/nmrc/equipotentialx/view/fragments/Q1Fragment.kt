package com.nmrc.equipotentialx.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.FragmentDataBinding
import com.nmrc.equipotentialx.databinding.FragmentQ1Binding
import com.nmrc.equipotentialx.viewmodel.EquipotentialsViewModel

class Q1Fragment : Fragment(R.layout.fragment_q1) {

    private var _binding: FragmentQ1Binding? = null
    private val binding get() = _binding!!
    private val vm: EquipotentialsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQ1Binding.bind(view)

        toQ2()
        backData()
    }

    private fun backData() {
        binding.ibBack.setOnClickListener {
            findNavController().navigate(R.id.action_q1Fragment_to_dataFragment)
        }
    }

    private fun toQ2() {
        binding.ibNextQ2.setOnClickListener {
            findNavController().navigate(R.id.action_q1Fragment_to_q2Fragment)
        }
    }
}