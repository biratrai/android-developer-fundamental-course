package com.example.gooner10.androiddeveloperfundamentals.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context

class WidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        for (appWidgetId in appWidgetIds!!) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
