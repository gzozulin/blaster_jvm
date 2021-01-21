package com.gzozulin.minigl.assembly

import com.gzozulin.minigl.assets.texturesLib
import com.gzozulin.minigl.gl.*

data class TextFragment(val text: String,
                        val background: col3 = col3().black(),
                        val color: col3 = col3().white())

data class TextLine(val fragments: List<TextFragment>)
data class TextPage(val lines: List<TextLine>)

class SimpleTextTechnique(color: Expression<vec4> = propv4(vec4(1f)),
                          backgr: Expression<vec4> = propv4(vec4(0f))) : GlResource() {

    private val rect = GlMesh.rect(-1f, 1f, -1f, 1f)
    private val font = texturesLib.loadTexture("textures/font.png")

    private val texCoord = constv2(SimpleVarrying.vTexCoord.name)

    private val propIdentityM = propm4(mat4().identity())
    private val propProjM = propm4(mat4().ortho(-1f, 1f, -1f, 1f, -1f, 1f))

    private val unifTileUV = unifv2i(vec2i(12))
    private val texCoordTiled = tile(texCoord, unifTileUV, propv2i(vec2i(16)))

    private val unifFont = unifsampler(font)

    private val result = ifv4(eq(texv4(texCoordTiled, unifFont), propv4(vec4(1f))), color, backgr)

    private val simpleTechnique = SimpleTechnique(propIdentityM, propIdentityM, propProjM, result)

    init {
        addChildren(simpleTechnique, rect, font)
    }

    private var counter = 0
    private var u = 0
    private var v = 0

    fun page(page: TextPage) {
        if (counter % 30 == 0) {
            u += 1
            if (u == 15) {
                u = 0
                v += 1
                if (v == 15) {
                    v = 0
                }
            }
            unifTileUV.value = vec2i(u, v)
        }
        glBind(font) {
            simpleTechnique.draw {
                for (line in page.lines) {
                    for (fragment in line.fragments) {
                        simpleTechnique.instance(rect)
                    }
                }
            }
        }
        counter++
    }
}

private val window = GlWindow()

private val examplePage = TextPage(listOf(TextLine(listOf(TextFragment("h")))))

private val floor = texturesLib.loadTexture("textures/floor.jpg")
private val grass = texturesLib.loadTexture("textures/grass.jpg")

private val texCoords = constv2(SimpleVarrying.vTexCoord.name)
private val floorSampler = texv4(texCoords, unifsampler(floor))
private val grassSampler = texv4(texCoords, unifsampler(grass))

private val simpleTextTechnique = SimpleTextTechnique(floorSampler, grassSampler)

fun main() {
    window.create(isHoldingCursor = false) {
        glUse(simpleTextTechnique, floor, grass) {
            glBind(floor, grass) {
                window.show {
                    glClear()
                    simpleTextTechnique.page(examplePage)
                }
            }
        }
    }
}