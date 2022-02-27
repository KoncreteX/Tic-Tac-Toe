// tictactoe :D
import java.util.Random;
import java.util.Scanner;
class tictactoe {
        /* |1|2|3|
           |4|5|6|
           |7|8|9|
        */
        String[] board = {"1","2","3","4","5","6","7","8","9"};

        public boolean computerFirst(){
            Random pick = new Random();
            //randomize player start
            if(pick.nextInt(2) == 1){
                return (true);
            }
            else {
                return (false);
            }
        }

        //set the board and insert pipes and newlines accordingly
        void setBoard(){
            System.out.print("|");
            for (int i = 0; i < board.length; i++){
                System.out.print(board[i] + "|");
                if (i == 2 || i == 5)
                    System.out.print("\n|");
                if (i == 8){
                    System.out.print("\n");
                }
            }
        }
        //take input from the user, replace corresponding spot in board
        public void playerTurn(){
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Input the index of the box you would like to mark: ");
            int userIn = keyboard.nextInt()-1;
            //check to make sure that space is not already taken and is a valid number
            if (userIn >= 0 && userIn < 9 && board[userIn] != "X" && board[userIn] != "O")
                // if it is, replace the chosen index with X
                board[userIn] = "X";
            else {
                // if not, ask for a new input and run the method again
                System.out.println("Try again");
                playerTurn();
            }
            // show the current board state
            setBoard();
        }

        public String[] computerTurn(){
            // randomize the space the computer would like to fill
            Random rand = new Random();
            int computerSpace = rand.nextInt(9);
            // if that space is taken, pick a new space by rerunning the method
            if (board[computerSpace].equals("X") || board[computerSpace].equals("O"))
                return(computerTurn());
            else{
                // if the space is not taken, mark it with an O
                board[computerSpace] = "O";
                System.out.println("Computers turn: ");
                setBoard();
                return(board);
            }
        }

        public boolean checkStalemate(){
            // loop through items in board, count each time an item is an X or an O, meaning it is already taken
            // if at the end of the loop, every space is taken, count will = 9
            int count = 0;
            for (int i = 0; i<board.length; i++) {
                if (board[i].equals("O") || board[i].equals("X") && (!(checkWin("X") || checkWin("O")))){
                    count++;
                }
            }
            return count == 9;
        }

        public boolean checkWin(String mark){
            // check all possible win sequences
            // horizontal
            if (board[0].equals(mark) && board[1].equals(mark) && board[2].equals(mark)){
                return(true);
            }
            else if (board[3].equals(mark) && board[4].equals(mark) && board[5].equals(mark)){
                return(true);
            }
            else if (board[6].equals(mark) && board[7].equals(mark) && board[8].equals(mark)){
                return(true);
            }
            // vertical
            else if (board[0].equals(mark) && board[3].equals(mark) && board[6].equals(mark)){
                return(true);
            }
            else if (board[1].equals(mark) && board[4].equals(mark) && board[7].equals(mark)){
                return(true);
            }
            else if (board[2].equals(mark) && board[5].equals(mark) && board[8].equals(mark)){
                return(true);
            }
            // diagonal
            else if (board[0].equals(mark) && board[4].equals(mark) && board[8].equals(mark)){
                return(true);
            }
            else if (board[2].equals(mark) && board[4].equals(mark) && board[6].equals(mark)){
                return(true);
            }
            // none
            else{
                return(false);
            }
        }

    }

    // Runner Class
    class tttRunner{
        public static void main(String[]args){
            tictactoe play = new tictactoe();
            // display blank board
            play.setBoard();
            // if the computer has been selected to play first, the computer has a turn
            if (play.computerFirst())
                play.computerTurn();
            // otherwise, the player gets their turn in the following while loop
            // while no one has one, and there are no stalemates
            while (!(play.checkWin("X") || play.checkWin("O") || play.checkStalemate())){
                // check before each turn to ensure there are no extra turns
                // check there are no winners, and no stalemate
                    if (!(play.checkWin("X") || play.checkWin("O") || play.checkStalemate()))
                        play.playerTurn();
                    if (!(play.checkWin("X") || play.checkWin("O") || play.checkStalemate()))
                        play.computerTurn();
            }
            // check the result of the game, and print out statements accordingly
            if (play.checkWin("X"))
                System.out.println("You win!");
            if (play.checkWin("O"))
                System.out.println("You loose!");
            if (play.checkStalemate())
                System.out.println("No one wins!");
        }
    }