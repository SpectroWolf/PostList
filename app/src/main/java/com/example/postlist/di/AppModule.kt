package com.example.postlist.di

import com.example.postlist.repositories.PostRepository
import com.example.postlist.repositories.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindRepository(
        postRepository: PostRepository
    ): Repository
}