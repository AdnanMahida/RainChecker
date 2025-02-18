package com.ad.rainchecker.core

import androidx.lifecycle.ViewModel
import com.ad.rainchecker.domain.repository.BaseRepository

abstract class BaseViewModel(protected val baseRepository: BaseRepository) : ViewModel()