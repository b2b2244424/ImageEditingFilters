package com.snippetdump.picops.filters;

import com.snippetdump.picops.model.ConvolutionMask;

import android.graphics.Bitmap;

/**
 * The Class GaussianBlurStrong.
 */
public class GaussianBlurStrong extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The convolution mask. */
	private ConvolutionMask convolutionMask;

	/**
	 * Instantiates a new gaussian blur strong.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public GaussianBlurStrong(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
		double[][] convolutionMask = new double[][] { { 1, 4, 6, 4, 1 }, { 4, 16, 24, 16, 4 },
				{ 6, 24, 36, 24, 16, 6 }, { 4, 16, 24, 16, 4 }, { 1, 4, 6, 4, 1 }, };
		this.convolutionMask = new ConvolutionMask(5);
		this.convolutionMask.applyFaltungskonfigurationBig(convolutionMask);
		this.convolutionMask.Factor = 256;
		this.convolutionMask.Offset = 0;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mGaussianBlurStrong
	 *            the m gaussian blur strong
	 * @return the bitmap
	 */
	public Bitmap executeFilter(GaussianBlurStrong mGaussianBlurStrong) {

		return ConvolutionMask.berechneFaltung5x5(mGaussianBlurStrong.getBitmapIn(),
				mGaussianBlurStrong.getConvolutionMask());
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
	 * Gets the convolution mask.
	 * 
	 * @return the convolution mask
	 */
	public ConvolutionMask getConvolutionMask() {
		return convolutionMask;
	}

	/**
	 * Sets the convolution mask.
	 * 
	 * @param convolutionMask
	 *            the new convolution mask
	 */
	public void setConvolutionMask(ConvolutionMask convolutionMask) {
		this.convolutionMask = convolutionMask;
	}
}
