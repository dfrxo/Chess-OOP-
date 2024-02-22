package chess;

import java.util.HashSet;

public abstract class Piece extends ReturnPiece{
	
	public boolean checkCheck(){
		// there's more to come..
		return false;
	}
	
    abstract ReturnPlay move(String file, int rank);

    abstract ReturnPiece findNewSpot(String file, int rank);

    abstract ReturnPlay eatThePiece(ReturnPiece newSpot, int newRank);
    
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