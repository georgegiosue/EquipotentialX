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

    companion object {
        private const val REPOSITORY = "https://github.com/16george/EquipotentialX"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInfoBinding.bind(view)

        backData()
        toSourceCode()
    }

    private fun toSourceCode() {
        binding.btnCode.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse(REPOSITORY)).also {
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