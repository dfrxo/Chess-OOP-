package chess;

import java.util.ArrayList;
import java.util.Map;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	
	
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}


class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}


public class Chess {
	
	enum Player { white, black }
	static ArrayList<ReturnPiece> piecesOnBoard = new ArrayList<>();
	static Player current;
	
	static ReturnPiece enpassantPossible=null;
	static boolean blackLeftCastle=true;
	static boolean whiteLeftCastle=true;
	static boolean blackRightCastle=true;
	static boolean whiteRightCastle=true;
	static char pawnPromotion='Q';

	
	/**
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {
		// h2 h3
		// convert 'h' to PieceFile h (done)
		
		ReturnPiece thePiece=null; // thePiece = the piece we're moving 
		ReturnPiece newSpot = null;
		ReturnPlay msg = new ReturnPlay();
		move = move.trim();
		
		
		
		//////////////////////////////
		//  DRAW AND RESIGN 
		//////////////////////////////
		
		
		
		
		
		try {
			if(move.substring(0,2).equals(move.substring(3,5))) { // Checks if they did -- h2 h2 -- move to the same spot

			}
		}	
		catch(Exception e){
			msg.piecesOnBoard = piecesOnBoard;
			msg.message = ReturnPlay.Message.ILLEGAL_MOVE;
			System.err.println("Wrong input");
			return msg;
		}
		
		
		
		
		String sf1 = String.valueOf(move.charAt(0));  // h
		String sr1 = String.valueOf(move.charAt(1));  // 2
		String sf2 = String.valueOf(move.charAt(3));  // h 
		String sr2 = String.valueOf(move.charAt(4));  // 3
		if(move.length()==7) {
			pawnPromotion = move.charAt(move.length()-1);
		}
		

		chess.ReturnPiece.PieceFile initFile = chess.ReturnPiece.PieceFile.valueOf(sf1); // enum.h
		int initRank = Integer.parseInt(sr1);											 // 2
		chess.ReturnPiece.PieceFile finalFile = chess.ReturnPiece.PieceFile.valueOf(sf2);// enum.h
		int finalRank = Integer.parseInt(sr2);											 // 3
		
		// After getting initial file and rank, piecesOnBoard[] to find which piece is at that address. 
		// Call the piece and return the outcome of that play by updating the board.
		

		// Searches for the piece we're moving
		for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
		{
			ReturnPiece pc = piecesOnBoard.get(i);
			if (pc.pieceFile.toString().equals(sf1) && pc.pieceRank == initRank)
			{
				thePiece = pc;
				break; 
			}
		}
		
		if(thePiece==null) {
			msg.piecesOnBoard = Chess.piecesOnBoard;
			msg.message = ReturnPlay.Message.ILLEGAL_MOVE;
			System.err.println("You chose an empty spot as a piece to move.");
			return msg;
		}
		
		
//		// Searches for where we're moving it to    -  We need to put this in each piece class	
		for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
		{
			ReturnPiece pc = piecesOnBoard.get(i);
			if (pc.pieceFile.toString().equals(sf2) && pc.pieceRank == finalRank)
			{
				newSpot = pc;
				break; 
			}
		}
		if (thePiece instanceof Pawn) {
			msg = ((Pawn) thePiece).move(sf2,finalRank);  
			
		}
		else if (thePiece instanceof Knight) {
			msg = ((Knight) thePiece).move(sf2, finalRank);

		} 
		else if (thePiece instanceof Bishop) {
			msg = ((Bishop) thePiece).move(sf2, finalRank);

		}
		else if (thePiece instanceof Rook) {
			msg = ((Rook) thePiece).move(sf2, finalRank);
		}
		else if (thePiece instanceof Queen) {
			msg = ((Queen) thePiece).move(sf2, finalRank);
		} 
		else if (thePiece instanceof King) {
			msg = ((King) thePiece).move(sf2, finalRank);

		}
//		else {
		// Code to execute if thePiece does not match any of the above
//		}
//		else {  // THIS ELSE STATEMENT IS FOR TESTING THE EAT THE PIECE MOVE
//				////////////////////////////////////////////////////////////
//				////////////////////////////////////////////////////////////
//			boolean emptySpace = true;        
//			for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
//			{
//				ReturnPiece pc = piecesOnBoard.get(i);
//				if (pc.pieceFile.toString().equals(sf2) && pc.pieceRank == finalRank)
//				{
//					emptySpace=false;
//					newSpot = pc;
//					break; 
//				}
//			}
//			
//			
//			if(emptySpace) {
//				thePiece.pieceRank = finalRank;
//				thePiece.pieceFile = finalFile;
//			}
//			msg.piecesOnBoard = Chess.piecesOnBoard;
//		}

			/////////////////////////////////////////////////////////////////////
		////////////////TESTING ONLY. REMOVE LATER//////////////////////////////
		/////////////////////////////////////////////////////////////////////////
		
	
		
		return msg;
	}
	public static void promotion(Pawn p) {
		ReturnPiece n;
		switch(pawnPromotion) {
		case 'N':
			if(p.pieceType.toString().charAt(0)=='W') {
				 n = new Knight(p.pieceFile, p.pieceRank, ReturnPiece.PieceType.WN);
			}
			else {
				 n = new Knight(p.pieceFile, p.pieceRank, ReturnPiece.PieceType.BN);
			}
			break;
		case 'B':
			if(p.pieceType.toString().charAt(0)=='W') {
				 n = new Bishop(p.pieceFile, p.pieceRank, ReturnPiece.PieceType.WB);
			}
			else {
				 n = new Bishop(p.pieceFile, p.pieceRank, ReturnPiece.PieceType.BB);
			}			break;
		case 'R':
			if(p.pieceType.toString().charAt(0)=='W') {
				 n = new Rook(p.pieceFile, p.pieceRank, ReturnPiece.PieceType.WR);
			}
			else {
				 n = new Rook(p.pieceFile, p.pieceRank, ReturnPiece.PieceType.BR);
			}			break;
		default:
			if(p.pieceType.toString().charAt(0)=='Q') {
				 n = new Queen(p.pieceFile, p.pieceRank, ReturnPiece.PieceType.WQ);
			}
			else {
				 n = new Queen(p.pieceFile, p.pieceRank, ReturnPiece.PieceType.BQ);
			}		
		}
		piecesOnBoard.remove(p);
		piecesOnBoard.add(n);
		pawnPromotion = 'Q';
	}
	public static boolean checkChecker() {
		
		
		return false;
	}
	public static void changePlayer() {
		if(current == Player.white){
			current = Player.black;
		}
		else {
			current = Player.white;
		}
	}
	public static void start() {		
		ReturnPiece bR1 = new Rook(ReturnPiece.PieceFile.a, 8, ReturnPiece.PieceType.BR);
		ReturnPiece bR2 = new Rook(ReturnPiece.PieceFile.h, 8, ReturnPiece.PieceType.BR);
		
		ReturnPiece bN1 = new Knight(ReturnPiece.PieceFile.b, 8, ReturnPiece.PieceType.BN);
		ReturnPiece bN2 = new Knight(ReturnPiece.PieceFile.g, 8, ReturnPiece.PieceType.BN);

		ReturnPiece bB1 = new Bishop(ReturnPiece.PieceFile.c, 8, ReturnPiece.PieceType.BB);
		ReturnPiece bB2 = new Bishop(ReturnPiece.PieceFile.f, 8, ReturnPiece.PieceType.BB);
		
		ReturnPiece bQ = new Queen(ReturnPiece.PieceFile.d, 8, ReturnPiece.PieceType.BQ); 
		ReturnPiece bK = new King(ReturnPiece.PieceFile.e, 8, ReturnPiece.PieceType.BK);

		ReturnPiece bP1= new Pawn(ReturnPiece.PieceFile.a, 3, ReturnPiece.PieceType.BP);
		ReturnPiece bP2= new Pawn(ReturnPiece.PieceFile.b, 7, ReturnPiece.PieceType.BP);
		ReturnPiece bP3= new Pawn(ReturnPiece.PieceFile.c, 7, ReturnPiece.PieceType.BP);
		ReturnPiece bP4= new Pawn(ReturnPiece.PieceFile.e, 7, ReturnPiece.PieceType.BP);
		ReturnPiece bP5= new Pawn(ReturnPiece.PieceFile.f, 7, ReturnPiece.PieceType.BP);
		ReturnPiece bP6= new Pawn(ReturnPiece.PieceFile.g, 7, ReturnPiece.PieceType.BP);
		ReturnPiece bP7= new Pawn(ReturnPiece.PieceFile.h, 7, ReturnPiece.PieceType.BP);
		ReturnPiece bP8= new Pawn(ReturnPiece.PieceFile.h, 7, ReturnPiece.PieceType.BP);

		ReturnPiece wP1= new Pawn(ReturnPiece.PieceFile.a, 2, ReturnPiece.PieceType.WP);
		ReturnPiece wP2= new Pawn(ReturnPiece.PieceFile.b, 2, ReturnPiece.PieceType.WP);
		ReturnPiece wP3= new Pawn(ReturnPiece.PieceFile.c, 2, ReturnPiece.PieceType.WP);
		ReturnPiece wP4= new Pawn(ReturnPiece.PieceFile.e, 2, ReturnPiece.PieceType.WP);
		ReturnPiece wP5= new Pawn(ReturnPiece.PieceFile.f, 2, ReturnPiece.PieceType.WP);
		ReturnPiece wP6= new Pawn(ReturnPiece.PieceFile.g, 2, ReturnPiece.PieceType.WP);
		ReturnPiece wP7= new Pawn(ReturnPiece.PieceFile.h, 2, ReturnPiece.PieceType.WP);
		ReturnPiece wP8= new Pawn(ReturnPiece.PieceFile.h, 2, ReturnPiece.PieceType.WP);

		ReturnPiece wR1= new Rook(ReturnPiece.PieceFile.a, 1, ReturnPiece.PieceType.WR);
		ReturnPiece wR2= new Rook(ReturnPiece.PieceFile.h, 1, ReturnPiece.PieceType.WR);
		ReturnPiece wN1= new Knight(ReturnPiece.PieceFile.b, 1, ReturnPiece.PieceType.WN);
		ReturnPiece wN2= new Knight(ReturnPiece.PieceFile.g, 1, ReturnPiece.PieceType.WN);
		ReturnPiece wB1= new Bishop(ReturnPiece.PieceFile.c, 1, ReturnPiece.PieceType.WB);
		ReturnPiece wB2= new Bishop(ReturnPiece.PieceFile.f, 1, ReturnPiece.PieceType.WB);
		ReturnPiece wQ= new Queen(ReturnPiece.PieceFile.d, 1, ReturnPiece.PieceType.WQ);
		ReturnPiece wK= new King(ReturnPiece.PieceFile.e, 1, ReturnPiece.PieceType.WK);

		Chess.piecesOnBoard.add(bN1);
		Chess.piecesOnBoard.add(bN2);
		Chess.piecesOnBoard.add(bB1);
		Chess.piecesOnBoard.add(bB2);
		Chess.piecesOnBoard.add(bQ);
		Chess.piecesOnBoard.add(bK);
		Chess.piecesOnBoard.add(bR1);
		Chess.piecesOnBoard.add(bR2);
		Chess.piecesOnBoard.add(wR2);
		Chess.piecesOnBoard.add(wR1);
		Chess.piecesOnBoard.add(wN2);
		Chess.piecesOnBoard.add(wN1);
		Chess.piecesOnBoard.add(wB1);
		Chess.piecesOnBoard.add(wB2);
		Chess.piecesOnBoard.add(wQ);
		Chess.piecesOnBoard.add(wK);	
		
		Chess.piecesOnBoard.add(wP1);
		Chess.piecesOnBoard.add(wP2);
		Chess.piecesOnBoard.add(wP3);
		Chess.piecesOnBoard.add(wP4);
		Chess.piecesOnBoard.add(wP5);
		Chess.piecesOnBoard.add(wP6);
		Chess.piecesOnBoard.add(wP7);
		//Chess.piecesOnBoard.add(wP8);
		
		Chess.piecesOnBoard.add(bP1);
		Chess.piecesOnBoard.add(bP2);
		Chess.piecesOnBoard.add(bP3);
		Chess.piecesOnBoard.add(bP4);
		Chess.piecesOnBoard.add(bP5);
		Chess.piecesOnBoard.add(bP6);
		Chess.piecesOnBoard.add(bP7);
		//Chess.piecesOnBoard.add(bP8);
		
		Chess.current = Player.white;
		
		PlayChess.printBoard(Chess.piecesOnBoard);
		}


}