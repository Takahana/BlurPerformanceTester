package tech.takahana.blurperformancetester.components.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import tech.takahana.blurperformancetester.R

@Composable
fun LabelCheckbox(
  modifier: Modifier = Modifier,
  label: @Composable () -> Unit,
  checked: Boolean,
  onCheckedChange: ((Boolean) -> Unit)?,
) {
  Row(
    modifier = modifier
      .clickable(enabled = onCheckedChange != null) {
        onCheckedChange?.invoke(!checked)
      },
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Checkbox(checked = checked, onCheckedChange = onCheckedChange)
    label()
  }
}

@Preview
@Composable
fun LabelCheckboxPreview() {
  MaterialTheme {
    LabelCheckbox(
      label = {
        Text(text = stringResource(id = R.string.app_name))
      },
      checked = true,
      onCheckedChange = null,
    )
  }
}