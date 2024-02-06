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
		ReturnPiece bR1 = new ReturnPiece();
		bR1.pieceFile = chess.ReturnPiece.PieceFile.a;
		bR1.pieceRank = 8;
		bR1.pieceType = chess.ReturnPiece.PieceType.BR;
		
		ReturnPiece bR2 = new ReturnPiece();
		bR2.pieceFile = chess.ReturnPiece.PieceFile.h;
		bR2.pieceRank = 8;
		bR2.pieceType = chess.ReturnPiece.PieceType.BR;
		
		ply.piecesOnBoard.add(bR1);
		ply.piecesOnBoard.add(bR2);
		String[][] temp = PlayChess.makeBlankBoard();
		PlayChess.printBoard(ply.piecesOnBoard);
//		
//		for (int i = 0; i < temp.length; i++) {
//            for (int j = 0; j < temp[i].length; j++) {
//                System.out.print(temp[i][j] + " ");
//            }
//            System.out.println(); // Move to the next line after printing each row
//        }
	}
}

