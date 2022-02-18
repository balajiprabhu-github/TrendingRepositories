package com.balajiprabhu.trendingrepositories.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.balajiprabhu.trendingrepositories.databinding.ItemRepositoryBinding
import com.balajiprabhu.trendingrepositories.model.data.GithubSearchModel

class RepositoryAdapter : RecyclerView.Adapter<CommonViewHolder>(){

    private val githubSearchModelList = mutableListOf<GithubSearchModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemCityWeatherBinding = ItemRepositoryBinding.inflate(layoutInflater, parent,false)
        return CommonViewHolder(itemCityWeatherBinding)
    }

    override fun getItemCount(): Int {
        return githubSearchModelList.size
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) = holder.bind(githubSearchModelList[position])

    fun setRepoListData(weatherList:List<GithubSearchModel>){
        githubSearchModelList.clear()
        githubSearchModelList.addAll(weatherList)
        notifyDataSetChanged()
    }

}