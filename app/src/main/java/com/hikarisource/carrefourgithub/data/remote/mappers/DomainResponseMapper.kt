package com.hikarisource.carrefourgithub.data.remote.mappers

interface DomainResponseMapper<DOMAIN, RESPONSE> {

    fun RESPONSE.toDomain(): DOMAIN

    fun DOMAIN.fromDomain(): RESPONSE

    fun List<RESPONSE>.toDomainList() = map { it.toDomain() }

    fun List<DOMAIN>.fromDomainList() = map { it.fromDomain() }
}