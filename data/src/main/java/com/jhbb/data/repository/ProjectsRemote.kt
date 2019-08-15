package com.jhbb.data.repository

import com.jhbb.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
}