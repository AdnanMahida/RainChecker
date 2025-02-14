package com.ad.composemvvmstarter.ui.main

import androidx.lifecycle.ViewModel
import com.ad.composemvvmstarter.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: BaseRepository
) : ViewModel()