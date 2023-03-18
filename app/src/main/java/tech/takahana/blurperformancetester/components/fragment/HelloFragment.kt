package tech.takahana.blurperformancetester.components.fragment

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import tech.takahana.blurperformancetester.R
import tech.takahana.blurperformancetester.databinding.FragmentHelloBinding

class HelloFragment : Fragment(R.layout.fragment_hello) {

  private var _binding: FragmentHelloBinding? = null
  private val binding: FragmentHelloBinding get() = _binding!!

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = FragmentHelloBinding.bind(view)

    binding.composeView.setContentOnFragment {
      HelloScreen()
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

@Composable
fun HelloScreen() {
  Box(
    modifier = Modifier
      .fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Text(text = stringResource(id = R.string.hello_world))
  }
}

@Preview(
  device = Devices.PIXEL_4,
  showBackground = true,
)
@Composable
fun HelloScreenPreview() {
  MaterialTheme {
    HelloScreen()
  }
}