package tech.takahana.blurperformancetester.components

interface Navigator {

  enum class Screen {
    ComposeRender,
    AndroidViewRender,
  }

  fun navigate(screen: Screen)
}