package com.balajiprabhu.trendingrepositories.model.network

import com.balajiprabhu.trendingrepositories.model.data.GithubSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubSearchRepositoryService {

    @GET(value = "search/repositories")
    suspend fun getGithubSearchResponse(
        @Query("q") q: String
    ) : Response<GithubSearchResponse>
}