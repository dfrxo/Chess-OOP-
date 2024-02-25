package chess;

import java.util.HashMap;

import chess.Chess.Player;
import chess.ReturnPlay.Message;

public class Knight extends Piece{
	// Inherited fields :: 
//	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
//	        BP, BR, BN, BB, BK, BQ};
//	static enum PieceFile {a, b, c, d, e, f, g, h};
//	PieceType pieceType;
//	PieceFile pieceFile;
//	int pieceRank;  // 1..8
    public Knight(PieceFile pieceFile, int pieceRank, PieceType pieceType) {
        this.pieceFile = pieceFile;
        this.pieceRank = pieceRank;
        this.pieceType = pieceType;
    }
	public boolean checkCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean validMove() {
		// TODO Auto-generated method stub
		return false;
	}

	public ReturnPlay move(String file, int rank) {
		// File and Rank are the location of the NEWSPOT. 
		
		int fileNumber = file.charAt(0);  // Used to put a numeric value on FILE 
											// a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		ReturnPiece newSpot = findNewSpot(file,rank);   // Finds the new spot
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay();
		String st = String.valueOf(this.pieceType); // CURRENT PIECE
		char color = st.charAt(0); 					// Color of current piece
		
		int x;
		int y;
		
		x = file.charAt(0) - this.pieceFile.toString().charAt(0);
		y = rank - this.pieceRank;
		
		if(Chess.current == Player.white){  // White's turn
			if(newSpot==null) { // Empty spot
				if(color == 'W') {
					if((x==1 && y==2) || (x==2 && y==1) || (x==2 && y==-1) || (x==1 && y==-2) || 
							(x==-1 && y==-2) || (x==-2 && y==-1) || (x==-2 && y==1) || (x==-1 && y==2)) {
						this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
						this.pieceRank = rank;
						updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					}
					else {
						updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
						updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
						System.err.println("That's illegal");
					}
				}
				else {
					System.err.println("You tried to move the wrong color piece :(");
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
				}
			}
			else if(newSpot.pieceType.toString().toLowerCase().charAt(0) == Chess.current.toString().charAt(0)) {   // Checks if eating own piece
				System.err.println("You tried to eat your own color piece.");
				updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
				updated_board_message.message = Message.ILLEGAL_MOVE;
			}
			else {              // There's a piece you can EAT
				updated_board_message = eatThePiece(newSpot, fileNumber);
			}
		}
		else { // Black's turn
			if(newSpot==null) { // Empty spot
				if(color == 'B') {
					if((x==1 && y==2) || (x==2 && y==1) || (x==2 && y==-1) || (x==1 && y==-2) || 
							(x==-1 && y==-2) || (x==-2 && y==-1) || (x==-2 && y==1) || (x==-1 && y==2)) {
						this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
						this.pieceRank = rank;
						updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					}
					else {
						updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
						updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
						System.err.println("That's illegal");
					}
				}
				else {
					System.err.println("You tried to move the wrong color piece :(");
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
				}
			}
			else if(newSpot.pieceType.toString().toLowerCase().charAt(0) == Chess.current.toString().charAt(0)) {   // Checks if eating own piece
				System.err.println("You tried to eat your own color piece.");
				updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
				updated_board_message.message = Message.ILLEGAL_MOVE;
			}
			else {              // There's a piece you can EAT
				updated_board_message = eatThePiece(newSpot, fileNumber);
			}
		}
		
		if(updated_board_message.message==null) {
			Chess.changePlayer();
		}
		
		
		return 	updated_board_message;
	}

	public ReturnPiece findNewSpot(String sf2, int pieceRank) {
		ReturnPiece newSpot=null;
		for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
		{
			ReturnPiece pc = Chess.piecesOnBoard.get(i);
			if (pc.pieceFile.toString().equals(sf2) && pc.pieceRank == pieceRank)
			{
				newSpot = pc;
				break; 
			}
		}
		return newSpot;		
	}

	public ReturnPlay eatThePiece(ReturnPiece newSpot, int newFile) {
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay();
		//int currRank = this.pieceRank;    // a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		//String st = this.pieceFile.toString();
		//int currFile = st.charAt(0);
		int newRank = newSpot.pieceRank;
		
		
		String stt = String.valueOf((char)newFile);
		Chess.piecesOnBoard.remove(newSpot);
		this.pieceRank = newRank;
		this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(stt);
		updated_board_message.piecesOnBoard = Chess.piecesOnBoard;

		
		
		return updated_board_message;
	}

}
