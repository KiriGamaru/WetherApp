package com.example.wetherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wetherapp.R
import com.example.wetherapp.adapters.NotesAdapter
import com.example.wetherapp.databinding.FragmentNotesBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
data class Note(
    val text: String? = null
)

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    lateinit var adapter: NotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)

        val database = Firebase.database
        val myRef = database.getReference("notes")
        binding.bSave.setOnClickListener {
            myRef.child(myRef.push().key ?:
            "do something!").setValue(Note(binding.edNote.text.toString()))
        }
        onChangeListener(myRef)
        initRcView()

        return binding.root
    }

    private fun initRcView() = with(binding){
        adapter = NotesAdapter()
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
    }


    private fun onChangeListener(dRef: DatabaseReference){
        dRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Note>()
                for(s in snapshot.children){
                    val note = s.getValue(Note::class.java)
                    if(note != null)list.add(note)
                }
                adapter.submitList(list)
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