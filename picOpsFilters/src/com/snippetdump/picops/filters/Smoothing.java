package com.snippetdump.picops.filters;

import com.snippetdump.picops.model.ConvolutionMask;

import android.graphics.Bitmap;

/**
 * The Class Smoothing.
 */
public class Smoothing extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The value. */
	private int value;

	/** The convolution mask. */
	private ConvolutionMask convolutionMask;

	/**
	 * Instantiates a new smoothing.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param value
	 *            the value
	 */
	public Smoothing(Bitmap bitmapIn, int value) {
		this.bitmapIn = bitmapIn;
		this.value = value;
		this.convolutionMask = new ConvolutionMask(value);
	}

	/**
	 * Execute filter.
	 * 
	 * @param mSmoothing
	 *            the m smoothing
	 * @return the bitmap
	 */
	public Bitmap executeFilter(Smoothing mSmoothing) {

		mSmoothing.getConvolutionMask().setAll(1);
		mSmoothing.getConvolutionMask().Factor = mSmoothing.getConvolutionMask().Maske.length;
		mSmoothing.getConvolutionMask().Offset = 0;

		return ConvolutionMask.berechneFaltungMxM(mSmoothing.getBitmapIn(),
				mSmoothing.getConvolutionMask());
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
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(int value) {
		this.value = value;
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
