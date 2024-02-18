package chess;

import java.util.ArrayList;

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
		String sf1 = String.valueOf(move.charAt(0));
		String sr1 = String.valueOf(move.charAt(1));
		String sf2 = String.valueOf(move.charAt(3));
		String sr2 = String.valueOf(move.charAt(4));

		chess.ReturnPiece.PieceFile initFile = chess.ReturnPiece.PieceFile.valueOf(sf1);
		int initRank = Integer.parseInt(sr1);
		chess.ReturnPiece.PieceFile finalFile = chess.ReturnPiece.PieceFile.valueOf(sf2);
		int finalRank = Integer.parseInt(sr2);
		
		// After getting initial file and rank, piecesOnBoard[] to find which piece is at that address. 
		// Call the piece and return the outcome of that play by updating the board.
		
		ReturnPiece thePiece=null; // thePiece = the piece we're moving 
		ReturnPiece newSpot = new ReturnPiece();
		
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
		
		// Searches for where we're moving it to    -  We need to put this in each piece class
		boolean emptySpace = true;        // If we don't find a piece, this is true. The piece can move there if within the rules.
		for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
		{
			ReturnPiece pc = piecesOnBoard.get(i);
			if (pc.pieceFile.toString().equals(sf2) && pc.pieceRank == finalRank)
			{
				emptySpace=false;
				newSpot = pc;
				break; 
			}
		}
		
		
		if(emptySpace) {
			thePiece.pieceRank = finalRank;
			thePiece.pieceFile = finalFile;
		}
		else {
			// we eat the whatever piece is in there
		}
		
		
		
		if(thePiece.pieceType.compareTo(chess.ReturnPiece.PieceType.WP) == 0 || thePiece.pieceType.compareTo(chess.ReturnPiece.PieceType.BP) == 0)
		{
			//chess.Pawn moveThePiece = new chess.Pawn(thePiece, piecesOnBoard , finalFile, finalRank);
			//System.out.println(moveThePiece);
		}
		

		// h2 h3
		ReturnPlay msg = new ReturnPlay();
		msg.piecesOnBoard = Chess.piecesOnBoard;
		
		return msg;
	}
	
	public static void start() {		
		// Initialize black rooks
		ReturnPiece bR1 = new ReturnPiece();
		bR1.pieceFile = chess.ReturnPiece.PieceFile.a;
		bR1.pieceRank = 8;
		bR1.pieceType = chess.ReturnPiece.PieceType.BR;
		ReturnPiece bR2 = new ReturnPiece();
		bR2.pieceFile = chess.ReturnPiece.PieceFile.h;
		bR2.pieceRank = 8;
		bR2.pieceType = chess.ReturnPiece.PieceType.BR;
		// Initialize black knights
		ReturnPiece bN1 = new ReturnPiece();
		bN1.pieceFile = chess.ReturnPiece.PieceFile.b;
		bN1.pieceRank = 8;
		bN1.pieceType = chess.ReturnPiece.PieceType.BN;

		ReturnPiece bN2 = new ReturnPiece();
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
		ReturnPiece bQ = new ReturnPiece();
		bQ.pieceFile = chess.ReturnPiece.PieceFile.d;
		bQ.pieceRank = 8;
		bQ.pieceType = chess.ReturnPiece.PieceType.BQ;

		// Initialize black king
		ReturnPiece bK = new ReturnPiece();
		bK.pieceFile = chess.ReturnPiece.PieceFile.e;
		bK.pieceRank = 8;
		bK.pieceType = chess.ReturnPiece.PieceType.BK;

		// Initialize black pawns
		for (chess.ReturnPiece.PieceFile file : chess.ReturnPiece.PieceFile.values()) {
		    ReturnPiece bP = new ReturnPiece();
		    bP.pieceFile = file;
		    bP.pieceRank = 7;
		    bP.pieceType = chess.ReturnPiece.PieceType.BP;
		    Chess.piecesOnBoard.add(bP);
		}

		// Initialize white pieces similarly
		ReturnPiece wR1 = new ReturnPiece();
		wR1.pieceFile = chess.ReturnPiece.PieceFile.a;
		wR1.pieceRank = 1;
		wR1.pieceType = chess.ReturnPiece.PieceType.WR;

		ReturnPiece wR2 = new ReturnPiece();
		wR2.pieceFile = chess.ReturnPiece.PieceFile.h;
		wR2.pieceRank = 1;
		wR2.pieceType = chess.ReturnPiece.PieceType.WR;
		// Initialize white knights
		ReturnPiece wN1 = new ReturnPiece();
		wN1.pieceFile = chess.ReturnPiece.PieceFile.b;
		wN1.pieceRank = 1;
		wN1.pieceType = chess.ReturnPiece.PieceType.WN;

		ReturnPiece wN2 = new ReturnPiece();
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
		ReturnPiece wQ = new ReturnPiece();
		wQ.pieceFile = chess.ReturnPiece.PieceFile.d;
		wQ.pieceRank = 1;
		wQ.pieceType = chess.ReturnPiece.PieceType.WQ;

		// Initialize black king
		ReturnPiece wK = new ReturnPiece();
		wK.pieceFile = chess.ReturnPiece.PieceFile.e;
		wK.pieceRank = 1;
		wK.pieceType = chess.ReturnPiece.PieceType.WK;

		// Initialize black pawns
		ReturnPiece wp1 = new ReturnPiece();
		wp1.pieceFile = chess.ReturnPiece.PieceFile.a;
		wp1.pieceRank = 2;
		wp1.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp1);

		ReturnPiece wp2 = new ReturnPiece();
		wp2.pieceFile = chess.ReturnPiece.PieceFile.b;
		wp2.pieceRank = 2;
		wp2.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp2);

		ReturnPiece wp3 = new ReturnPiece();
		wp3.pieceFile = chess.ReturnPiece.PieceFile.c;
		wp3.pieceRank = 2;
		wp3.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp3);

		ReturnPiece wp4 = new ReturnPiece();
		wp4.pieceFile = chess.ReturnPiece.PieceFile.d;
		wp4.pieceRank = 2;
		wp4.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp4);

		ReturnPiece wp5 = new ReturnPiece();
		wp5.pieceFile = chess.ReturnPiece.PieceFile.e;
		wp5.pieceRank = 2;
		wp5.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp5);

		ReturnPiece wp6 = new ReturnPiece();
		wp6.pieceFile = chess.ReturnPiece.PieceFile.f;
		wp6.pieceRank = 2;
		wp6.pieceType = chess.ReturnPiece.PieceType.WP;
		Chess.piecesOnBoard.add(wp6);

		ReturnPiece wp7 = new ReturnPiece();
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
		Chess.piecesOnBoard.add(wp1);
		Chess.piecesOnBoard.add(wp2);
		Chess.piecesOnBoard.add(wp3);
		Chess.piecesOnBoard.add(wp4);
		Chess.piecesOnBoard.add(wp5);
		Chess.piecesOnBoard.add(wp6);
		Chess.piecesOnBoard.add(wp7);
		Chess.piecesOnBoard.add(wp8);
		PlayChess.printBoard(Chess.piecesOnBoard);
		
		//ReturnPiece p1 = new Pawn(wp1,Chess.piecesOnBoard, null, 0);
	}
	
	public static void updateBoard() {
		
	}
}
