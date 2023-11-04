package hr.fer.ruazosa.lecture4.notesapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import hr.fer.ruazosa.lecture4.notesapplication.databinding.FragmentNoteDetailsBinding
import java.util.Date


class NoteDetailsFragment : Fragment() {

    private var _binding: FragmentNoteDetailsBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: NotesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        val noteTitle: String? = arguments?.getString("noteTitle")
        val noteDescription: String? = arguments?.getString("noteDescription")

        if(noteTitle != null && noteDescription != null){
            binding.noteDetailsTitleEditTextId.setText(noteTitle.toString())
            binding.noteDetailsNoteDescriptionEditTextId.setText(noteDescription.toString())
        }

        val originalNote: Note? = sharedViewModel.getNote(arguments?.getInt("notePosition"))

        binding.saveNoteButtonId.setOnClickListener {
            if(originalNote != null){
                originalNote.noteTitle = binding.noteDetailsTitleEditTextId.text.toString()
                originalNote.noteDescription = binding.noteDetailsNoteDescriptionEditTextId.text.toString()
                originalNote.noteDate = Date()

                sharedViewModel.updateNote(originalNote, arguments?.getInt("notePosition"))
            }else {
                val note = Note(noteDate = Date(), noteTitle = binding.noteDetailsTitleEditTextId.text.toString(),
                noteDescription = binding.noteDetailsNoteDescriptionEditTextId.text.toString())

                sharedViewModel.saveNote(note)
            }

            val navigationController = findNavController()
            navigationController.popBackStack() // removes the top mos fragment on the stack and navigets back to the previous fragment
        }
        return view
    }
}