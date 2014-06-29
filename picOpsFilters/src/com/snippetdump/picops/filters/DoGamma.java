package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class DoGamma.
 */
public class DoGamma extends Filter {

	private static final int MAX_SIZE = 256;
	private static final double MAX_VALUE_DBL = 255.0;
	private static final int MAX_VALUE_INT = 255;
	private static final double REVERSE = 1.0;

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The red. */
	private double red;

	/** The green. */
	private double green;

	/** The blue. */
	private double blue;

	/**
	 * Instantiates a new do gamma.
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
	public DoGamma(Bitmap bitmapIn, double red, double green, double blue) {
		this.bitmapIn = bitmapIn;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mDoGamma
	 *            the m do gamma
	 * @return the bitmap
	 */
	public Bitmap executeFilter(DoGamma mDoGamma) {

		long time = System.currentTimeMillis();
		int width = mDoGamma.getBitmapIn().getWidth();
		int height = mDoGamma.getBitmapIn().getHeight();
		int alpha, red, green, blue;

		int[] gammaRed = new int[MAX_SIZE];
		int[] gammaGreen = new int[MAX_SIZE];
		int[] gammaBlue = new int[MAX_SIZE];

		for (int i = 0; i < MAX_SIZE; i++) {
			gammaRed[i] = (int) Math.min(MAX_VALUE_INT, (int) ((MAX_VALUE_DBL * Math.pow(i
					/ MAX_VALUE_DBL, REVERSE / mDoGamma.getRed())) + 0.5));
			gammaGreen[i] = (int) Math.min(
					MAX_VALUE_INT,
					(int) ((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL,
							REVERSE / mDoGamma.getGreen())) + 0.5));
			gammaBlue[i] = (int) Math.min(
					MAX_VALUE_INT,
					(int) ((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL,
							REVERSE / mDoGamma.getBlue())) + 0.5));
		}

		int[] pixels = new int[width * height];
		mDoGamma.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < pixels.length; i++) {

			alpha = Color.alpha(pixels[i]);
			red = gammaRed[Color.red(pixels[i])];
			green = gammaGreen[Color.green(pixels[i])];
			blue = gammaBlue[Color.blue(pixels[i])];

			pixels[i] = Color.argb(alpha, red, green, blue);
		}
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, mDoGamma.getBitmapIn().getConfig());
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
