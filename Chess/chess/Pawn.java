package chess;

public class Pawn extends Piecee{
//	ReturnPiece pce;
//	String file;
//	int row;
//	ReturnPlay board;
	
	public Pawn(ReturnPiece pce, ReturnPlay board) {
		super(pce, board);
	}

	@Override
	boolean checkCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void move() {
		// TODO Auto-generated method stub
		
	}

}
