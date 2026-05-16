// https://developer.android.com/develop/ui/views/graphics/agsl/using-agsl#using_runtimeshader_with_jetpack_compose

package app.kaito_dogi.agsl_sample.shader

import android.graphics.RuntimeShader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ShaderBrush

private const val COLOR_SHADER_SRC =
  """uniform float2 iResolution;
   half4 main(float2 fragCoord) {
   float2 scaled = fragCoord/iResolution.xy;
   return half4(scaled, 0, 1);
}"""

// created as top level constants
val colorShader = RuntimeShader(COLOR_SHADER_SRC)
val shaderBrush = ShaderBrush(shader = colorShader)

@Composable
internal fun Sample(
  modifier: Modifier = Modifier,
) {
  Canvas(
    modifier = modifier.fillMaxSize(),
  ) {
    colorShader.setFloatUniform(
      "iResolution",
      size.width,
      size.height,
    )
    drawCircle(brush = shaderBrush)
  }
}
