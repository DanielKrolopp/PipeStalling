#version 330

uniform float time;
uniform float red;
uniform float green;
uniform float blue;

out vec4 gl_FragColor;

float noise(in vec2 coordinate)
{
    return fract(sin(dot(coordinate*time, vec2(12.9898, 78.233)))*43758.5453);
}

void main() {
    // Pass through our original color with full opacity.
    float luma = noise(gl_FragCoord.xy);
    luma *= .1f;
    gl_FragColor = vec4((1 - luma) * red, (1 - luma) * green, (1 - luma) * blue, 1);
    gl_FragDepth = -10;
}