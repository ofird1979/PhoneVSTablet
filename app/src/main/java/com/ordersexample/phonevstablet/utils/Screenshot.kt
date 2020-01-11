package com.ordersexample.phonevstablet.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

class Screenshot {
   companion object{
       fun takescreenshot(v: View): Bitmap {
           val bitmap: Bitmap = Bitmap.createBitmap(v.width, v.height, Bitmap.Config.ARGB_8888)
           val canvas: Canvas = Canvas(bitmap)
           v.draw(canvas)
           return bitmap
       }

       fun takescreenshotOfRootView(v: View): Bitmap {
           return takescreenshot(v.rootView)
       }
   }

}