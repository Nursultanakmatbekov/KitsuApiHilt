package com.example.data.network.remote.apiservices

import com.example.data.network.remote.dtos.createpost.CreatePostDto
import com.example.data.network.remote.dtos.post.PostResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface PostApiService {

    @GET("edge/posts")
    suspend fun getPosts(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("sort") sort: String = "createdAt"
    ): PostResponseDto

    @Headers("Content-Type: application/vnd.api+json")
    @POST("edge/posts")
    suspend fun createPost(
        @Body body: CreatePostDto?
    )
}