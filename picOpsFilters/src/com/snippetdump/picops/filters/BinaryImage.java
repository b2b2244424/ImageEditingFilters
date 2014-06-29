package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class BinaryImage.
 */
public class BinaryImage extends Filter {

	private static final Double VALUE_RED = 0.3;
	private static final Double VALUE_GREEN = 0.59;
	private static final Double VALUE_BLUE = 0.11;

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The threshold. */
	private int threshold;

	/**
	 * Instantiates a new binary image.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param threshold
	 *            the threshold
	 */
	public BinaryImage(Bitmap bitmapIn, int threshold) {
		this.bitmapIn = bitmapIn;
		this.threshold = threshold;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mBinaryImage
	 *            the m binary image
	 * @return the bitmap
	 */
	public Bitmap executeFilter(BinaryImage mBinaryImage) {

		long time = System.currentTimeMillis();
		DoGreyscale mDoGreyscale = new DoGreyscale(mBinaryImage.getBitmapIn());
		Bitmap bitmapOut = mDoGreyscale.executeFilter(mDoGreyscale);

		int width = mBinaryImage.getBitmapIn().getWidth();
		int height = mBinaryImage.getBitmapIn().getHeight();
		int pixel, red, green, blue;
		double grey;
		int[] pixelsGreyscaled = new int[width * height];
		bitmapOut.getPixels(pixelsGreyscaled, 0, width, 0, 0, width, height);
		bitmapOut.recycle();
		bitmapOut = null;

		for (int i = 0; i < pixelsGreyscaled.length; i++) {
			pixel = pixelsGreyscaled[i];
			red = Color.red(pixel);
			green = Color.green(pixel);
			blue = Color.blue(pixel);
			grey = ((red * VALUE_RED) + (green * VALUE_GREEN) + (blue * VALUE_BLUE));
			red = green = blue = thresholdOperation(mBinaryImage.getThreshold(), grey);
			pixelsGreyscaled[i] = Color.argb(Color.alpha(pixel), red, green, blue);
		}
		bitmapOut = Bitmap.createBitmap(width, height, mBinaryImage.getBitmapIn().getConfig());
		bitmapOut.setPixels(pixelsGreyscaled, 0, width, 0, 0, width, height);
		time = System.currentTimeMillis() - time;

		return bitmapOut;
	}

	/**
	 * Threshold operation.
	 * 
	 * @param mBinaryImage
	 *            the m binary image
	 * @param grey
	 *            the grey
	 * @return the int
	 */
	private int thresholdOperation(int threshold, double grey) {
		if (grey > threshold)
			return 255;
		else
			return 0;
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
	 * Gets the threshold.
	 * 
	 * @return the threshold
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * Sets the threshold.
	 * 
	 * @param threshold
	 *            the new threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
}
