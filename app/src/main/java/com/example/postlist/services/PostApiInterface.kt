package com.example.postlist.services

import com.example.postlist.models.CommentList
import com.example.postlist.models.PostList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiInterface {

    @GET("posts")
    suspend fun getPostList(): Response<PostList>

    @GET("posts/{post_id}/comments")
    suspend fun getCommentList(@Path("post_id") id: String) : Response<CommentList>

}