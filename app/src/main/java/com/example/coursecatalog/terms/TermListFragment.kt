package com.example.coursecatalog.terms


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        val application = requireNotNull(this.activity).application

        val dataSource = CatalogDatabase.getInstance(application).catalogDatabaseDao


        val binding: FragmentTermListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_term_list, container, false)

        val termListViewModel by lazy {
            getViewModel { TermListViewModel(dataSource, application)}
        }

        val adapter = TermAdapter(TermAdapter.TermListener { termId ->
            termListViewModel.onTermClicked(termId)
        })

        binding.termListViewModel = termListViewModel

        binding.termList.adapter = adapter

        binding.lifecycleOwner = this

        termListViewModel.terms.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            it?.let{
                adapter.submitList(it)
            }
        })

        termListViewModel.navigateToTermDetail.observe(viewLifecycleOwner, Observer{
            it?.let {
                this.findNavController().navigate(
                    // TODO: pass termid to next fragment
                    TermListFragmentDirections.actionTermListFragmentToTermDetailFragment())
                termListViewModel.onTermNavigated()
            }
        })


        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_term_list, container, false)
        return binding.root
    }


}
