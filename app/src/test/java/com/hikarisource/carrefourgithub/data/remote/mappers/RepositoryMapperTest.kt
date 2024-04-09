package com.hikarisource.carrefourgithub.data.remote.mappers

import com.hikarisource.carrefourgithub.data.remote.mappers.RepositoryMapper.fromDomain
import com.hikarisource.carrefourgithub.data.remote.mappers.RepositoryMapper.fromDomainList
import com.hikarisource.carrefourgithub.data.remote.mappers.RepositoryMapper.toDomain
import com.hikarisource.carrefourgithub.data.remote.mappers.RepositoryMapper.toDomainList
import com.hikarisource.carrefourgithub.data.remote.reponse.RepositoryResponse
import com.hikarisource.carrefourgithub.domain.model.Repository
import org.junit.Assert
import org.junit.Test

class RepositoryMapperTest {

    @Test
    fun `GIVEN Repository WHEN fromDomain called THEN must have expected values`() {
        // GIVEN
        val repository = Repository(
            id = 26899533,
            name = "30daysoflaptops.github.io",
            url = "https://github.com/mojombo/30daysoflaptops.github.io"
        )

        val expected = RepositoryResponse(
            id = 26899533,
            name = "30daysoflaptops.github.io",
            url = "https://github.com/mojombo/30daysoflaptops.github.io"
        )

        // WHEN
        val actual = repository.fromDomain()

        // THEN
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN RepositoryResponse WHEN toDomain called THEN must have expected values`() {
        // GIVEN
        val user = RepositoryResponse(
            id = 26899533,
            name = "30daysoflaptops.github.io",
            url = "https://github.com/mojombo/30daysoflaptops.github.io"
        )

        val expected = Repository(
            id = 26899533,
            name = "30daysoflaptops.github.io",
            url = "https://github.com/mojombo/30daysoflaptops.github.io"
        )

        // WHEN
        val actual = user.toDomain()

        // THEN
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun `GIVEN Repository list WHEN fromDomainList called THEN must have expected values`() {
        // GIVEN
        val users = listOf(
            Repository(
                id = 26899533,
                name = "30daysoflaptops.github.io",
                url = "https://github.com/mojombo/30daysoflaptops.github.io"
            )
        )

        val expected = listOf(
            RepositoryResponse(
                id = 26899533,
                name = "30daysoflaptops.github.io",
                url = "https://github.com/mojombo/30daysoflaptops.github.io"
            )
        )

        // WHEN
        val actual = users.fromDomainList()

        // THEN
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN RepositoryResponse list WHEN toDomainList called THEN must have expected values`() {
        // GIVEN
        val repositoryResponseList = listOf(
            RepositoryResponse(
                id = 26899533,
                name = "30daysoflaptops.github.io",
                url = "https://github.com/mojombo/30daysoflaptops.github.io"
            )
        )

        val expected = listOf(
            Repository(
                id = 26899533,
                name = "30daysoflaptops.github.io",
                url = "https://github.com/mojombo/30daysoflaptops.github.io"
            )
        )

        // WHEN
        val actual = repositoryResponseList.toDomainList()

        // THEN
        Assert.assertEquals(expected, actual)
    }
}