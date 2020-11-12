package com.thomaskioko.githubstargazer.browse.data.interactor

import com.thomaskioko.githubstargazer.browse.data.mapper.ViewDataMapper.mapEntityToRepoViewModel
import com.thomaskioko.githubstargazer.browse.data.model.RepoViewDataModel
import com.thomaskioko.githubstargazer.core.ViewState
import com.thomaskioko.githubstargazer.core.interactor.Interactor
import com.thomaskioko.githubstargazer.repository.api.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRepoByIdInteractor @Inject constructor(
    private val repository: GithubRepository
) : Interactor<Long, RepoViewDataModel>() {

    override suspend fun run(params: Long): Flow<ViewState<RepoViewDataModel>> = flow {

        emit(ViewState.loading())

        val entity = repository.getRepoById(params)
        val repoViewDataModel = mapEntityToRepoViewModel(entity)

        emit(ViewState.success(repoViewDataModel))
    }.catch {
        emit(ViewState.error(it.message.orEmpty()))
    }
}