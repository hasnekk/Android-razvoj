package hr.fer.ruazosa.lecture4.notesapplication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

class NotesViewModel : ViewModel() {
    var listOfNotes = MutableLiveData<MutableList<Note>>()
    fun saveNote(note: Note) {
        NoteRepository.notes.add(note)
        listOfNotes.value = NoteRepository.notes
    }

    fun getNote(noteAtIndex: Int?): Note? {
        if (noteAtIndex == null) return null
        return NoteRepository.notes[noteAtIndex]
    }

    fun getNoteCount(): Int {
        return NoteRepository.notes.size
    }

    fun deleteNote(note: Note) {
        NoteRepository.notes.remove(note);
        listOfNotes.value = NoteRepository.notes
    }

    fun updateNote(note: Note, position: Int?) {
        if (position != null) {
            NoteRepository.notes[position] = note
            listOfNotes.value = NoteRepository.notes
        }
    }
}