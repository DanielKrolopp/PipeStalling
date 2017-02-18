#version 330

uniform sampler2D tex;

in vec4 pass_Color;

out vec4 gl_FragColor;

void main() {
    // Pass through our original color with full opacity.
    gl_FragColor = pass_Color;
}