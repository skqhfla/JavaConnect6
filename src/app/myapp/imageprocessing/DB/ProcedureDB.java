package app.myapp.imageprocessing.DB;

import java.util.Stack;
import java.awt.image.BufferedImage;

public class ProcedureDB {
	public static Stack<BufferedImage> undoImage = new Stack<BufferedImage>();
	public static Stack<BufferedImage> redoImage = new Stack<BufferedImage>();
	public static BufferedImage next = null;
	public static BufferedImage blackAndWhite = null;
	
	public static Stack<Integer> brightness = new Stack<Integer>();
	public static Stack<Integer> redoBrightness = new Stack<Integer>();
	public static Stack<Integer> contrast = new Stack<Integer>();
	public static Stack<Integer> redoContrast = new Stack<Integer>();
	public static Stack<Float> saturation = new Stack<Float>();
	public static Stack<Integer> redoSaturation = new Stack<Integer>();
	
	public static void pushToUndoStack(BufferedImage BI) {
		if(next!=null) {
		ProcedureDB.undoImage.push(next);
		System.out.println(next);
		}
		next = BI;
	}
}


