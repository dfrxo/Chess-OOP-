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
	
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */
		ReturnPlay ply = new ReturnPlay();
		ply.piecesOnBoard = new ArrayList<ReturnPiece>();
		
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
		    ply.piecesOnBoard.add(bP);
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
		for (chess.ReturnPiece.PieceFile file : chess.ReturnPiece.PieceFile.values()) {
		    ReturnPiece wP = new ReturnPiece();
		    wP.pieceFile = file;
		    wP.pieceRank = 2;
		    wP.pieceType = chess.ReturnPiece.PieceType.WP;
		    ply.piecesOnBoard.add(wP);
		}

		// Finally, add all pieces to the piecesOnBoard list
		ply.piecesOnBoard.add(bN1);
		ply.piecesOnBoard.add(bN2);
		ply.piecesOnBoard.add(bB1);
		ply.piecesOnBoard.add(bB2);
		ply.piecesOnBoard.add(bQ);
		ply.piecesOnBoard.add(bK);
		ply.piecesOnBoard.add(bR1);
		ply.piecesOnBoard.add(bR2);
		ply.piecesOnBoard.add(wR2);
		ply.piecesOnBoard.add(wR1);
		ply.piecesOnBoard.add(wN2);
		ply.piecesOnBoard.add(wN1);
		ply.piecesOnBoard.add(wB1);
		ply.piecesOnBoard.add(wB2);
		ply.piecesOnBoard.add(wQ);
		ply.piecesOnBoard.add(wK);
		PlayChess.printBoard(ply.piecesOnBoard);

	}
}
