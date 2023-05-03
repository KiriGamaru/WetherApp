package com.example.wetherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wetherapp.databinding.NotesListItemBinding
import com.example.wetherapp.fragments.Note

class NotesAdapter: ListAdapter<Note, NotesAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(private val binding: NotesListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: Note) = with(binding){
            tvNote.text = note.text
        }
        companion object{
            fun create(parent: ViewGroup): ItemHolder{
                return ItemHolder(NotesListItemBinding.inflate(LayoutInflater.
                from(parent.context), parent, false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

}