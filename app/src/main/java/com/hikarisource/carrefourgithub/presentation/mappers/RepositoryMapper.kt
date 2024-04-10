package com.hikarisource.carrefourgithub.presentation.mappers

import com.hikarisource.carrefourgithub.domain.model.Repository
import com.hikarisource.carrefourgithub.presentation.model.RepositoryPresentation

object RepositoryMapper : DomainPresentationMapper<Repository, RepositoryPresentation> {

    override fun RepositoryPresentation.toDomain() = Repository(
        id = id,
        name = name,
        url = url,
    )

    override fun Repository.fromDomain() = RepositoryPresentation(
        id = id,
        name = name,
        url = url,
    )
}