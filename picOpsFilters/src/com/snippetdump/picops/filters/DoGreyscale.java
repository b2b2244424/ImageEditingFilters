package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class DoGreyscale.
 */
public class DoGreyscale extends Filter {
	
	/** The bitmap in. */
	private Bitmap bitmapIn;

	/**
	 * Instantiates a new do greyscale.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public DoGreyscale(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mDoGreyscale
	 *            the m do greyscale
	 * @return the bitmap
	 */
	public Bitmap executeFilter(DoGreyscale mDoGreyscale) {

		long time = System.currentTimeMillis();
		// Define constant values
		final double constValueRed = 0.299;
		final double constValueGreen = 0.587;
		final double constValueBlue = 0.114;

		// Get the dimensions
		int alpha, red, green, blue;
		int width = mDoGreyscale.getBitmapIn().getWidth();
		int height = mDoGreyscale.getBitmapIn().getHeight();
		int[] pixels = new int[width * height];
		// Store every pixel into an integer array
		mDoGreyscale.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width,
				height);

		for (int i = 0; i < pixels.length; i++) {

			alpha = Color.alpha(pixels[i]);
			red = Color.red(pixels[i]);
			green = Color.green(pixels[i]);
			blue = Color.blue(pixels[i]);

			// Apply constant values
			red = green = blue = (int) (constValueRed * red + constValueGreen
					* green + constValueBlue * blue);
			pixels[i] = Color.argb(alpha, red, green, blue);
		}
		// Write in output Bitmap
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, mDoGreyscale
				.getBitmapIn().getConfig());
		bitmapOut.setPixels(pixels, 0, width, 0, 0, width, height);
		pixels = null;
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
