package tech.takahana.blurperformancetester.components.render

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import coil.compose.AsyncImage
import coil.request.ImageRequest
import tech.takahana.blurperformancetester.R
import tech.takahana.blurperformancetester.components.fragment.setContentOnFragment
import tech.takahana.blurperformancetester.databinding.FragmentComposeRenderBinding
import tech.takahana.blurperformancetester.domain.domainobject.ImageSize
import tech.takahana.blurperformancetester.domain.domainobject.RemoteImage

class ComposeRenderFragment : Fragment(R.layout.fragment_compose_render) {

  private var _binding: FragmentComposeRenderBinding? = null
  private val binding: FragmentComposeRenderBinding get() = _binding!!

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = FragmentComposeRenderBinding.bind(view)

    binding.composeView.setContentOnFragment {
      MaterialTheme {
        ComposeRenderScreen()
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  companion object {
    fun newInstance(): ComposeRenderFragment {
      return ComposeRenderFragment()
    }
  }
}

@Composable
fun ComposeRenderScreen() {

  val context = LocalContext.current
  val isPreview = LocalInspectionMode.current
  val image = if (isPreview) null else RemoteImage.W8256_H5504

  var imageWidth by remember { mutableStateOf(0) }
  var imageHeight by remember { mutableStateOf(0) }
  var renderState by remember { mutableStateOf(RenderState.Initialized) }

  val configuration = LocalConfiguration.current
  val density = LocalDensity.current
  val screenWidth = density.run {
    configuration.screenWidthDp.dp.roundToPx()
  }
  val screenHeight = density.run {
    configuration.screenHeightDp.dp.roundToPx()
  }

  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    Text(text = "screenWidth: $screenWidth")
    Text(text = "screenHeight: $screenHeight")
    Text(text = "width: $imageWidth")
    Text(text = "height: $imageHeight")
    when (renderState) {
      RenderState.Initialized -> Unit
      RenderState.Processing -> Text(text = stringResource(id = R.string.processing))
      RenderState.Completed -> Text(text = stringResource(id = R.string.complete))
    }

    AsyncImage(
      model = ImageRequest.Builder(context)
        .data(image)
        .listener(
          onStart = {
            renderState = RenderState.Processing
          },
          onSuccess = { request, result ->
            val bitmapDrawable = result.drawable as? BitmapDrawable
            bitmapDrawable?.bitmap?.let {
              imageWidth = it.width
              imageHeight = it.height
            }
            renderState = RenderState.Completed
          })
        .build(),
      modifier = Modifier.blur(radius = 16.dp),
      contentDescription = stringResource(id = R.string.output),
    )
  }
}

@Preview(
  device = Devices.PIXEL_4,
  showBackground = true,
)
@Composable
fun ComposeRenderScreenPreview() {
  MaterialTheme {
    ComposeRenderScreen()
  }
}