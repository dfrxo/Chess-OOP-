package chess;

import chess.Chess.Player;
import chess.ReturnPlay.Message; 
public class Rook extends Piece{
		
	public String file;
	public int rank;
	
	
    public Rook(PieceFile pieceFile, int pieceRank, PieceType pieceType) {
        this.pieceFile = pieceFile;
        this.pieceRank = pieceRank;
        this.pieceType = pieceType;
    }
	@Override
	public boolean checkCheck() {
		// TODO Auto-generated method stub
		return false;
	}
	public String toString() {
		return pieceFile + String.valueOf(pieceRank);
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
	
	public boolean validMove() {
				
		int NewfileNumber = file.charAt(0);  // Used to put a numeric value on FILE 
		// a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		String piecefilestring = this.pieceFile.toString();
		int OldfileNumber = piecefilestring.charAt(0);
		String st = String.valueOf(this.pieceType); // CURRENT PIECE
		char color = st.charAt(0); 					// Color of current piece
		
		

		if(Chess.current == Player.white) {
				if(file.equals(piecefilestring))
				{
					// to check if the path is clear vertically
					if(this.pieceRank < rank) {
						
						for (int i = this.pieceRank+1; i <= rank; i++) {
							ReturnPiece newSpot = findNewSpot(file,i);   // up until the destination
							if(newSpot != null && i != rank)
							{
								return false;
							}
							else if(newSpot == null && i != rank)
							{
								continue;
							}
							else if (newSpot == null && i == rank || newSpot != null && newSpot.pieceRank == rank && newSpot.pieceType.toString().charAt(0) != color)
							{
								return true;
							}
							
						}
						return false;
						
					}
					
					else {
						for (int i = this.pieceRank-1; i >= rank; i--) {
							ReturnPiece newSpot = findNewSpot(file,i);   // down until the destination
							if(newSpot != null && i != rank)
							{
								return false;
							}
							else if(newSpot == null && i != rank)
							{
								continue;
							}
							else if (newSpot == null && i == rank || newSpot != null && newSpot.pieceRank == rank && newSpot.pieceType.toString().charAt(0) != color)
							{
								return true;
							}
							
						}
						return false;
					}
					
				} //int --> character --> string --> .valueOf PieceFile
				
				else if(rank == this.pieceRank)
				{
					// to check if the path is clear horizontally
								if(OldfileNumber < NewfileNumber) {
									
									for (int i = OldfileNumber+1; i <= NewfileNumber; i++) {
										
										String s = String.valueOf(i);
										ReturnPiece newSpot = findNewSpot(s, rank);   // up until the destination
										if(newSpot != null && i != NewfileNumber)
										{
											return false;
										}
										else if(newSpot == null && i != NewfileNumber)
										{
											continue;
										}
										else if (newSpot == null && i == NewfileNumber || newSpot != null && newSpot.pieceFile.equals(file) && newSpot.pieceType.toString().charAt(0) != color)
										{
											return true;
										}
										
									}
									return false;
								}
								
								else {
									for (int i = OldfileNumber-1; i >= NewfileNumber; i--) {
										char c = (char)i;
										String s = String.valueOf(c);
										ReturnPiece newSpot = findNewSpot(s, rank);   // down until the destination
										if(newSpot != null && i != NewfileNumber)
										{
											return false;
										}
										else if(newSpot == null && i != NewfileNumber)
										{
											continue;
										}
										else if (newSpot == null && i == NewfileNumber || newSpot != null && newSpot.pieceFile.equals(file) && newSpot.pieceType.toString().charAt(0) != color)
										{
											return true;
										}
										
									}
									return false;
								}
					
				}
				return false;
				
		}
		if(Chess.current == Player.black) {
				if(file.equals(piecefilestring))
				{
					// to check if the path is clear vertically
					if(this.pieceRank < rank) {
						
						for (int i = this.pieceRank+1; i <= rank; i++) {
							ReturnPiece newSpot = findNewSpot(file,i);   // up until the destination
							if(newSpot != null && i != rank)
							{
								return false;
							}
							else if(newSpot == null && i != rank)
							{
								continue;
							}
							else if (newSpot == null && i == rank || newSpot != null && newSpot.pieceRank == rank && newSpot.pieceType.toString().charAt(0) != color)
							{
								return true;
							}
							
						}
						return false;
						
					}
					
					else {
						for (int i = this.pieceRank-1; i >= rank; i--) {
							ReturnPiece newSpot = findNewSpot(file,i);   // down until the destination
							if(newSpot != null && i != rank)
							{
								return false;
							}
							else if(newSpot == null && i != rank)
							{
								continue;
							}
							else if (newSpot == null && i == rank || newSpot != null && newSpot.pieceRank == rank && newSpot.pieceType.toString().charAt(0) != color)
							{
								return true;
							}
							
						}
						return false;
					}
					
				} //int --> character --> string --> .valueOf PieceFile
				
				else if(rank == this.pieceRank)
				{
					// to check if the path is clear horizontally
								if(OldfileNumber < NewfileNumber) {
									
									for (int i = OldfileNumber+1; i <= NewfileNumber; i++) {
										
										String s = String.valueOf(i);
										ReturnPiece newSpot = findNewSpot(s, rank);   // up until the destination
										if(newSpot != null && i != NewfileNumber)
										{
											return false;
										}
										else if(newSpot == null && i != NewfileNumber)
										{
											continue;
										}
										else if (newSpot == null && i == NewfileNumber || newSpot != null && newSpot.pieceFile.equals(file) && newSpot.pieceType.toString().charAt(0) != color)
										{
											return true;
										}
										
									}
									return false;
								}
								
								else {
									for (int i = OldfileNumber-1; i >= NewfileNumber; i--) {
										String s = String.valueOf(i);
										ReturnPiece newSpot = findNewSpot(s, rank);   // down until the destination
										if(newSpot != null && i != NewfileNumber)
										{
											return false;
										}
										else if(newSpot == null && i != NewfileNumber)
										{
											continue;
										}
										else if (newSpot == null && i == NewfileNumber || newSpot != null && newSpot.pieceFile.equals(file) && newSpot.pieceType.toString().charAt(0) != color)
										{
											return true;
										}
										
									}
									return false;
								}
					
				}
				return false;
		}
		return false;
	}

	public ReturnPlay move(String file, int rank) {
		this.file = file;
		this.rank = rank;
		System.out.println();
		
		// file and rank are the position of the potential new piece location
		// so h2 to ---h4---
		
		chess.ReturnPlay updated_board_message = new chess.ReturnPlay(); // New board to be updated
		String st = String.valueOf(this.pieceType); // CURRENT PIECE
		char color = st.charAt(0); 		// Color of current piece
		
		boolean isValid = validMove();
		
		if (!isValid) {
			updated_board_message.message = Message.ILLEGAL_MOVE;
			updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
			return updated_board_message;
		}
		
		
		// castling condition check
//		static boolean blackLeftCastle=true;
//	    static boolean whiteLeftCastle=true;
//	    static boolean blackRightCastle=true;
//	    static boolean whiteRightCastle=true;
		String piecefilestring = this.pieceFile.toString();
		if(this.pieceFile.toString().equals("a") && Chess.current == Player.white)
		{
			Chess.whiteLeftCastle=false;
		}
		if(this.pieceFile.toString().equals("h") && Chess.current == Player.white)
		{
			Chess.whiteRightCastle =false;
		}
		if(this.pieceFile.toString().equals("a") && Chess.current == Player.black)
		{
			Chess.blackRightCastle=false;
		}
		if(this.pieceFile.toString().equals("a") && Chess.current == Player.black)
		{
			Chess.blackLeftCastle=false;
		}
			
			
			
		
		ReturnPiece newSpot = null;
		// Searches for where we're moving it to    -  We need to put this in each piece class	
				for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
				{
					ReturnPiece pc = Chess.piecesOnBoard.get(i);
					if (pc.pieceFile.toString().equals(file) && pc.pieceRank == rank)
					{
						newSpot = pc;
						break; 
					}
				}
				
		
		Chess.piecesOnBoard.remove(newSpot);
		this.pieceRank = rank;
		this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
		if(updated_board_message.message==null) {
			Chess.changePlayer();
		}
		updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
		return updated_board_message;
		
	}

	@Override
	public ReturnPlay eatThePiece(ReturnPiece eatThePiece, int rank) {
		// TODO Auto-generated method stub
		return null;
	}

}