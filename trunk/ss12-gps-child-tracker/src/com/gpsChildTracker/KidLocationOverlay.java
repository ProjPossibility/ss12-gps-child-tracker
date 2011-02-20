package com.gpsChildTracker;

//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class KidLocationOverlay extends Overlay {
	
	GeoPoint point;
	
	public KidLocationOverlay(GeoPoint p){
		point = p;
	}
	
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		
		super.draw(canvas, mapView, shadow);
		Paint paint = new Paint();
		// Converts lat/lng-Point to OUR coordinates on the screen.
		Point myScreenCoords = new Point();
		mapView.getProjection().toPixels(point, myScreenCoords);
		paint.setStrokeWidth(15);
		paint.setARGB(255, 255, 0, 0);
		paint.setStyle(Paint.Style.STROKE);
		/*
		Bitmap bmp = BitmapFactory.decodeResource( getResources(), R.drawable.icon);
		canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
		canvas.drawText("Here I am...", myScreenCoords.x, myScreenCoords.y, paint);
		*/
		canvas.drawPoint(myScreenCoords.x, myScreenCoords.y, paint);
		//canvas.drawPicture(new Picture(R.drawable.icon), new Rect(myScreenCoords.x - 5, myScreenCoords.y + 5, myScreenCoords.x + 5, myScreenCoords.y - 5));
		}
	
	public GeoPoint getPoint(){
		return point;
	}
}
