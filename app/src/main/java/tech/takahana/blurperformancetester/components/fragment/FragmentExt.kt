package tech.takahana.blurperformancetester.components.fragment

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

fun ComposeView.setContentOnFragment(
  content: @Composable () -> Unit,
) {
  setContent {
    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    content()
  }
}