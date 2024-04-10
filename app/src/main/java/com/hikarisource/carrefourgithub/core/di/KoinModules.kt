package com.hikarisource.carrefourgithub.core.di

import com.hikarisource.carrefourgithub.data.fake.UserRepositoryFake
import com.hikarisource.carrefourgithub.data.remote.api.GithubApi
import com.hikarisource.carrefourgithub.data.remote.repository.UserRepositoryApi
import com.hikarisource.carrefourgithub.data.repository.UserRepository
import com.hikarisource.carrefourgithub.domain.usecase.GetAllUsersUseCase
import com.hikarisource.carrefourgithub.domain.usecase.GetRepositoriesFromUserUseCase
import com.hikarisource.carrefourgithub.domain.usecase.GetUserByNameUseCase
import com.hikarisource.carrefourgithub.presentation.features.user.list.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val apiModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(GithubApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<GithubApi> { get<Retrofit>().create(GithubApi::class.java) }
}

@Suppress("UnusedPrivateProperty")
private val repositoriesFakeModule = module {
    single<UserRepository> { UserRepositoryFake }
}

@Suppress("UnusedPrivateProperty")
private val repositoryApiModule = module {
    single<UserRepository> { UserRepositoryApi(get()) }
}

private val useCaseModule = module {
    single { GetAllUsersUseCase(get()) }
    single { GetRepositoriesFromUserUseCase(get()) }
    single { GetUserByNameUseCase(get()) }
}

private val viewModelModule = module {
    viewModel { UserListViewModel(get()) }
}

object AppModules {

    val modules = module { }.apply {
        includes(apiModule, repositoryApiModule, useCaseModule, viewModelModule)
    }
}