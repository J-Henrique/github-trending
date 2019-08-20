package com.jhbb.remote.test.factory

import com.jhbb.data.model.ProjectEntity
import com.jhbb.remote.models.OwnerModel
import com.jhbb.remote.models.ProjectModel
import com.jhbb.remote.models.ProjectsResponseModel

object ProjectDataFactory {

    fun makeOwner(): OwnerModel {
        return OwnerModel(DataFactory.randomUuid(), DataFactory.randomUuid())
    }

    fun makeProject(): ProjectModel {
        return ProjectModel(DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomInt(),
            DataFactory.randomUuid(), makeOwner())
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), DataFactory.randomUuid(),
            DataFactory.randomUuid(), false)
    }

    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(makeProject(), makeProject()))
    }
}