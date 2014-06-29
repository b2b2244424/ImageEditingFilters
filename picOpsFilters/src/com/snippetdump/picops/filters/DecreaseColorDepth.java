package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class DecreaseColorDepth.
 */
public class DecreaseColorDepth extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The bit offset. */
	private int bitOffset;

	/**
	 * Instantiates a new decrease color depth.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param bitOffset
	 *            the bit offset
	 */
	public DecreaseColorDepth(Bitmap bitmapIn, int bitOffset) {
		this.bitmapIn = bitmapIn;
		this.bitOffset = bitOffset;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mDecreaseColorDepth
	 *            the m decrease color depth
	 * @return the bitmap
	 */
	public Bitmap executeFilter(DecreaseColorDepth mDecreaseColorDepth) {

		if (mDecreaseColorDepth.getBitOffset() == 0) {
			mDecreaseColorDepth.setBitOffset(32);
		} else if (mDecreaseColorDepth.getBitOffset() == 1) {
			mDecreaseColorDepth.setBitOffset(64);
		} else if (mDecreaseColorDepth.getBitOffset() == 2) {
			mDecreaseColorDepth.setBitOffset(128);
		}
		long time = System.currentTimeMillis();
		int width = mDecreaseColorDepth.getBitmapIn().getWidth();
		int height = mDecreaseColorDepth.getBitmapIn().getHeight();
		int[] pixels = new int[width * height];
		int red, green, blue;
		mDecreaseColorDepth.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < pixels.length; i++) {

			red = Color.red(pixels[i]);
			green = Color.green(pixels[i]);
			blue = Color.blue(pixels[i]);

			red = ((red + (mDecreaseColorDepth.getBitOffset() / 2))
					- ((red + (mDecreaseColorDepth.getBitOffset() / 2)) % mDecreaseColorDepth
							.getBitOffset()) - 1);
			if (red < 0)
				red = 0;

			green = ((green + (mDecreaseColorDepth.getBitOffset() / 2))
					- ((green + (mDecreaseColorDepth.getBitOffset() / 2)) % mDecreaseColorDepth
							.getBitOffset()) - 1);
			if (green < 0)
				green = 0;

			blue = ((blue + (mDecreaseColorDepth.getBitOffset() / 2))
					- ((blue + (mDecreaseColorDepth.getBitOffset() / 2)) % mDecreaseColorDepth
							.getBitOffset()) - 1);
			if (blue < 0)
				blue = 0;

			pixels[i] = Color.argb(Color.alpha(pixels[i]), red, green, blue);
		}
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, mDecreaseColorDepth.getBitmapIn()
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
	 * Gets the bit offset.
	 * 
	 * @return the bit offset
	 */
	public int getBitOffset() {
		return bitOffset;
	}

	/**
	 * Sets the bit offset.
	 * 
	 * @param bitOffset
	 *            the new bit offset
	 */
	public void setBitOffset(int bitOffset) {
		this.bitOffset = bitOffset;
	}
}
