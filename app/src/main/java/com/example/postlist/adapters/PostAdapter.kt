package com.example.postlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postlist.databinding.PostItemBinding
import com.example.postlist.models.Post
import com.example.postlist.ui.CommentsView


class PostAdapter(private val context: Context, private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var postList = ArrayList<Post>()

    fun setPostsList(data: ArrayList<Post>) {
        this.postList = data
    }

    class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindPostList(data: Post, context: Context) {
            binding.tvPostItemTitle.text = data.title
            binding.tvPostItemBody.text = data.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindPostList(postList[position], context)
        holder.binding.cardViewPost.setOnClickListener {
            val dialog = CommentsView(
                postList[holder.adapterPosition].id.toString(),
                postList[holder.adapterPosition].title,
                context)
            dialog.show(fragmentManager, "teste")
        }
    }

    override fun getItemCount(): Int = postList.size
}