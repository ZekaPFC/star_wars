package com.marko.starwars.ui.residents_list_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko.starwars.R
import com.marko.starwars.data.resident.Resident
import com.marko.starwars.di.component.FragmentComponent
import com.marko.starwars.di.scope.FragmentScope
import com.marko.starwars.ui.MainActivity
import com.marko.starwars.ui.utils.SizeUtil
import kotlinx.android.synthetic.main.resident_list_fragment.*
import javax.inject.Inject

@FragmentScope
class ResidentListFragment : Fragment() {
    private lateinit var fragmentComponent: FragmentComponent

    @Inject
    lateinit var adapter: ResidentAdapter
    @Inject
    lateinit var residentListViewModel: ResidentListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent = (context as MainActivity).appComponent.fragmentComponent().create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).showAppBar()
        (activity as MainActivity).appBarTitle(getString(R.string.resident_list_title))
        fragmentComponent.inject(this)
        return inflater.inflate(R.layout.resident_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeResidentData()
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = adapter
    }

    private fun observeResidentData() {
        residentListViewModel.residentsLiveData.observe(
            viewLifecycleOwner,
            Observer<List<Resident>> {
                adapter.addItems(it, true)
            }
        )
    }
}