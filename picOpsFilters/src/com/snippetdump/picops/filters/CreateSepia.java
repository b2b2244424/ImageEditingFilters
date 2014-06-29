package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class CreateSepia.
 */
public class CreateSepia extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The intensity. */
	private int intensity;

	/** The Constant depth. */
	private final static int depth = 20;

	/**
	 * Instantiates a new creates the sepia.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param intensity
	 *            the intensity
	 */
	public CreateSepia(Bitmap bitmapIn, int intensity) {
		this.bitmapIn = bitmapIn;
		this.intensity = intensity;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mCreateSepia
	 *            the m create sepia
	 * @return the bitmap
	 */
	public Bitmap executeFilter(CreateSepia mCreateSepia) {

		long time = System.currentTimeMillis();
		int width = mCreateSepia.getBitmapIn().getWidth();
		int height = mCreateSepia.getBitmapIn().getHeight();
		int red, green, blue, grey;
		red = green = blue = grey = 0;
		int[] pixels = new int[width * height];
		mCreateSepia.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < pixels.length; i++) {

			red = Color.red(pixels[i]);
			green = Color.green(pixels[i]);
			blue = Color.blue(pixels[i]);

			grey = (red + green + blue) / 3;
			red = green = blue = grey;
			red = red + (depth * 2);
			green += depth;

			if (red > 255)
				red = 255;
			if (green > 255)
				green = 255;
			if (blue > 255)
				blue = 255;

			blue -= intensity;

			if (blue > 255)
				blue = 255;
			if (blue < 0)
				blue = 0;

			pixels[i] = Color.argb(Color.alpha(pixels[i]), red, green, blue);
		}
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, mCreateSepia.getBitmapIn()
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
	 * Gets the intensity.
	 * 
	 * @return the intensity
	 */
	public int getIntensity() {
		return intensity;
	}

	/**
	 * Sets the intensity.
	 * 
	 * @param intensity
	 *            the new intensity
	 */
	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}
}
