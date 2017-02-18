#version 150

in  vec4 ex_Color;
out vec4 gl_FragColor;

void main() {
    // Pass through our original color with full opacity.
    gl_FragColor = ex_Color;
}