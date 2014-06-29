package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * The Class RotateImage.
 */
public class RotateImage extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The degree. */
	private float degree;

	/**
	 * Instantiates a new rotate image.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param degree
	 *            the degree
	 */
	public RotateImage(Bitmap bitmapIn, float degree) {
		this.bitmapIn = bitmapIn;
		this.degree = degree;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mRotateImage
	 *            the m rotate image
	 * @return the bitmap
	 */
	public Bitmap executeFilter(RotateImage mRotateImage) {

		long time = System.currentTimeMillis();
		Matrix matrix = new Matrix();
		matrix.postRotate(mRotateImage.getDegree());
		time = System.currentTimeMillis() - time;

		return Bitmap.createBitmap(mRotateImage.getBitmapIn(), 0, 0, mRotateImage.getBitmapIn()
				.getWidth(), mRotateImage.getBitmapIn().getHeight(), matrix, true);
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
	 * Gets the degree.
	 * 
	 * @return the degree
	 */
	public float getDegree() {
		return degree;
	}

	/**
	 * Sets the degree.
	 * 
	 * @param degree
	 *            the new degree
	 */
	public void setDegree(float degree) {
		this.degree = degree;
	}
}
