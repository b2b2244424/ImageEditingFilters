package com.snippetdump.picops.filters;

import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class VintageEffect.
 */
public class VintageEffect extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/**
	 * Instantiates a new vintage effect.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public VintageEffect(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	// For reference: http://www.youtube.com/watch?v=m6NyJHKWgZg
	/**
	 * Execute filter.
	 * 
	 * @param mVintageEffect
	 *            the m vintage effect
	 * @return the bitmap
	 */
	public Bitmap executeFilter(VintageEffect mVintageEffect) {

		long time = System.currentTimeMillis();
		int width = mVintageEffect.getBitmapIn().getWidth();
		int height = mVintageEffect.getBitmapIn().getHeight();
		int[] pixels = new int[width * height];
		mVintageEffect.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);
		Vector<float[]> pixelsHSV = new Vector<float[]>();

		for (int pixel : pixels) {
			float[] hsv = { 0.0f, 0.0f, 0.0f };
			Color.RGBToHSV(Color.red(pixel), Color.green(pixel), Color.blue(pixel), hsv);
			pixelsHSV.add(hsv);
		}

		increaseSaturation(pixelsHSV);
		// Inline contrast insert here

		// Adjust color channels
		// Overwriting with saturated pixels
		for (int i = 0; i < width * height; i++) {
			pixels[i] = Color.HSVToColor(pixelsHSV.get(i));
		}
		// Vector not needed anymore
		pixelsHSV = null;
		adjustChannels(pixels);
		// Insert vignette effect
		// Insert hue
		int[] pixelsHue = new int[width * height];
		for (int i = 0; i < width * height; i++) {
			pixelsHue[i] = pixels[i];
		}
		adjustHueAndAlpha(pixels, pixelsHue);
		blendArrays(pixels, pixelsHue, 0.7);
		pixelsHue = null;
		// Insert color
		int[] fullColor = new int[width * height];
		for (int i = 0; i < fullColor.length; i++) {
			fullColor[i] = Color.argb(0xFF, 0xFF, 0x0, 0x99);
		}
		blendArrays(pixels, fullColor, 0.9);
		fullColor = null;

		Bitmap bitmapOut = Bitmap.createBitmap(width, height, mVintageEffect.getBitmapIn()
				.getConfig());
		bitmapOut.setPixels(pixels, 0, width, 0, 0, width, height);

		bitmapOut = vignette(bitmapOut);
		time = System.currentTimeMillis() - time;

		return bitmapOut;
	}

	/**
	 * Vignette.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @return the bitmap
	 */
	private Bitmap vignette(Bitmap bitmapIn) {

		Vignette mVignette = new Vignette(bitmapIn);

		return mVignette.executeFilter(mVignette);
	}

	/**
	 * Blend arrays.
	 * 
	 * @param pixels
	 *            the pixels
	 * @param pixelsHue
	 *            the pixels hue
	 * @param value
	 *            the value
	 */
	private void blendArrays(int[] pixels, int[] pixelsHue, double value) {

		int r1, r2, r, g1, g2, g, b1, b2, b;
		r1 = r2 = r = g1 = g2 = g = b1 = b2 = b = 0;

		for (int i = 0; i < pixels.length; i++) {

			r1 = Color.red(pixels[i]);
			r2 = Color.red(pixelsHue[i]);
			r = (int) (r1 * value + r2 * (1.0 - value));

			g1 = Color.green(pixels[i]);
			g2 = Color.green(pixelsHue[i]);
			g = (int) (g1 * value + g2 * (1.0 - value));

			b1 = Color.blue(pixels[i]);
			b2 = Color.blue(pixelsHue[i]);
			b = (int) (b1 * value + b2 * (1.0 - value));

			pixels[i] = Color.argb(0xFF, r, g, b);
		}
	}

	/**
	 * Increase saturation.
	 * 
	 * @param hsv
	 *            the hsv
	 */
	private void increaseSaturation(Vector<float[]> hsv) {
		for (float[] hsvPixel : hsv) {
			hsvPixel[1] *= 1.2;
		}
	}

	/**
	 * Adjust channels.
	 * 
	 * @param pixels
	 *            the pixels
	 */
	private void adjustChannels(int[] pixels) {
		int red, green, blue;
		red = green = blue = 0;
		for (int i = 0; i < pixels.length; i++) {
			red = Color.red(pixels[i]);
			red *= 0.9;
			if (red > 240)
				red = 240;

			green = Color.green(pixels[i]);
			if (green > 123)
				green *= 1.05;
			if (green > 255)
				green = 255;

			blue = Color.blue(pixels[i]);
			if (blue < 125) {
				blue *= 1.15;
			} else if (blue >= 125) {
				blue *= 0.85;
			}
			if (blue > 255)
				blue = 255;

			pixels[i] = Color.argb(Color.alpha(pixels[i]), red, green, blue);
		}
	}

	/**
	 * Adjust hue and alpha.
	 * 
	 * @param pixels
	 *            the pixels
	 * @param pixelsHue
	 *            the pixels hue
	 */
	private void adjustHueAndAlpha(int[] pixels, int[] pixelsHue) {

		Vector<float[]> pixelsHueHsv = new Vector<float[]>();
		for (int pixel : pixels) {
			float[] hsv = { 0.0f, 0.0f, 0.0f };
			Color.RGBToHSV(Color.red(pixel), Color.green(pixel), Color.blue(pixel), hsv);
			pixelsHueHsv.add(hsv);
		}

		for (int i = 0; i < pixelsHueHsv.size(); i++) {
			float[] pixelHSV = pixelsHueHsv.get(i);
			pixelHSV[0] = 50.0f;
			pixelHSV[1] = 0.25f;
			pixelsHueHsv.set(i, pixelHSV);
		}

		for (int i = 0; i < pixelsHueHsv.size(); i++) {
			pixelsHue[i] = Color.HSVToColor(0xFF, pixelsHueHsv.get(i));
		}
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