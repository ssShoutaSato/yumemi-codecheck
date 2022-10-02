package jp.co.yumemi.android.code_check.model.connection.response.factory

import android.util.Log
import jp.co.yumemi.android.code_check.model.data.RepositoryInformation
import org.json.JSONObject

/**
 * This class creates search response for git repository information.
 */
class SearchGitRepositoryInformationResponseFactory {
    companion object {
        private const val KEY_ITEMS = "items"
        private const val KEY_NAME = "full_name"
        private const val KEY_OWNER = "owner"
        private const val KEY_AVATAR_URL = "avatar_url"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_STARGAZERS_COUNT = "stargazers_count"
        private const val KEY_WATCHERS_COUNT = "watchers_count"
        private const val KEY_FORKS_COUNT = "forks_count"
        private const val KEY_ISSUE_COUNT = "open_issues_count"

        /**
         * Create response.
         *
         * @param response The server response.<br>
         *                 This response should be in Json format.
         * @return A list of git repository information.
         */
        fun create(response: String): List<RepositoryInformation> {
            val list = mutableListOf<RepositoryInformation>()
            val jsonBody = JSONObject(response)
            val jsonItems = jsonBody.optJSONArray(KEY_ITEMS)
            if (jsonItems == null) {
                Log.e(
                    "SearchGitRepositoryInformationResponseFactory",
                    "Key does not exist in response. : items"
                )
                return list.toList()
            }

            for (i in 0 until jsonItems.length()) {
                val jsonItem = jsonItems.optJSONObject(i)
                val name = jsonItem.optString(KEY_NAME)
                val owner = jsonItem.optJSONObject(KEY_OWNER)
                val ownerIconUrl = if (owner == null) {
                    ""
                } else {
                    owner.optString(KEY_AVATAR_URL) ?: ""
                }
                val language = jsonItem.optString(KEY_LANGUAGE)
                val stargazersCount = jsonItem.optLong(KEY_STARGAZERS_COUNT)
                val watchersCount = jsonItem.optLong(KEY_WATCHERS_COUNT)
                val forksCount = jsonItem.optLong(KEY_FORKS_COUNT)
                val openIssuesCount = jsonItem.optLong(KEY_ISSUE_COUNT)

                list.add(
                    RepositoryInformation(
                        name = name,
                        ownerIconUrl = ownerIconUrl,
                        language = language,
                        stargazersCount = stargazersCount,
                        watchersCount = watchersCount,
                        forksCount = forksCount,
                        openIssuesCount = openIssuesCount
                    )
                )
            }

            return list.toList()
        }
    }
}