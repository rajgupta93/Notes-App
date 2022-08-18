package com.example.newapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.R
import com.example.newapp.databinding.ItemNotesBinding
import com.example.newapp.model.notes
import com.example.newapp.ui.fragment.HomeFragmentDirections
import java.util.zip.Inflater

class adapter(val requireContext: Context, var notesList: List<notes>) : RecyclerView.Adapter<adapter.notesViewHolder>() {

    class notesViewHolder(val binding : ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
       return notesViewHolder(
           ItemNotesBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           ))
    }

    fun filtering(newlist : List<notes>) {
    notesList = newlist
    notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data : notes = notesList[position]
        holder.binding.title.text =  notesList[position].title
        holder.binding.subtitle.text = notesList[position].subtitle

        when(notesList[position].priority) {
            1 -> {
                holder.binding.priorityBtn.setBackgroundResource(R.drawable.red_dot)
            }

            2 -> {
                holder.binding.priorityBtn.setBackgroundResource(R.drawable.yelllow_dot)
            }

            3 -> {
                holder.binding.priorityBtn.setBackgroundResource(R.drawable.green_dot)
            }

        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size;
    }
}