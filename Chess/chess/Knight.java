package chess;

public class Knight extends ReturnPiece implements Piece{
	// Inherited fields :: 
//	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
//	        BP, BR, BN, BB, BK, BQ};
//	static enum PieceFile {a, b, c, d, e, f, g, h};
//	PieceType pieceType;
//	PieceFile pieceFile;
//	int pieceRank;  // 1..8
	
	public boolean checkCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validMove() {
		// TODO Auto-generated method stub
		return false;
	}

	public ReturnPlay move(String file, int rank) {
		// TODO Auto-generated method stub
		return null;
	}

	public ReturnPiece findNewSpot(String file, int rank) {
		// TODO Auto-generated method stub
		return null;
	}

	public ReturnPlay eatThePiece(ReturnPiece eatThePiece, int rank) {
		// TODO Auto-generated method stub
		return null;
	}

}
