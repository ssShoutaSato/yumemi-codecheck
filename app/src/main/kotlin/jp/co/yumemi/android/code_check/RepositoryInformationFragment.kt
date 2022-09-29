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

class RepositoryInformationFragment : Fragment(R.layout.fragment_repository_information) {
    private val args: RepositoryInformationFragmentArgs by navArgs()
    private var binding: FragmentRepositoryInformationBinding? = null
    private val _binding get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("検索した日時", lastSearchDate.toString())

        binding = FragmentRepositoryInformationBinding.bind(view)

        var info = args.repositoryInformation

        _binding.ownerIconView.load(info.ownerIconUrl);
        _binding.nameView.text = info.name;
        _binding.languageView.text = info.language;
        _binding.starsView.text = "${info.stargazersCount} stars";
        _binding.watchersView.text = "${info.watchersCount} watchers";
        _binding.forksView.text = "${info.forksCount} forks";
        _binding.openIssuesView.text = "${info.openIssuesCount} open issues";
    }
}
