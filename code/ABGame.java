
import java.util.*;

public class ABGame {
    static Character[] final_board = new Character[22];
    static int pos_evaluated = -1;

    public static void main(String[] args){
        if (args.length != 3) {
            System.out.println("Invalid number of input arguments");
        }

        Character[] board = Morris.inputReader(args[0]);
        int depth = Integer.parseInt(args[2]);

        // FOR WHITE STARTING : 
        Character curr_player = 'W', maximising_player = 'W';
        int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;

        int minimax_estimate = ABminimax(board, depth, curr_player, maximising_player, alpha, beta);

        Morris.outputCreater(args[1], final_board, pos_evaluated, minimax_estimate);
       
    }

    public static int ABminimax(Character[] board, int depth, Character curr_player, Character maximising_player, int alpha, int beta) {
        
        pos_evaluated++;

        if (depth == 0){
            final_board = Morris.copyOfBoard(board);
            return Morris.estimateMidgameEndgame(board, maximising_player);
        }

        ArrayList<Character[]> nextMove = Morris.generateMovesMidgameEndgame(board, curr_player);
        Character next_player = curr_player == 'W' ? 'B' : 'W';

        if (curr_player == maximising_player) {
            int maxEval = Integer.MIN_VALUE;
           
            for (Character[] b : nextMove){
                int eval = ABminimax(b, depth - 1, next_player, maximising_player, alpha, beta);
                if (eval > maxEval){
                    maxEval = eval;
                    final_board = Morris.copyOfBoard(b);
                }
                alpha = Math.max(alpha, eval);
                if (beta <= alpha)
                    break;
            }

            return maxEval;
        }

        // else curr_player is NOT the maximising player

        int minEval = Integer.MAX_VALUE;
           
            for (Character[] b : nextMove){
                int eval = ABminimax(b, depth - 1, next_player, maximising_player, alpha, beta);
                if (eval < minEval){
                    minEval = eval;
                    final_board = Morris.copyOfBoard(b);
                }
                beta = Math.min(beta, eval);
                if (beta <= alpha)
                    break;
            }

        return minEval;

    }
}
