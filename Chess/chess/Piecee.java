package chess;

public abstract class Piecee {
	ReturnPiece pce;
	String file;
	int row;
	ReturnPlay board;
	
	public Piecee(ReturnPiece pce, ReturnPlay board) {
		this.pce=pce;
		this.file = pce.pieceFile.toString();
		this.row=pce.pieceRank;
		this.board = board;
	}
	abstract boolean checkCheck();
	abstract void move();
	
}
