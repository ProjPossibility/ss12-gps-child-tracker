package com.gpsChildTracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class kidLocationOverlay extends Overlay {
	
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		/*
		super.draw(canvas, mapView, shadow);
		Paint paint = new Paint();
		// Converts lat/lng-Point to OUR coordinates on the screen.
		Point myScreenCoords = new Point();
		mapView.getProjection().toPixels(p, myScreenCoords);
		paint.setStrokeWidth(1);
		paint.setARGB(255, 255, 255, 255);
		paint.setStyle(Paint.Style.STROKE);
		Bitmap bmp = BitmapFactory.decodeResource( getResources(), R.drawable.marker);
		canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
		canvas.drawText("Here I am...", myScreenCoords.x, myScreenCoords.y, paint);
		*/
		return true;
		}
}
