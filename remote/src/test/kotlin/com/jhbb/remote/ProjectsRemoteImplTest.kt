package com.jhbb.remote

import com.jhbb.data.model.ProjectEntity
import com.jhbb.remote.mapper.ProjectsResponseModelMapper
import com.jhbb.remote.models.ProjectModel
import com.jhbb.remote.models.ProjectsResponseModel
import com.jhbb.remote.service.GithubTrendingService
import com.jhbb.remote.test.factory.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test

class ProjectsRemoteImplTest {

    private val service = mock<GithubTrendingService>()
    private val mapper = mock<ProjectsResponseModelMapper>()

    private val projectsRemoteImpl = ProjectsRemoteImpl(service, mapper)

    @Test
    fun getProjectsCompletes() {
        stubGitHubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectsReponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        val testObserver = projectsRemoteImpl.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallServer() {
        stubGitHubTrendingServiceSearchRepositories(Observable.just(ProjectDataFactory.makeProjectsResponse()))
        stubProjectsReponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        projectsRemoteImpl.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGitHubTrendingServiceSearchRepositories(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectsReponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = projectsRemoteImpl.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsServerWithCorrectParameters() {
        stubGitHubTrendingServiceSearchRepositories(Observable.just(
            ProjectDataFactory.makeProjectsResponse()))
        stubProjectsReponseModelMapperMapFromModel(any(),
            ProjectDataFactory.makeProjectEntity())

        projectsRemoteImpl.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }

    private fun stubGitHubTrendingServiceSearchRepositories(observable: Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
            .thenReturn(observable)
    }

    private fun stubProjectsReponseModelMapperMapFromModel(model: ProjectModel,
                                                           entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }
}