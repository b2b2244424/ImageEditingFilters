package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;

/**
 * The Class MirrorImageGlass.
 */
public class MirrorImageGlass extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The Constant GAP. */
	private static final int GAP = 4;

	/**
	 * Instantiates a new mirror image glass.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 */
	public MirrorImageGlass(Bitmap bitmapIn) {
		this.bitmapIn = bitmapIn;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mMirrorImageGlass
	 *            the m mirror image glass
	 * @return the bitmap
	 */
	public Bitmap executeFilter(MirrorImageGlass mMirrorImageGlass) {

		long time = System.currentTimeMillis();
		int width = mMirrorImageGlass.getBitmapIn().getWidth();
		int height = mMirrorImageGlass.getBitmapIn().getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1.0f, -1.0f);

		Bitmap mirroredImage = Bitmap.createBitmap(mMirrorImageGlass.bitmapIn, 0, height / 2,
				width, height / 2, matrix, false);
		Bitmap fullImage = Bitmap.createBitmap(width, (height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(fullImage);
		canvas.drawBitmap(mMirrorImageGlass.getBitmapIn(), 0, 0, null);

		Paint paint = new Paint();
		canvas.drawRect(0, height, width, height + GAP, paint);

		canvas.drawBitmap(mirroredImage, 0, height + GAP, null);

		Paint paint2 = new Paint();
		LinearGradient lgrad = new LinearGradient(0, mMirrorImageGlass.getBitmapIn().getHeight(),
				0, fullImage.getHeight() + GAP, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
		paint2.setShader(lgrad);
		paint2.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawRect(0, height, width, fullImage.getHeight() + GAP, paint2);
		mirroredImage.recycle();
		mirroredImage = null;
		time = System.currentTimeMillis() - time;

		return fullImage;
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
