package com.example.dao

import com.example.models.Account
import com.example.models.Article

interface DAOFacade {
    suspend fun allArticles(): List<Article>
    suspend fun article(id: Int): Article?
    suspend fun addNewArticle(title: String, body: String): Article?
    suspend fun editArticle(id: Int, title: String, body: String): Boolean
    suspend fun deleteArticle(id: Int): Boolean

    suspend fun emailSignUp(email: String, password: String): Account?

    suspend fun emailLogin(email: String, password: String): Account?
}