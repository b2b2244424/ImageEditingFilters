package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class HardLightMode.
 */
public class HardLightMode extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/**
	 * Instantiates a new hard light mode.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public HardLightMode(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mHardLightMode
	 *            the m hard light mode
	 * @return the bitmap
	 */
	public Bitmap executeFilter(HardLightMode mHardLightMode) {

		long time = System.currentTimeMillis();
		DoGreyscale mDoGreyscale = new DoGreyscale(mHardLightMode.getBitmapIn());
		Bitmap bitmapOut = mDoGreyscale.executeFilter(mDoGreyscale);

		int width = mHardLightMode.getBitmapIn().getWidth();
		int height = mHardLightMode.getBitmapIn().getHeight();
		int[] pixels = new int[width * height];
		bitmapOut.getPixels(pixels, 0, width, 0, 0, width, height);
		bitmapOut.recycle();
		bitmapOut = null;
		double grey;
		int alpha, red, green, blue;

		for (int i = 0; i < pixels.length; i++) {

			alpha = Color.alpha(pixels[i]);
			red = Color.red(pixels[i]);
			green = Color.green(pixels[i]);
			blue = Color.blue(pixels[i]);
			grey = ((red * 0.3f) + (green * 0.59f) + (blue * 0.11f));

			red = green = blue = (int) hardLightLayer(grey, grey);
			pixels[i] = Color.argb(alpha, red, green, blue);
		}
		bitmapOut = Bitmap.createBitmap(width, height, mHardLightMode.getBitmapIn().getConfig());
		bitmapOut.setPixels(pixels, 0, width, 0, 0, width, height);
		pixels = null;
		time = System.currentTimeMillis() - time;

		return bitmapOut;
	}

	/**
	 * Hard light layer.
	 * 
	 * @param maskVal
	 *            the mask val
	 * @param imgVal
	 *            the img val
	 * @return the double
	 */
	private double hardLightLayer(double maskVal, double imgVal) {

		if (maskVal > 128)
			return 255 - (((255 - (2 * (maskVal - 128))) * (255 - imgVal)) / 256);
		else
			return (2 * maskVal * imgVal) / 256;
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
