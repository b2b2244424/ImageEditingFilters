package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * The Class Polaroid.
 */
public class Polaroid extends Filter {
	
	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The Constant side. */
	private static final double side = 0.075;

	/** The Constant bottom. */
	private static final double bottom = 0.2;

	/**
	 * Instantiates a new polaroid.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public Polaroid(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mPolaroid
	 *            the m polaroid
	 * @return the bitmap
	 */
	public Bitmap executeFilter(Polaroid mPolaroid) {

		long time = System.currentTimeMillis();
		int width = mPolaroid.getBitmapIn().getWidth();
		int height = mPolaroid.getBitmapIn().getHeight();
		int _width, _height;
		_width = _height = 0;

		// Check if for format
		// Adjust size for Polaroid frame
		if (width > height) {
			// Case: Landscape
			_width = width + 2 * ((int) (width * side));
			_height = height + ((int) (height * side))
					+ ((int) (width * bottom));
		} else if (width < height) {
			// Case: Portrait
			_width = width + 2 * ((int) (width * side));
			_height = height + ((int) (height * side))
					+ ((int) (height * bottom));
		} else {
			// Case: same sides
			_width = width + 2 * ((int) (width * side));
			_height = height + ((int) (height * side))
					+ ((int) (height * bottom));
		}

		// Rect and Paint for Polaroid frame
		Rect rect = new Rect(1, 1, _width - 1, _height - 1);
		RectF rectf = new RectF(rect);
		Paint paint = new Paint();
		paint.setColor(Color.argb(0xFF, 0xF1, 0xF1, 0xF1));

		Bitmap bitmapOut = Bitmap.createBitmap(_width, _height, mPolaroid
				.getBitmapIn().getConfig());

		VintageEffect mVintageEffect = new VintageEffect(
				mPolaroid.getBitmapIn());

		Canvas canvas = new Canvas(bitmapOut);
		// Draw border
		canvas.drawColor(Color.argb(0xFF, 0xE1, 0xE1, 0xE1));
		canvas.drawRect(rectf, paint);
		canvas.drawBitmap(mVintageEffect.executeFilter(mVintageEffect),
				(float) (width * side), (float) (width * side), null);
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
