package com.ftchinese.api.dao

import com.ftchinese.api.models.RawStory

interface DAOFacade {
    suspend fun story(id: String): RawStory?
}