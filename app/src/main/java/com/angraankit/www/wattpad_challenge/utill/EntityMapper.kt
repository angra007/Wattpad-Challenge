package com.angraankit.www.wattpad_challenge.utill

interface EntityMapper <EntityModel, DomainModel> {

    fun getEntityFromModel (model : DomainModel) : EntityModel?

    fun getModelFromEntity (model: EntityModel) : DomainModel
}