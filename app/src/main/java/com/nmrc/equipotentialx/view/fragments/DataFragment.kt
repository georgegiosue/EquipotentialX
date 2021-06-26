package com.nmrc.equipotentialx.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.FragmentDataBinding


class DataFragment : Fragment(R.layout.fragment_data) {

    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDataBinding.bind(view)

        toQ1()
        toInfo()
    }

    private fun toInfo() {
        binding.ivInfo.setOnClickListener {
            findNavController().navigate(R.id.action_dataFragment_to_infoFragment)
        }
    }

    private fun toQ1() {
        binding.ibNextQ1.setOnClickListener {
            findNavController().navigate(R.id.action_dataFragment_to_q1Fragment)
        }
    }
}