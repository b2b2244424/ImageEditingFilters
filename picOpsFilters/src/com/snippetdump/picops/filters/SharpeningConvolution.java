package com.snippetdump.picops.filters;

import com.snippetdump.picops.model.ConvolutionMask;

import android.graphics.Bitmap;

/**
 * The Class SharpeningConvolution.
 */
public class SharpeningConvolution extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The convolution mask. */
	private ConvolutionMask convolutionMask;

	/**
	 * Instantiates a new sharpening convolution.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public SharpeningConvolution(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
		double[][] sharpeningConfig = new double[][] { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 }, };
		this.convolutionMask = new ConvolutionMask(3);
		this.convolutionMask.applyFaltungskonfiguration(sharpeningConfig);
		this.convolutionMask.Factor = 1;
		this.convolutionMask.Offset = 0;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mSharpeningConvolution
	 *            the m sharpening convolution
	 * @return the bitmap
	 */
	public Bitmap executeFilter(SharpeningConvolution mSharpeningConvolution) {

		return ConvolutionMask.berechneFaltung3x3(mSharpeningConvolution.getBitmapIn(),
				mSharpeningConvolution.getConvolutionMask());
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
