package com.snippetdump.picops.filters;

import com.snippetdump.picops.model.ConvolutionMask;

import android.graphics.Bitmap;

/**
 * The Class SmoothingForExport.
 */
public class SmoothingForExport extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The cur height. */
	private int oldSizeOfMask, prevWidth, prevHeight, curWidth, curHeight;

	/** The convolution mask. */
	private ConvolutionMask convolutionMask;

	/**
	 * Instantiates a new smoothing for export.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param oldSizeOfMask
	 *            the old size of mask
	 * @param prevWidth
	 *            the prev width
	 * @param prevHeight
	 *            the prev height
	 * @param curWidth
	 *            the cur width
	 * @param curHeight
	 *            the cur height
	 */
	public SmoothingForExport(Bitmap bitmapIn, int oldSizeOfMask, int prevWidth, int prevHeight,
			int curWidth, int curHeight) {
		this.bitmapIn = bitmapIn;
		this.oldSizeOfMask = oldSizeOfMask;
		this.prevWidth = prevWidth;
		this.prevHeight = prevHeight;
		this.curWidth = curWidth;
		this.curHeight = curHeight;
		this.convolutionMask = new ConvolutionMask(adjustSizeForMask(oldSizeOfMask, prevWidth,
				prevHeight, curWidth, curHeight));
	}

	/**
	 * Execute filter.
	 * 
	 * @param mSmoothingForExport
	 *            the m smoothing for export
	 * @return the bitmap
	 */
	public Bitmap executeFilter(SmoothingForExport mSmoothingForExport) {

		mSmoothingForExport.getConvolutionMask().setAll(1);
		mSmoothingForExport.getConvolutionMask().Factor = convolutionMask.Maske.length;
		mSmoothingForExport.getConvolutionMask().Offset = 0;

		return ConvolutionMask.berechneFaltungMxM(mSmoothingForExport.getBitmapIn(),
				mSmoothingForExport.getConvolutionMask());
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
	 * Adjust size for mask.
	 * 
	 * @param oldSizeOfMask
	 *            the old size of mask
	 * @param prevWidth
	 *            the prev width
	 * @param prevHeight
	 *            the prev height
	 * @param curWidth
	 *            the cur width
	 * @param curHeight
	 *            the cur height
	 * @return the int
	 */
	private int adjustSizeForMask(int oldSizeOfMask, int prevWidth, int prevHeight, int curWidth,
			int curHeight) {

		int newSizeMask = 0;
		int prevPixels = prevWidth * prevHeight;
		int oldMaskPixels = oldSizeOfMask * oldSizeOfMask;

		double oldRatio = prevPixels / oldMaskPixels;
		float fltNewSizeMask = (float) Math.sqrt(((curWidth * curHeight) / oldRatio));
		newSizeMask = Math.round(fltNewSizeMask);

		return newSizeMask;
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
	 * Gets the old size of mask.
	 * 
	 * @return the old size of mask
	 */
	public int getOldSizeOfMask() {
		return oldSizeOfMask;
	}

	/**
	 * Sets the old size of mask.
	 * 
	 * @param oldSizeOfMask
	 *            the new old size of mask
	 */
	public void setOldSizeOfMask(int oldSizeOfMask) {
		this.oldSizeOfMask = oldSizeOfMask;
	}

	/**
	 * Gets the prev width.
	 * 
	 * @return the prev width
	 */
	public int getPrevWidth() {
		return prevWidth;
	}

	/**
	 * Sets the prev width.
	 * 
	 * @param prevWidth
	 *            the new prev width
	 */
	public void setPrevWidth(int prevWidth) {
		this.prevWidth = prevWidth;
	}

	/**
	 * Gets the prev height.
	 * 
	 * @return the prev height
	 */
	public int getPrevHeight() {
		return prevHeight;
	}

	/**
	 * Sets the prev height.
	 * 
	 * @param prevHeight
	 *            the new prev height
	 */
	public void setPrevHeight(int prevHeight) {
		this.prevHeight = prevHeight;
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
}
