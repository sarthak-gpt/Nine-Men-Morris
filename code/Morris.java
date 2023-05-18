import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;   
import java.io.IOException; 

public class Morris{

    public static Character[] inputReader(String input_file) {

        try {
            Scanner scanner = new Scanner(new File(input_file));
            while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
                //System.out.println("The given input is " + line);

                if (!inputValidator(line)) {
                    System.out.println("Invalid Input");
                    return null;
                }

                return inputStringToArray(line);

			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
        }

        return null;


    }

    public static void outputCreater(String output_file_name, Character[] board_position, int pos_evaluated, int estimate){
        try {
			FileWriter fileWriter = new FileWriter(output_file_name);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            StringBuilder sb = new StringBuilder();
            for (Character c: board_position)
                sb.append(c);
            
			bufferedWriter.write("Board Position: " + sb.toString() + "\n");
            bufferedWriter.write("Positions evaluated by static estimation: " + pos_evaluated + "\n");
            bufferedWriter.write("MINIMAX estimate: " + estimate + "\n");

			bufferedWriter.close();
		}
		catch(IOException ex) {
			System.out.println("Error writing to file '" + output_file_name + "'");
		}
    }

    public static boolean inputValidator(String input){
        if (input.length() != 22){
            System.out.println("Invalid length of String");
            return false;
        }

        for (char c : input.toCharArray())
            if (!(c == 'x' || c == 'W' || c == 'B')){
                System.out.println("Invalid character in input");
                return false;
            }

        return true;
    }

    public static Character[] inputStringToArray(String s){
        Character[] arr = new Character [22];
        for (int i = 0; i < 22; i++)
            arr[i] = s.charAt(i);
        return arr;
    }

    public static Character[] copyOfBoard(Character[] arr){
        Character[] copy_arr = new Character[22];

        for (int i = 0; i < 22; i++)
            copy_arr[i] = arr[i];

        return copy_arr;
    }

    public static int[] countPlayers(Character[] board) {
        int[] count = new int [2];

        for (Character c : board){
            if (c == 'W')
                count[0]++;
            else if (c =='B')
                count[1]++;
        }

        return count;
    }

    public static ArrayList<Integer> neighboursOfPostion(int pos) {
        if (pos < 0 || pos > 21){
            System.out.println("Position is invalid");
            return null;
        }

        int[] arr = new int[1];

        switch (pos) {
            case 0: { arr = new int[]{3,1,19}; break;}
            case 1: { arr = new int[]{4,0,2}; break;}
            case 2: { arr = new int[]{5,1,12}; break;}
            case 3: { arr = new int[]{0,6,4,8}; break;}
            case 4: { arr = new int[]{3,5,1}; break;}
            case 5: { arr = new int[]{7,2,4,11}; break;}
            case 6: { arr = new int[]{9,7,3}; break;}
            case 7: { arr = new int[]{6,5,10}; break;}
            case 8: { arr = new int[]{3,16,9}; break;}
            case 9: { arr = new int[]{13,6,8}; break;}
            case 10: { arr = new int[]{15,11,7}; break;}
            case 11: { arr = new int[]{18,5,10,12}; break;}
            case 12: { arr = new int[]{21,2,11}; break;}
            case 13: { arr = new int[]{16,9,14}; break;}
            case 14: { arr = new int[]{13,15,17}; break;}
            case 15: { arr = new int[]{14,10,18}; break;}
            case 16: { arr = new int[]{19,13,8,17}; break;}
            case 17: { arr = new int[]{20,14,16,18}; break;}
            case 18: { arr = new int[]{17,15,21,11}; break;}
            case 19: { arr = new int[]{0,16,20}; break;}
            case 20: { arr = new int[]{19,17,21}; break;}
            case 21: { arr = new int[]{20,18,12}; break;}
        }
        ArrayList<Integer> al = new ArrayList<>();
        for (int ele : arr)
            al.add(ele);
        return al;
    }

    public static Boolean checkMillAtPosition(Character[] arr, int pos){
        if (pos < 0 || pos > 21){
            System.out.println("Position is invalid");
            return null;
        }

        if (arr[pos] == 'x'){
            System.out.println("Error at check mill it is x");
            return null;
        }

        Character C = arr[pos];

        switch (pos){
            case 0: return (( arr[3] == C &&  arr[6] == C) || ( arr[1] == C &&  arr[2] == C));
            case 1: return (( arr[0] == C &&  arr[2] == C)) ;
            case 2: return (( arr[0] == C &&  arr[1] == C) || ( arr[5] == C &&  arr[7] == C) || ( arr[21] == C &&  arr[12] == C));
            case 3: return (( arr[0] == C &&  arr[6] == C) || ( arr[8] == C &&  arr[16] == C) || ( arr[4] == C &&  arr[5] == C));
            case 4: return (( arr[3] == C &&  arr[5] == C));
            case 5: return (( arr[7] == C &&  arr[2] == C) || ( arr[11] == C &&  arr[18] == C) || ( arr[3] == C &&  arr[4] == C)) ;
            case 6: return (( arr[0] == C &&  arr[3] == C) || ( arr[9] == C &&  arr[12] == C)) ;
            case 7: return (( arr[5] == C &&  arr[2] == C) || ( arr[10] == C &&  arr[15] == C)) ;
            case 8: return (( arr[16] == C &&  arr[3] == C)) ;
            case 9: return (( arr[6] == C &&  arr[13] == C)) ;
            case 10: return (( arr[7] == C &&  arr[15] == C) || ( arr[11] == C &&  arr[12] == C)) ;
            case 11: return (( arr[10] == C &&  arr[12] == C) || ( arr[18] == C &&  arr[5] == C)) ;
            case 12: return (( arr[21] == C &&  arr[2] == C) || ( arr[10] == C &&  arr[1] == C)) ;
            case 13: return (( arr[9] == C &&  arr[6] == C) || ( arr[16] == C &&  arr[19] == C) || ( arr[14] == C &&  arr[15] == C)) ;
            case 14: return (( arr[17] == C &&  arr[20] == C) || ( arr[13] == C &&  arr[15] == C)) ;
            case 15: return (( arr[13] == C &&  arr[14] == C) || ( arr[18] == C &&  arr[21] == C)  || ( arr[10] == C &&  arr[7] == C)) ;
            case 16: return (( arr[19] == C &&  arr[13] == C) || ( arr[17] == C &&  arr[18] == C)  || ( arr[8] == C &&  arr[3] == C)) ;
            case 17: return (( arr[20] == C &&  arr[19] == C) || ( arr[16] == C &&  arr[18] == C)) ;
            case 18: return (( arr[15] == C &&  arr[21] == C) || ( arr[16] == C &&  arr[17] == C)  || ( arr[11] == C &&  arr[5] == C)) ;
            case 19: return (( arr[16] == C &&  arr[13] == C) || ( arr[20] == C &&  arr[21] == C)) ;
            case 20: return (( arr[17] == C &&  arr[14] == C) || ( arr[19] == C &&  arr[21] == C)) ;
            case 21: return (( arr[19] == C &&  arr[20] == C) || ( arr[18] == C &&  arr[15] == C) || ( arr[12] == C &&  arr[2] == C)) ;  
        }

        return false;
    }

    public static int countPotentialMills(Character[] arr, Character curr_player) {

        int count = 0;
        Character[] board = copyOfBoard(arr);
        for (int i = 0; i < 22; i++) {
            if (board[i] == 'x'){
                board[i] = curr_player;
                if (checkMillAtPosition(board, i));
                    count++;
                board[i] = 'x';
            }
        }

        return count;
    }

    public static void generateRemove(Character[] arr, ArrayList<Character[]> list, Character playerToBeRemoved){
        if (!(playerToBeRemoved == 'B' || playerToBeRemoved == 'W')){
            System.out.println("Invalid character to be removed");
            return ;
        }

        int counter = 0;

        for (int i = 0; i < 22; i++)
            if (arr[i] == playerToBeRemoved) {
                if (! checkMillAtPosition(arr, i)){
                    counter++;
                    Character[] copy_arr = copyOfBoard(arr);
                    copy_arr[i] = 'x';
                    list.add(copy_arr);
                }
            }
        
        if (counter == 0) 
            list.add(arr);

        // SEE WHAT PDF IS SAYING ABOUT IF NO POSITIONS WERE ADDED !!!!
    }

    public static ArrayList<Character[]> generateAdd(Character[] arr, Character playerToBeAdded){

        ArrayList<Character[]> list = new ArrayList<>();
        Character playerToBeRemoved = playerToBeAdded == 'W' ? 'B' : 'W';

        for (int i = 0; i < 22; i++)
            if (arr[i] == 'x') {
                Character[] copy_arr = copyOfBoard(arr);
                copy_arr[i] = playerToBeAdded;
                if (checkMillAtPosition(copy_arr, i))
                    generateRemove(copy_arr, list, playerToBeRemoved);
                else
                    list.add(copy_arr);
            }
        
        return list;
    }

    public static ArrayList<Character[]> generateMove(Character[] arr, Character playerToBeAdded){

        ArrayList<Character[]> list = new ArrayList<>();
        Character playerToBeRemoved = playerToBeAdded == 'W' ? 'B' : 'W';

        for (int i = 0; i < 22; i++)
            if (arr[i] == playerToBeAdded) {
                ArrayList<Integer> neighbours = neighboursOfPostion(i);
                for (int j : neighbours) {
                    if (arr[j] == 'x'){
                        Character[] copy_arr = copyOfBoard(arr);
                        copy_arr[i] = 'x';
                        copy_arr[j] = playerToBeAdded;

                        if (checkMillAtPosition(copy_arr, j))
                            generateRemove(copy_arr, list, playerToBeRemoved);
                        else
                            list.add(copy_arr);
                    }
                }
            }
        
        return list;
    }

    public static ArrayList<Character[]> generateHopping(Character[] board, Character playerToBeAdded){
        ArrayList<Character[]> list = new ArrayList<>();
        Character playerToBeRemoved = playerToBeAdded == 'W' ? 'B' : 'W';

        for (int alpha = 0; alpha < 22; alpha++){
            if (board[alpha] == playerToBeAdded){
                for (int beta = 0; beta < 22; beta++){
                    if (board[beta] == 'x'){
                        Character[] b = copyOfBoard(board);
                        b[alpha] = 'x';
                        b[beta] = playerToBeAdded;

                        if (checkMillAtPosition(b, beta))
                            generateRemove(b, list, playerToBeRemoved);
                        else
                            list.add(b);
                    }
                }
            }
        }    
        return list;
    }

    public static ArrayList<Character[]> generateMovesOpening(Character[] board, Character playerToBeAdded) {
        return (generateAdd(board, playerToBeAdded)); 
    }

    public static ArrayList<Character[]> generateMovesMidgameEndgame(Character[] board, Character playerToBeAdded) {

        int[] count = countPlayers(board);

        if (playerToBeAdded == 'W'){
            if (count[0] == 3)
                return generateHopping(board, playerToBeAdded);
            else
                return generateMove(board, playerToBeAdded);
        }

        // else playerToBeAdded is B
        if (count[1] == 3)
            return generateHopping(board, playerToBeAdded);
        else
            return generateMove(board, playerToBeAdded);

    }

    public static int estimateOpening(Character[] board, Character maximising_player){

        int[] count = countPlayers(board);

        if (maximising_player == 'W')
            return count[0] - count[1];
        // else currPlayer = 'B'
        return count[1] - count[0];
    }

    public static int estimateOpeningImproved(Character[] board, Character maximising_player){

        int[] count = countPlayers(board);

        if (maximising_player == 'W')
            return count[0] + countPotentialMills(board, maximising_player) - count[1];
        // else currPlayer = 'B'
        return count[1] + countPotentialMills(board, maximising_player) - count[0];
    }

    public static int estimateMidgameEndgame(Character[] board, Character maximising_player){
        
        int[] count = countPlayers(board);
        ArrayList<Character[]> list;

        if (maximising_player == 'W') {
            list = generateMovesMidgameEndgame(board, 'B');
            if (count[1] <= 2)
                return 10000;
            else if (count[0] <= 2)
                return -10000;
            else if (list.size() == 0)
                return 10000;
            else 
                return 1000 * (count[0] - count[1]) - list.size();
        }
            
        // else maximising_player = 'B'
        list = generateMovesMidgameEndgame(board, 'W');
        if (count[0] <= 2)
            return 10000;
        else if (count[1] <= 2)
            return -10000;
        else if (list.size() == 0)
            return 10000;
        else 
            return 1000 * (count[1] - count[0]) - list.size();
        
    }

    public static int estimateMidgameEndgameImproved(Character[] board, Character maximising_player){
        
        int[] count = countPlayers(board);
        ArrayList<Character[]> list;

        if (maximising_player == 'W') {
            list = generateMovesMidgameEndgame(board, 'B');
            if (count[1] <= 2)
                return 10000;
            else if (count[0] <= 2)
                return -10000;
            else if (list.size() == 0)
                return 10000;
            else 
                return 1000 * (count[0] + countPotentialMills(board, maximising_player) - count[1]) - list.size();
        }
            
        // else maximising_player = 'B'
        list = generateMovesMidgameEndgame(board, 'W');
        if (count[0] <= 2)
            return 10000;
        else if (count[1] <= 2)
            return -10000;
        else if (list.size() == 0)
            return 10000;
        else 
            return 1000 * (count[1] + countPotentialMills(board, maximising_player) - count[0]) - list.size();
        
    }

    public static void main(String[] args) {
       
    }
}