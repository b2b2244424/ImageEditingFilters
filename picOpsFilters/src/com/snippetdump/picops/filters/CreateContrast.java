package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class CreateContrast.
 */
public class CreateContrast extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The value. */
	private double value;

	/**
	 * Instantiates a new creates the contrast.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param value
	 *            the value
	 */
	public CreateContrast(Bitmap bitmapIn, double value) {
		this.bitmapIn = bitmapIn;
		this.value = value;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mCreateContrast
	 *            the m create contrast
	 * @return the bitmap
	 */
	public Bitmap executeFilter(CreateContrast mCreateContrast) {

		long time = System.currentTimeMillis();
		int width = mCreateContrast.getBitmapIn().getWidth();
		int height = mCreateContrast.getBitmapIn().getHeight();
		int alpha, red, green, blue;
		double contrast = Math.pow((100 + mCreateContrast.getValue()) / 100, 2);
		int[] pixels = new int[width * height];
		mCreateContrast.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < pixels.length; i++) {

			alpha = Color.alpha(pixels[i]);

			red = Color.red(pixels[i]);
			red = (int) (((((red / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
			if (red > 255)
				red = 255;
			if (red < 0)
				red = 0;

			green = Color.green(pixels[i]);
			green = (int) (((((green / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
			if (green > 255)
				green = 255;
			if (green < 0)
				green = 0;

			blue = Color.blue(pixels[i]);
			blue = (int) (((((blue / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
			if (blue > 255)
				blue = 255;
			if (blue < 0)
				blue = 0;

			pixels[i] = Color.argb(alpha, red, green, blue);
		}
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, mCreateContrast.getBitmapIn()
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
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(double value) {
		this.value = value;
	}
}
