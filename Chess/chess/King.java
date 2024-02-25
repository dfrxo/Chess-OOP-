package chess;

import java.util.HashSet;

import chess.Chess.Player;
import chess.ReturnPiece.PieceFile;
import chess.ReturnPlay.Message;

public class King extends Piece{
    public King(PieceFile pieceFile, int pieceRank, PieceType pieceType) {
        this.pieceFile = pieceFile;
        this.pieceRank = pieceRank;
        this.pieceType = pieceType;
    }
    
	public ReturnPlay move(String file, int rank) {
		int newFileNumber = file.charAt(0);  // Used to put a numeric value on FILE 
		// a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		// file and rank are the position of the potential new piece location
		// so h2 to ---h4---
		int currFileNumber = (int)this.pieceFile.toString().charAt(0);

		PieceFile originalFile = this.pieceFile;
		int originalRank = this.pieceRank;
		
		ReturnPiece newSpot = findNewSpot(file,rank);   // Finds the new spot
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay(); // New board to be updated
		String st = String.valueOf(this.pieceType); // CURRENT PIECE
		char color = st.charAt(0); 					// Color of current piece		
		
		int fileMovement = Math.abs(newFileNumber-currFileNumber);
		int rankMovement = Math.abs(rank-this.pieceRank);
		
		String originalMove = this.pieceFile.toString()+this.pieceRank + " "+ file+rank;
		
		
		//// WRITE CODE FOR KING PUTTING ITSELF IN CHECk
		///  CAN I MAKE IT SO THE KING CHECKS ALL POTENTIAL MOVES, 
		// IF THE POTENTIAL MOVE IS IN POTENTIAL MOVES HASHSET, RETURN MESSAGE
		// 
		// ALSO FOR CHECK. DO THE MOVE, THEN CHECK POTENTIAL MOVES OF ALL OTHER PIECES. IF THE 
		// PIECE THE KING IS ON IS IN THE POTENTIAL MOVES, YOU'RE IN CHECK.
		
		
		if(Chess.current == Player.white) {			
			if(newSpot==null) {
				if(color == 'W') {
					if(fileMovement<2 && rankMovement <2) {
						this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
						this.pieceRank = rank;	
						Chess.whiteLeftCastle = false;
						Chess.whiteRightCastle = false;
					}
					else if(originalMove.equals("e1 g1") || originalMove.equals("e1 c1")) { // Castling 
						// e1 g1 rook goes h1 f1
						// e1 c1 rook goes a1 d1
						updated_board_message = castle(originalMove);
						Chess.whiteLeftCastle = false;
						Chess.whiteRightCastle = false;
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
						Chess.whiteLeftCastle = false;
						Chess.whiteRightCastle = false;
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
						Chess.blackLeftCastle = false;
						Chess.blackRightCastle = false;
					}
					else if(originalMove.equals("e8 g8") || originalMove.equals("e8 c8")) { // Castling 
						
						updated_board_message = castle(originalMove);
						Chess.blackLeftCastle = false;
						Chess.blackRightCastle = false;
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
						Chess.blackLeftCastle = false;
						Chess.blackRightCastle = false;
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
		updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
		
		if(updated_board_message.message==null) {
			if(Chess.checkChecker(updated_board_message, color)) {
				this.pieceFile = originalFile;
				this.pieceRank = originalRank;
				
				if(newSpot!=null) {
					Chess.piecesOnBoard.add(newSpot);
				}
				
				updated_board_message.message = Message.ILLEGAL_MOVE;
				System.err.println("This move would put you in check.");
				
			}
			else {
			Chess.changePlayer();
			}
		}
		
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
			if(straightLineCheck(104,1,"right") && Chess.whiteRightCastle) {  // 
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
			if(straightLineCheck(97,1,"left") && Chess.whiteLeftCastle) {  // 
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
			if(straightLineCheck(104,8,"right") && Chess.blackRightCastle) {  // 
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
			if(straightLineCheck(97,8,"left") && Chess.blackLeftCastle) {  // 
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

	@Override
	void populateMoves() {
//		if(0==0) {
			String st = this.pieceFile.toString();
			int x = st.charAt(0);
			int y = pieceRank;
			int tempx;
			int tempy;
			ReturnPiece newSpot=null; 
			for(tempx = x-1;tempx<x+2;tempx++) {
				for(tempy = y-1;tempy<y+2;tempy++) {
					newSpot = findNewSpot(String.valueOf((char)(tempx)),tempy);
					if(newSpot!=null && tempy!=y && tempx !=x) {
						if(newSpot.pieceType.toString().charAt(0)!=this.pieceType.toString().charAt(0)) {
							Piece.potentialMoves.add((tempx + " " + tempy).trim());
						}
						break;
					}
					Piece.potentialMoves.add((tempx + " " + tempy).trim());
				}
	//		}
		}
	}
	public HashSet<String> populateMovesCheckMate() {
//		if(0==0) {
		 	HashSet<String> kingSpots = new HashSet<>();
			String st = this.pieceFile.toString();
			int x = st.charAt(0);
			int y = pieceRank;
			int tempx;
			int tempy;
			ReturnPiece newSpot=null; 
			for(tempx = x-1;tempx<x+2;tempx++) {
				for(tempy = y-1;tempy<y+2;tempy++) {
					newSpot = findNewSpot(String.valueOf((char)(tempx)),tempy);
					if(newSpot!=null && tempy!=y && tempx !=x) {
						if(newSpot.pieceType.toString().charAt(0)!=this.pieceType.toString().charAt(0)) {
							kingSpots.add((tempx + " " + tempy).trim());
						}
						break;
					}
					kingSpots.add((tempx + " " + tempy).trim());
				}
	//		}
		}
			return kingSpots;

	}
}
