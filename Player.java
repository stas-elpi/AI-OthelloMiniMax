import java.util.ArrayList;
import java.util.Random;

public class Player
{
    private int maxDepth;
    private int playerLetter;

    Player(int maxDepth, int playerLetter)
    {
        this.maxDepth = maxDepth;
        this.playerLetter = playerLetter;
    }

    Move MiniMax(Board board)
    {
        int alpha= Integer.MIN_VALUE;       //arxika a kai b gia prionisma
        int beta = Integer.MAX_VALUE;
        if(playerLetter == Board.black)
        {
            //If the X plays then it wants to maximize the heuristics value
            return max(new Board(board), 0, alpha, beta);
        }
        else
        {
            //If the O plays then it wants to minimize the heuristics value
            return min(new Board(board), 0, alpha, beta);
        }
    }

    // The max and min functions are called one after another until a max depth is reached or tic-tac-toe.
    // We create a tree using backtracking DFS.
    Move max(Board board, int depth, int alpha, int beta)
    {
        Random r = new Random();
        /* If MAX is called on a state that is terminal or after a maximum depth is reached,
         * then a heuristic is calculated on the state and the move returned.
         */
        ArrayList<Board> childrenpossible = board.getChildren(Board.black);
        if(board.isTerminal() || (depth == this.maxDepth) || childrenpossible.isEmpty())   //elegxoume kai an ginetai kati flip
        {
            return new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
        }
        //The children-moves of the state are calculated
        ArrayList<Board> children = board.getChildren(Board.black);
        Move maxMove = new Move(Integer.MIN_VALUE); // put max node initially to smallest value.
        for(Board child: children)
        {
            //And for each child min is called, on a lower depth
            Move move = min(child, depth + 1, alpha, beta);
            //The child-move with the greatest value is selected and returned by max
            if(move.getValue() >= maxMove.getValue())
            {
                //If the heuristic has the save value then we randomly choose one of the two moves
                if((move.getValue()) == maxMove.getValue())
                {
                    if(r.nextInt(2) == 0)
                    {
                        maxMove.setRow(child.getLastMove().getRow());
                        maxMove.setCol(child.getLastMove().getCol());
                        maxMove.setValue(move.getValue());
                    }
                }
                else
                {
                    maxMove.setRow(child.getLastMove().getRow());
                    maxMove.setCol(child.getLastMove().getCol());
                    maxMove.setValue(move.getValue());
                }
            }
            if(alpha<move.getValue()){
                alpha = move.getValue();    //ananeonoume to a
            }
            if(beta<=move.getValue()){          /// fyge an to paidi einai megalutero apo toba
                break;
            }
        }
        return maxMove;
    }

    //Min works similarly to max
    Move min(Board board, int depth, int alpha, int beta)
    {
        Random r = new Random();

        ArrayList<Board> childrenpossible = board.getChildren(Board.black);
        if(board.isTerminal() || (depth == this.maxDepth) || childrenpossible.isEmpty())  //elegxoume kai an ginetai kati flip
        {
            return new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
        }
        ArrayList<Board> children = board.getChildren(Board.white);
        Move minMove = new Move(Integer.MAX_VALUE);
        for(Board child: children)
        {
            Move move = max(child, depth + 1, alpha, beta);
            if(move.getValue() <= minMove.getValue())
            {
                if((move.getValue()) == minMove.getValue())
                {
                    if(r.nextInt(2) == 0)
                    {
                        minMove.setRow(child.getLastMove().getRow());
                        minMove.setCol(child.getLastMove().getCol());
                        minMove.setValue(move.getValue());
                    }
                }
                else
                {
                    minMove.setRow(child.getLastMove().getRow());
                    minMove.setCol(child.getLastMove().getCol());
                    minMove.setValue(move.getValue());
                }
            }
            if(beta>move.getValue()){
                beta = move.getValue();             //ananeonoume to b
            }
            if(move.getValue()<=alpha){             // fyge an to paidi einai mikrotero apo to a
                break;
            }
        }
        return minMove;
    }

}
