package com.angraankit.www.wattpad_challenge.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.angraankit.www.wattpad_challenge.R
import com.angraankit.www.wattpad_challenge.model.Story
import com.angraankit.www.wattpad_challenge.ui.adapter.StoriesListAdapter
import com.angraankit.www.wattpad_challenge.utill.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var storiesListAdapter: StoriesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView ()
        subscribeObservers ()
        viewModel.setStateEvent(MainStateEvent.GetStories)
    }

    private fun subscribeObservers () {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dateState ->
            when (dateState) {
                is DataState.Success<List<Story>> -> {
                    stopLoading ()
                    refreshList(dateState.data)
                    displaySuccess ()
                }

                is DataState.Error -> {
                    stopLoading ()
                }

                is DataState.Loading -> {
                    startLoading ()
                    dateState.data?.let {
                        refreshList (
                            list = it
                        )
                    }
                }
            }
        })
    }

    private fun startLoading () {
        loading_indicator.visibility = View.VISIBLE
    }

    private fun stopLoading () {
        loading_indicator.visibility = View.GONE
    }

    private fun displaySuccess () {
        val toast = Toast.makeText(activity, resources.getString(R.string.stories_saved_message), Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun setUpView () {
        loading_indicator.visibility = View.GONE
        storiesListAdapter = StoriesListAdapter()
        stories_recycler_view.layoutManager = LinearLayoutManager(activity)
        stories_recycler_view.setHasFixedSize(true)
        stories_recycler_view.adapter = storiesListAdapter
    }

    private fun refreshList (list : List<Story>) {
        storiesListAdapter.refreshList(list)
    }
}