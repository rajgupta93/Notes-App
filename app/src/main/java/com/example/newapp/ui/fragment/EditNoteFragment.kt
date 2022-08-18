package com.example.newapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newapp.R
import com.example.newapp.databinding.FragmentEditNoteBinding
import com.example.newapp.model.notes
import com.example.newapp.viewmodel.viewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditNoteFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentEditNoteBinding
    val viewmodel : viewModel by viewModels()
    val note by navArgs<EditNoteFragmentArgs>()
    var priority : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        binding.title.setText(note.data.title)
        binding.subtitle.setText(note.data.subtitle)
        binding.note.setText(note.data.notes)
         priority =  note.data.priority

        when(note.data.priority) {
            1 -> {
                binding.redBtn.setImageResource(R.drawable.ic_baseline_check_24)

            }
            2 -> {
                binding.yellowBtn.setImageResource(R.drawable.ic_baseline_check_24)
            }
            3 -> {
                binding.greenBtn.setImageResource(R.drawable.ic_baseline_check_24)
            }
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

        binding.updatenote.setOnClickListener {
            updateNotes(it)
        }



        return binding.root
    }

    fun closefragment(it: View){
        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)

    }

    fun updateNotes(it : View){
         note.data.title  = binding.title.text.toString()
        note.data.subtitle = binding.subtitle.text.toString()
        note.data.subtitle = binding.subtitle.text.toString()
        note.data.priority = priority
        viewmodel.updateNotes(note.data)
        Toast.makeText(requireContext(),"Notes updated Sucessfully", Toast.LENGTH_SHORT).show()
        requireActivity().onBackPressed()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {
            val bottomSheetDialog: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
            bottomSheetDialog.setContentView(R.layout.delete_dialog)
            val textViewYes = bottomSheetDialog.findViewById<TextView>(R.id.yes)
            val textViewNo = bottomSheetDialog.findViewById<TextView>(R.id.no)

            textViewYes?.setOnClickListener {

                bottomSheetDialog.dismiss()
                findNavController().popBackStack()
                viewmodel.deleteNotes(note.data.id!!)
            }

            textViewNo?.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.show()
        } else if (item.itemId == android.R.id.home) {
                      Log.e("back button pressed","Back")
            findNavController().popBackStack()
            }
            return super.onOptionsItemSelected(item)
        }

}