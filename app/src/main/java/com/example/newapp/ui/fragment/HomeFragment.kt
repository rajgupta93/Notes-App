package com.example.newapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.GridLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newapp.R
import com.example.newapp.databinding.FragmentHomeBinding
import com.example.newapp.model.notes
import com.example.newapp.ui.adapter.adapter
import com.example.newapp.viewmodel.viewModel
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var  binding: FragmentHomeBinding
    lateinit var notelist : List<notes>
    lateinit var Adapter :adapter


    val viewmodel : viewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding =  FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.FAB.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_creatNoteFragment);
        }
        Toast.makeText(context,"title is ",Toast.LENGTH_SHORT).show()
        viewmodel.getNotes().observe(viewLifecycleOwner) { notesList ->

            notelist = notesList;

            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

            Adapter = adapter(requireContext(),notelist)
            binding.recyclerView.adapter = Adapter
            for (i in notesList) {
                Toast.makeText(context, "title is ${i.title}", Toast.LENGTH_SHORT).show()
                Log.e("title", "title is ${i.title}")
            }
        }

       binding.filterHigh.setOnClickListener {
           viewmodel.getHighPriorityNotes().observe(viewLifecycleOwner) { notesList ->
               notelist = notesList;


               Adapter = adapter(requireContext(),notelist)
               binding.recyclerView.adapter = Adapter
           }
       }

       binding.filterMedium.setOnClickListener {
           viewmodel.getMediumPriorityNotes().observe(viewLifecycleOwner) { notesList ->
               notelist = notesList;


               Adapter = adapter(requireContext(),notelist)
               binding.recyclerView.adapter = Adapter
           }
       }

       binding.filterLow.setOnClickListener {
           viewmodel.getLowPriorityNotes().observe(viewLifecycleOwner) { notesList ->

               notelist = notesList;


               Adapter = adapter(requireContext(),notelist)
               binding.recyclerView.adapter = Adapter
           }
       }

        binding.showAll.setOnClickListener {
            viewmodel.getNotes().observe(viewLifecycleOwner) { notesList ->

                notelist = notesList;


                Adapter = adapter(requireContext(),notelist)
                binding.recyclerView.adapter = Adapter
            }
        }
        return  binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

                }
            }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)


        val searchview = menu.findItem(R.id.search).actionView as SearchView
        searchview.queryHint = "Enter Notes here...."
        searchview.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                noteFiltering(p0!!);
                return true
                TODO("Not yet implemented")
            }


        }


        )
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun noteFiltering(text: String) {
        val notefiltering = arrayListOf<notes>()

        for (i in notelist) {
            if(i.title.contains(text,ignoreCase = true) || i.subtitle.contains(text,ignoreCase = true)) {
                notefiltering.add(i);
            }
            Adapter.filtering(notefiltering)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.search) {

        }
        return super.onOptionsItemSelected(item)
    }
}
