package com.hikarisource.carrefourgithub.presentation.mappers

interface DomainPresentationMapper<DOMAIN, PRESENTATION> {

    fun PRESENTATION.toDomain(): DOMAIN

    fun DOMAIN.fromDomain(): PRESENTATION

    fun List<PRESENTATION>.toDomainList() = map { it.toDomain() }

    fun List<DOMAIN>.fromDomainList() = map { it.fromDomain() }
}