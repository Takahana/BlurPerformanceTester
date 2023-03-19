package tech.takahana.blurperformancetester.components.setting

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.takahana.blurperformancetester.R
import tech.takahana.blurperformancetester.components.Navigator
import tech.takahana.blurperformancetester.components.Navigator.Screen
import tech.takahana.blurperformancetester.components.compose.LabelRadioButton
import tech.takahana.blurperformancetester.components.fragment.FakeViewModelStoreOwner
import tech.takahana.blurperformancetester.components.fragment.setContentOnFragment
import tech.takahana.blurperformancetester.databinding.FragmentSettingBinding
import tech.takahana.blurperformancetester.domain.domainobject.ComposeImageLoader

class SettingFragment : Fragment(R.layout.fragment_setting) {

  private var _binding: FragmentSettingBinding? = null
  private val binding: FragmentSettingBinding get() = _binding!!

  private val navigator: Navigator? get() = activity as? Navigator
  private val viewModelStoreOwner: ViewModelStoreOwner get() = requireActivity()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding = FragmentSettingBinding.bind(view)

    binding.composeView.setContentOnFragment {
      MaterialTheme {
        SettingScreen(
          viewModelStoreOwner = viewModelStoreOwner,
          onClickRun = {
            navigator?.navigate(Screen.ComposeRender)
          }
        )
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

@Composable
fun SettingScreen(
  viewModelStoreOwner: ViewModelStoreOwner,
  settingViewModel: SettingViewModel = viewModel(viewModelStoreOwner),
  onClickRun: () -> Unit,
) {
  val screenUiState by settingViewModel.uiState.collectAsState()
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 8.dp)
      .verticalScroll(scrollState)
  ) {
    Text(
      text = stringResource(id = R.string.ui_toolkit),
      style = MaterialTheme.typography.titleMedium,
    )

    LabelRadioButton(
      label = {
        Text(text = stringResource(id = R.string.compose))
      },
      selected = screenUiState is SettingScreenUiState.Display.Compose,
      onClick = {},
    )

    when (val uiState = screenUiState) {
      SettingScreenUiState.Initialized -> Unit
      is SettingScreenUiState.Display.Compose -> {
        ComposeSettingScreen(
          uiState = uiState,
          onSelectComposeImageLoader = settingViewModel::onSelectComposeImageLoader,
        )
      }
    }

    Button(
      modifier = Modifier
        .fillMaxWidth(),
      onClick = { onClickRun() }
    ) {
      Text(text = stringResource(id = R.string.run))
    }
  }
}

@Composable
fun ComposeSettingScreen(
  uiState: SettingScreenUiState.Display.Compose,
  onSelectComposeImageLoader: (ComposeImageLoader) -> Unit,
) {
  Text(
    text = stringResource(id = R.string.image_loader),
    style = MaterialTheme.typography.titleMedium,
  )

  Column {
    ComposeImageLoader.values().forEach { loader ->
      when (loader) {
        ComposeImageLoader.Coil -> {
          LabelRadioButton(
            modifier = Modifier.fillMaxWidth(),
            label = {
              Text(text = stringResource(id = R.string.coil))
            },
            selected = uiState.selectedImageLoader == loader,
            onClick = {
              onSelectComposeImageLoader(ComposeImageLoader.Coil)
            }
          )
        }
        ComposeImageLoader.Glide -> {
          LabelRadioButton(
            modifier = Modifier.fillMaxWidth(),
            label = {
              Text(text = stringResource(id = R.string.glide))
            },
            selected = uiState.selectedImageLoader == loader,
            onClick = {
              onSelectComposeImageLoader(ComposeImageLoader.Glide)
            }
          )
        }
      }
    }
  }
}

@Preview(
  device = Devices.PIXEL_4,
  showBackground = true,
)
@Composable
fun SettingScreenPreview() {
  MaterialTheme {
    SettingScreen(
      viewModelStoreOwner = FakeViewModelStoreOwner(),
      onClickRun = {},
    )
  }
}