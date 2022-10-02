package jp.co.yumemi.android.code_check.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * An information of repository.
 *
 * @param name The repository name.
 * @param ownerIconUrl An ownerIcon url of repository.
 * @param language The language of repository.
 * @param stargazersCount The count of stargazers.
 * @param watchersCount The count of watchers.
 * @param forksCount The count of fork.
 * @param openIssuesCount The count of issues.
 */
@Parcelize
data class RepositoryInformation(
    val name: String,
    val ownerIconUrl: String,
    val language: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val forksCount: Long,
    val openIssuesCount: Long,
) : Parcelable
