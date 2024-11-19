


import java.util.ArrayList;

public class Board
{
    public static final int black = 1; //max
    public static final int white = -1; //min
    public static final int EMPTY = 0;

    private int[][] gameBoard;

    /* Variable containing who played last; whose turn resulted in this board
     * Even a new Board has lastLetterPlayed value; it denotes which player will play first
     */
    private int lastPlayer;

    //Immediate move that lead to this board
    private Move lastMove;

    Board()
    {
        this.lastMove = new Move();
        this.lastPlayer = white;
        this.gameBoard = new int[8][8];
        for(int i = 0; i < this.gameBoard.length; i++)
        {
            for(int j = 0; j < this.gameBoard.length; j++)
            {
                this.gameBoard[i][j] = EMPTY;
            }
        }
        this.gameBoard[3][3] = black;  //arxikes etoimes kinhseis
        this.gameBoard[4][3] = white;
        this.gameBoard[3][4] = white;
        this.gameBoard[4][4] = black;

    }

    Board(Board board)
    {
        this.lastMove = board.lastMove;
        this.lastPlayer = board.lastPlayer;
        this.gameBoard = new int[8][8];
        for(int i = 0; i < this.gameBoard.length; i++)
        {
            for(int j = 0; j < this.gameBoard.length; j++)
            {
                this.gameBoard[i][j] = board.gameBoard[i][j];
            }
        }
    }

    //Make a move; it places a pawn in the board
    void makeMove(int row, int col, int color)
    {
        this.gameBoard[row][col] = color;
        this.lastMove = new Move(row, col);
        this.lastPlayer = color;
    }

    //Checks whether a move is valid; whether a square is empty
    boolean isValidMove(int row, int col)
    {
        if((row > 7) || (col > 7) || (row < 0) || (col < 0)) return false;

        if(this.gameBoard[row][col] != EMPTY) return false;
        return true;


    }

    /* Generates the children of the state
     * Any square in the board that is empty results to a child
     */
    ArrayList<Board> getChildren(int letter)
    {

        ArrayList<Board> children = new ArrayList<>();
        for(int row = 0; row < this.gameBoard.length; row++)
        {
            for(int col = 0; col < this.gameBoard.length; col++)
            {
                if(this.isValidMove(row, col))
                {
                    Board child = new Board(this);
                    if(child.flip(row, col, letter)){       //elegxei an ginontai allages ston pinaka kai tis kanei
                        child.makeMove(row, col, letter);
                        children.add(child);
                    }

                }
            }
        }
        return children;
    }



    int evaluate()
    {
        int whitetotal = 0;
        int blacktotal = 0;
        for(int row=0; row<=7; row++){
            for(int col=0; col<=7; col++){
                if(this.gameBoard[row][col] == white){
                    whitetotal++;
                }
                else if(this.gameBoard[row][col] == black);{   //metrame ta pionia
                    blacktotal++;
                }
            }
        }
        int f1 = blacktotal - whitetotal;
        int f2 = 0;
        if(this.gameBoard[0][0]==black){            //metrame tis gwniews
            f2++;
        }
        else if(this.gameBoard[0][0]== white);{
        f2--;
    }
        if(this.gameBoard[7][7]==black){
            f2++;
        }
        else if(this.gameBoard[7][7]== white);{
        f2--;
    }
        if(this.gameBoard[7][0]==black){
            f2++;
        }
        else if(this.gameBoard[7][0]== white);{
        f2--;
    }
        if(this.gameBoard[0][7]==black){
            f2++;
        }
        else if(this.gameBoard[0][7]== white);{
        f2--;
    }

        int f3 = 0;

        for(int x=1; x<=6; x++){
            if(this.gameBoard[x][0]==black){   //metrame tis akraies mh gwniakes theseis
                f3++;
            }
            if(this.gameBoard[x][7]==black){
                f3++;
            }
            if(this.gameBoard[x][0]==white){
                f3--;
            }
            if(this.gameBoard[x][7]==white){
                f3--;
            }
        }
        for(int y=1; y<=6; y++){
            if(this.gameBoard[0][y]==black){
                f3++;
            }
            if(this.gameBoard[7][y]==black){
                f3++;
            }
            if(this.gameBoard[0][y]==white){
                f3--;
            }
            if(this.gameBoard[7][y]==white){
                f3--;
            }
        }

        int V = f1 + 3*f2 + 2*f3; //Euretikh
        return V;


    }

    boolean flip(int x , int y, int color)
    {
        boolean flipped = false;      //elegei kai epistrefei an ginontai allages. Kanei tis allages ston pinaka

        int i=x+1;    //gia kathe syntetagmenh
        if(i<=7){
            while (gameBoard[i][y] == color * (-1)) {     //koitame an h epomenh thesh einai anapodo xrwma
                if (i+1<=7){
                    if (gameBoard[i+1][y] == color) {    //an h methepomenh (epomenh tou i) einai to idio xrwma
                        for(int j=x+1; j<=i; j++){
                            gameBoard[j][y] = color;   //allazoume ola ta endiamesa
                        }
                        flipped=true;    //katagrafoume oti egine allagh
                        break;
                    }
                    i++;   //pame epomenh thesh
                }
                else{
                    break;  //an ektos oriwn fyge
                }
            }
        }
        i=x-1;              //omoia gia kathe allh syntetagmenh
        if(i>=0){
            while (gameBoard[i][y] == color * (-1)) {
                if (i-1>=0){
                    if (gameBoard[i-1][y] == color) {
                        for(int j=x-1; j>=i; j--){
                            gameBoard[j][y] = color;
                        }
                        flipped=true;
                        break;
                    }
                    i--;
                }
                else{
                    break;
                }
            }
        }


        int m=y+1;
        if(m<=7){
            while (gameBoard[x][m] == color * (-1)) {
                if (m+1<=7){
                    if (gameBoard[x][m+1] == color) {
                        for(int n=y+1; n<=m; n++){
                            gameBoard[x][n] = color;
                        }
                        flipped=true;
                        break;
                    }
                    m++;
                }
                else{
                    break;
                }
            }
        }
        m=y-1;
        if(m>=0){
            while (gameBoard[x][m] == color * (-1)) {
                if (m-1>=0){
                    if (gameBoard[x][m-1] == color) {
                        for(int n=y-1; n>=m; n--){
                            gameBoard[x][n] = color;
                        }
                        flipped=true;
                        break;
                    }
                    m--;
                }
                else{
                    break;
                }
            }
        }


        i=x+1;
        m=y+1;
        if(i<=7 && m<=7){
            while (gameBoard[i][m] == color * (-1)) {
                if (i+1<=7 && m+1<=7){
                    if (gameBoard[i+1][m+1] == color) {
                        int n = y+1;
                        for(int j=x+1; j<=i; j++) {
                            gameBoard[j][n] = color;
                            n++;
                        }
                        flipped=true;
                        break;
                    }
                    i++;
                    m++;
                }
                else{
                    break;
                }
            }
        }

        i=x-1;
        m=y-1;
        if(i>=0 && m>=0){
            while (gameBoard[i][m] == color * (-1)) {
                if (i-1>=0 && m-1>=0){
                    if (gameBoard[i-1][m-1] == color) {
                        int n = y-1;
                        for(int j=x-1; j>=i; j--) {
                            gameBoard[j][n] = color;
                            n--;
                        }
                        flipped=true;
                        break;
                    }
                    i--;
                    m--;
                }
                else{
                    break;
                }
            }
        }

        i=x+1;
        m=y-1;
        if(i<=7 && m>=0){
            while (gameBoard[i][m] == color * (-1)) {
                if (i+1<=7 && m-1>=0){
                    if (gameBoard[i+1][m-1] == color) {
                        int n = y-1;
                        for(int j=x+1; j<=i; j++) {
                            gameBoard[j][n] = color;
                            n--;
                        }
                        flipped=true;
                        break;
                    }
                    i++;
                    m--;
                }
                else{
                    break;
                }
            }
        }

        i=x-1;
        m=y+1;
        if(i>=0 && m<=7){
            while (gameBoard[i][m] == color * (-1)) {
                if (i-1>=0 && m+1<=7){
                    if (gameBoard[i-1][m+1] == color) {
                        int n = y+1;
                        for(int j=x-1; j>=i; j--) {
                            gameBoard[j][n] = color;
                            n++;
                        }
                        flipped=true;
                        break;
                    }
                    i--;
                    m++;
                }
                else{
                    break;
                }
            }
        }

        return flipped;
    }







    void print()
    {
        System.out.println("  1 2 3 4 5 6 7 8");
        for(int row=0; row<=7; row++)
        {
            System.out.print(row+1);
            System.out.print(" ");
            for(int col=0; col<=7; col++)
            {
                switch (this.gameBoard[row][col]) {
                    case black-> System.out.print("X ");
                    case white -> System.out.print("O ");
                    case EMPTY -> System.out.print("- ");
                    default -> {
                    }
                }
            }
            System.out.println(" ");
        }
        //System.out.println("*********");
    }

    Move getLastMove()
    {
        return this.lastMove;
    }

    int getLastPlayer()
    {
        return this.lastPlayer;
    }

    int[][] getGameBoard()
    {
        return this.gameBoard;
    }

    void setGameBoard(int[][] gameBoard)
    {
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                this.gameBoard[i][j] = gameBoard[i][j];
            }
        }
    }

    void setLastMove(Move lastMove)
    {
        this.lastMove.setRow(lastMove.getRow());
        this.lastMove.setCol(lastMove.getCol());
        this.lastMove.setValue(lastMove.getValue());
    }

    void setLastPlayer(int lastPlayer)
    {
        this.lastPlayer = lastPlayer;
    }

    boolean isTerminal()
    {
        for(int k = 0; k<=7; k++){
            for(int l=0; l<=7; l++){
                if(gameBoard[k][l]== EMPTY){
                    return false;
                }

            }
        }
        //KAI an de mporei na kanei kinhsh kapoios!!!
        return true;

    }

    int[] Score(){     //ypologise ta telika score

        int sumO = 0;
        int sumX = 0;
        for(int i = 0; i<=7; i++){
            for(int j=0; j<=7; j++){
                if(this.gameBoard[i][j] == black){
                    sumX++;
                }
                else if(this.gameBoard[i][j] == white){
                    sumO++;
                }
            }
        }
        int[] arrayScore = new int[2];
        arrayScore[0] = sumX;
        arrayScore[1] = sumO;
        return arrayScore;
    }

}

