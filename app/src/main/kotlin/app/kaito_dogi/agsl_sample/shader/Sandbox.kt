// https://qiita.com/doxas/items/f3f8bf868f12851ea143

package app.kaito_dogi.agsl_sample.shader

import android.graphics.RuntimeShader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ShaderBrush
import kotlinx.coroutines.android.awaitFrame

private const val COLOR_SHADER_SRC = """
uniform float iTime;
uniform float2 iResolution;

half4 main(float2 fragCoord) {
  float2 p = (fragCoord * 2.0 - iResolution) / min(iResolution.x, iResolution.y);
  float l = 0.1 / length(p) * (1.0 + sin(iTime * 2.0)) / 2.0;
  return half4(half3(l), 1.0);
}
"""

@Composable
internal fun Sandbox(
  modifier: Modifier = Modifier,
) {
  val shader = remember { RuntimeShader(COLOR_SHADER_SRC) }
  val brush = remember(key1 = shader) { ShaderBrush(shader) }

  val time = remember { mutableFloatStateOf(value = 0f) }

  LaunchedEffect(key1 = Unit) {
    val start = awaitFrame()
    while (true) {
      val frame = awaitFrame()
      time.floatValue = (frame - start) / 1_000_000_000f
    }
  }

  Canvas(
    modifier = modifier.fillMaxSize(),
  ) {
    shader.setFloatUniform(
      "iResolution",
      size.width,
      size.height,
    )

    shader.setFloatUniform(
      "iTime",
      time.floatValue,
    )

    drawRect(
      brush = brush,
      size = size,
    )
  }
}
