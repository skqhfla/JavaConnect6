package app.myapp.imageprocessing.Listener;

import app.myapp.imageprocessing.Canvas;
import app.myapp.imageprocessing.DB.ProcedureDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoListener implements ActionListener {
	
	boolean first = true;

	@Override
	public void actionPerformed(ActionEvent e) {
		if(first && ProcedureDB.redoImage.isEmpty()) {
			first = false;
			ProcedureDB.redoImage.push(ProcedureDB.next);
		}
		else if(ProcedureDB.redoImage.isEmpty()) {
			System.out.println("Redo Stack is Empty");
			return;
		}
//		System.out.println(ProcedureDB.redoImage.peek());
		Canvas.resultImage.image = LoadImageListener.deepCopy(ProcedureDB.redoImage.peek());
		ProcedureDB.undoImage.push(ProcedureDB.redoImage.pop());
		Canvas.resultImage.repaint();
	}
	
}
