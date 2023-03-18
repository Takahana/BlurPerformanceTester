package tech.takahana.blurperformancetester

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.takahana.blurperformancetester.components.Navigator
import tech.takahana.blurperformancetester.components.Navigator.Screen
import tech.takahana.blurperformancetester.components.render.ComposeRenderFragment

class MainActivity : AppCompatActivity(), Navigator {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun navigate(screen: Screen) {
    val fragment = when (screen) {
      Screen.ComposeRender -> ComposeRenderFragment.newInstance()
    }
    supportFragmentManager
      .beginTransaction()
      .replace(R.id.container, fragment)
      .addToBackStack(null)
      .commit()
  }
}