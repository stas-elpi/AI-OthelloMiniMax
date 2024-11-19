import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;


public class Main
{
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Choose minimax depth");
        Scanner input = new Scanner(System.in);

        String strdepth = input.nextLine();      //pairnoume eisodo to epithtimito level
        int depth = Integer.parseInt(strdepth);
        while(depth <0){
            System.out.println("Wrong Input");       //elegxoume an einai swsth h eisodos
            System.out.println("Choose minimax depth");
            strdepth = input.nextLine();
            depth = Integer.parseInt(strdepth);
        }

        System.out.println("first to play? type yes or no");     //dialegoume poios paizei protos
        String firsttoplay = input.nextLine();
        while(!firsttoplay.equals("yes") && !firsttoplay.equals("no")){    //elegxoume an einai swsth h eisodos
            System.out.println("Wrong Input");
            System.out.println("first to play? type yes or no");
            firsttoplay = input.nextLine();
        }
        Player pc;
        if(firsttoplay.equals("yes")){               //paizei panta 1os o max kai einai black
            pc = new Player(depth, Board.white);     //an oxi o minimax einai leukos, min kai paizei deuteros
        }
        else{
            pc = new Player(depth, Board.black);   // alliws mauros kai max
        }


        Board board = new Board();

        board.print();

        //Put this out of comments for the O to play first
        //board.setLastLetterPlayed(Board.X);

        boolean noMoves_black = false;    //metavlhtes pou elexgoun an mporei na paixei o kathe paixths
        boolean noMoves_white = false;

        while(!board.isTerminal() && !(noMoves_black && noMoves_white))  //elegxoume an gemise o pinakas h kanenas paikths den mporei na paiksei
        {
            System.out.println();
            //I get to play first
            if(firsttoplay.equals("yes")) {          //o xrhsths paizei prwtos kai o pc deuteros (xrhsths black, pc white kai min)
                switch (board.getLastPlayer()) {

                    case Board.black:
                        //paizei o pc meta ton mavro (emas)
                        TimeUnit.SECONDS.sleep(1);      //perimene prin paiksei o pc (minimax)
                        System.out.println("white plays..");

                        ArrayList<Board> children = board.getChildren(Board.white);    //dokimase an yparxoun diathesimes kinhseis
                        if(children.isEmpty()){
                            System.out.println(" ");
                            System.out.println("No Available Moves");
                            System.out.println("you lose your turn,  ");

                            noMoves_white = true;
                            board.setLastPlayer(board.white);
                            break;
                        }
                        noMoves_white = false;
                        Move movewhite = pc.MiniMax(board);           //kane minimax
                        board.flip(movewhite.getRow(),movewhite.getCol(),Board.white);    //kane tis allages pou prokalese h kinhsh (kalse thn flip)
                        board.makeMove(movewhite.getRow(), movewhite.getCol(), Board.white);   //apothikeuse thn kinhsh
                        break;

                    case Board.white:   //paizei o xrhsths

                        System.out.println("black plays");

                        ArrayList<Board> children2 = board.getChildren(Board.black);    // //dokimase an yparxoun diathesimes kinhseis
                        if(children2.isEmpty()){
                            System.out.println(" ");
                            System.out.println("No Available Moves");
                            System.out.println("you lose your turn,  ");

                            noMoves_black = true;
                            board.setLastPlayer(board.black);
                            break;
                        }

                        noMoves_black = false;
                        System.out.println("row (1-8): ");     //pare ws eisodo to row kai to column elegxontas an einai entos oriwn
                        String inputrow = input.nextLine();
                        int row = Integer.parseInt(inputrow);

                        while(row<1 || row>8){
                            System.out.println("Wrong row");
                            inputrow = input.nextLine();
                            row = Integer.parseInt(inputrow);
                        }
                        System.out.println("column (1-8): ");
                        String inputcolumn = input.nextLine();
                        int column = Integer.parseInt(inputcolumn);

                        while(column<1 || column>8) {
                            System.out.println("Wrong column");
                            System.out.println("column (1-8): ");
                            inputcolumn = input.nextLine();
                            column = Integer.parseInt(inputcolumn);
                        }

                        while(!board.isValidMove(row-1, column-1) || !board.flip(row-1, column-1, Board.black)){
                            System.out.println("WRONG MOVE");

                            System.out.println("row (1-8): ");       //an h kinhsh pou epelekse o xrhsths einai lathos tote ksanazhtame apo thn arxh
                            inputrow = input.nextLine();
                            row = Integer.parseInt(inputrow);
                            while(row<1 || row>8){
                                System.out.println("Wrong row");
                                inputrow = input.nextLine();
                                row = Integer.parseInt(inputrow);
                            }
                            System.out.println("column (1-8): ");
                            inputcolumn = input.nextLine();
                            column = Integer.parseInt(inputcolumn);
                            while(column<1 || column>8) {
                                System.out.println("Wrong column");
                                System.out.println("column (1-8): ");
                                inputcolumn = input.nextLine();
                                column = Integer.parseInt(inputcolumn);
                            }
                        }

                        board.makeMove(row-1, column-1, Board.black);
                        break;
                    default:
                        break;
                }
            }
            else{                                   //o xrhsths paizei deuteros kai o pc prwtos (xrhsths white, pc black kai max)
                // pc plays first                   //antistoixa sta parakatw alla o xrhsths me ton pc anapoda
                switch(board.getLastPlayer()) {

                    case Board.white:
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("black plays..");

                        ArrayList<Board> children = board.getChildren(Board.black);
                        if(children.isEmpty()){
                            System.out.println(" ");
                            System.out.println("No Available Moves");
                            System.out.println("you lose your turn,  ");

                            noMoves_black = true;
                            board.setLastPlayer(board.black);
                            break;
                        }

                        noMoves_black = false;

                        Move moveblack = pc.MiniMax(board);
                        board.flip(moveblack.getRow(),moveblack.getCol(),Board.black);
                        board.makeMove(moveblack.getRow(), moveblack.getCol(), Board.black);
                        break;

                    case Board.black:

                        System.out.println("white plays");

                        ArrayList<Board> children2 = board.getChildren(Board.white);
                        if(children2.isEmpty()){
                            System.out.println(" ");
                            System.out.println("No Available Moves");
                            System.out.println("you lose your turn,  ");
                            noMoves_white = true;
                            board.setLastPlayer(board.white);
                            break;
                        }

                        noMoves_white = false;
                        System.out.println("row (1-8): ");
                        String inputrow = input.nextLine();
                        int row = Integer.parseInt(inputrow);
                        while(row<1 || row>8){
                            System.out.println("Wrong row");
                            inputrow = input.nextLine();
                            row = Integer.parseInt(inputrow);
                        }
                        System.out.println("column (1-8): ");
                        String inputcolumn = input.nextLine();
                        int column = Integer.parseInt(inputcolumn);
                        while(column<1 || column>8){
                            System.out.println("Wrong column");
                            inputcolumn = input.nextLine();
                            column = Integer.parseInt(inputcolumn);
                        }

                        while(!board.isValidMove(row-1, column-1) || !board.flip(row-1 , column-1, board.white)){
                            System.out.println("WRONG MOVE");

                            System.out.println("row (1-8): ");
                            inputrow = input.nextLine();
                            row = Integer.parseInt(inputrow);
                            while(row<1 || row>8){
                                System.out.println("Wrong row");
                                inputrow = input.nextLine();
                                row = Integer.parseInt(inputrow);
                            }
                            System.out.println("column (1-8): ");
                            inputcolumn = input.nextLine();
                            column = Integer.parseInt(inputcolumn);
                            while(column<1 || column>8){
                                System.out.println("Wrong column");
                                inputcolumn = input.nextLine();
                                column = Integer.parseInt(inputcolumn);
                            }
                        }
                        board.makeMove(row-1, column-1, board.white);
                        break;
                    default:
                        break;
                }


            }


            board.print();  //ektyponetai o pinakas
        }
                            //feugoume apo thn while
        //score
        int[] FinalScores = board.Score();   //vriskoume kai apothikeuoume ta score
        System.out.println(" ");
        System.out.println("***************");

        if(FinalScores[0] > FinalScores[1]){    //vriskoume to nikhth
            System.out.println("BLACK WON!");
        }
        else if(FinalScores[1]> FinalScores[0]){
            System.out.println("WHITE WON!");
        }
        else{
            System.out.println("its a draw...");
        }
        System.out.println(" ");
        System.out.print("Final Score:\nblack: ");  //ta emfanizoume
        System.out.println(FinalScores[0]);
        System.out.print("white: ");
        System.out.print(FinalScores[1]);
    }
}
