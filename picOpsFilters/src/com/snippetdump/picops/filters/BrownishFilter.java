package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class BrownishFilter.
 */
public class BrownishFilter extends Filter {
	
	/** The bitmap in. */
	private Bitmap bitmapIn;

	/**
	 * Instantiates a new brownish filter.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public BrownishFilter(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mBrownishFilter
	 *            the m brownish filter
	 * @return the bitmap
	 */
	public Bitmap executeFilter(BrownishFilter mBrownishFilter) {

		long time = System.currentTimeMillis();
		int width = mBrownishFilter.getBitmapIn().getWidth();
		int height = mBrownishFilter.getBitmapIn().getHeight();
		int alpha, red, green, blue, pixel;
		int[] pixels = new int[width * height];
		mBrownishFilter.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width,
				height);
		float[] hsv = new float[3];
		float hue, saturation, value;
		hue = saturation = value = 0;
		for (int i = 0; i < pixels.length; i++) {

			pixel = pixels[i];
			alpha = Color.alpha(pixel);
			red = Color.red(pixel);
			red *= 1.25;
			if (red > 255)
				red = 255;

			green = Color.green(pixel);

			blue = Color.blue(pixel);
			blue *= 0.5;

			
			Color.RGBToHSV(red, green, blue, hsv);
			hue = hsv[0];
			saturation = hsv[1];
			value = hsv[2];

			hue *= 0.7f;
			saturation *= 0.9f;
			value *= 0.85f;

			hsv[0] = hue;
			hsv[1] = saturation;
			hsv[2] = value;

			pixel = Color.HSVToColor(alpha, hsv);
			pixels[i] = pixel;
		}
		Bitmap bitmapOut = Bitmap.createBitmap(width, height,
				mBrownishFilter.bitmapIn.getConfig());
		bitmapOut.setPixels(pixels, 0, width, 0, 0, width, height);
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
