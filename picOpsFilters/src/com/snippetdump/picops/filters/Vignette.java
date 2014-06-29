package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

/**
 * The Class Vignette.
 */
public class Vignette extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/**
	 * Instantiates a new vignette.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public Vignette(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mVignette
	 *            the m vignette
	 * @return the bitmap
	 */
	public Bitmap executeFilter(Vignette mVignette) {

		long time = System.currentTimeMillis();
		int width = mVignette.getBitmapIn().getWidth();
		int height = mVignette.getBitmapIn().getHeight();
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapOut);
		canvas.drawBitmap(mVignette.getBitmapIn(), 0, 0, null);

		int tenthLeftRight = (int) (width / 5);
		int tenthTopBottom = (int) (height / 5);

		// Gradient left - right
		Shader linGradLR = new LinearGradient(0, height / 2, tenthLeftRight / 2, height / 2,
				Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP);
		// Gradient top - bottom
		Shader linGradTB = new LinearGradient(width / 2, 0, width / 2, tenthTopBottom, Color.BLACK,
				Color.TRANSPARENT, Shader.TileMode.CLAMP);
		// Gradient right - left
		Shader linGradRL = new LinearGradient(width, height / 2, (width - tenthLeftRight),
				height / 2, Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP);
		// Gradient bottom - top
		Shader linGradBT = new LinearGradient(width / 2, height, width / 2,
				(height - tenthTopBottom), Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP);

		Paint paint = new Paint();
		paint.setShader(linGradLR);
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setAlpha(125);
		// Rect for Grad left - right
		Rect rect = new Rect(0, 0, tenthLeftRight, height);
		RectF rectf = new RectF(rect);
		canvas.drawRect(rectf, paint);

		// Rect for Grad top - bottom
		paint.setShader(linGradTB);
		rect = new Rect(0, 0, width, tenthTopBottom);
		rectf = new RectF(rect);
		canvas.drawRect(rectf, paint);

		// Rect for Grad right - left
		paint.setShader(linGradRL);
		rect = new Rect(width, 0, width - tenthLeftRight, height);
		rectf = new RectF(rect);
		canvas.drawRect(rectf, paint);

		// Rect for Grad bottom - top
		paint.setShader(linGradBT);
		rect = new Rect(0, height - tenthTopBottom, width, height);
		rectf = new RectF(rect);
		canvas.drawRect(rectf, paint);
		time = System.currentTimeMillis() - time;

		return bitmapOut;
	}

	/**
	 * Gets the bitmap in.
	 * 
	 * @return the bitmap in
	 */
	public Bitmap getBitmapIn() {
		return bitmapIn;
	}

	/**
	 * Sets the bitmap in.
	 * 
	 * @param bitmapIn
	 *            the new bitmap in
	 */
	public void setBitmapIn(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}
}
