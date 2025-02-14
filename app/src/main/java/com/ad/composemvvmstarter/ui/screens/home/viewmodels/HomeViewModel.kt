package com.ad.composemvvmstarter.ui.screens.home.viewmodels

import com.ad.composemvvmstarter.core.BaseViewModel
import com.ad.composemvvmstarter.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    baseRepository: BaseRepository
) : BaseViewModel(baseRepository)