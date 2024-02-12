package chess;

import java.util.ArrayList;

public class Pawn implements Piecee{
	ReturnPiece pce;
	chess.ReturnPiece.PieceFile file;
	int row;
	ArrayList<ReturnPiece> board = new ArrayList<ReturnPiece>();
	String finalFile;
	int finalRank;
	chess.ReturnPlay.Message m = null;
	
<<<<<<< HEAD
	public Pawn(ReturnPiece pce, ArrayList<ReturnPiece> board, chess.ReturnPiece.PieceFile finalFile, int finalRank) 
	{
		this.pce = pce;
		this.board = board;
=======
	public Pawn(ReturnPiece pce, ReturnPlay board) {
		super(pce, board);
	}
	boolean checkCheck() {
		return false;
	}
	void move() {
>>>>>>> a7b55587edb57558406b0b753ef12f0987b2ef1e
		
	}
	public String toString() {
		return file + String.valueOf(row);
	}

	
	public boolean checkCheck() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean validMove() {
		return true; 
	}

	/**
	 * @return ReturnPlay object. message is null if validMove() == True. Updates board.
	 */
	public ReturnPlay move(ArrayList<ReturnPiece> board, chess.ReturnPiece.PieceFile finalFile, int finalRank) {
		// TODO Auto-generated method stub
		if (!checkCheck() && validMove())
		{
			  chess.ReturnPlay updated_board_message = new chess.ReturnPlay();
			  
			  int ind = board.indexOf(pce);
			  pce.pieceFile = finalFile;
			  pce.pieceRank = finalRank;
			  board.set(ind, pce);
			  
			  updated_board_message.piecesOnBoard = board;
			  updated_board_message.message = m;
			  
			  return updated_board_message;
			  
		}
		return null;
	}
}
		

