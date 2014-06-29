package com.snippetdump.picops.filters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * The Class RoundCorners.
 */
public class RoundCorners extends Filter {

	/** The bitmap in. */
	private Bitmap bitmapIn;

	/** The round. */
	private float round;

	/**
	 * Instantiates a new round corners.
	 * 
	 * @param bitmapIn
	 *            the bitmap in
	 * @param round
	 *            the round
	 */
	public RoundCorners(Bitmap bitmapIn, float round) {
		this.bitmapIn = bitmapIn;
		this.round = round;
	}

	/**
	 * Execute filter.
	 * 
	 * @param mRoundCorners
	 *            the m round corners
	 * @return the bitmap
	 */
	public Bitmap executeFilter(RoundCorners mRoundCorners) {

		long time = System.currentTimeMillis();
		int width = mRoundCorners.getBitmapIn().getWidth();
		int height = mRoundCorners.getBitmapIn().getHeight();
		Bitmap bitmapOut = Bitmap.createBitmap(width, height, mRoundCorners.getBitmapIn()
				.getConfig());

		Canvas canvas = new Canvas(bitmapOut);
		canvas.drawARGB(0, 0, 0, 0);
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);

		final Rect rectangle = new Rect(0, 0, width, height);
		final RectF rectanglef = new RectF(rectangle);

		canvas.drawRoundRect(rectanglef, round, round, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(mRoundCorners.getBitmapIn(), rectangle, rectangle, paint);
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

	/**
	 * Gets the round.
	 * 
	 * @return the round
	 */
	public float getRound() {
		return round;
	}

	/**
	 * Sets the round.
	 * 
	 * @param round
	 *            the new round
	 */
	public void setRound(float round) {
		this.round = round;
	}
}
