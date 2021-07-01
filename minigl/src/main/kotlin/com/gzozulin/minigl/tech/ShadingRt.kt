package com.gzozulin.minigl.tech

import com.gzozulin.minigl.api.*
import com.gzozulin.minigl.scene.*
import kotlin.system.exitProcess

private const val FRAMES_CNT = Int.MAX_VALUE
private const val SAMPLES_CNT = 128
private const val SAMPLES_PER_BATCH = 1
private const val BOUNCES_CNT = 3

private val unifTime = unifi { System.currentTimeMillis().toInt() }

data class ShadingRt(val window: GlWindow,
                     val sampleCnt: Expression<Int>, val rayBounces: Expression<Int>,
                     val eye: Expression<vec3>, val center: Expression<vec3>, val up: Expression<vec3>,
                     val fovy: Expression<Float>, val aspect: Expression<Float>,
                     val aperture: Expression<Float>, val focus: Expression<Float>) {
    internal val rect = glMeshCreateRect()
    private val matrix = constm4(mat4().orthoBox())

    private val colorSampled = fragmentColorRt(
        unifTime,
        sampleCnt, rayBounces,
        eye, center, up,
        fovy, aspect, aperture, focus,
        namedTexCoordsV2())

    internal var currentBuffer = 0
    internal val buffer0 = TechniqueRtt(window)
    internal val buffer1 = TechniqueRtt(window)
    internal val fromBuffer = unifs()
    private val colorAdded = divv4f(addv4(sampler(fromBuffer), colorSampled), constf(2f)) // do not div if empty
    internal val shadingSamples = ShadingFlat(matrix, colorAdded)

    internal val unifPresent = unifs()
    internal val shadingPresent = ShadingFlat(matrix, sampler(unifPresent))
}

private data class MaterialsCollection(val lambertians: List<LambertianMaterial>,
                                       val metallics: List<MetallicMaterial>,
                                       val dielectrics: List<DielectricMaterial>,
                                       val lookup: Map<RtMaterial, Int>)

private fun glShadingRtMaterialType(material: RtMaterial) = when (material) {
    is LambertianMaterial -> MaterialType.LAMBERTIAN.ordinal
    is MetallicMaterial -> MaterialType.METALLIC.ordinal
    is DielectricMaterial -> MaterialType.DIELECTRIC.ordinal
    else -> error("Unknown material!")
}

private fun glShadingRtCollectMaterials(hitables: List<Any>): MaterialsCollection {
    val lambertians = mutableListOf<LambertianMaterial>()
    val metallics = mutableListOf<MetallicMaterial>()
    val dielectrics = mutableListOf<DielectricMaterial>()
    hitables.forEach { hitable ->
        when (hitable) {
            is Sphere -> {
                when (hitable.material) {
                    is LambertianMaterial -> lambertians.add(hitable.material)
                    is MetallicMaterial -> metallics.add(hitable.material)
                    is DielectricMaterial -> dielectrics.add(hitable.material)
                    else -> error("Unknown material!")
                }
            }
            else -> error("Unknown hitable!")
        }
    }

    val distinctLambertians = lambertians.distinct()
    check(distinctLambertians.size <= MAX_LAMBERTIANS) { "Too many Lambertian materials" }
    val distinctMetallics = metallics.distinct()
    check(distinctMetallics.size <= MAX_METALLICS) { "Too many Metallic materials" }
    val distinctDielectrics = dielectrics.distinct()
    check(distinctDielectrics.size <= MAX_DIELECTRICS) { "Too many Dielectric materials" }

    val lookup = mutableMapOf<RtMaterial, Int>()
    distinctLambertians.forEachIndexed { index, rtMaterial ->
        lookup[rtMaterial] = index
    }
    distinctMetallics.forEachIndexed { index, rtMaterial ->
        lookup[rtMaterial] = index
    }
    distinctDielectrics.forEachIndexed { index, rtMaterial ->
        lookup[rtMaterial] = index
    }
    return MaterialsCollection(distinctLambertians, distinctMetallics, distinctDielectrics, lookup)
}

internal fun glShadingRtSubmitHitables(program: GlProgram, hitables: List<Any>) {
    check(hitables.size <= MAX_HITABLES) { "More hitables than defined in shader!" }
    glProgramCheckBound(program)

    val materialsCollection = glShadingRtCollectMaterials(hitables)
    materialsCollection.lambertians.forEachIndexed { index, lambertian ->
        glProgramArrayUniform(program, "uLambertianMaterials[%d].albedo", index, lambertian.albedo)
    }
    materialsCollection.metallics.forEachIndexed { index, metallic ->
        glProgramArrayUniform(program, "uMetallicMaterials[%d].albedo", index, metallic.albedo)
    }
    materialsCollection.dielectrics.forEachIndexed { index, metallic ->
        glProgramArrayUniform(program, "uDielectricMaterials[%d].reflectiveIndex", index, metallic.reflectiveIdx)
    }

    var spheresCnt = 0
    var hitablesCnt = 0
    hitables.forEach { hitable ->
        when (hitable) {
            is Sphere -> {
                check(spheresCnt + 1 <= MAX_SPHERES) { "More spheres than defined in shader!" }
                glProgramArrayUniform(program, "uSpheres[%d].center", spheresCnt, hitable.center)
                glProgramArrayUniform(program, "uSpheres[%d].radius", spheresCnt, hitable.radius)
                glProgramArrayUniform(program, "uSpheres[%d].materialType", spheresCnt,
                    glShadingRtMaterialType(hitable.material))
                glProgramArrayUniform(program, "uSpheres[%d].materialIndex", spheresCnt,
                    materialsCollection.lookup[hitable.material]!!)
                glProgramArrayUniform(program, "uHitables[%d].type",  hitablesCnt, HitableType.SPHERE.ordinal)
                glProgramArrayUniform(program, "uHitables[%d].index", hitablesCnt, spheresCnt)
                spheresCnt++
                hitablesCnt++
            }
            else -> error("Unknown Hitable!")
        }
    }
    glProgramUniform(program, "uHitablesCnt", hitablesCnt)
}

fun glShadingRtUse(shadingRt: ShadingRt, callback: Callback) {
    glShadingFlatUse(shadingRt.shadingSamples) {
        glShadingFlatUse(shadingRt.shadingPresent) {
            glRttUse(shadingRt.buffer0) {
                glRttUse(shadingRt.buffer1) {
                    glMeshUse(shadingRt.rect) {
                        callback.invoke()
                    }
                }
            }
        }
    }
}

fun glShadingRtDraw(shadingRt: ShadingRt, hitables: List<Any>, callback: Callback) {
    glShadingFlatDraw(shadingRt.shadingSamples) {
        glShadingRtSubmitHitables(shadingRt.shadingSamples.program, hitables)
        callback.invoke()
    }
}

fun glShadingRtSamples(shadingRt: ShadingRt) {
    val to: TechniqueRtt
    val from: TechniqueRtt
    when (shadingRt.currentBuffer) {
        0 -> {
            to = shadingRt.buffer0
            from = shadingRt.buffer1
            shadingRt.currentBuffer = 1
        }
        1 -> {
            to = shadingRt.buffer1
            from = shadingRt.buffer0
            shadingRt.currentBuffer = 0
        }
        else -> error("Wtf!?")
    }
    glRttDraw(to) {
        glTextureBind(from.color) {
            shadingRt.fromBuffer.value = from.color
            glShadingFlatInstance(shadingRt.shadingSamples, shadingRt.rect)
        }
    }
    glShadingFlatDraw(shadingRt.shadingPresent) {
        glTextureBind(to.color) {
            shadingRt.unifPresent.value = to.color
            glShadingFlatInstance(shadingRt.shadingPresent, shadingRt.rect)
        }
    }
}

private val window = GlWindow(isFullscreen = false)
//private val capturer = Capturer(window)

private val controller = ControllerScenic(
    positions = listOf(
        vec3(-5f, 0.7f, -5f),
        vec3( 5f, 0.7f, -5f),
        vec3( 5f, 0.7f,  5f),
        vec3(-5f, 0.7f,  5f),
    ),
    points = listOf(vec3()))

private val sampleCnt = consti(SAMPLES_PER_BATCH)
private val rayBounces = consti(BOUNCES_CNT)

private val eye = unifv3(vec3(-5f, 0.7f, -5f))
private val center = unifv3(vec3())
private val up = constv3(vec3().up())

private val fovy = constf(radf(90.0f))
private val aspect = constf(window.width.toFloat() / window.height.toFloat())
private val aperture = constf(0f)
private val focus = constf(1f)

private val shadingRt = ShadingRt(window, sampleCnt, rayBounces, eye, center, up, fovy, aspect, aperture, focus)

private val lambertians = (0 until 15).map { LambertianMaterial(vec3().rand())  }.toList()
private val metallics =   (0 until 16).map { MetallicMaterial(vec3().rand())    }.toList()
private val dielectrics = (0 until 16).map { DielectricMaterial(randf(1f, 10f)) }.toList()

private fun sphereRandom() = Sphere(
    vec3().rand(vec3(-5f, 0.2f, -5f), vec3(5f, 0.2f, 5f)), 0.2f,
    when(randi(3)) {
        0 -> lambertians.random()
        1 -> metallics.random()
        2 -> dielectrics.random()
        else -> error("wtf?!")
    })

private val hitables = listOf(
    Sphere(vec3(0f, -1000f, 0f), 1000f, LambertianMaterial(vec3().dkGrey())),
    Sphere(vec3(0f, 1f, 0f), 1f, dielectrics.random()),
    Sphere(vec3(-4f, 1f, 0f), 1f, lambertians.random()),
    Sphere(vec3(4f, 1f, 0f), 1f, metallics.random()),
    *(1..80).map { sphereRandom() }.toTypedArray()
)

private var statsDumped = false
private fun glShadingRtDumpStats(start: Long, stop: Long) {
    if (!statsDumped) {
        statsDumped = true
        val millisTotal = stop - start
        val millisPerFrame = millisTotal / FRAMES_CNT
        println(String.format("Job took: %.2f sec, per frame: %.2f sec, 10 sec video will take: ~%.2f min",
            millisTotal.toFloat() / 1000f,
            millisPerFrame.toFloat() / 1000f,
            10f * 60f * millisPerFrame.toFloat() / 1000f / 60f))
    }
}

fun main() {
    window.create {
        glShadingRtUse(shadingRt) {
            glShadingRtDraw(shadingRt, hitables) {
                var frame = 0
                //capturer.capture {
                    val start = System.currentTimeMillis()
                    window.show {
                        /*controller.apply { position, direction ->
                            eye.value = position
                            center.value = vec3().set(position).add(direction)
                        }*/
                        if (frame < FRAMES_CNT) {
                            glShadingRtSamples(shadingRt)
                            //capturer.addFrame()
                            frame++
                        } else {
                            val stop = System.currentTimeMillis()
                            glShadingRtDumpStats(start, stop)
                            exitProcess(0)
                        }
                    }
                //}
            }
        }
    }
}