package tech.takahana.blurperformancetester.components

interface Navigator {

  enum class Screen {
    ComposeRender,
  }

  fun navigate(screen: Screen)
}