package com.example.postlist.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.postlist.models.Comment
import com.example.postlist.models.CommentList
import com.example.postlist.models.Post
import com.example.postlist.models.PostList
import com.example.postlist.repositories.Repository
import com.example.postlist.utils.MainCoroutineRule
import com.example.postlist.utils.Resource
import com.example.postlist.utils.Status
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private var repository: Repository = mockk()


    @Before
    fun setup() {
        viewModel = MainViewModel(repository)

    }

    private fun mockPostList(): PostList {
        val postList = PostList()

        val post1 = Post(
            "Imagine an incredible text here",
            1,
            "Imagine an incredible title here",
            1,
        )

        val post2 = Post(
            "Imagine an incredible text here 2",
            2,
            "Imagine an incredible title here 2",
            1,
        )

        postList.add(post1)
        postList.add(post2)

        return postList
    }

    private fun mockCommentList(): CommentList {

        val commentList = CommentList()
        val temporaryList = CommentList()

        val comment1 = Comment(
            "Imagine an incredible text here",
            "incredible@mail.com",
            1,
            "Distinguish Name 1",
            1
        )

        val comment2 = Comment(
            "Imagine an incredible text here 2",
            "notsoincredible@mail.com",
            2,
            "Distinguish Name 2",
            2
        )

        val comment3 = Comment(
            "Imagine an incredible text here 3",
            "notsoincredible@mail.com",
            3,
            "Distinguish Name 2",
            2
        )

        commentList.add(comment1)
        commentList.add(comment2)
        commentList.add(comment3)

        return commentList
    }

    @Test
    fun `should return mock postList of posts when called`() {

        val postList = mockPostList()

        coEvery { repository.getPostList() } returns Resource<PostList>(
            Status.SUCCESS, postList, ""
        )
        runBlockingTest {
            viewModel.getPostList()
        }
        coVerify { repository.getPostList() }

        print(postList)
    }

    @Test
    fun `should return mock commentlist of comments when called`() {

        val commentList = mockCommentList()

        coEvery { repository.getCommentList("2") } returns Resource<CommentList>(
            Status.SUCCESS, commentList, ""
        )
        runBlockingTest {
            viewModel.getCommentList("2")
        }
        coVerify { repository.getCommentList("2") }

        print(commentList)
    }

}

