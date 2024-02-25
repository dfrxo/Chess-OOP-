package chess;

import java.util.ArrayList;

import chess.Chess.Player;
import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

public class Pawn extends Piece{
	// Inherited fields :: 
//	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
//	        BP, BR, BN, BB, BK, BQ};
//	static enum PieceFile {a, b, c, d, e, f, g, h};
//	PieceType pieceType;
//	PieceFile pieceFile;
//	int pieceRank;  // 1..8
//  ArrayList<ReturnPiece> board = (ArrayList<ReturnPiece>)Chess.piecesOnBoard.clone();
	public Pawn(PieceFile pieceFile, int pieceRank, PieceType pieceType) {
		this.pieceFile = pieceFile;
		this.pieceRank = pieceRank;
		this.pieceType = pieceType;
	}
	public boolean checkCheck() {
		return false;
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
	public ReturnPlay move(String file, int rank) {
		System.out.println();
		int fileNumber = file.charAt(0);  // Used to put a numeric value on FILE 
												// a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		// file and rank are the position of the potential new piece location
		// so h2 to ---h4---
		
		ReturnPiece newSpot = findNewSpot(file,rank);   // Finds the new spot
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay(); // New board to be updated
		String st = String.valueOf(this.pieceType); // CURRENT PIECE
		char color = st.charAt(0); 					// Color of current piece
		
		if(Chess.current == Player.white) { // White piece.   // Chess.current is the current player's turn
			if(newSpot==null) {
				// it's an empty spot
				if(color=='W' && pieceRank==2  && rank-this.pieceRank==2 && 
						this.pieceFile.toString().equals(file) && straightLineCheck((int)file.charAt(0),rank,"up")) { 									
					this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
					this.pieceRank = rank;
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					
					Chess.enpassantPossible = this;
					
				}
				// If white 
				else if(color=='W' && rank-this.pieceRank==1 && this.pieceFile.toString().equals(file)) {
					this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
					this.pieceRank = rank;					
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;	
					Chess.enpassantPossible = null;

				}
				else if(color=='W' && Chess.enpassantPossible!=null && rank-this.pieceRank==1) {
					updated_board_message = enpassant(newSpot,fileNumber, rank);
				}
				else {
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					updated_board_message.message = Message.ILLEGAL_MOVE;
				}
			}
			else if(newSpot.pieceType.toString().toLowerCase().charAt(0) == Chess.current.toString().charAt(0)) {   // Checks if eating own piece
				System.err.println("You tried to eat your own color piece.");
				updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
				updated_board_message.message = Message.ILLEGAL_MOVE;
			}
			else {
				// there's something in that spot so we eat it
				updated_board_message = eatThePiece(newSpot, fileNumber);
				}
		}
		
		
		else {  // Black piece
			if(newSpot==null) {
				// it's an empty spot
				if(color=='B' && pieceRank==7  && this.pieceRank-rank==2 && this.pieceFile.toString().equals(file)
						&& straightLineCheck((int)file.charAt(0),rank,"down")) { 
					this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
					this.pieceRank = rank;
					Chess.enpassantPossible = this;
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;					
				}
				else if(color=='B' && this.pieceRank-rank==1 && this.pieceFile.toString().equals(file)) {
					this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
					this.pieceRank = rank;
					Chess.enpassantPossible = null;
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
				}
				else if(color=='B' && Chess.enpassantPossible!=null && Math.abs(rank-this.pieceRank)==1) {
					updated_board_message = enpassant(newSpot,fileNumber, rank);
				}
				else {
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					updated_board_message.message = Message.ILLEGAL_MOVE;
				}
			}
			else if(newSpot.pieceType.toString().toLowerCase().charAt(0) == Chess.current.toString().charAt(0)) {   // Checks if eating own piece
				System.err.println("You tried to eat your own color piece.");
				updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
				updated_board_message.message = Message.ILLEGAL_MOVE;
			}
			else {
				// there's something in that spot so we eat it
				updated_board_message = eatThePiece(newSpot, fileNumber);
				}
		}
		
		
		// Move to the next turn, change the current player to white or black. 
		if(updated_board_message.message==null) {
			if(Chess.current==Player.white && this.pieceRank==8) {
				Chess.promotion(this);
			}
			else if(Chess.current==Player.black && this.pieceRank==1) {
				Chess.promotion(this);
			}
			Chess.changePlayer();
		}
		
		
		
		////////////////////////////////
		/// Check for check/////////////
		////////////////////////////////
		
		
		return updated_board_message;
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

	public ReturnPlay eatThePiece(ReturnPiece newSpot, int newFile) { // Set "this" to newSpot if it's a valid move. Rank is the new rank.
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay();
		int currRank = this.pieceRank;    // a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		String st = this.pieceFile.toString();
		int currFile = st.charAt(0);
		int newRank = newSpot.pieceRank;
		
		
		// currFile = 104; -- "h" ascii value. we're at h4
		// currRank = 4; -- we're at h4 
		
		// newFile = 103 ("g) -- where we're moving
		// newRank = 5;  -- g5 where we're moving
		
		// white can only go +1
		// black can only go -1
		
		if((newRank-currRank==1 && Math.abs(currFile-newFile)==1 && Chess.current == Player.white)
				|| 
				(newRank-currRank==-1 && Math.abs(currFile-newFile)==1 && Chess.current == Player.black)) {
			String stt = String.valueOf((char)newFile);
			Chess.piecesOnBoard.remove(newSpot);
			this.pieceRank = newRank;
			this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(stt);

		}
		else {
			updated_board_message.message = Message.ILLEGAL_MOVE;
		}
		updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
		
				
		return updated_board_message;
	}
	public ReturnPlay enpassant(ReturnPiece newSpot, int newFile, int newRank) {
		// Must check left and right of "this"
		//
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay();
		int currRank = this.pieceRank;    // a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		String st = this.pieceFile.toString();
		int currFile = st.charAt(0);
		
		

		if(Math.abs(newRank-currRank)==1 && Math.abs(currFile-newFile)==1 && newFile == (int)Chess.enpassantPossible.pieceFile.toString().charAt(0)) {
			String stt = String.valueOf((char)newFile);
			Chess.piecesOnBoard.remove(Chess.enpassantPossible);
			Chess.enpassantPossible = null;		

			this.pieceRank = newRank;
			this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(stt);
		}
		else {
			updated_board_message.message = Message.ILLEGAL_MOVE;
		}
		updated_board_message.piecesOnBoard = Chess.piecesOnBoard;

		return updated_board_message;
	}
}




























		