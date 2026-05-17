package app.kaito_dogi.agsl_sample

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import app.kaito_dogi.agsl_sample.shader.AndroidDevelopers
import app.kaito_dogi.agsl_sample.shader.Sandbox
import app.kaito_dogi.agsl_sample.shader.ShaderItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App(
  modifier: Modifier = Modifier
) {
  var isExpanded by remember { mutableStateOf(value = false) }
  var shaderItem by remember { mutableStateOf(value = ShaderItem.AndroidDeveloper) }

  Scaffold(
    modifier = modifier,
    floatingActionButton = {
      FloatingActionButton(
        onClick = { isExpanded = true },
        content = {
          Icon(
            painter = painterResource(id = R.drawable.ic_more_vertical),
            contentDescription = null,
          )
        },
      )
    },
    containerColor = Color.Black,
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .padding(paddingValues = innerPadding)
        .fillMaxSize(),
      contentAlignment = Alignment.Center,
    ) {
      // FIXME: メニューの表示位置を左下にする
      DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { isExpanded = false },
        modifier = Modifier.align(alignment = Alignment.TopStart),
      ) {
        ShaderItem.entries.forEach {
          DropdownMenuItem(
            text = { Text(text = it.title) },
            onClick = {
              shaderItem = it
              isExpanded = false
            },
          )
        }
      }

      AnimatedContent(
        targetState = shaderItem,
        modifier = Modifier.fillMaxSize(),
      ) {
        when (it) {
          ShaderItem.AndroidDeveloper -> AndroidDevelopers(modifier = Modifier.fillMaxSize())
          ShaderItem.PulsingLight -> Sandbox(modifier = Modifier.fillMaxSize())
        }
      }
    }
  }
}
