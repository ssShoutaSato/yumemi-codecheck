/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.co.yumemi.android.code_check.model.connection.ServerConnection
import jp.co.yumemi.android.code_check.model.data.RepositoryInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * This viewmodel for searching.
 */
class SearchViewModel : ViewModel() {
    val list = MutableLiveData<List<RepositoryInformation>>(mutableListOf())

    /**
     * Search repository with the text of argument.
     *
     * @param inputText The text for search.
     * @return The list of search results.
     */
    @Suppress("OPT_IN_IS_NOT_ENABLED")
    fun search(inputText: String) = runBlocking {
        return@runBlocking withContext(Dispatchers.Default) {
            list.postValue(ServerConnection().searchGitRepositoryInformation(inputText))
        }
    }
}
