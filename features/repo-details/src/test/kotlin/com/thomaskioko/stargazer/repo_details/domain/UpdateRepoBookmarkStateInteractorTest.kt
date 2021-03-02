package com.thomaskioko.stargazer.repo_details.domain

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.stargazer.core.ViewState.Loading
import com.thomaskioko.stargazer.core.ViewState.Success
import com.thomaskioko.stargazer.repo_details.domain.model.UpdateObject
import com.thomaskioko.stargazer.repo_details.util.ViewMockData.makeRepoEntity
import com.thomaskioko.stargazer.repo_details.util.ViewMockData.makeRepoViewDataModelList
import com.thomaskioko.stargazer.repository.api.GithubRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyLong

internal class UpdateRepoBookmarkStateInteractorTest {

    private val repository: GithubRepository = mock()
    private val interactor = UpdateRepoBookmarkStateInteractor(repository)

    // @Test TODO: fix failing test on CI
    fun `whenever updateRepoIsInvoked expectedDataIsReturned`() = runBlocking {
        whenever(repository.getRepoById(anyLong())).doReturn(makeRepoEntity())

        val updateObject = UpdateObject(anyLong(), anyBoolean())

        val result = interactor(updateObject).toList()
        val expected = listOf(
            Loading(),
            Success(makeRepoViewDataModelList()[0])
        )

        assertThat(result).isEqualTo(expected)
    }
}
