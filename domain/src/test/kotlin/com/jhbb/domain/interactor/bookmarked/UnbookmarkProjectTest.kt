package com.jhbb.domain.interactor.bookmarked

import com.jhbb.domain.executor.PostExecutionThread
import com.jhbb.domain.repository.ProjectRepository
import com.jhbb.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException

class UnbookmarkProjectTest {

    private lateinit var unbookmarkProject: UnbookmarkProject
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        unbookmarkProject = UnbookmarkProject(projectRepository, postExecutionThread)
    }

    @Test
    fun unbookmarkProjectCompletes() {
        stubUnbookmarkProject(Completable.complete())

        val testObserver = unbookmarkProject.buildUseCaseCompletable(
            UnbookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())
        ).test()

        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun unbookmarkProjectThrowsException() {
        val testObserver = unbookmarkProject.buildUseCaseCompletable().test()
    }

    private fun stubUnbookmarkProject(completable: Completable) {
        whenever(projectRepository.unbookmarkProject(any()))
            .thenReturn(completable)
    }
}