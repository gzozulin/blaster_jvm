package com.gzozulin.minigl.assembly

import com.gzozulin.minigl.api.*
import com.gzozulin.minigl.api2.*
import com.gzozulin.minigl.api2.Expression
import com.gzozulin.minigl.api2.GlMesh
import com.gzozulin.minigl.api2.GlProgram
import com.gzozulin.minigl.api2.GlShader
import com.gzozulin.minigl.api2.constv4
import com.gzozulin.minigl.api2.constm4

private val vertexSrc = """
    $VERSION
    $PRECISION_HIGH
    $DECLARATIONS_VERT
    $CONST_UNIF
    
    layout (location = 0) in vec3 aPosition;
    layout (location = 1) in vec2 aTexCoord;
    layout (location = 2) in vec3 aNormal;

    out vec2 vTexCoord;

    void main() {
        vTexCoord = aTexCoord;
        gl_Position = %MATRIX% * vec4(aPosition, 1.0);
    }
    
""".trimIndent()

private val fragmentSrs = """
    $VERSION
    $PRECISION_HIGH
    $DECLARATIONS_FRAG
    $CONST_UNIF
    
    in vec2 vTexCoord;

    layout (location = 0) out vec4 oFragColor;

    void main() {
        oFragColor = %COLOR%;
    }
""".trimIndent()

data class FlatTechnique2(
    val matrix: Expression<mat4>, val color: Expression<col4> = constv4(vec4(1f))) {

    private val vertShader = GlShader(backend.GL_VERTEX_SHADER,
        glExprSubstitute(vertexSrc, mapOf("MATRIX" to matrix)))

    private val fragShader = GlShader(backend.GL_FRAGMENT_SHADER,
        glExprSubstitute(fragmentSrs, mapOf("COLOR" to color)))

    internal val program = GlProgram(vertShader, fragShader)
}

fun glFlatTechniqueUse(flatTechnique: FlatTechnique2, callback: Callback) =
    glProgramUse(flatTechnique.program, callback)

fun glFlatTechniqueBind(flatTechnique: FlatTechnique2, callback: Callback) =
    glProgramBind(flatTechnique.program, callback)

fun glFlatTechniqueDraw(flatTechnique: FlatTechnique2, mesh: GlMesh) {
    flatTechnique.matrix.submit(flatTechnique.program)
    flatTechnique.color.submit(flatTechnique.program)
    glMeshBind(mesh) {
        glDrawTriangles(mesh)
    }
}

private val rect = glMeshCreateRect()
private val texture = glTextureCreate2D(2, 2, listOf(
    col4(col3().rose(), 1f),
    col4(col3().azure(), 1f),
    col4(col3().aquamarine(), 1f),
    col4(col3().chartreuse(), 1f)
))

private val uniformSampler = unift(texture)
private val namedTexCoords = namedv2("vTexCoord")

private val matrix = constm4(mat4().ortho(-5f, 5f, -5f, 5f, 1f, -1f))
private val color = tex(namedTexCoords, uniformSampler)

private val flatTechnique = FlatTechnique2(matrix, color)

private val window = GlWindow()

private fun use(callback: Callback) {
    glFlatTechniqueUse(flatTechnique) {
        glMeshUse(rect) {
            glTextureUse(texture) {
                callback.invoke()
            }
        }
    }
}

private fun draw() {
    glFlatTechniqueBind(flatTechnique) {
        glMeshBind(rect) {
            glTextureBind(texture) {
                glFlatTechniqueDraw(flatTechnique, rect)
            }
        }
    }
}

fun main() {
    window.create {
        use {
            window.show {
                draw()
            }
        }
    }
}