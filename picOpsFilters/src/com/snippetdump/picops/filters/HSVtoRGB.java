package com.snippetdump.picops.filters;

import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class HSVtoRGB.
 */
public class HSVtoRGB extends Filter {

	/** The vector in. */
	private Vector<float[]> vectorIn;

	/** The width. */
	private int width;

	/** The height. */
	private int height;

	/**
	 * Instantiates a new hS vto rgb.
	 * 
	 * @param vectorIn
	 *            the vector in
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public HSVtoRGB(Vector<float[]> vectorIn, int width, int height) {
		this.vectorIn = vectorIn;
		this.width = width;
		this.height = height;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mHSVtoRGB
	 *            the m hs vto rgb
	 * @return the bitmap
	 */
	public Bitmap executeFilter(HSVtoRGB mHSVtoRGB) {

		Bitmap bitmapOut = Bitmap.createBitmap(mHSVtoRGB.getWidth(), mHSVtoRGB.getHeight(),
				Bitmap.Config.ARGB_8888);
		int[] pixels = new int[mHSVtoRGB.getVectorIn().size()];

		for (int i = 0; i < mHSVtoRGB.getVectorIn().size(); i++) {
			pixels[i] = Color.HSVToColor(0xFF, mHSVtoRGB.getVectorIn().get(i));
		}
		bitmapOut.setPixels(pixels, 0, mHSVtoRGB.getWidth(), 0, 0, mHSVtoRGB.getWidth(),
				mHSVtoRGB.getHeight());

		return bitmapOut;
	}

	/**
	 * Gets the vector in.
	 * 
	 * @return the vector in
	 */
	public Vector<float[]> getVectorIn() {
		return vectorIn;
	}

	/**
	 * Sets the vector in.
	 * 
	 * @param vectorIn
	 *            the new vector in
	 */
	public void setVectorIn(Vector<float[]> vectorIn) {
		this.vectorIn = vectorIn;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 * 
	 * @param width
	 *            the new width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 * 
	 * @param height
	 *            the new height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
