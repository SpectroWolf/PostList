package com.example.postlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postlist.databinding.CommentsItemBinding
import com.example.postlist.models.Comment


class CommentAdapter(private val context: Context) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    var commentsList = ArrayList<Comment>()

    fun setComments(data: ArrayList<Comment>) {
        this.commentsList = data
    }


    class CommentViewHolder(private val binding: CommentsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCommentList(data: Comment, context: Context) {
            binding.tvCommentsItemName.text = data.name
            binding.tvCommentsItemEmail.text = data.email
            binding.tvCommentsItemBody.text = data.body
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentAdapter.CommentViewHolder {
        val binding =
            CommentsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentAdapter.CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentAdapter.CommentViewHolder, position: Int) {
        holder.bindCommentList(commentsList[position], context)
    }

    override fun getItemCount(): Int = commentsList.size

}