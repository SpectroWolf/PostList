package com.example.postlist.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postlist.adapters.CommentAdapter
import com.example.postlist.databinding.CommentsViewBinding
import com.example.postlist.models.Comment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsView(
    private val id: String,
    private val title: String,
    private val myContext: Context
) : DialogFragment() {

    private lateinit var binding: CommentsViewBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = CommentsViewBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()
        binding.tvCommentsTitle.text = "O que a galera comentou sobre o post " + title

        initRecycler()
        createComments()

        return dialog
    }

    private fun createComments() {
        viewModel.getCommentList(id)
        viewModel.commentList.observe(this) { response ->
            if (response.data != null) {
                commentAdapter.setComments(response.data as ArrayList<Comment>)
                commentAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(
                    this.myContext,
                    response.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initRecycler() {
        binding.rvComments.layoutManager =
            LinearLayoutManager(myContext, RecyclerView.VERTICAL, false)
        binding.rvComments.addItemDecoration(
            DividerItemDecoration(
                myContext,
                DividerItemDecoration.VERTICAL
            )
        )
        commentAdapter = CommentAdapter(myContext)
        binding.rvComments.adapter = commentAdapter

    }

}