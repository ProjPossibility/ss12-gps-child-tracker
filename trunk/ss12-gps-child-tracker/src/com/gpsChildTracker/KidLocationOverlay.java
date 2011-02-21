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
	int red;
	int green;
	int blue;
	
	
	public KidLocationOverlay(GeoPoint p){
		point = p;
		red = 255;
		green = 0;
		blue = 0;
	}
	
	public void setRed(int r){
		red = r;
	}
	
	public void setBlue(int r){
		blue = r;
	}
	
	public void setGreen(int r){
		green = r;
	}
	
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		
		super.draw(canvas, mapView, shadow);
		Paint paint = new Paint();
		// Converts lat/lng-Point to OUR coordinates on the screen.
		Point myScreenCoords = new Point();
		mapView.getProjection().toPixels(point, myScreenCoords);
		paint.setStrokeWidth(15);
		paint.setARGB(255, red, green, blue);
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
