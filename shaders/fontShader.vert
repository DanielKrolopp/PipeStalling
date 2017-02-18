#version 330

in vec3 in_Position;
in vec4 in_Color;
in vec3 in_Normal;
in vec2 in_TexCoord;

out vec4 pass_Color;
out vec2 pass_Tex;

varying vec2 vTexCoord;

void main() {

	vec3 pos = vec3(in_Position.x / 960 - 1, 1 - in_Position.y / 540, in_Position.z);
    gl_Position = vec4(pos, 1.0);
    
    pass_Color = in_Color;
    pass_Tex = in_TexCoord;
}