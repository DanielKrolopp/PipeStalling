#version 150

uniform sampler2D textureSampler;

in vec2 pass_TexCoord;

out vec4 gl_FragColor;

void main() {
    // Pass through our original color with full opacity.
    //vec4 color = texture2D(0, gl_TexCoord[0].st);
    //color.w = 1;
    gl_FragColor = vec4(1,1,1,1);
}