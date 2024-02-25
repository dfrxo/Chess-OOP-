package chess;

import java.util.HashMap;
import java.util.HashSet;

import chess.Chess.Player;

public abstract class Piece extends ReturnPiece{
	public static HashSet<String> potentialMoves = new HashSet<>();
	public static boolean checkCheck(){		
		ReturnPiece newSpot=null;
		
		if(Chess.current==Player.white) {
			for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
			{
				ReturnPiece pc = Chess.piecesOnBoard.get(i);
				if (pc.pieceType==PieceType.WB)
				{
					newSpot = pc;
					break; 
				}
			}
			
			
		}
		else {
			for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
			{
				ReturnPiece pc = Chess.piecesOnBoard.get(i);
				if (pc.pieceType==PieceType.BB)
				{
					newSpot = pc;
					break; 
				}
			}
		}
		
		
		
		return false;
	}
	
	abstract void populateMoves();
	
    abstract ReturnPlay move(String file, int rank);

    abstract ReturnPiece findNewSpot(String file, int rank);

    abstract ReturnPlay eatThePiece(ReturnPiece newSpot, int newRank);
    
	public boolean diagonalCheck(int newFile, int newRank, String direction) {
		// newFile = the new File --  	// a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		// rank = new rank				// 1 2 3 4 5 6 7 8 
		// direction = direction the piece wants to move.  {up, down, left, right}
		
		String st = this.pieceFile.toString();
		int currFile = st.charAt(0);
		HashMap<Integer,Integer> spotSet = new HashMap<>();
		ReturnPiece newSpot=null;
		int spotsToCheck=0;
		spotsToCheck = Math.abs(newRank-this.pieceRank);
		int f;
		
		if(Math.abs(newRank-this.pieceRank) == Math.abs(newFile-currFile)){
			if(direction.equals("leftdown")) {
				f = currFile-1;
				for(int i = this.pieceRank-1; i > newRank; i--) {
					spotSet.put(i, f);
					f--;
				}
			}
			else if(direction.equals("leftup")) {
				f = currFile-1;
				for(int i = this.pieceRank+1; i < newRank; i++) {
					spotSet.put(i, f);
					f--;
				}
			}
			else if(direction.equals("rightup")) {
				f = currFile+1;
				for(int i = this.pieceRank+1; i < newRank; i++) {
					spotSet.put(i, f);
					f++;
				}
			}
			else if(direction.equals("rightdown")) {
				f = currFile+1;
				for(int i = this.pieceRank-1; i > newRank; i--) {
					spotSet.put(i, f);
					f++;
				}
			}
		}
		else {
			
		}
		for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
		{
			ReturnPiece pc = Chess.piecesOnBoard.get(i);
			if(spotSet.containsKey(pc.pieceRank) && spotSet.get(pc.pieceRank)==(pc.pieceFile.toString().charAt(0))) {
			    return false;
			}						
		}
		
		return true;
	}
    
	public boolean straightLineCheck(int newFile, int newRank, String direction) {
		// newFile = the new File --  	// a:97 b:98 c:99 d:100 e:101 f:102 g:103 h:104
		// rank = new rank				// 1 2 3 4 5 6 7 8 
		// direction = direction the piece wants to move.  {up, down, left, right}
		String st = this.pieceFile.toString();
		int currFile = st.charAt(0);
		HashSet<Integer> spotSet = new HashSet<>();
		ReturnPiece newSpot=null;
		int spotsToCheck=0;
		boolean fileChange=false;
		
		if(direction.equals("up")) {
			spotsToCheck=newRank-this.pieceRank;
			for(int j = this.pieceRank+1; j<newRank; j++) {
				spotSet.add(j);
			}			
		}
		else if(direction.equals("left")) {
			fileChange = true;
			spotsToCheck=currFile-newFile;
			for(int j = currFile-1; j>newFile; j--) {
				spotSet.add(j);
			}
		}
		else if(direction.equals("right")) {
			fileChange =true;
			spotsToCheck=newFile-currFile;
			for(int j = currFile+1; j<newFile; j++) {
				spotSet.add(j);
			}
		}
		else if(direction.equals("down")) {
			spotsToCheck=this.pieceRank-newRank;
			for(int j = this.pieceRank-1; j>newRank; j--) {
				spotSet.add(j);
			}	
		}
		
		if(fileChange) {
			for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
			{
				ReturnPiece pc = Chess.piecesOnBoard.get(i);
				if(pc.pieceRank==this.pieceRank) {
					if(spotSet.contains((int)pc.pieceFile.toString().charAt(0))) {
						return false;
					}
				}
				
			}
		}
		else {
			for (int i = 0; i < Chess.piecesOnBoard.size(); i++)
				{
					ReturnPiece pc = Chess.piecesOnBoard.get(i);
					if(pc.pieceFile.toString().charAt(0)==currFile) {
						if(spotSet.contains(pc.pieceRank)) {
							return false;
						}
					}
					
				}
			}
		return true;
	}
}