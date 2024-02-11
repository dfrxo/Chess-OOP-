package chess;

import java.util.ArrayList;

public interface Piecee {
	/**
	ReturnPiece pce;
	String file;
	int row;
	ReturnPlay board;
	*/
	/**
	public Piecee(ReturnPiece pce, ReturnPlay board) {
		this.pce=pce;
		this.file = pce.pieceFile.toString();
		this.row=pce.pieceRank;
		this.board = board;
	}
	*/
	boolean checkCheck();
	boolean validMove();
	ReturnPlay move(ArrayList<ReturnPiece> board, chess.ReturnPiece.PieceFile finalFile, int finalRank);
	
}
