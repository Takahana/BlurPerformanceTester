package tech.takahana.blurperformancetester.components.setting

import androidx.compose.runtime.Immutable
import tech.takahana.blurperformancetester.domain.domainobject.AndroidViewImageLoader
import tech.takahana.blurperformancetester.domain.domainobject.ComposeImageLoader

sealed interface SettingScreenUiState {

  @Immutable
  object Initialized : SettingScreenUiState

  sealed interface Display : SettingScreenUiState {

    @Immutable
    data class Compose(
      val selectedImageLoader: ComposeImageLoader,
    ) : Display {

      companion object {
        val default
          get() = Compose(
            selectedImageLoader = ComposeImageLoader.Coil,
          )
      }
    }

    data class AndroidView(
      val selectedImageLoader: AndroidViewImageLoader,
    ) : Display {

      companion object {
        val default
          get() = AndroidView(
            selectedImageLoader = AndroidViewImageLoader.Glide,
          )
      }
    }
  }
}
