package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Pawn extends ReturnPiece{
	// Inherited fields :: 
//	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
//	        BP, BR, BN, BB, BK, BQ};
//	static enum PieceFile {a, b, c, d, e, f, g, h};
//	PieceType pieceType;
//	PieceFile pieceFile;
//	int pieceRank;  // 1..8
	
	
	
	
//	ReturnPiece pce;
//	chess.ReturnPiece.PieceFile file;
//	int row;
	ArrayList<ReturnPiece> board = (ArrayList<ReturnPiece>)Chess.piecesOnBoard.clone();
//	String finalFile;
	int finalRank;
//	chess.ReturnPlay.Message m = null;
	
//	public Pawn(ReturnPiece pce, ArrayList<ReturnPiece> board, chess.ReturnPiece.PieceFile finalFile, int finalRank) 
//	{
//		this.pce = pce;
//		this.board = board;
//	}
//	public Pawn(ReturnPiece pce, ReturnPlay board) {
//		//super(pce, board);
//	}
	public boolean checkCheck() {
		return false;
	}
	void move() {

		
	}
	public String toString() {
		return pieceFile + String.valueOf(pieceRank);
	}

	
	public boolean validMove() {
		return true; 
	}

	/**
	 * @return ReturnPlay object. message is null if validMove() == True. Updates board.
	 */
	public ReturnPlay move(ReturnPiece n) {
		
		System.out.println("this works");
		return null;
	}
//	public ReturnPlay move(ArrayList<ReturnPiece> board, chess.ReturnPiece.PieceFile finalFile, int finalRank) {
//		// TODO Auto-generated method stub
//		if (!checkCheck() && validMove())
//		{
//			  chess.ReturnPlay updated_board_message = new chess.ReturnPlay();
//			  
//			  int ind = board.indexOf(pce);
//			  pce.pieceFile = finalFile;
//			  pce.pieceRank = finalRank;
//			  board.set(ind, pce);
//			  
//			  updated_board_message.piecesOnBoard = board;
//			  updated_board_message.message = m;
//			  
//			  return updated_board_message;
//			  
//		}
//		return null;
//	}
}
		