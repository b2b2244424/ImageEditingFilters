package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * The Class MirrorImage.
 */
public class MirrorImage extends Filter {
	
	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The type. */
	private int type;

	/** The Constant FLIP_VERTICAL. */
	private static final int FLIP_VERTICAL = 1;

	/** The Constant FLIP_HORIZONTAL. */
	private static final int FLIP_HORIZONTAL = 2;

	/**
	 * Instantiates a new mirror image.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param type
	 *            the type
	 */
	public MirrorImage(Bitmap bitmapIn, int type) {
		this.bitmapIn = bitmapIn;
		this.type = type;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mMirrorImage
	 *            the m mirror image
	 * @return the bitmap
	 */
	public Bitmap executeFilter(MirrorImage mMirrorImage) {

		long time = System.currentTimeMillis();
		Matrix matrix = new Matrix();

		if (type == FLIP_VERTICAL)
			matrix.preScale(1.0f, -1.0f);
		if (type == FLIP_HORIZONTAL)
			matrix.preScale(-1.0f, 1.0f);
		time = System.currentTimeMillis() - time;

		return Bitmap.createBitmap(mMirrorImage.getBitmapIn(), 0, 0,
				mMirrorImage.getBitmapIn().getWidth(), mMirrorImage
						.getBitmapIn().getHeight(), matrix, true);
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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(int type) {
		this.type = type;
	}
}
