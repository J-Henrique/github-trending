package com.jhbb.domain.interactor.browse

import com.jhbb.domain.executor.PostExecutionThread
import com.jhbb.domain.model.Project
import com.jhbb.domain.repository.ProjectRepository
import com.jhbb.domain.test.ProjectDataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectRepository, postExecutionThread)
    }

    @Test
    fun getProjectsComplete() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))

        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnsData() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(projects))

        val testObserver = getProjects.buildUseCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectRepository.getProjects())
            .thenReturn(observable)
    }
}