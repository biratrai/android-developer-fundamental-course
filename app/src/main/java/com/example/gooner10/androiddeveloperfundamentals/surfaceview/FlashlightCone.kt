package com.example.gooner10.androiddeveloperfundamentals.surfaceview

/**
 * Custom SurfaceView class
 */
class FlashlightCone {
    private var x: Int? = null
    private var y: Int? = null
    private var radius: Int? = null

    constructor(viewWidth: Int, viewHeight: Int) {
        x = viewWidth
        y = viewHeight
        radius = (if (viewWidth <= viewHeight) (x!! / 3) else (y!! / 3))
    }

    fun update(newX: Int, newY: Int) {
        x = newX
        y = newY
    }
}