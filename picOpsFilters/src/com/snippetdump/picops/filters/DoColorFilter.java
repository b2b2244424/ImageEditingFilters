package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class DoColorFilter.
 */
public class DoColorFilter extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The blue. */
	private double red, green, blue;

	/**
	 * Instantiates a new do color filter.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param red
	 *            the red
	 * @param green
	 *            the green
	 * @param blue
	 *            the blue
	 */
	public DoColorFilter(Bitmap bitmapIn, double red, double green, double blue) {
		this.bitmapIn = bitmapIn;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mDoColorFilter
	 *            the m do color filter
	 * @return the bitmap
	 */
	public Bitmap executeFilter(DoColorFilter mDoColorFilter) {

		long time = System.currentTimeMillis();
		int width = mDoColorFilter.getBitmapIn().getWidth();
		int height = mDoColorFilter.getBitmapIn().getHeight();
		int alpha, red, green, blue;
		int[] pixels = new int[width * height];
		mDoColorFilter.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < pixels.length; i++) {

			alpha = Color.alpha(pixels[i]);
			red = (int) (Color.red(pixels[i]) * mDoColorFilter.getRed());
			green = (int) (Color.green(pixels[i]) * mDoColorFilter.getGreen());
			blue = (int) (Color.blue(pixels[i]) * mDoColorFilter.getBlue());
			pixels[i] = Color.argb(alpha, red, green, blue);
		}
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, mDoColorFilter.getBitmapIn()
				.getConfig());
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

	/**
	 * Gets the red.
	 * 
	 * @return the red
	 */
	public double getRed() {
		return red;
	}

	/**
	 * Sets the red.
	 * 
	 * @param red
	 *            the new red
	 */
	public void setRed(double red) {
		this.red = red;
	}

	/**
	 * Gets the green.
	 * 
	 * @return the green
	 */
	public double getGreen() {
		return green;
	}

	/**
	 * Sets the green.
	 * 
	 * @param green
	 *            the new green
	 */
	public void setGreen(double green) {
		this.green = green;
	}

	/**
	 * Gets the blue.
	 * 
	 * @return the blue
	 */
	public double getBlue() {
		return blue;
	}

	/**
	 * Sets the blue.
	 * 
	 * @param blue
	 *            the new blue
	 */
	public void setBlue(double blue) {
		this.blue = blue;
	}
}
