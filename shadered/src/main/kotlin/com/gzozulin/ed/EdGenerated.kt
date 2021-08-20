package com.gzozulin.ed

import com.gzozulin.minigl.api.*

private val PATTERN_WHITESPACE = "\\s+".toPattern()

@Suppress("UNCHECKED_CAST")
internal fun edParseOperation(lineNo: Int, operation: String, heap: MutableMap<String, Expression<*>>): Expression<*> {
    val split = operation.split(PATTERN_WHITESPACE).toMutableList()
    return when (split.removeFirst()) {
                "error" -> error()
"itof" -> itof(edParseExpression(lineNo, split.removeFirst(), heap))
"ftoi" -> ftoi(edParseExpression(lineNo, split.removeFirst(), heap))
"dtof" -> dtof(edParseExpression(lineNo, split.removeFirst(), heap))
"addf" -> addf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"subf" -> subf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"mulf" -> mulf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"divf" -> divf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"eqv2" -> eqv2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"eqiv2" -> eqiv2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"eqv3" -> eqv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"eqv4" -> eqv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"signf" -> signf(edParseExpression(lineNo, split.removeFirst(), heap))
"absf" -> absf(edParseExpression(lineNo, split.removeFirst(), heap))
"sqrtf" -> sqrtf(edParseExpression(lineNo, split.removeFirst(), heap))
"sinf" -> sinf(edParseExpression(lineNo, split.removeFirst(), heap))
"cosf" -> cosf(edParseExpression(lineNo, split.removeFirst(), heap))
"tanf" -> tanf(edParseExpression(lineNo, split.removeFirst(), heap))
"powf" -> powf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"minf" -> minf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"maxf" -> maxf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"clampf" -> clampf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"smoothf" -> smoothf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"floorf" -> floorf(edParseExpression(lineNo, split.removeFirst(), heap))
"fractf" -> fractf(edParseExpression(lineNo, split.removeFirst(), heap))
"schlickf" -> schlickf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"remapf" -> remapf(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"v2" -> v2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"ftov2" -> ftov2(edParseExpression(lineNo, split.removeFirst(), heap))
"v2zero" -> v2zero()
"addv2" -> addv2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"subv2" -> subv2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"mulv2" -> mulv2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"divv2" -> divv2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"mulv2f" -> mulv2f(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"divv2f" -> divv2f(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"addv2f" -> addv2f(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"subv2f" -> subv2f(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"dotv2" -> dotv2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"getxv2" -> getxv2(edParseExpression(lineNo, split.removeFirst(), heap))
"getyv2" -> getyv2(edParseExpression(lineNo, split.removeFirst(), heap))
"lenv2" -> lenv2(edParseExpression(lineNo, split.removeFirst(), heap))
"indexv3" -> indexv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"v3" -> v3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"v2tov3" -> v2tov3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"ftov3" -> ftov3(edParseExpression(lineNo, split.removeFirst(), heap))
"v3zero" -> v3zero()
"v3one" -> v3one()
"v3front" -> v3front()
"v3back" -> v3back()
"v3left" -> v3left()
"v3right" -> v3right()
"v3up" -> v3up()
"v3down" -> v3down()
"v3white" -> v3white()
"v3black" -> v3black()
"v3ltGrey" -> v3ltGrey()
"v3grey" -> v3grey()
"v3dkGrey" -> v3dkGrey()
"v3red" -> v3red()
"v3green" -> v3green()
"v3blue" -> v3blue()
"v3yellow" -> v3yellow()
"v3magenta" -> v3magenta()
"v3cyan" -> v3cyan()
"v3orange" -> v3orange()
"v3rose" -> v3rose()
"v3violet" -> v3violet()
"v3azure" -> v3azure()
"v3aquamarine" -> v3aquamarine()
"v3chartreuse" -> v3chartreuse()
"xyv3" -> xyv3(edParseExpression(lineNo, split.removeFirst(), heap))
"xzv3" -> xzv3(edParseExpression(lineNo, split.removeFirst(), heap))
"yzv3" -> yzv3(edParseExpression(lineNo, split.removeFirst(), heap))
"absv3" -> absv3(edParseExpression(lineNo, split.removeFirst(), heap))
"negv3" -> negv3(edParseExpression(lineNo, split.removeFirst(), heap))
"dotv3" -> dotv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"crossv3" -> crossv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"addv3" -> addv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"subv3" -> subv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"subv3f" -> subv3f(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"mulv3" -> mulv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"mulv3f" -> mulv3f(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"divv3f" -> divv3f(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"divv3" -> divv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"powv3" -> powv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"mixv3" -> mixv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"maxv3" -> maxv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"minv3" -> minv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"lenv3" -> lenv3(edParseExpression(lineNo, split.removeFirst(), heap))
"sqrtv3" -> sqrtv3(edParseExpression(lineNo, split.removeFirst(), heap))
"lensqv3" -> lensqv3(edParseExpression(lineNo, split.removeFirst(), heap))
"normv3" -> normv3(edParseExpression(lineNo, split.removeFirst(), heap))
"lerpv3" -> lerpv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"reflectv3" -> reflectv3(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"v4" -> v4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"v3tov4" -> v3tov4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"ftov4" -> ftov4(edParseExpression(lineNo, split.removeFirst(), heap))
"v4tov3" -> v4tov3(edParseExpression(lineNo, split.removeFirst(), heap))
"v4zero" -> v4zero()
"v4one" -> v4one()
"addv4" -> addv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"subv4" -> subv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"mulv4" -> mulv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"mulv4f" -> mulv4f(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"divv4" -> divv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"divv4f" -> divv4f(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"getxv4" -> getxv4(edParseExpression(lineNo, split.removeFirst(), heap))
"getyv4" -> getyv4(edParseExpression(lineNo, split.removeFirst(), heap))
"getzv4" -> getzv4(edParseExpression(lineNo, split.removeFirst(), heap))
"getwv4" -> getwv4(edParseExpression(lineNo, split.removeFirst(), heap))
"getrv4" -> getrv4(edParseExpression(lineNo, split.removeFirst(), heap))
"getgv4" -> getgv4(edParseExpression(lineNo, split.removeFirst(), heap))
"getbv4" -> getbv4(edParseExpression(lineNo, split.removeFirst(), heap))
"getav4" -> getav4(edParseExpression(lineNo, split.removeFirst(), heap))
"setxv4" -> setxv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"setyv4" -> setyv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"setzv4" -> setzv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"setwv4" -> setwv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"setrv4" -> setrv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"setgv4" -> setgv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"setbv4" -> setbv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"setav4" -> setav4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"iv2" -> iv2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"iv2zero" -> iv2zero()
"iv2tov2" -> iv2tov2(edParseExpression(lineNo, split.removeFirst(), heap))
"iv2tov4" -> iv2tov4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"getxiv2" -> getxiv2(edParseExpression(lineNo, split.removeFirst(), heap))
"getyiv2" -> getyiv2(edParseExpression(lineNo, split.removeFirst(), heap))
"getuiv2" -> getuiv2(edParseExpression(lineNo, split.removeFirst(), heap))
"getviv2" -> getviv2(edParseExpression(lineNo, split.removeFirst(), heap))
"tile" -> tile(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"scalem2" -> scalem2(edParseExpression(lineNo, split.removeFirst(), heap))
"transformv2" -> transformv2(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"m4ident" -> m4ident()
"mulm4" -> mulm4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"transformv4" -> transformv4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"translatem4" -> translatem4(edParseExpression(lineNo, split.removeFirst(), heap))
"rotatem4" -> rotatem4(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"scalem4" -> scalem4(edParseExpression(lineNo, split.removeFirst(), heap))
"rayBack" -> rayBack()
"rayPoint" -> rayPoint(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"sdXZPlane" -> sdXZPlane(edParseExpression(lineNo, split.removeFirst(), heap))
"sdSphere" -> sdSphere(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"sdBox" -> sdBox(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"sdCappedCylinder" -> sdCappedCylinder(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"sdCone" -> sdCone(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"sdTriPrism" -> sdTriPrism(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"opUnion" -> opUnion(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"opSubtraction" -> opSubtraction(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"opIntersection" -> opIntersection(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"rndf" -> rndf(edParseExpression(lineNo, split.removeFirst(), heap))
"rndv2" -> rndv2(edParseExpression(lineNo, split.removeFirst(), heap))
"rndv3" -> rndv3(edParseExpression(lineNo, split.removeFirst(), heap))
"rndv4" -> rndv4(edParseExpression(lineNo, split.removeFirst(), heap))
"seedRandom" -> seedRandom(edParseExpression(lineNo, split.removeFirst(), heap))
"seededRndf" -> seededRndf()
"randomInUnitSphere" -> randomInUnitSphere()
"randomInUnitDisk" -> randomInUnitDisk()
"sampler" -> sampler(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"texel" -> texel(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"samplerq" -> samplerq(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"fragmentColorRt" -> fragmentColorRt(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"gammaSqrt" -> gammaSqrt(edParseExpression(lineNo, split.removeFirst(), heap))
"luminosity" -> luminosity(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"diffuseContrib" -> diffuseContrib(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"halfVector" -> halfVector(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"specularContrib" -> specularContrib(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"lightContrib" -> lightContrib(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"pointLightContrib" -> pointLightContrib(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"dirLightContrib" -> dirLightContrib(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"shadingFlat" -> shadingFlat(edParseExpression(lineNo, split.removeFirst(), heap))
"shadingPhong" -> shadingPhong(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"getNormalFromMap" -> getNormalFromMap(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"distributionGGX" -> distributionGGX(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"geometrySchlickGGX" -> geometrySchlickGGX(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"geometrySmith" -> geometrySmith(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"fresnelSchlick" -> fresnelSchlick(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"shadingPbr" -> shadingPbr(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"sandConvert" -> sandConvert(edParseExpression(lineNo, split.removeFirst(), heap))
"sandPhysics" -> sandPhysics(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"sandSolver" -> sandSolver(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"sandDraw" -> sandDraw(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))
"raymarcher" -> raymarcher(edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap),edParseExpression(lineNo, split.removeFirst(), heap))

        "namedTexCoordsV2" -> namedTexCoordsV2()
        "namedTexCoordsV3" -> namedTexCoordsV3()
        "namedGlFragCoordV2" -> namedGlFragCoordV2()
        "cachev4" -> cachev4(edParseExpression(lineNo, split.removeFirst(), heap))
        "discard" -> discard<Any>()
        "ifexp" -> ifexp<Any>(edParseExpression(lineNo, split.removeFirst(), heap), edParseExpression(lineNo, split.removeFirst(), heap), edParseExpression(lineNo, split.removeFirst(), heap))
        "eqexp" -> eqexp<Any>(edParseExpression(lineNo, split.removeFirst(), heap), edParseExpression(lineNo, split.removeFirst(), heap))
        "moreexp" -> moreexp<Any>(edParseExpression(lineNo, split.removeFirst(), heap), edParseExpression(lineNo, split.removeFirst(), heap))
        "notexp" -> notexp(edParseExpression(lineNo, split.removeFirst(), heap))
        else -> error("Unknown operation!")
    }
}