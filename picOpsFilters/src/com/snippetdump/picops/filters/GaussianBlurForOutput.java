package com.snippetdump.picops.filters;

import com.snippetdump.picops.model.ConvolutionMask;
import com.snippetdump.picops.model.PascalsTriangle;

import android.graphics.Bitmap;

/**
 * The Class GaussianBlurForOutput.
 */
public class GaussianBlurForOutput extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The convolution mask. */
	private ConvolutionMask convolutionMask;

	/** The orig width. */
	private int origWidth;

	/** The orig height. */
	private int origHeight;

	/** The cur width. */
	private int curWidth;

	/** The cur height. */
	private int curHeight;

	/** The used mask size. */
	private int usedMaskSize;

	/**
	 * Instantiates a new gaussian blur for output.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param origWidth
	 *            the orig width
	 * @param origHeight
	 *            the orig height
	 * @param curWidth
	 *            the cur width
	 * @param curHeight
	 *            the cur height
	 * @param usedMaskSize
	 *            the used mask size
	 */
	public GaussianBlurForOutput(Bitmap bitmapIn, int origWidth, int origHeight, int curWidth,
			int curHeight, int usedMaskSize) {
		this.bitmapIn = bitmapIn;
		this.origWidth = origWidth;
		this.origHeight = origHeight;
		this.curWidth = curWidth;
		this.curHeight = curHeight;
		this.usedMaskSize = usedMaskSize;
		double[][] convolutionMask = PascalsTriangle.generateFaltungsmaske(usedMaskSize,
				origWidth, origHeight, curWidth, curHeight);
		this.convolutionMask = new ConvolutionMask(convolutionMask.length);
		this.convolutionMask.applyFaltungskonfigurationBig(convolutionMask, convolutionMask.length);
		this.convolutionMask.Factor = PascalsTriangle.koeffZurNorm(convolutionMask);
		this.convolutionMask.Offset = 0;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mGaussianBlurForOutput
	 *            the m gaussian blur for output
	 * @return the bitmap
	 */
	public Bitmap executeFilter(GaussianBlurForOutput mGaussianBlurForOutput) {

		return ConvolutionMask.berechneFaltungMxM(mGaussianBlurForOutput.getBitmapIn(),
				mGaussianBlurForOutput.getConvolutionMask());
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

	/**
	 * Gets the orig width.
	 * 
	 * @return the orig width
	 */
	public int getOrigWidth() {
		return origWidth;
	}

	/**
	 * Sets the orig width.
	 * 
	 * @param origWidth
	 *            the new orig width
	 */
	public void setOrigWidth(int origWidth) {
		this.origWidth = origWidth;
	}

	/**
	 * Gets the orig height.
	 * 
	 * @return the orig height
	 */
	public int getOrigHeight() {
		return origHeight;
	}

	/**
	 * Sets the orig height.
	 * 
	 * @param origHeight
	 *            the new orig height
	 */
	public void setOrigHeight(int origHeight) {
		this.origHeight = origHeight;
	}

	/**
	 * Gets the cur width.
	 * 
	 * @return the cur width
	 */
	public int getCurWidth() {
		return curWidth;
	}

	/**
	 * Sets the cur width.
	 * 
	 * @param curWidth
	 *            the new cur width
	 */
	public void setCurWidth(int curWidth) {
		this.curWidth = curWidth;
	}

	/**
	 * Gets the cur height.
	 * 
	 * @return the cur height
	 */
	public int getCurHeight() {
		return curHeight;
	}

	/**
	 * Sets the cur height.
	 * 
	 * @param curHeight
	 *            the new cur height
	 */
	public void setCurHeight(int curHeight) {
		this.curHeight = curHeight;
	}

	/**
	 * Gets the used mask size.
	 * 
	 * @return the used mask size
	 */
	public int getUsedMaskSize() {
		return usedMaskSize;
	}

	/**
	 * Sets the used mask size.
	 * 
	 * @param usedMaskSize
	 *            the new used mask size
	 */
	public void setUsedMaskSize(int usedMaskSize) {
		this.usedMaskSize = usedMaskSize;
	}
}
