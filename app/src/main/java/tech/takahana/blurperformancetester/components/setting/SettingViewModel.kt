package tech.takahana.blurperformancetester.components.setting

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tech.takahana.blurperformancetester.domain.domainobject.AndroidViewImageLoader
import tech.takahana.blurperformancetester.domain.domainobject.ComposeImageLoader

class SettingViewModel : ViewModel() {

  private val mutableUiState =
    MutableStateFlow<SettingScreenUiState>(SettingScreenUiState.Initialized)
  val uiState: StateFlow<SettingScreenUiState> = mutableUiState.asStateFlow()

  init {
    mutableUiState.value = SettingScreenUiState.Display.Compose.default
  }

  fun onSelectComposeImageLoader(loader: ComposeImageLoader) {
    mutableUiState.update {
      when (it) {
        SettingScreenUiState.Initialized -> it
        is SettingScreenUiState.Display.Compose -> it.copy(selectedImageLoader = loader)
        is SettingScreenUiState.Display.AndroidView -> it
      }
    }
  }

  fun onSelectAndroidViewImageLoader(loader: AndroidViewImageLoader) {
    mutableUiState.update {
      when (it) {
        SettingScreenUiState.Initialized -> it
        is SettingScreenUiState.Display.Compose -> it
        is SettingScreenUiState.Display.AndroidView -> it.copy(selectedImageLoader = loader)
      }
    }
  }

  fun onSelectUIToolkit(
    uiState: SettingScreenUiState,
  ) {
    mutableUiState.value = uiState
  }
}