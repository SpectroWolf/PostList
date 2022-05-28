package com.example.postlist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postlist.adapters.PostAdapter
import com.example.postlist.databinding.ActivityMainBinding
import com.example.postlist.models.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
        createPostList()
    }

    private fun createPostList() {
        viewModel.getPostList()
        viewModel.postList.observe(this) { response ->
            if (response.data != null) {
                postAdapter.setPostsList(response.data as ArrayList<Post>)
                postAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    response.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvPostList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvPostList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        postAdapter = PostAdapter(this)
        binding.rvPostList.adapter = postAdapter
    }
}