package com.example.postlist.repositories

import com.example.postlist.models.CommentList
import com.example.postlist.models.PostList
import com.example.postlist.services.PostApiInterface
import com.example.postlist.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postApiInterface: PostApiInterface
) : Repository {
    override suspend fun getPostList(): Resource<PostList> {
        return try {
            val response = postApiInterface.getPostList()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred getting Posts.", null)
            } else {
                Resource.error("An unknown error occurred getting Posts.", null)
            }
        } catch (e: Exception){
            Resource.error("Couldn't reach the server. Check your internet connection.", null)
        }
    }

    override suspend fun getCommentList(id: String): Resource<CommentList> {
        return try {
            val response = postApiInterface.getCommentList(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred getting Comments.", null)
            } else {
                Resource.error("An unknown error occurred getting Comments.", null)
            }
        } catch (e: Exception){
            Resource.error("Couldn't reach the server. Check your internet connection.", null)
        }
    }

}