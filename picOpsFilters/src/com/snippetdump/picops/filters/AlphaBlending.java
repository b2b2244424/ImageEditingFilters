package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

/**
 * The Class AlphaBlending.
 */
public class AlphaBlending extends Filter {
	
	/** The bitmap in. */
	private Bitmap bitmapIn;

	/**
	 * Instantiates a new alpha blending.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public AlphaBlending(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mAlphaBlending
	 *            the m alpha blending
	 * @return the bitmap
	 */
	public Bitmap executeFilter(AlphaBlending mAlphaBlending) {

		long time = System.currentTimeMillis();
		Bitmap bitmapOut = null;
		int width = mAlphaBlending.getBitmapIn().getWidth();
		int height = mAlphaBlending.getBitmapIn().getHeight();
		int alpha, red, green, /* blue, */pixel;

		DoGreyscale mDoGreyscale = new DoGreyscale(mAlphaBlending.getBitmapIn());
		Bitmap bitmapAlpha = mDoGreyscale.executeFilter(mDoGreyscale);

		/* Setting alpha channel @ 0.5 */
		int[] pixelsAlpha = new int[width * height];
		bitmapAlpha.getPixels(pixelsAlpha, 0, width, 0, 0, width, height);
		for (int i = 0; i < pixelsAlpha.length; i++) {
			pixel = pixelsAlpha[i];
			alpha = Color.alpha(pixel);
			alpha /= 2;
			pixel = Color.argb(alpha, Color.red(pixel), Color.green(pixel), Color.blue(pixel));
			pixelsAlpha[i] = pixel;
		}
		bitmapAlpha.setPixels(pixelsAlpha, 0, width, 0, 0, width, height);
		pixelsAlpha = null;

		/* lowering the values in the red and green channels */
		bitmapOut = Bitmap.createBitmap(width, height, mAlphaBlending.getBitmapIn().getConfig());
		int[] pixels = new int[width * height];
		mAlphaBlending.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 0; i < pixels.length; i++) {
			pixel = pixels[i];
			red = Color.red(pixel);
			red /= 2;
			green = Color.green(pixel);
			green /= 2;
			pixel = Color.argb(Color.alpha(pixel), red, green, Color.blue(pixel));
			pixels[i] = pixel;
		}
		bitmapOut.setPixels(pixels, 0, width, 0, 0, width, height);

		/* Blending the two bitmap on a canvas object */
		Canvas canvas = new Canvas(bitmapOut);
		canvas.drawBitmap(bitmapAlpha, 0, 0, null);
		bitmapAlpha.recycle();
		bitmapAlpha = null;
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
}
