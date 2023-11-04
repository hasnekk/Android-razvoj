package hr.fer.ruazosa.lecture4.notesapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.lecture4.notesapplication.databinding.NoteCellBinding

/*
* Adapter class for the ReclyerView
* Responsible for providing data to the RecyclerView and creating inidvidiual views for the list
*
* Connects the ViewModel to the RecycleView and prepares the data for the ReclycleView
* */

// RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() => NotesViewHolder is a nested class inside NotesAdapter
// it defines how data inside the RecycelView list will look like
class NotesAdapter(notesViewModel: NotesViewModel, navController: NavController): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    var notesViewModel = notesViewModel
    var navController = navController
    var _binding: NoteCellBinding? = null
    private val binding get() = _binding!!

    class NotesViewHolder(noteView: View): RecyclerView.ViewHolder(noteView) {
        var noteDateTextView: TextView = noteView.findViewById(R.id.noteDateTextViewId)
        var noteTitleTextView: TextView = noteView.findViewById(R.id.noteTitleTextViewId)
        var noteDeletebtn: Button = noteView.findViewById(R.id.deleteNoteBtn)
    }

    // creates a view for the RecycleView list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        _binding = NoteCellBinding.inflate(inflater) // Layout for the individual view int the list
        val notesView = binding.root
        return NotesViewHolder(notesView)
    }

    // binds the data fromt the ViewModel to the ViewHolder (NotesViewHolder)
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesViewModel.getNote(position)
        holder.noteDateTextView.text = note!!.noteDate.toString()
        holder.noteTitleTextView.text = note!!.noteTitle.toString()

        holder.noteDeletebtn.setOnClickListener{
            notesViewModel.deleteNote(note!!)
            notifyItemRemoved(position) // Notify the adapter that an item has been removed
        }

        // A property to access the item inside the list (RecycleView)
        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("noteTitle", note.noteTitle.toString())
            bundle.putString("noteDescription", note.noteDescription.toString())
            bundle.putInt("notePosition", position)
            navController.navigate(R.id.action_notesListFragment_to_noteDetailsFragment, bundle)
            notifyItemChanged(position)
        }
    }
    override fun getItemCount(): Int {
        return notesViewModel.getNoteCount()
    }
}