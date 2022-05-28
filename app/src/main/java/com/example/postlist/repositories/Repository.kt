package com.example.postlist.repositories

import com.example.postlist.models.CommentList
import com.example.postlist.models.PostList
import com.example.postlist.utils.Resource

interface Repository {

    suspend fun getPostList(): Resource<PostList>

    suspend fun getCommentList(id: String): Resource<CommentList>

}