package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * The Class UnsharpMask.
 */
public class UnsharpMask extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The box height. */
	private int boxWidth, boxHeight;

	/**
	 * Instantiates a new unsharp mask.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param boxWidth
	 *            the box width
	 * @param boxHeight
	 *            the box height
	 */
	public UnsharpMask(Bitmap bitmapIn, int boxWidth, int boxHeight) {
		this.bitmapIn = bitmapIn;
		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mUnsharpMask
	 *            the m unsharp mask
	 * @return the bitmap
	 */
	public Bitmap executeFilter(UnsharpMask mUnsharpMask) {

		long time = System.currentTimeMillis();
		int width = mUnsharpMask.getBitmapIn().getWidth();
		int height = mUnsharpMask.getBitmapIn().getHeight();
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, mUnsharpMask.getBitmapIn()
				.getConfig());
		int[] pixels = new int[width * height];
		mUnsharpMask.getBitmapIn().getPixels(pixels, 0, width, 0, 0, width, height);
		bitmapOut.setPixels(pixels, 0, width, 0, 0, width, height);

		int left = boxWidth / 2 + 1;
		int top = boxHeight / 2 + 1;
		int right = width - left;
		int bottom = height - top;

		// TODO: Make amount and threshold adjustable
		float unsharpMaskAmount = 0.5f;
		int unsharpMaskThreshold = 3;

		int[][] sourcePixels = loadPixelsFromImage(mUnsharpMask.getBitmapIn());
		int[][] blurredPixels = new int[width][height];

		inlineApplyBlur(bitmapOut, sourcePixels, blurredPixels, left, top, right, bottom, boxWidth,
				boxHeight);
		applyUnsharpMask(bitmapOut, sourcePixels, blurredPixels, left, top, right, bottom,
				unsharpMaskAmount, unsharpMaskThreshold);
		time = System.currentTimeMillis() - time;

		return bitmapOut;
	}

	/**
	 * Load pixels from image.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @return the int[][]
	 */
	private int[][] loadPixelsFromImage(Bitmap bitmapIn) {

		int width = bitmapIn.getWidth();
		int height = bitmapIn.getHeight();
		int[][] pixels = new int[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				pixels[x][y] = bitmapIn.getPixel(x, y);
			}
		}

		return pixels;
	}

	/**
	 * Inline apply blur.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param sourcePixels
	 *            the source pixels
	 * @param blurredPixels
	 *            the blurred pixels
	 * @param left
	 *            the left
	 * @param top
	 *            the top
	 * @param right
	 *            the right
	 * @param bottom
	 *            the bottom
	 * @param filtX
	 *            the filt x
	 * @param filtY
	 *            the filt y
	 */
	private void inlineApplyBlur(Bitmap bitmapIn, int[][] sourcePixels, int[][] blurredPixels,
			int left, int top, int right, int bottom, int filtX, int filtY) {

		for (int x = left; x < right; x++) {
			for (int y = top; y < bottom; y++) {
				blurredPixels[x][y] = blurPixels(sourcePixels, (x - filtX / 2), (y - filtY / 2),
						(x + filtX / 2), (y + filtY / 2));
				bitmapIn.setPixel(x, y, blurredPixels[x][y]);
			}
		}
	}

	/**
	 * Blur pixels.
	 * 
	 * @param sourcePixels
	 *            the source pixels
	 * @param left
	 *            the left
	 * @param top
	 *            the top
	 * @param right
	 *            the right
	 * @param bottom
	 *            the bottom
	 * @return the int
	 */
	private int blurPixels(int[][] sourcePixels, int left, int top, int right, int bottom) {

		int red, green, blue;
		red = green = blue = 0;
		int boxSize = (right - left) * (bottom - top);
		int pixel = 0;

		for (int x = left; x < right; x++) {

			for (int y = top; y < bottom; y++) {

				pixel = sourcePixels[x][y];
				red += Color.red(pixel);
				green += Color.green(pixel);
				blue += Color.blue(pixel);

			}
		}

		red /= boxSize;
		green /= boxSize;
		blue /= boxSize;

		return Color.argb(0xFF, red, green, blue);
	}

	/**
	 * Apply unsharp mask.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param sourcePixels
	 *            the source pixels
	 * @param blurredPixels
	 *            the blurred pixels
	 * @param left
	 *            the left
	 * @param top
	 *            the top
	 * @param right
	 *            the right
	 * @param bottom
	 *            the bottom
	 * @param amount
	 *            the amount
	 * @param threshold
	 *            the threshold
	 */
	private void applyUnsharpMask(Bitmap bitmapIn, int[][] sourcePixels, int[][] blurredPixels,
			int left, int top, int right, int bottom, float amount, int threshold) {

		int oRed, oGreen, oBlue;
		oRed = oGreen = oBlue = 0;
		int bRed, bGreen, bBlue;
		bRed = bGreen = bBlue = 0;
		int unsharpMaskPixel = 0;
		int oPixel = 0;
		int bPixel = 0;

		for (int x = left; x < right; x++) {
			for (int y = top; y < bottom; y++) {

				oPixel = sourcePixels[x][y];
				bPixel = blurredPixels[x][y];

				oRed = Color.red(oPixel);
				oGreen = Color.green(oPixel);
				oBlue = Color.blue(oPixel);
				bRed = Color.red(bPixel);
				bGreen = Color.green(bPixel);
				bBlue = Color.blue(bPixel);

				if (Math.abs(oRed - bRed) >= threshold) {

					oRed = (int) (amount * (oRed - bRed) + oRed);
					oRed = oRed > 255 ? 255 : oRed < 0 ? 0 : oRed;
				}

				if (Math.abs(oGreen - bGreen) >= threshold) {

					oGreen = (int) (amount * (oGreen - bGreen) + oGreen);
					oGreen = oGreen > 255 ? 255 : oGreen < 0 ? 0 : oGreen;
				}

				if (Math.abs(oBlue - bBlue) >= threshold) {

					oBlue = (int) (amount * (oBlue - bBlue) + oBlue);
					oBlue = oBlue > 255 ? 255 : oBlue < 0 ? 0 : oBlue;
				}

				unsharpMaskPixel = Color.argb(0xFF, oRed, oGreen, oBlue);
				bitmapIn.setPixel(x, y, unsharpMaskPixel);
			}
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

	/**
	 * Gets the box width.
	 * 
	 * @return the box width
	 */
	public int getBoxWidth() {
		return boxWidth;
	}

	/**
	 * Sets the box width.
	 * 
	 * @param boxWidth
	 *            the new box width
	 */
	public void setBoxWidth(int boxWidth) {
		this.boxWidth = boxWidth;
	}

	/**
	 * Gets the box height.
	 * 
	 * @return the box height
	 */
	public int getBoxHeight() {
		return boxHeight;
	}

	/**
	 * Sets the box height.
	 * 
	 * @param boxHeight
	 *            the new box height
	 */
	public void setBoxHeight(int boxHeight) {
		this.boxHeight = boxHeight;
	}
}
