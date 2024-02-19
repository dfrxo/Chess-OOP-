package chess;

import java.util.ArrayList;

import chess.Chess.Player;
import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.ReturnPlay.Message;

public class Pawn extends ReturnPiece{
	// Inherited fields :: 
//	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
//	        BP, BR, BN, BB, BK, BQ};
//	static enum PieceFile {a, b, c, d, e, f, g, h};
//	PieceType pieceType;
//	PieceFile pieceFile;
//	int pieceRank;  // 1..8
	ArrayList<ReturnPiece> board = (ArrayList<ReturnPiece>)Chess.piecesOnBoard.clone();
	int finalRank;

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
		
		
		
		ReturnPiece p = null;
		for(ReturnPiece i: Chess.piecesOnBoard) {
			if(i.equals((ReturnPiece)this)) {
				p = i;
				System.out.println("THIS WORKED");
			}
		}
		
		
		
		
		if(Chess.current == Player.white) { // White piece.   // Chess.current is the current player's turn
			if(newSpot==null) {
				char rankk;
				// it's an empty spot
				if(color=='W' && pieceRank==2  && rank-this.pieceRank==2) { 
					this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
					this.pieceRank = rank;
					
					// this code may be necessary to update the TypePiece on the board.
					// this.pieceFile is not working
					p.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
					p.pieceRank = rank;
					
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;					
				}
				// If white 
				else if(color=='W' && rank-this.pieceRank==1) {
					this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
					this.pieceRank = rank;
					
					// this code may be necessary to update the TypePiece on the board.
					// this.pieceFile is not working
					p.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
					p.pieceRank = rank;
					
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;			
				}
				else {
					updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					updated_board_message.message = Message.ILLEGAL_MOVE;
				}
			}
			else {
				// there's something in that spot so we eat it
				updated_board_message = eatThePiece(newSpot, fileNumber);
				}
		}
		else {  // Black piece
			
		}
		
		
		
		
		
		
		

		// If white is on starting position and it moves 2 spots up, it's valid.
		
		
		return updated_board_message;
	}
	public ReturnPiece findNewSpot(String sf2, int pieceRank) {
		ReturnPiece newSpot=null;
		for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
		{
			ReturnPiece pc = Chess.piecesOnBoard.get(i);
			if (pc.pieceFile.toString().equals(sf2) && pc.pieceRank == finalRank)
			{
				newSpot = pc;
				break; 
			}
		}
		return newSpot;
		
	}
	public ReturnPlay eatThePiece(ReturnPiece n, int rank) {
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay();
		int currFile = this.pieceRank;    // a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
				
				
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
		