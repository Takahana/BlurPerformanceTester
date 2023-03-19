package tech.takahana.blurperformancetester.components.setting

import androidx.compose.runtime.Immutable
import tech.takahana.blurperformancetester.domain.domainobject.AndroidViewBlurLibrary
import tech.takahana.blurperformancetester.domain.domainobject.ComposeBlurLibrary

sealed interface SettingScreenUiState {

  @Immutable
  object Initialized : SettingScreenUiState

  sealed interface Display : SettingScreenUiState {

    @Immutable
    data class Compose(
      val selectedImageLoader: ComposeBlurLibrary,
    ) : Display {

      companion object {
        val default
          get() = Compose(
            selectedImageLoader = ComposeBlurLibrary.Modifier,
          )
      }
    }

    data class AndroidView(
      val selectedImageLoader: AndroidViewBlurLibrary,
    ) : Display {

      companion object {
        val default
          get() = AndroidView(
            selectedImageLoader = AndroidViewBlurLibrary.Glide,
          )
      }
    }
  }
}
