package com.jhbb.remote.mapper

import com.jhbb.data.model.ProjectEntity
import com.jhbb.remote.models.ProjectModel

open class ProjectsResponseModelMapper :
    ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName,
            model.starCount.toString(), model.dateCreated, model.owner.ownerName,
            model.owner.ownerAvatar, false)
    }
}