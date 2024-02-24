package chess;

import chess.Chess.Player;
import chess.ReturnPlay.Message;

public class King extends Piece{

	public ReturnPlay move(String file, int rank) {
		int newFileNumber = file.charAt(0);  // Used to put a numeric value on FILE 
		// a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		// file and rank are the position of the potential new piece location
		// so h2 to ---h4---
		int currFileNumber = (int)this.pieceFile.toString().charAt(0);

		ReturnPiece newSpot = findNewSpot(file,rank);   // Finds the new spot
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay(); // New board to be updated
		String st = String.valueOf(this.pieceType); // CURRENT PIECE
		char color = st.charAt(0); 					// Color of current piece		
		
		int fileMovement = Math.abs(newFileNumber-currFileNumber);
		int rankMovement = Math.abs(rank-this.pieceRank);
		
		String originalMove = this.pieceFile.toString()+this.pieceRank + " "+ file+rank;
		
		
		
		if(Chess.current == Player.white) {			
			if(newSpot==null) {
				if(color == 'W') {
					if(fileMovement<2 && rankMovement <2) {
						this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
						this.pieceRank = rank;	
					}
					else if(originalMove.equals("e1 g1") || originalMove.equals("e1 c1")) { // Castling 
						// e1 g1 rook goes h1 f1
						// e1 c1 rook goes a1 d1
						updated_board_message = castle(originalMove);
					}
					else {
						System.err.println("Moved too far.");
						updated_board_message.message = Message.ILLEGAL_MOVE;
					}
				}
				else {
					System.err.println("You tried to move the wrong color piece :(");
					updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
				}
			}
			else if(newSpot.pieceType.toString().toLowerCase().charAt(0) == Chess.current.toString().charAt(0)) {   // Checks if eating own piece
				System.err.println("You tried to eat your own color piece.");
				updated_board_message.message = Message.ILLEGAL_MOVE;
			}
			else { //Newspot not null, try to eat the piece.
				if(color == 'W') {
					if(fileMovement<2 && rankMovement <2) {
						updated_board_message = eatThePiece(newSpot,newFileNumber);
					}
					else {
						System.err.println("Moved too far.");
						updated_board_message.message = Message.ILLEGAL_MOVE;
					}
				}
				else {
					System.err.println("You tried to move the wrong color piece :(");
					updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
				}
			}
			
		}
		else { // Current player is Black
			if(newSpot==null) {
				if(color == 'B') {
					if(fileMovement<2 && rankMovement <2) {
						this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
						this.pieceRank = rank;	
					}
					else if(originalMove.equals("e8 g8") || originalMove.equals("e8 c8")) { // Castling 
						
						updated_board_message = castle(originalMove);
					}
					else {
						System.err.println("Moved too far.");
						updated_board_message.message = Message.ILLEGAL_MOVE;
					}
				}
				else {
					System.err.println("You tried to move the wrong color piece :(");
					updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
				}
			}
			else if(newSpot.pieceType.toString().toLowerCase().charAt(0) == Chess.current.toString().charAt(0)) {   // Checks if eating own piece
				System.err.println("You tried to eat your own color piece.");
				updated_board_message.message = Message.ILLEGAL_MOVE;
			}
			else { //Newspot not null, try to eat the piece.
				if(color == 'B') {
					if(fileMovement<2 && rankMovement <2) {
						updated_board_message = eatThePiece(newSpot,newFileNumber);
					}
					else {
						System.err.println("Moved too far.");
						updated_board_message.message = Message.ILLEGAL_MOVE;
					}
				}
				else {
					System.err.println("You tried to move the wrong color piece :(");
					updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
				}
			}
		}
		
		if(updated_board_message.message==null) {
			Chess.changePlayer();
		}
		updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
		
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

	public ReturnPlay castle(String move) {

		
		// e1 g1 rook goes h1 f1
		// e1 c1 rook goes a1 d1
		// e8 g8 - rook goes to h8 f8 
		// e8 c8 - rook goes to a8 d8
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay(); // New board to be updated
		
		ReturnPiece rook;
		
		
		switch(move) {
		case "e1 g1":			
			// if(king or rook has moved): invalid move
			if(straightLineCheck(104,1,"right")) {  // 
				rook = findNewSpot("h",1);
				
				this.pieceFile = chess.ReturnPiece.PieceFile.valueOf("g");
				rook.pieceFile = chess.ReturnPiece.PieceFile.valueOf("f");
			}
			else {
				updated_board_message.message = Message.ILLEGAL_MOVE;
				System.err.println("Can't castle");
			}
			break;
		case "e1 c1":
			if(straightLineCheck(97,1,"left")) {  // 
				rook = findNewSpot("a",1);
				
				this.pieceFile = chess.ReturnPiece.PieceFile.valueOf("c");
				rook.pieceFile = chess.ReturnPiece.PieceFile.valueOf("d");
			}
			else {
				updated_board_message.message = Message.ILLEGAL_MOVE;
				System.err.println("Can't castle");
			}
			break;
		case "e8 g8":
			if(straightLineCheck(104,8,"right")) {  // 
				rook = findNewSpot("h",8);
				
				this.pieceFile = chess.ReturnPiece.PieceFile.valueOf("g");
				rook.pieceFile = chess.ReturnPiece.PieceFile.valueOf("f");
			}
			else {
				updated_board_message.message = Message.ILLEGAL_MOVE;
				System.err.println("Can't castle");
			}
			break;
			
		case "e8 c8":
			if(straightLineCheck(97,8,"left")) {  // 
				rook = findNewSpot("a",8);
				
				this.pieceFile = chess.ReturnPiece.PieceFile.valueOf("c");
				rook.pieceFile = chess.ReturnPiece.PieceFile.valueOf("d");
			}
			else {
				updated_board_message.message = Message.ILLEGAL_MOVE;
				System.err.println("Can't castle");
			}
			break;
		default:
			
		}
		
		
		return updated_board_message;
	}
}
