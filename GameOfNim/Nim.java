import java.util.*;

/*

Nim is an ancient game that is thought to have originated in China. The game
is played by two players. The game begins when 26 sticks are placed on a table.
The players take turns taking 1, 2, or 3 sticks per turn. The person that 
takes the last stick wins.

*/

class Nim {
    private static int numSticks = 26;
    private static int numDraws = 3;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter player1's name: ");
        String player1 = input.next();
        System.out.print("Enter player2's name: ");
        String player2 = input.next();

        // pick a random person to go first
        int rand = (int)Math.floor(Math.random() * 2);
        String whosTurn;
        boolean torf;
        String showSticks = new String(new char[26]).replace("\0", "|");

        System.out.println("\n" + showSticks.replace("", " ").trim() + "\t(26) sticks remaining\n");

        // player 1 goes second if torf is true
        torf = rand == 0 ? true : false;

        // game while loop
        while (numSticks > 0) {
            // hmm...how will turn base work?
            // I USE BOOLEANS!
            // for each iteration, just change the boolean to the not current
            whosTurn = torf ? player1 : player2;
            torf = !torf;
            System.out.print(whosTurn + "'s turn: ");
            int guess = input.nextInt();
            if (guess > numDraws || guess < 1) {
                System.out.println(numDraws + " is the max and 1 is the min");
                torf = !torf; // i dont want them to lose their turn
            } else {
                numSticks -= guess;
                if (numSticks < 0) {
                    System.out.println("\t(0) sticks remaining" + "\n\n" + whosTurn.toUpperCase() + " wins! GG!");
                    break;
                }
                showSticks = new String(new char[numSticks]).replace("\0", "|");
                System.out.println(showSticks.replace("", " ").trim() + "\t(" + showSticks.length() + ") sticks remaining" + "\n\n");
                if (numSticks == 0) {
                    System.out.println(whosTurn.toUpperCase() + " wins! GG!");
                    break;
                }
            }
        }
    }
}