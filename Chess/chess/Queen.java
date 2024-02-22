package chess;

import java.util.HashSet;

import chess.Chess.Player;
import chess.ReturnPlay.Message;

public class Queen extends Piece{

	
	public ReturnPlay move(String file, int rank) {
		// Queen
		int newFileNumber = file.charAt(0);  // Used to put a numeric value on FILE 
		// a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		// file and rank are the position of the potential new piece location
		// so h2 to ---h4---
		int currFileNumber = (int)this.pieceFile.toString().charAt(0);
		
		
		ReturnPiece newSpot = findNewSpot(file,rank);   // Finds the new spot
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay(); // New board to be updated
		String st = String.valueOf(this.pieceType); // CURRENT PIECE
		char color = st.charAt(0); 					// Color of current piece
		
		if(Chess.current == Player.white) { // White piece.   // Chess.current is the current player's turn
			if(newSpot==null) {
				if(color == 'W') {
					if((currFileNumber == newFileNumber)) {  // if 'h' == 'h' (104==104)
						
						// Check each spot till you reach the new spot
						System.out.println("upwards test");
						
						if(rank>this.pieceRank) {
							if(!straightLineCheck(newFileNumber,rank,"up")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
								this.pieceRank = rank;									
							}
						}
						else if(rank<this.pieceRank) {
							if(!straightLineCheck(newFileNumber,rank,"down")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
								this.pieceRank = rank;									
							}
						}
						
						updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					}
					else if((this.pieceRank==rank)) {
						if(rank>this.pieceRank) {
//							if(!straightLineCheck(newFileNumber,rank,"up")) {
//								System.err.println("Blocked by another piece");
//								updated_board_message.message = Message.ILLEGAL_MOVE;
//							}
//							else {
//								this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
//								this.pieceRank = rank;									
//							}
//						}
//						else if(rank<this.pieceRank) {
//							if(!straightLineCheck(newFileNumber,rank,"down")) {
//								System.err.println("Blocked by another piece");
//								updated_board_message.message = Message.ILLEGAL_MOVE;
//							}
//							else {
//								this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
//								this.pieceRank = rank;									
//							}
						}
						
						updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					}
					else if(Math.abs(newFileNumber-currFileNumber)==Math.abs(this.pieceRank-rank)) {
						
						// Check each spot till you reach the new spot
						System.out.println("Diagonal move test");
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
			}
			else if(newSpot.pieceType.toString().toLowerCase().charAt(0) == Chess.current.toString().charAt(0)) {   // Checks if eating own piece
				System.err.println("You tried to eat your own color piece.");
				updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
				updated_board_message.message = Message.ILLEGAL_MOVE;
			}
			
			else {
				// first check if there's any pieces in the way
				// try and eat the piece
				updated_board_message = eatThePiece(newSpot, newFileNumber);

			}
		}
		else {  // Black piece
			if(newSpot==null) {
				if(color == 'B') {
					if((currFileNumber == newFileNumber) || (this.pieceRank == rank)) {  // if 'h' == 'h' (104==104)
						
						
						System.out.println("upwards test");
						// Check each spot till you reach the new spot
						
						this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
						this.pieceRank = rank;
						updated_board_message.piecesOnBoard = Chess.piecesOnBoard;

					}
					else if(Math.abs(newFileNumber-currFileNumber)==Math.abs(this.pieceRank-rank)) {
						
						System.out.println("Diagonal move test");
						// Check each spot till you reach the new spot
						
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
			}
			else if(newSpot.pieceType.toString().toLowerCase().charAt(0) == Chess.current.toString().charAt(0)) {   // Checks if eating own piece
				System.err.println("You tried to eat your own color piece.");
				updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
				updated_board_message.message = Message.ILLEGAL_MOVE;
			}
			else {
				// first check if there's any pieces in the way
				// try and eat the piece				
				
				updated_board_message = eatThePiece(newSpot, newFileNumber);

			}
		}
		if(updated_board_message.message==null) {
			Chess.changePlayer();
		}
		
		return updated_board_message;
	}

	public ReturnPiece findNewSpot(String sf2, int rank) {
		ReturnPiece newSpot=null;
		for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
		{
			ReturnPiece pc = Chess.piecesOnBoard.get(i);
			if (pc.pieceFile.toString().equals(sf2) && pc.pieceRank == rank)
			{
				newSpot = pc;
				break; 
			}
		}
		return newSpot;				
	}

	public ReturnPlay eatThePiece(ReturnPiece newSpot, int newRank) {
		// TODO Auto-generated method stub
		return null;
	}


}
