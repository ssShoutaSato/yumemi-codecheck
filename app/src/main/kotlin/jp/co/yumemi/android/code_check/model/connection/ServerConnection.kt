package jp.co.yumemi.android.code_check.model.connection

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import jp.co.yumemi.android.code_check.model.connection.response.factory.SearchGitRepositoryInformationResponseFactory
import jp.co.yumemi.android.code_check.model.data.RepositoryInformation

/**
 * This class for HTTP communication.
 */
class ServerConnection {
    companion object {
        private const val URL_SEARCH_GIT_REPOSITORY = "https://api.github.com/search/repositories"
        private const val MEDIA_TYPE_ACCEPT = "Accept"
        private const val MEDIA_TYPE_SEARCH_GIT_REPOSITORY_JSON = "application/vnd.github.v3+json"
        private const val KEY_SEARCH_QUERY = "q"
    }

    /**
     * Search the git repository information.
     *
     * @param text The text for search.
     */
    suspend fun searchGitRepositoryInformation(text : String) : List<RepositoryInformation>{
        val response: HttpResponse = HttpClient(Android).get(URL_SEARCH_GIT_REPOSITORY) {
            header(MEDIA_TYPE_ACCEPT, MEDIA_TYPE_SEARCH_GIT_REPOSITORY_JSON)
            parameter(KEY_SEARCH_QUERY, text)
        }

        return SearchGitRepositoryInformationResponseFactory.create(response.receive())
    }
}
