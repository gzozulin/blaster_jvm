package com.gzozulin.proj

import com.gzozulin.minigl.assembly.SpanVisibility
import com.gzozulin.minigl.assembly.TextPage
import org.kodein.di.instance
import kotlin.math.abs

private const val FRAMES_PER_SPAN = 3
private const val MILLIS_PER_FRAME = 16

class CasePlayback {
    private val repo: Repository by ProjApp.injector.instance()

    private var isAdvancingSpans = true // spans or timeout
    private var currentFrame = 0
    private var currentOrder = 0
    private var currentTimeout = 0L

    lateinit var currentPage: TextPage<OrderedSpan>
    var currentCenter = 0

    fun prepareOrder() {
        findCurrentPage()
        updateOrderVisibility()
        findOrderTimeout(repo.scenario)
    }

    private fun findCurrentPage() {
        for (renderedPage in repo.renderedPages) {
            for (span in renderedPage.spans) {
                if (span.order == currentOrder) {
                    currentPage = renderedPage
                    return
                }
            }
        }
        error("Did not found next page!")
    }

    private fun findOrderTimeout(scenario: List<ScenarioNode>) {
        for (scenarioNode in scenario) {
            if (scenarioNode.order == currentOrder) {
                currentTimeout = scenarioNode.timeout
                return
            } else if (scenarioNode.children != null) {
                findOrderTimeout(scenarioNode.children)
            }
        }
    }

    private fun updateOrderVisibility() {
        currentPage.spans
            .filter { it.order == currentOrder }
            .forEach { it.visibility = SpanVisibility.INVISIBLE }
    }

    fun updateSpans() {
        currentTimeout -= MILLIS_PER_FRAME
        if (isAdvancingSpans) {
            advanceSpans()
        } else {
            advanceTimeout()
        }
    }

    private fun advanceSpans() {
        currentFrame++
        if (currentFrame == FRAMES_PER_SPAN) {
            currentFrame = 0
            val found = findNextInvisibleSpan()
            if (found != null) {
                found.visibility = SpanVisibility.VISIBLE
                updateCenter(found)
            } else {
                isAdvancingSpans = false
            }
        }
    }

    private fun findNextInvisibleSpan() =
        currentPage.spans.firstOrNull {
            it.order == currentOrder &&
                    it.visibility == SpanVisibility.INVISIBLE &&
                    it.text.isNotBlank()
        }

    private fun updateCenter(span: OrderedSpan) {
        val newCenter = currentPage.findLineNo(span)
        val delta = newCenter - currentCenter
        if (abs(delta) >= LINES_TO_SHOW) {
            currentCenter += delta - (LINES_TO_SHOW - 1)
        }
    }

    private fun advanceTimeout() {
        if (currentTimeout <= 0) {
            isAdvancingSpans = true
            nextOrder()
            prepareOrder()
        }
    }

    private fun nextOrder() {
        currentOrder++
        if (currentOrder == repo.scenarioNodeCnt) {
            currentOrder = 0
            repo.renderedPages.forEach {
                it.spans.forEach { span -> span.visibility = SpanVisibility.GONE }
            }
        }
    }
}