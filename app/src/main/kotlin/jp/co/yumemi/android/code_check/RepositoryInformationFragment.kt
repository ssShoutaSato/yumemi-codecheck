/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.code_check.MainActivity.Companion.lastSearchDate
import jp.co.yumemi.android.code_check.databinding.FragmentRepositoryInformationBinding

/**
 * This fragment for displaying the repository information.
 */
class RepositoryInformationFragment : Fragment(R.layout.fragment_repository_information) {
    private val args: RepositoryInformationFragmentArgs by navArgs()

    /**
     * [Fragment.onViewCreated]
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Search date : ", lastSearchDate.toString())

        val binding = FragmentRepositoryInformationBinding.bind(view)

        var info = args.repositoryInformation

        binding.ownerIconView.load(info.ownerIconUrl)
        binding.nameView.text = info.name
        binding.languageView.text = info.language
        binding.starsView.text = "${info.stargazersCount} stars"
        binding.watchersView.text = "${info.watchersCount} watchers"
        binding.forksView.text = "${info.forksCount} forks"
        binding.openIssuesView.text = "${info.openIssuesCount} open issues"
    }
}
