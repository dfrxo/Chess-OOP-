package chess;

import chess.ReturnPlay.Message; 
public class Rook extends Piece{
		
	public String file;
	public int rank;
	
	@Override
	public boolean checkCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean validMove() {
				
		int NewfileNumber = file.charAt(0);  // Used to put a numeric value on FILE 
		// a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		String piecefilestring = this.pieceFile.toString();
		int OldfileNumber = piecefilestring.charAt(0);
		
		
		if(file.equals(piecefilestring))
		{
			// to check if the path is clear
			if(this.pieceRank < rank) {
				
				for (int i = this.pieceRank+1; i <= rank; i++) {
					ReturnPiece newSpot = findNewSpot(file,i);   // up until the destination
					if (newSpot != null && newSpot.pieceRank == rank)
					{
						return true;
					}
					else if(newSpot != null)
					{
						return false;
					}
				}
			}
			
			else {
				for (int i = this.pieceRank-1; i >= rank; i--) {
					ReturnPiece newSpot = findNewSpot(file,i);   // down until the destination
					if (newSpot != null && newSpot.pieceRank == rank)
					{
						return true;
					}
					else if(newSpot != null)
					{
						return false;
					}
				}
			}
			return true;
		} //int --> character --> string --> .valueOf PieceFile
		
		else if(rank == this.pieceRank)
		{
			// to check if the path is clear
						if(OldfileNumber < NewfileNumber) {
							
							for (int i = OldfileNumber+1; i <= NewfileNumber; i++) {
								
								String s = String.valueOf(i);
								ReturnPiece newSpot = findNewSpot(s, rank);   // up until the destination
								if (newSpot != null && newSpot.pieceFile.equals(file))
								{
									return true;
								}
								else if (newSpot != null)
								{
									return false;
								}
							}
						}
						
						else {
							for (int i = OldfileNumber-1; i >= NewfileNumber; i--) {
								String s = String.valueOf(i);
								ReturnPiece newSpot = findNewSpot(s, rank);   // down until the destination
								if (newSpot != null && newSpot.pieceFile.equals(file))
								{
									return true;
								}
								else if (newSpot != null)
								{
									return false;
								}
							}
						}
			return true;
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
			return updated_board_message;
		}
		
		updated_board_message.piecesOnBoard = Chess.piecesOnBoard;
		ReturnPiece newSpot = null;
		// Searches for where we're moving it to    -  We need to put this in each piece class	
				for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
				{
					ReturnPiece pc = updated_board_message.piecesOnBoard.get(i);
					if (pc.pieceFile.toString().equals(file) && pc.pieceRank == rank)
					{
						newSpot = pc;
						break; 
					}
				}
		updated_board_message.piecesOnBoard.remove(newSpot);
		this.pieceRank = rank;
		this.pieceFile = chess.ReturnPiece.PieceFile.valueOf(file);
		return updated_board_message;
		
	}

	@Override
	public ReturnPiece findNewSpot(String newSpot, int newFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnPlay eatThePiece(ReturnPiece eatThePiece, int rank) {
		// TODO Auto-generated method stub
		return null;
	}

}
