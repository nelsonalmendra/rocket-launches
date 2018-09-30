package com.nelsonalmendra.rocketlaunches.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nelsonalmendra.rocketlaunches.R
import com.nelsonalmendra.rocketlaunches.data.database.Rocket
import kotlinx.android.synthetic.main.fragment_rockets_list.*

class RocketsListFragment : Fragment() {

    private lateinit var rocketsViewModel: RocketsViewModel
    private lateinit var adapter: RocketListAdapter
    private lateinit var listener: IRocketListFragment

    companion object {
        fun newInstance(): RocketsListFragment {
            return RocketsListFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as IRocketListFragment
        rocketsViewModel = ViewModelProviders.of(this).get(RocketsViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rocketsViewModel.allRockets.observe(this, Observer { rockets ->
            rockets?.let { setRockets(it) }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_rockets_list, container,false)
        val activity = activity
        val recyclerView = view.findViewById(R.id.recyclerview) as RecyclerView
        adapter = RocketListAdapter(activity!!.applicationContext) { it -> listener.onRocketClick(it) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener { rocketsViewModel.refreshData() }

        showActiveSwitch.setOnCheckedChangeListener { _, checked ->
            if(checked)
                adapter.filterActive()
            else
                adapter.resetFilter()
        }
    }

    private fun setRockets(rockets: List<Rocket>) {
        adapter.setRockets(rockets)
        if(showActiveSwitch.isChecked)
            adapter.filterActive()
        swipeRefreshLayout.isRefreshing = false
    }

    interface IRocketListFragment {
        fun onRocketClick(rocket: Rocket)
    }
}