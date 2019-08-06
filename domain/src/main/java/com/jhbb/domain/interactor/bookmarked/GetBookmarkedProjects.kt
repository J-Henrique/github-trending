package com.jhbb.domain.interactor.bookmarked

import com.jhbb.domain.executor.PostExecutionThread
import com.jhbb.domain.interactor.ObservableUseCase
import com.jhbb.domain.model.Project
import com.jhbb.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBookmarkedProjects @Inject constructor(
        private val repository: ProjectRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return repository.getBookmarkedProjects()
    }
}