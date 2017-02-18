#version 330

uniform sampler2D tex;

in vec4 pass_Color;
in vec2 pass_Tex;

out vec4 gl_FragColor;

void main() {
    // Pass through our original color with full opacity.
    gl_FragColor = texture2D(tex, pass_Tex.xy) + vec4(1,1,1,1);
}