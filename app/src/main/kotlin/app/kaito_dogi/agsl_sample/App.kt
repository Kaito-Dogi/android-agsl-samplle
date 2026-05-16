package app.kaito_dogi.agsl_sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun App(
  modifier: Modifier = Modifier
) = Scaffold(
  modifier = modifier,
) { innerPadding ->
  Box(
    modifier = Modifier
      .padding(paddingValues = innerPadding)
      .fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Text(text = "AGSL Sample")
  }
}
