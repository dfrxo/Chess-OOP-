package chess;

import chess.Chess.Player;
import chess.ReturnPlay.Message;

public class Bishop extends Piece{

    public Bishop(PieceFile pieceFile, int pieceRank, PieceType pieceType) {
        this.pieceFile = pieceFile;
        this.pieceRank = pieceRank;
        this.pieceType = pieceType;
    }
    
	ReturnPlay move(String file, int rank) {
		// TODO Auto-generated method stub
		int newFileNumber = file.charAt(0);
		int currFileNumber = (int)this.pieceFile.toString().charAt(0);

		ReturnPiece newSpot = findNewSpot(file,rank);   // Finds the new spot
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay(); // New board to be updated
		String st = String.valueOf(this.pieceType); // CURRENT PIECE
		char color = st.charAt(0); 					// Color of current piece
		
		if(Math.abs(rank-this.pieceRank) == Math.abs(newFileNumber-currFileNumber)) {
			if(Chess.current == Player.white) { // White piece.   // Chess.current is the current player's turn
				if(newSpot==null) {
					if(color == 'W') {
						if(currFileNumber>newFileNumber) {
							if(rank<this.pieceRank) {
								if(!diagonalCheck(newFileNumber,rank,"leftdown")) {
									System.err.println("Blocked by another piece");
									updated_board_message.message = Message.ILLEGAL_MOVE;
								}
								else {
									this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
									this.pieceRank = rank;									
								}	
							}
							else {
								if(!diagonalCheck(newFileNumber,rank,"leftup")) {
									System.err.println("Blocked by another piece");
									updated_board_message.message = Message.ILLEGAL_MOVE;
								}
								else {
									this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
									this.pieceRank = rank;									
								}	
							}
						}
						else if(currFileNumber<newFileNumber) {
							if(rank<this.pieceRank) {
								if(!diagonalCheck(newFileNumber,rank,"rightdown")) {
									System.err.println("Blocked by another piece");
									updated_board_message.message = Message.ILLEGAL_MOVE;
								}
								else {
									this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
									this.pieceRank = rank;									
								}	
							}
							else {
								if(!diagonalCheck(newFileNumber,rank,"rightup")) {
									System.err.println("Blocked by another piece");
									updated_board_message.message = Message.ILLEGAL_MOVE;
								}
								else {
									this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
									this.pieceRank = rank;									
								}	
							}
						}
						else {
							updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
							System.err.println("That's illegal");
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
				else { // newSpot is not null
					if(currFileNumber>newFileNumber) {
						if(rank<this.pieceRank) {
							if(!diagonalCheck(newFileNumber,rank,"leftdown")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								updated_board_message = eatThePiece(newSpot, newFileNumber);					
							}	
						}
						else {
							if(!diagonalCheck(newFileNumber,rank,"leftup")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								updated_board_message = eatThePiece(newSpot, newFileNumber);								
							}	
						}
						//updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					}
					else if(currFileNumber<newFileNumber) {
						if(rank<this.pieceRank) {
							if(!diagonalCheck(newFileNumber,rank,"rightdown")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								updated_board_message = eatThePiece(newSpot, newFileNumber);								
							}	
						}
						else {
							if(!diagonalCheck(newFileNumber,rank,"rightup")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								updated_board_message = eatThePiece(newSpot, newFileNumber);								
							}	
						}
						
					}
				}
			}
			else {
				if(newSpot==null) {
					if(color == 'B') {
						if(currFileNumber>newFileNumber) {
							if(rank<this.pieceRank) {
								if(!diagonalCheck(newFileNumber,rank,"leftdown")) {
									System.err.println("Blocked by another piece");
									updated_board_message.message = Message.ILLEGAL_MOVE;
								}
								else {
									this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
									this.pieceRank = rank;									
								}	
							}
							else {
								if(!diagonalCheck(newFileNumber,rank,"leftup")) {
									System.err.println("Blocked by another piece");
									updated_board_message.message = Message.ILLEGAL_MOVE;
								}
								else {
									this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
									this.pieceRank = rank;									
								}	
							}
						}
						else if(currFileNumber<newFileNumber) {
							if(rank<this.pieceRank) {
								if(!diagonalCheck(newFileNumber,rank,"rightdown")) {
									System.err.println("Blocked by another piece");
									updated_board_message.message = Message.ILLEGAL_MOVE;
								}
								else {
									this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
									this.pieceRank = rank;									
								}	
							}
							else {
								if(!diagonalCheck(newFileNumber,rank,"rightup")) {
									System.err.println("Blocked by another piece");
									updated_board_message.message = Message.ILLEGAL_MOVE;
								}
								else {
									this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
									this.pieceRank = rank;									
								}	
							}
						}
						else {
							updated_board_message.message = ReturnPlay.Message.ILLEGAL_MOVE;
							System.err.println("That's illegal");
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
				else { // newSpot is not null
					if(currFileNumber>newFileNumber) {
						if(rank<this.pieceRank) {
							if(!diagonalCheck(newFileNumber,rank,"leftdown")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								updated_board_message = eatThePiece(newSpot, newFileNumber);					
							}	
						}
						else {
							if(!diagonalCheck(newFileNumber,rank,"leftup")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								updated_board_message = eatThePiece(newSpot, newFileNumber);								
							}	
						}
						//updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
					}
					else if(currFileNumber<newFileNumber) {
						if(rank<this.pieceRank) {
							if(!diagonalCheck(newFileNumber,rank,"rightdown")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								updated_board_message = eatThePiece(newSpot, newFileNumber);								
							}	
						}
						else {
							if(!diagonalCheck(newFileNumber,rank,"rightup")) {
								System.err.println("Blocked by another piece");
								updated_board_message.message = Message.ILLEGAL_MOVE;
							}
							else {
								updated_board_message = eatThePiece(newSpot, newFileNumber);								
							}	
						}
						
					}
				}
			}
		}
		else {
			updated_board_message.message = Message.ILLEGAL_MOVE;
			System.err.println("No teleporting");
		}

		if(updated_board_message.message==null) {
			Chess.changePlayer();
		}
		
		updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
		return updated_board_message;
	}

	ReturnPiece findNewSpot(String sf2, int rank) {
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

	ReturnPlay eatThePiece(ReturnPiece newSpot, int newFile) {
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