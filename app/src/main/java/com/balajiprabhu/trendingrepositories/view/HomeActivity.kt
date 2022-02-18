package com.balajiprabhu.trendingrepositories.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.balajiprabhu.trendingrepositories.R
import com.balajiprabhu.trendingrepositories.databinding.ActivityHomeBinding
import com.balajiprabhu.trendingrepositories.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity(), LifecycleOwner, SearchView.OnQueryTextListener {

    lateinit var homeViewModel: HomeViewModel

    private val repositoryAdapter = RepositoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityHomeBinding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
        lifecycle.addObserver(homeViewModel)
        setContentView(binding.root)

        binding.repositoriesRecyclerview.adapter = repositoryAdapter

        homeViewModel.githubSearchModelListLiveData.observe(this, Observer {
            repositoryAdapter.setRepoListData(it)
        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        val search = menu?.findItem(R.id.action_search)
        val searchView = search?.actionView as? SearchView

        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun searchDatabase(query: String?){
        val searchQuery = "$query"
    }
}