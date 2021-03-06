package com.example.coursecatalog.terms


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR.term
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.coursecatalog.R
import com.example.coursecatalog.database.CatalogDatabase
import com.example.coursecatalog.util.getViewModel
import com.example.coursecatalog.databinding.FragmentTermListBinding


class TermListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // getTerm a reference to the application
        val application = requireNotNull(this.activity).application

        // getTerm the catalog database DAO to access the database
        val dataSource = CatalogDatabase.getInstance(application).catalogDatabaseDao

        // inflates the binding for the fragment
        val binding: FragmentTermListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_term_list, container, false)

        // gets the viewmodel object for this fragment
        val termListViewModel by lazy {
            getViewModel { TermListViewModel(dataSource, application)}
        }

        /**
         * gets the adapter for the recyclerview to use to render
         * the list items
         */
        val adapter = TermAdapter(TermAdapter.TermListener { termId ->
            termListViewModel.onTermClicked(termId)
        })

        // bind viewmodel to fragment
        binding.termListViewModel = termListViewModel

        // bind adapter to recyclerview
        binding.termList.adapter = adapter

        // bind lifecyclerowner to fragment
        binding.lifecycleOwner = this

        // observe term table for changes so recyclerview updates
        termListViewModel.terms.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })

        // observe for user clicking on a term to go to the detail view
        termListViewModel.navigateToTermDetail.observe(viewLifecycleOwner, Observer{
            it?.let {
                this.findNavController().navigate(
                    TermListFragmentDirections.actionTermListFragmentToTermDetailFragment(it))
                termListViewModel.onTermNavigated()
            }
        })

        // observe for user backing out to main menu
        termListViewModel.navigateToMainMenu.observe(viewLifecycleOwner, Observer{
            it?.let {
                this.findNavController().navigate(
                    TermListFragmentDirections.actionTermListFragmentToMainMenuFragment())
                termListViewModel.onTermNavigated()
            }
        })

        /**
         * makes sure that when the user hits the back button
         * it navigates them back to the term list
         */
        super.onCreate(savedInstanceState)
        // This callback will only be called when MyFragment is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            termListViewModel.onNavigateToMainMenu()
            termListViewModel.onMainMenuNavigated()
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


        // Inflate the layout for this fragment
        return binding.root
    }


}
