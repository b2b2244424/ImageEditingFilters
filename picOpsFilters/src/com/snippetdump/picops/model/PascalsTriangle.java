package com.snippetdump.picops.model;

/**
 * Is needed in order to create Gaussian convolution masks.
 */
public class PascalsTriangle extends Model {

	/** The pasc dreieck. */
	private static final int[][] pascDreieck = { { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 3, 3, 1, 0, 0, 0, 0, 0, 0, 0 }, { 1, 4, 6, 4, 1, 0, 0, 0, 0, 0, 0 },
			{ 1, 5, 10, 10, 5, 1, 0, 0, 0, 0, 0 }, { 1, 6, 15, 20, 15, 6, 1, 0, 0, 0, 0 },
			{ 1, 7, 21, 35, 35, 21, 7, 1, 0, 0, 0 }, { 1, 8, 28, 56, 70, 56, 28, 8, 1, 0, 0 },
			{ 1, 9, 36, 84, 126, 126, 84, 36, 9, 1 } /*
													 * , {1, 10, 45, 120, 210,
													 * 252, 210, 120, 45, 10,
													 * 1}, {1, 11, 55, 165, 330,
													 * 462, 462, 330, 165, 55,
													 * 11, 1}, {1, 12, 66, 220,
													 * 495, 792, 924, 792, 495,
													 * 220, 66, 12, 1}, {1, 13,
													 * 78, 286, 715, 1287, 1716,
													 * 1716, 1287, 715, 286, 78,
													 * 13, 1}, {1, 14, 91, 364,
													 * 1001, 2002, 3003, 3432,
													 * 3003, 2002, 1001, 364,
													 * 91, 14, 1}, {1, 15, 105,
													 * 455, 1365, 3003, 5005,
													 * 6435, 6435, 5005, 3003,
													 * 1365, 455, 105, 15, 1},
													 * {1, 16, 120, 560, 1820,
													 * 4368, 8008, 11440, 12870,
													 * 11440, 8008, 4368, 1820,
													 * 560, 120, 16, 1}, {1, 17,
													 * 136, 680, 2380, 6188,
													 * 12376, 19448, 24310,
													 * 24310, 19448, 12376,
													 * 6188, 2380, 680, 136, 17,
													 * 1}, {1, 18, 153, 816,
													 * 3060, 8568, 18564, 31824,
													 * 43758, 48620, 43758,
													 * 31824, 18564, 8568, 3060,
													 * 816, 153, 18, 1}, {1, 19,
													 * 171, 969, 3876, 11628,
													 * 27132, 50388, 75582,
													 * 92378, 92378, 75582,
													 * 50388, 27132, 11628,
													 * 3876, 969, 171 , 19, 1},
													 * {1, 20, 190, 1140, 4845,
													 * 15504, 38760, 77520,
													 * 125970, 167960, 184756,
													 * 167960, 125970, 77520,
													 * 38760, 15504, 4845, 1140,
													 * 190 , 20 , 1}, {1, 21,
													 * 210, 1330, 5985, 20349,
													 * 54264, 116280, 203490,
													 * 293930, 352716, 352716,
													 * 293930, 203490, 116280,
													 * 54264, 20349, 5985, 1330,
													 * 210, 21, 1}, {1, 22, 231,
													 * 1540, 7315, 26334, 74613,
													 * 170544, 319770, 497420,
													 * 646646, 705432, 646646,
													 * 497420, 319770, 170544,
													 * 74613, 26334, 7315, 1540,
													 * 231, 22, 1}, {1, 23, 253,
													 * 1771, 8855, 33649,
													 * 100947, 245157, 490314,
													 * 817190, 1144066, 1352078,
													 * 1352078, 1144066, 817190,
													 * 490314, 245157, 100947,
													 * 33649, 8855, 1771, 253,
													 * 23, 1}, {1, 24, 276,
													 * 2024, 10626, 42504,
													 * 134596, 346104, 735471,
													 * 1307504, 1961256,
													 * 2496144, 2704156,
													 * 2496144, 1961256,
													 * 1307504, 735471, 346104,
													 * 134596, 42504, 10626,
													 * 2024, 276, 24, 1}, {1,
													 * 25, 300, 2300, 12650,
													 * 53130, 177100, 480700,
													 * 1081575, 2042975,
													 * 3268760, 4457400,
													 * 5200300, 5200300,
													 * 4457400, 3268760,
													 * 2042975, 1081575, 480700,
													 * 177100, 53130, 12650,
													 * 2300, 300, 25, 1}
													 */};

	/**
	 * Instantiates a new pascalsches dreieck.
	 */
	public PascalsTriangle() {
	}

	/**
	 * Generate faltungsmaske.
	 * 
	 * @param sizeMask
	 *            the size mask
	 * @param prevImgWidth
	 *            the prev img width
	 * @param prevImgHeight
	 *            the prev img height
	 * @param currImgWidth
	 *            the curr img width
	 * @param currImgHeight
	 *            the curr img height
	 * @return the double[][]
	 */
	public static double[][] generateFaltungsmaske(int sizeMask, int prevImgWidth,
			int prevImgHeight, int currImgWidth, int currImgHeight) {
		/**
		 * Anhand der Bildpunkte des skalierten Bildes und der Bildpunkte der
		 * dort benutzten Faltungsmaske wird ein Bildpunktverh�ltnis gebildet.
		 **/
		int ratioImage = prevImgWidth * prevImgHeight;
		int ratioMask = sizeMask * sizeMask;
		float ratioImageToMask = ratioImage / ratioMask;

		/**
		 * Berechnung der neuen Faltungsmaskengr��e durch die Wurzel aus den
		 * gesamten Bildpunkten des neuen Bildes durch das Bildpunktverh�ltnis
		 * des alten Bildes
		 **/
		float dblNewMaskSize = (float) Math
				.sqrt(((currImgWidth * currImgHeight) / ratioImageToMask));
		/** Runden auf Integer-Wert **/
		int newMaskSize = Math.round(dblNewMaskSize);
		if (newMaskSize % 2 == 0) {
			newMaskSize += 1;
		}
		/** Ausgabe-Array **/
		double[][] outputArray = new double[newMaskSize][newMaskSize];
		/** Array anlegen und mit Zeile aus dem Pascalschen Dreieck f�llen **/
		int[] arrayFromPasc = new int[newMaskSize];
		for (int i = 0; i < newMaskSize; i++) {
			// arrayFromPasc[i] = pascDreieck[i][newMaskSize-1];
			arrayFromPasc[i] = pascDreieck[newMaskSize - 1][i];
		}
		/** F�llen der Faltungsmaske **/
		for (int x = 0; x < newMaskSize; x++) {
			for (int y = 0; y < newMaskSize; y++) {
				outputArray[x][y] = arrayFromPasc[y] * arrayFromPasc[x];
			}
		}

		return outputArray;
	}

	/**
	 * Koeffizientenermittlung zur Normierung.
	 * 
	 * @param array
	 *            the array
	 * @return the int
	 */
	public static int koeffZurNorm(double[][] array) {
		int koeffizient, x, y;
		koeffizient = x = y = 0;

		for (x = 0; x < array.length; x++) {
			for (y = 0; y < array.length; y++) {
				koeffizient += array[x][y];
			}
		}

		return koeffizient;
	}
}
