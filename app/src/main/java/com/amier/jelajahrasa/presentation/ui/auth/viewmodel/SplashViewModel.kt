package com.amier.jelajahrasa.presentation.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import com.amier.jelajahrasa.domain.usecase.AuthUseCase
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val useCase: AuthUseCase) : ViewModel() {
}