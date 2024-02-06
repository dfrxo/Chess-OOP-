package chess;

import java.util.ArrayList;

public interface Piece {	
	
	void move();
	boolean checkChecker();
	
	
	default ArrayList<ReturnPiece> pieceMaker(ArrayList<ReturnPiece> piecesOnBoard) {
		
		
		return null;
	}
}
