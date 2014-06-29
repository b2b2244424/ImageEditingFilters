package com.snippetdump.picops.filters;

import com.snippetdump.picops.model.ConvolutionMask;

import android.graphics.Bitmap;

/**
 * The Class GaussianBlur.
 */
public class GaussianBlur extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The convolution mask. */
	private ConvolutionMask convolutionMask;

	/**
	 * Instantiates a new gaussian blur.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public GaussianBlur(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
		double[][] convolutionMask = new double[][] { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 }, };
		this.convolutionMask = new ConvolutionMask(3);
		this.convolutionMask.applyFaltungskonfiguration(convolutionMask);
		this.convolutionMask.Factor = 16;
		this.convolutionMask.Offset = 0;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mGaussianBlur
	 *            the m gaussian blur
	 * @return the bitmap
	 */
	public Bitmap executeFilter(GaussianBlur mGaussianBlur) {

		return ConvolutionMask.berechneFaltung3x3(mGaussianBlur.getBitmapIn(),
				mGaussianBlur.getConvolutionMask());
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
