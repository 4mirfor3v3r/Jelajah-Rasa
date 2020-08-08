package com.amier.jelajahrasa.presentation.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.amier.jelajahrasa.domain.usecase.DetailUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val useCase: DetailUseCase):ViewModel() {
}