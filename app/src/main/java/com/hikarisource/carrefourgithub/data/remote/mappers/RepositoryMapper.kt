package com.hikarisource.carrefourgithub.data.remote.mappers

import com.hikarisource.carrefourgithub.data.remote.reponse.RepositoryResponse
import com.hikarisource.carrefourgithub.domain.model.Repository

object RepositoryMapper : DomainResponseMapper<Repository, RepositoryResponse> {

    override fun RepositoryResponse.toDomain() = Repository(
        id = id,
        name = name,
        url = url,
    )

    override fun Repository.fromDomain() = RepositoryResponse(
        id = id,
        name = name,
        url = url,
    )
}