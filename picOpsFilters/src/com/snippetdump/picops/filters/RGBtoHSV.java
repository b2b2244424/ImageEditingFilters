package com.snippetdump.picops.filters;

import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class RGBtoHSV.
 */
public class RGBtoHSV {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/**
	 * Instantiates a new rG bto hsv.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public RGBtoHSV(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mRGBtoHSV
	 *            the m rgb to hsv
	 * @return the vector
	 */
	public Vector<float[]> executeFilter(RGBtoHSV mRGBtoHSV) {

		int width = mRGBtoHSV.getBitmapIn().getWidth();
		int height = mRGBtoHSV.getBitmapIn().getHeight();
		int[] pixels = new int[width * height];
		mRGBtoHSV.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);
		Vector<float[]> hsvValues = new Vector<float[]>();

		for (int i = 0; i < pixels.length; i++) {

			float[] hsv = new float[3];
			Color.colorToHSV(pixels[i], hsv);
			hsvValues.add(hsv);
		}

		return hsvValues;
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
