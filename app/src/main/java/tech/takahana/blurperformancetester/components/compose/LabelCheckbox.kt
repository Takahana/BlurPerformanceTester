package tech.takahana.blurperformancetester.components.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.blurperformancetester.R

@Composable
fun LabelRadioButton(
  modifier: Modifier = Modifier,
  label: @Composable () -> Unit,
  selected: Boolean,
  onClick: (() -> Unit)?,
) {
  Row(
    modifier = modifier
      .clickable(enabled = onClick != null) {
        onClick?.invoke()
      },
    verticalAlignment = Alignment.CenterVertically,
  ) {
    RadioButton(
      selected = selected,
      onClick = { onClick?.invoke() }
    )
    label()
  }
}

@Preview
@Composable
fun LabelRadioButtonPreview() {
  MaterialTheme {
    LabelRadioButton(
      label = {
        Text(text = stringResource(id = R.string.app_name))
      },
      selected = true,
      onClick = null,
    )
  }
}