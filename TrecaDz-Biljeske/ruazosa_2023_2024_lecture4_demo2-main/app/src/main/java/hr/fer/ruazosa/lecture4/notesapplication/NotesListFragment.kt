package hr.fer.ruazosa.lecture4.notesapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.ruazosa.lecture4.notesapplication.databinding.FragmentNotesListBinding

class NotesListFragment : Fragment() {
    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: NotesViewModel by activityViewModels() // retrieves a ViewModel associted with the hosting activity (Main Acitvity)

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( // define layout and UI for the fragment
        inflater: LayoutInflater, // inflating the layourt into a tree like structure and creating XML View objects
        container: ViewGroup?, // parent view into wich the fragment will be placed
        savedInstanceState: Bundle? // hold saved data that can be used to restore the framents previous state in case of rotating screnn...
    ): View? {
        // (how to inflate, parent viewgroup where the inflated layout has to attached, should the layourt be attached to the container)
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        val view = binding.root

        navController = findNavController()

        /*
        * represents the current state and environment of an application.
        * It provides access to various resources, services, and system information
        * */
        binding.notesListRecyclerViewId.layoutManager = LinearLayoutManager(context) // what kind of layout should the ReclyceView use (LinearLayoutMenager => vertical list), context of the current activity or fragment
        val adapter = NotesAdapter(sharedViewModel, navController) // adapter for the RecycleView, connects the RecylceView witht the ViewModel and preps the data for it

        // settings for a decorator
        val decorator = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)

        binding.notesListRecyclerViewId.addItemDecoration(decorator)
        binding.notesListRecyclerViewId.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_notesListFragment_to_noteDetailsFragment)
        }
        return view
    }
}