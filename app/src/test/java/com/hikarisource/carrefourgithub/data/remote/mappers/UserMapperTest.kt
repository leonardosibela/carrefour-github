package com.hikarisource.carrefourgithub.data.remote.mappers

import com.hikarisource.carrefourgithub.data.remote.mappers.UserMapper.fromDomain
import com.hikarisource.carrefourgithub.data.remote.mappers.UserMapper.fromDomainList
import com.hikarisource.carrefourgithub.data.remote.mappers.UserMapper.toDomain
import com.hikarisource.carrefourgithub.data.remote.mappers.UserMapper.toDomainList
import com.hikarisource.carrefourgithub.data.remote.reponse.UserResponse
import com.hikarisource.carrefourgithub.domain.model.User
import org.junit.Assert
import org.junit.Test

class UserMapperTest {

    @Test
    fun `GIVEN User WHEN fromDomain called THEN must have expected values`() {
        // GIVEN
        val user = User(
            id = 1,
            login = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            url = "https://api.github.com/users/mojombo"
        )

        val expected = UserResponse(
            id = 1,
            login = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            url = "https://api.github.com/users/mojombo"
        )

        // WHEN
        val actual = user.fromDomain()

        // THEN
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN UserResponse WHEN toDomain called THEN must have expected values`() {
        // GIVEN
        val user = UserResponse(
            id = 1,
            login = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            url = "https://api.github.com/users/mojombo"
        )

        val expected = User(
            id = 1,
            login = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            url = "https://api.github.com/users/mojombo"
        )

        // WHEN
        val actual = user.toDomain()

        // THEN
        Assert.assertEquals(expected, actual)
    }


    @Test
    fun `GIVEN User list WHEN fromDomainList called THEN must have expected values`() {
        // GIVEN
        val users = listOf(
            User(
                id = 1,
                login = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                url = "https://api.github.com/users/mojombo"
            )
        )

        val expected = listOf(
            UserResponse(
                id = 1,
                login = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                url = "https://api.github.com/users/mojombo"
            )
        )

        // WHEN
        val actual = users.fromDomainList()

        // THEN
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `GIVEN UserResponse list WHEN toDomainList called THEN must have expected values`() {
        // GIVEN
        val user = listOf(
            UserResponse(
                id = 1,
                login = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                url = "https://api.github.com/users/mojombo"
            )
        )

        val expected = listOf(
            User(
                id = 1,
                login = "mojombo",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                url = "https://api.github.com/users/mojombo"
            )
        )

        // WHEN
        val actual = user.toDomainList()

        // THEN
        Assert.assertEquals(expected, actual)
    }
}