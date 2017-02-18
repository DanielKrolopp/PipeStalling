#version 330

uniform float time;

out vec4 gl_FragColor;

float noise(in vec2 coordinate)
{
    return fract(sin(dot(coordinate*time, vec2(12.9898, 78.233)))*43758.5453);
}

void main() {
    // Pass through our original color with full opacity.
    float luma = noise(gl_FragCoord.xy);
    luma *= .1f;
    gl_FragColor = vec4(1 - luma, 1 - luma, .8 - luma, 1);
    gl_FragDepth = 0;
}