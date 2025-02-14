package com.ad.composemvvmstarter.ui.screens.settings.viewmodels

import com.ad.composemvvmstarter.core.BaseViewModel
import com.ad.composemvvmstarter.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    baseRepository: BaseRepository
) : BaseViewModel(baseRepository)