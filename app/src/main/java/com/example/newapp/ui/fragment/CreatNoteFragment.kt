package com.example.newapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.newapp.R
import com.example.newapp.databinding.FragmentBlankBinding
import com.example.newapp.model.notes
import com.example.newapp.viewmodel.viewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreatNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreatNoteFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var binding: FragmentBlankBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var priority: Int = 1
    val viewmodel : viewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding =  FragmentBlankBinding.inflate( layoutInflater,container, false)

        binding.createNotes.setOnClickListener {
            createNotes(it)

        }

        binding.redBtn.setOnClickListener {
            binding.redBtn.setImageResource(R.drawable.ic_baseline_check_24)
            binding.yellowBtn.setImageResource(0)
            binding.greenBtn.setImageResource(0)
            priority = 1

        }
        binding.yellowBtn.setOnClickListener {
            binding.yellowBtn.setImageResource(R.drawable.ic_baseline_check_24)
            binding.redBtn.setImageResource(0)
            binding.greenBtn.setImageResource(0)
            priority = 2

        }
        binding.greenBtn.setOnClickListener {
            binding.greenBtn.setImageResource(R.drawable.ic_baseline_check_24)
            binding.redBtn.setImageResource(0)
            binding.yellowBtn.setImageResource(0)
            priority = 3
        }


        return binding.root;

    }

    fun createNotes(it : View){
        var title  = binding.title.text.toString()
        var subtitle = binding.subtitle.text.toString()
        var notes = binding.subtitle.text.toString()
        var note = notes(null,title,subtitle,notes,priority)
        viewmodel.addNotes(note)
        requireActivity().onBackPressed()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreatNoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreatNoteFragment().apply {

            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

       if (item.itemId == android.R.id.home) {
            Log.e("back button pressed","Back")
            findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }


}