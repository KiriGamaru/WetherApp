package com.example.wetherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wetherapp.R
import com.example.wetherapp.databinding.FragmentNotesBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    val database = Firebase.database
    val myRef = database.getReference("message")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotesFragment()
    }
}