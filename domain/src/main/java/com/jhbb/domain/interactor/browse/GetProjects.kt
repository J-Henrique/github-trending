package com.jhbb.domain.interactor.browse

import com.jhbb.domain.executor.PostExecutionThread
import com.jhbb.domain.interactor.ObservableUseCase
import com.jhbb.domain.model.Project
import com.jhbb.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjects @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread): ObservableUseCase<List<Project>, Nothing?>(postExecutionThread){

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getProjects()
    }
}