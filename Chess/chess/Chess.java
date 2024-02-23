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
			msg = ((Pawn) thePiece).move(sf2,finalRank);  //    --------------------
		}
		else if (thePiece instanceof Knight) {
			msg = ((Knight) thePiece).move(sf2, finalRank);

		} 
		//else if (thePiece instanceof Bishop) {
		//	
		//}
		else if (thePiece instanceof Rook) {
			msg = ((Rook) thePiece).move(sf2, finalRank);
		}
		else if (thePiece instanceof Queen) {
			msg = ((Queen) thePiece).move(sf2, finalRank);
		} 
//		else if (thePiece instanceof King) {
//		
//		}
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
		// Initialize black rooks
		ReturnPiece bR1 = new Rook();
		bR1.pieceFile = chess.ReturnPiece.PieceFile.a;
		bR1.pieceRank = 8;
		bR1.pieceType = chess.ReturnPiece.PieceType.BR;
		ReturnPiece bR2 = new Rook();
		bR2.pieceFile = chess.ReturnPiece.PieceFile.h;
		bR2.pieceRank = 8;
		bR2.pieceType = chess.ReturnPiece.PieceType.BR;
		// Initialize black knights
		ReturnPiece bN1 = new Knight();
		bN1.pieceFile = chess.ReturnPiece.PieceFile.b;
		bN1.pieceRank = 8;
		bN1.pieceType = chess.ReturnPiece.PieceType.BN;

		ReturnPiece bN2 = new Knight();
		bN2.pieceFile = chess.ReturnPiece.PieceFile.g;
		bN2.pieceRank = 8;
		bN2.pieceType = chess.ReturnPiece.PieceType.BN;

		// Initialize black bishops
		ReturnPiece bB1 = new ReturnPiece();
		bB1.pieceFile = chess.ReturnPiece.PieceFile.c;
		bB1.pieceRank = 8;
		bB1.pieceType = chess.ReturnPiece.PieceType.BB;

		ReturnPiece bB2 = new ReturnPiece();
		bB2.pieceFile = chess.ReturnPiece.PieceFile.f;
		bB2.pieceRank = 8;
		bB2.pieceType = chess.ReturnPiece.PieceType.BB;

		// Initialize black queen
		ReturnPiece bQ = new Queen();
		bQ.pieceFile = chess.ReturnPiece.PieceFile.d;
		bQ.pieceRank = 8;
		bQ.pieceType = chess.ReturnPiece.PieceType.BQ;

		// Initialize black king
		ReturnPiece bK = new ReturnPiece();
		bK.pieceFile = chess.ReturnPiece.PieceFile.e;
		bK.pieceRank = 8;
		bK.pieceType = chess.ReturnPiece.PieceType.BK;

				ReturnPiece bp1 = new Pawn();
				bp1.pieceFile = chess.ReturnPiece.PieceFile.a;
				bp1.pieceRank = 4;
				bp1.pieceType = chess.ReturnPiece.PieceType.BP;
				Chess.piecesOnBoard.add(bp1);

				ReturnPiece bp2 = new Pawn();
				bp2.pieceFile = chess.ReturnPiece.PieceFile.b;
				bp2.pieceRank = 7;
				bp2.pieceType = chess.ReturnPiece.PieceType.BP;
				Chess.piecesOnBoard.add(bp2);

				ReturnPiece bp3 = new Pawn();
				bp3.pieceFile = chess.ReturnPiece.PieceFile.c;
				bp3.pieceRank = 7;
				bp3.pieceType = chess.ReturnPiece.PieceType.BP;
				Chess.piecesOnBoard.add(bp3);

//				ReturnPiece bp4 = new Pawn();
//				bp4.pieceFile = chess.ReturnPiece.PieceFile.d;
//				bp4.pieceRank = 7;
//				bp4.pieceType = chess.ReturnPiece.PieceType.BP;
//				Chess.piecesOnBoard.add(bp4);

				ReturnPiece bp5 = new Pawn();
				bp5.pieceFile = chess.ReturnPiece.PieceFile.e;
				bp5.pieceRank = 7;
				bp5.pieceType = chess.ReturnPiece.PieceType.BP;
				Chess.piecesOnBoard.add(bp5);

				ReturnPiece bp6 = new Pawn();
				bp6.pieceFile = chess.ReturnPiece.PieceFile.f;
				bp6.pieceRank = 7;
				bp6.pieceType = chess.ReturnPiece.PieceType.BP;
				Chess.piecesOnBoard.add(bp6);

				ReturnPiece bp7 = new Pawn();
				bp7.pieceFile = chess.ReturnPiece.PieceFile.g;
				bp7.pieceRank = 7;
				bp7.pieceType = chess.ReturnPiece.PieceType.BP;
				Chess.piecesOnBoard.add(bp7);


				ReturnPiece bp8 = new Pawn();
				bp8.pieceFile = chess.ReturnPiece.PieceFile.h;
				bp8.pieceRank = 7;
				bp8.pieceType = chess.ReturnPiece.PieceType.BP;
				Chess.piecesOnBoard.add(bp8);

		// Initialize white pieces similarly
		ReturnPiece wR1 = new Rook();
		wR1.pieceFile = chess.ReturnPiece.PieceFile.a;
		wR1.pieceRank = 1;
		wR1.pieceType = chess.ReturnPiece.PieceType.WR;

		ReturnPiece wR2 = new Rook();
		wR2.pieceFile = chess.ReturnPiece.PieceFile.h;
		wR2.pieceRank = 1;
		wR2.pieceType = chess.ReturnPiece.PieceType.WR;
		// Initialize white knights
		ReturnPiece wN1 = new Knight();
		wN1.pieceFile = chess.ReturnPiece.PieceFile.b;
		wN1.pieceRank = 1;
		wN1.pieceType = chess.ReturnPiece.PieceType.WN;

		ReturnPiece wN2 = new Knight();
		wN2.pieceFile = chess.ReturnPiece.PieceFile.g;
		wN2.pieceRank = 1;
		wN2.pieceType = chess.ReturnPiece.PieceType.WN;

		// Initialize black bishops
		ReturnPiece wB1 = new ReturnPiece();
		wB1.pieceFile = chess.ReturnPiece.PieceFile.c;
		wB1.pieceRank = 1;
		wB1.pieceType = chess.ReturnPiece.PieceType.WB;

		ReturnPiece wB2 = new ReturnPiece();
		wB2.pieceFile = chess.ReturnPiece.PieceFile.f;
		wB2.pieceRank = 1;
		wB2.pieceType = chess.ReturnPiece.PieceType.WB;

		// Initialize black queen
		ReturnPiece wQ = new Queen();
		wQ.pieceFile = chess.ReturnPiece.PieceFile.d;
		wQ.pieceRank = 1;
		wQ.pieceType = chess.ReturnPiece.PieceType.WQ;

		// Initialize black king
		ReturnPiece wK = new ReturnPiece();
		wK.pieceFile = chess.ReturnPiece.PieceFile.e;
		wK.pieceRank = 1;
		wK.pieceType = chess.ReturnPiece.PieceType.WK;

		// Initialize black pawns
		ReturnPiece wp1 = new Pawn();
		wp1.pieceFile = chess.ReturnPiece.PieceFile.a;
		wp1.pieceRank = 2;
		wp1.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp1);

		ReturnPiece wp2 = new Pawn();
		wp2.pieceFile = chess.ReturnPiece.PieceFile.b;
		wp2.pieceRank = 2;
		wp2.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp2);

		ReturnPiece wp3 = new Pawn();
		wp3.pieceFile = chess.ReturnPiece.PieceFile.c;
		wp3.pieceRank = 2;
		wp3.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp3);

//		ReturnPiece wp4 = new Pawn();
//		wp4.pieceFile = chess.ReturnPiece.PieceFile.d;
//		wp4.pieceRank = 2;
//		wp4.pieceType = chess.ReturnPiece.PieceType.WP;
//		Chess.piecesOnBoard.add(wp4);

		ReturnPiece wp5 = new Pawn();
		wp5.pieceFile = chess.ReturnPiece.PieceFile.e;
		//wp5.pieceRank = 2;
		wp5.pieceRank = 5;
		wp5.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp5);

		ReturnPiece wp6 = new Pawn();
		wp6.pieceFile = chess.ReturnPiece.PieceFile.f;
		wp6.pieceRank = 2;
		wp6.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp6);

		ReturnPiece wp7 = new Pawn();
		wp7.pieceFile = chess.ReturnPiece.PieceFile.g;
		wp7.pieceRank = 2;
		wp7.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp7);

		Pawn wp8 = new Pawn();
		wp8.pieceFile = chess.ReturnPiece.PieceFile.h;
		wp8.pieceRank = 2;
		wp8.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp8);

		// Finally, add all pieces to the piecesOnBoard list
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

		Chess.current = Player.white;
		
		PlayChess.printBoard(Chess.piecesOnBoard);
		}

}