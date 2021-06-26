package com.nmrc.equipotentialx.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nmrc.equipotentialx.R
import com.nmrc.equipotentialx.databinding.FragmentInfoBinding


class InfoFragment : Fragment(R.layout.fragment_info) {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInfoBinding.bind(view)

        backData()
        toSourceCode()
    }

    private fun toSourceCode() {
        binding.btnCode.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/16george")).also {
                startActivity(it)
            }
        }
    }

    private fun backData() {
        binding.ibBack.setOnClickListener {
            findNavController().navigate(R.id.action_infoFragment_to_dataFragment)
        }
    }
}