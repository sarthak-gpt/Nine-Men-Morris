
import java.util.*;

public class MiniMaxOpeningBlack {

    static Character[] final_board = new Character[22];
    static int pos_evaluated = -1;
    
    public static void main(String[] args){
        
        if (args.length != 3) {
            System.out.println("Invalid number of input arguments");
        }

        Character[] board = Morris.inputReader(args[0]);
        int depth = Integer.parseInt(args[2]);

        // FOR BLACK STARTING : 
        Character curr_player = 'B', maximising_player = 'B';

        int minimax_estimate = minimax(board, depth, curr_player, maximising_player);

        Morris.outputCreater(args[1], final_board, pos_evaluated, minimax_estimate);
        
    }

    public static int minimax(Character[] board, int depth, Character curr_player, Character maximising_player) {
        
        pos_evaluated++;

        if (depth == 0){
            final_board = Morris.copyOfBoard(board);
            return Morris.estimateOpening(board, maximising_player);
        }

        ArrayList<Character[]> nextMove = Morris.generateMovesOpening(board, curr_player);
        Character next_player = curr_player == 'W' ? 'B' : 'W';

        if (curr_player == maximising_player) {
            int maxEval = Integer.MIN_VALUE;
           
            for (Character[] b : nextMove){
                int eval = minimax(b, depth - 1, next_player, maximising_player);
                //maxEval = Math.max(maxEval, eval);
                if (eval > maxEval){
                    maxEval = eval;
                    final_board = Morris.copyOfBoard(b);
                }
            }

            return maxEval;
        }

        // else curr_player is NOT the maximising player

        int minEval = Integer.MAX_VALUE;
           
            for (Character[] b : nextMove){
                int eval = minimax(b, depth - 1, next_player, maximising_player);
                //minEval = Math.min(minEval, eval);
                if (eval < minEval){
                    minEval = eval;
                    final_board = Morris.copyOfBoard(b);
                }
            }

        return minEval;

    }
}
