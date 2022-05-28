package com.example.postlist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postlist.models.CommentList
import com.example.postlist.models.PostList
import com.example.postlist.repositories.Repository
import com.example.postlist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val postList: MutableLiveData<Resource<PostList>> = MutableLiveData()
    val commentList: MutableLiveData<Resource<CommentList>> = MutableLiveData()

    fun getPostList() {
        viewModelScope.launch {
            val response = repository.getPostList()
            postList.value = response
        }
    }

    fun getCommentList(id: String) {
        viewModelScope.launch {
            val response = repository.getCommentList(id)
            commentList.value = response
        }
    }
}