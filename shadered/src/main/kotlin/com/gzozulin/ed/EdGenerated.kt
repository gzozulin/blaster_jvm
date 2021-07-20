package com.gzozulin.ed

import com.gzozulin.minigl.api.*

internal fun edParseReference(reference: String, params: MutableList<String>, heap: Map<String, Expression<*>>) =
    when (reference) {
                "flagError" -> flagError()
"sqrtv" -> sqrtv(edParseParam(params.removeFirst(), heap))
"sinv" -> sinv(edParseParam(params.removeFirst(), heap))
"cosv" -> cosv(edParseParam(params.removeFirst(), heap))
"tanv" -> tanv(edParseParam(params.removeFirst(), heap))
"powv" -> powv(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"minv" -> minv(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"maxv" -> maxv(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"clamp" -> clamp(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"smoothstep" -> smoothstep(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"schlick" -> schlick(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"length" -> length(edParseParam(params.removeFirst(), heap))
"itof" -> itof(edParseParam(params.removeFirst(), heap))
"ftoi" -> ftoi(edParseParam(params.removeFirst(), heap))
"dtof" -> dtof(edParseParam(params.removeFirst(), heap))
"addf" -> addf(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"subf" -> subf(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"mulf" -> mulf(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"divf" -> divf(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"v2" -> v2(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"ftov2" -> ftov2(edParseParam(params.removeFirst(), heap))
"v2zero" -> v2zero()
"subv2f" -> subv2f(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"iv2" -> iv2(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"getxv2" -> getxv2(edParseParam(params.removeFirst(), heap))
"getyv2" -> getyv2(edParseParam(params.removeFirst(), heap))
"getuv2" -> getuv2(edParseParam(params.removeFirst(), heap))
"getvv2" -> getvv2(edParseParam(params.removeFirst(), heap))
"tile" -> tile(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"indexv3" -> indexv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"v3" -> v3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"v2tov3" -> v2tov3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"ftov3" -> ftov3(edParseParam(params.removeFirst(), heap))
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
"negv3" -> negv3(edParseParam(params.removeFirst(), heap))
"dotv3" -> dotv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"crossv3" -> crossv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"addv3" -> addv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"subv3" -> subv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"mulv3" -> mulv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"mulv3f" -> mulv3f(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"divv3f" -> divv3f(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"divv3" -> divv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"powv3" -> powv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"mixv3" -> mixv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"lenv3" -> lenv3(edParseParam(params.removeFirst(), heap))
"lensqv3" -> lensqv3(edParseParam(params.removeFirst(), heap))
"normv3" -> normv3(edParseParam(params.removeFirst(), heap))
"lerpv3" -> lerpv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"reflectv3" -> reflectv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"v4" -> v4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"v3tov4" -> v3tov4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"ftov4" -> ftov4(edParseParam(params.removeFirst(), heap))
"v4zero" -> v4zero()
"addv4" -> addv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"subv4" -> subv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"mulv4" -> mulv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"mulv4f" -> mulv4f(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"divv4" -> divv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"divv4f" -> divv4f(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"getxv4" -> getxv4(edParseParam(params.removeFirst(), heap))
"getyv4" -> getyv4(edParseParam(params.removeFirst(), heap))
"getzv4" -> getzv4(edParseParam(params.removeFirst(), heap))
"getwv4" -> getwv4(edParseParam(params.removeFirst(), heap))
"getrv4" -> getrv4(edParseParam(params.removeFirst(), heap))
"getgv4" -> getgv4(edParseParam(params.removeFirst(), heap))
"getbv4" -> getbv4(edParseParam(params.removeFirst(), heap))
"getav4" -> getav4(edParseParam(params.removeFirst(), heap))
"setxv4" -> setxv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"setyv4" -> setyv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"setzv4" -> setzv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"setwv4" -> setwv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"setrv4" -> setrv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"setgv4" -> setgv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"setbv4" -> setbv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"setav4" -> setav4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"m4ident" -> m4ident()
"mulm4" -> mulm4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"transformv4" -> transformv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"translatem4" -> translatem4(edParseParam(params.removeFirst(), heap))
"rotatem4" -> rotatem4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"scalem4" -> scalem4(edParseParam(params.removeFirst(), heap))
"rayBack" -> rayBack()
"rayPoint" -> rayPoint(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"eqv2" -> eqv2(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"eqv3" -> eqv3(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"eqv4" -> eqv4(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"rndf" -> rndf(edParseParam(params.removeFirst(), heap))
"rndv2" -> rndv2(edParseParam(params.removeFirst(), heap))
"rndv3" -> rndv3(edParseParam(params.removeFirst(), heap))
"rndv4" -> rndv4(edParseParam(params.removeFirst(), heap))
"seedRandom" -> seedRandom(edParseParam(params.removeFirst(), heap))
"seededRndf" -> seededRndf()
"randomInUnitSphere" -> randomInUnitSphere()
"randomInUnitDisk" -> randomInUnitDisk()
"errorHandler" -> errorHandler(edParseParam(params.removeFirst(), heap))
"luminosity" -> luminosity(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"diffuseContrib" -> diffuseContrib(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"halfVector" -> halfVector(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"specularContrib" -> specularContrib(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"lightContrib" -> lightContrib(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"pointLightContrib" -> pointLightContrib(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"dirLightContrib" -> dirLightContrib(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"shadingFlat" -> shadingFlat(edParseParam(params.removeFirst(), heap))
"shadingPhong" -> shadingPhong(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"getNormalFromMap" -> getNormalFromMap(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"distributionGGX" -> distributionGGX(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"geometrySchlickGGX" -> geometrySchlickGGX(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"geometrySmith" -> geometrySmith(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"fresnelSchlick" -> fresnelSchlick(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"shadingPbr" -> shadingPbr(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"fragmentColorRt" -> fragmentColorRt(edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap),edParseParam(params.removeFirst(), heap))
"gammaSqrt" -> gammaSqrt(edParseParam(params.removeFirst(), heap))

        "namedTexCoordsV2" -> namedTexCoordsV2()
        "namedTexCoordsV3" -> namedTexCoordsV3()
        "namedGlFragCoordV2" -> namedGlFragCoordV2()
        "cachev4" -> cachev4(edParseParam(params.removeFirst(), heap))
        "texel" -> texel(edParseParam(params.removeFirst(), heap), edParseParam(params.removeFirst(), heap))
        "sampler" -> sampler(edParseParam(params.removeFirst(), heap), edParseParam(params.removeFirst(), heap))
        "samplerq" -> samplerq(edParseParam(params.removeFirst(), heap), edParseParam(params.removeFirst(), heap))
        "discard" -> discard()
        "ifexp" -> ifexp(edParseParam(params.removeFirst(), heap), edParseParam(params.removeFirst(), heap), edParseParam(params.removeFirst(), heap))
        //"more" -> more(edParseParam(params.removeFirst(), heap), edParseParam(params.removeFirst(), heap))
        "not" -> not(edParseParam(params.removeFirst(), heap))
        else -> error("Unknown operation! " + reference)
    }