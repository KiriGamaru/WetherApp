package com.example.wetherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wetherapp.R
import com.example.wetherapp.databinding.FragmentNotesBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        val database = Firebase.database
        val myRef = database.getReference("message")
        binding.bSave.setOnClickListener{
            myRef.setValue(binding.edNote.text.toString())
        }
        onChangeListener(myRef)
        return binding.root
    }

    private fun onChangeListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.apply {
                    tvNotes.append("\n")
                    tvNotes.append(snapshot.value.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotesFragment()
    }
}