package com.jhbb.remote

import com.jhbb.data.model.ProjectEntity
import com.jhbb.data.repository.ProjectsRemote
import com.jhbb.remote.mapper.ProjectsResponseModelMapper
import com.jhbb.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectsResponseModelMapper
): ProjectsRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map{
                it.items.map { mapper.mapFromModel(it) }
            }
    }
}