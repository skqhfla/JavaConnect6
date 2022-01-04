package app.myapp.imageprocessing.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import app.myapp.imageprocessing.Canvas;
import app.myapp.imageprocessing.DB.ProcedureDB;

public class UndoListener implements ActionListener {
	
	boolean first = true;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(ProcedureDB.undoImage.isEmpty()) {
			System.out.println("Undo Stack is Empty");
			return;
		}
//		System.out.println(ProcedureDB.undoImage.peek());
		Canvas.resultImage.image = LoadImageListener.deepCopy(ProcedureDB.undoImage.peek());
		ProcedureDB.redoImage.push(ProcedureDB.undoImage.pop());
		Canvas.resultImage.repaint();
	}
	
}
