package com.jhbb.domain.repository

import com.jhbb.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>
}