package chess;

public class Pawn extends Piecee{
//	ReturnPiece pce;
//	String file;
//	int row;
//	ReturnPlay board;
	
	public Pawn(ReturnPiece pce, ReturnPlay board) {
		super(pce, board);
	}
	boolean checkCheck() {
		return false;
	}
	void move() {
		
	}
	public String toString() {
		return file + String.valueOf(row);
	}

}
