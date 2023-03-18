package tech.takahana.blurperformancetester.components.setting

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingViewModel : ViewModel() {

  private val mutableUiState =
    MutableStateFlow<SettingScreenUiState>(SettingScreenUiState.Initialized)
  val uiState: StateFlow<SettingScreenUiState> = mutableUiState.asStateFlow()

  init {
    mutableUiState.value = SettingScreenUiState.Display.Compose.default
  }
}