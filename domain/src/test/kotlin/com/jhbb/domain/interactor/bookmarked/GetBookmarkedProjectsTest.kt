package com.jhbb.domain.interactor.bookmarked

import com.jhbb.domain.executor.PostExecutionThread
import com.jhbb.domain.model.Project
import com.jhbb.domain.repository.ProjectRepository
import com.jhbb.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class GetBookmarkedProjectsTest {

    private lateinit var getBookmarkedProjects: GetBookmarkedProjects
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBookmarkedProjects = GetBookmarkedProjects(projectRepository, postExecutionThread)
    }

    @Test
    fun getBookmarkedProjectsComplete() {
        stubGetProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBookmarkedProjectsReturnsData() {
        val bookmarkedProjects = ProjectDataFactory.makeProjectList(2)
        stubGetProjects(Observable.just(bookmarkedProjects))

        val testObserver = getBookmarkedProjects.buildUseCaseObservable().test()

        testObserver.assertValue(bookmarkedProjects)
    }

    private fun stubGetProjects(observable: Observable<List<Project>>) {
        whenever(projectRepository.getBookmarkedProjects())
            .thenReturn(observable)
    }
}