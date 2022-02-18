package com.balajiprabhu.trendingrepositories.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.balajiprabhu.trendingrepositories.model.data.GithubSearchModel
import com.balajiprabhu.trendingrepositories.model.data.GithubSearchResponse
import com.balajiprabhu.trendingrepositories.model.network.NetworkProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(),DefaultLifecycleObserver {

    val isLoading = ObservableBoolean(false)
    val githubSearchModelListLiveData = MutableLiveData<List<GithubSearchModel>>()

    private val networkProvider = NetworkProvider

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        getRepoModel()
    }

    fun onRefresh() {
        isLoading.set(true)
        getRepoModel()
    }
    private fun getRepoModel() {

        CoroutineScope(Dispatchers.IO).launch {
            val response = networkProvider.providesAppClient().getGithubSearchResponse("q")
            println(response)
            response.body()?.let {
                handleRepoModel(it)
            }
        }
    }


    private fun handleRepoModel(githubSearchResponse: GithubSearchResponse) {

        val githubSearchModelList : List<GithubSearchModel> = githubSearchResponse.items.map {
            GithubSearchModel(
                userName = it.owner?.login.safeString(),
                repoName = it.name.safeString(),
                repoDescription = it.description.safeString(),
                language = it.language.safeString(),
                starCount = it.stargazersCount.safeInt().toString(),
                avatar = it.owner?.avatarUrl.safeString()
            )
        }.toList()

        githubSearchModelListLiveData.postValue(githubSearchModelList)
        isLoading.set(false)
    }


    private fun String?.safeString() : String {
        return this ?: ""
    }

    private fun Int?.safeInt() : Int {
        return this ?: 0
    }

}