package com.example.gooner10.androiddeveloperfundamentals.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import com.example.gooner10.androiddeveloperfundamentals.R

class WidgetProvider : AppWidgetProvider() {
    val TAG = WidgetProvider::class.java.simpleName

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        for (appWidgetId in appWidgetIds!!) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int) {

        // Construct the RemoteViews object.
        val views = RemoteViews(context!!.packageName,
                R.layout.app_widget)
//        views.setTextViewText(R.id.appwidget_update,
//                context.getResources().getString(
//                        R.string.date_count_format, 1, dateString))
        Log.d(TAG, "appWidgetManager: " + appWidgetManager)
        Log.d(TAG, "view: " + views)
        Log.d(TAG, "appID: " + appWidgetId)
        // Instruct the widget manager to update the widget.
        appWidgetManager!!.updateAppWidget(appWidgetId, views)
    }
}
