import java.util.*;

/*

Nim is an ancient game that is thought to have originated in China. The game
is played by two players. The game begins when 26 sticks are placed on a table.
The players take turns taking 1, 2, or 3 sticks per turn. The person that 
takes the last stick wins.

*/

class Nim {
    // IM PRETTY SURE THERE IS A BETTER WAY OF DOING THIS...BUT OH WELL
    private static int numSticks = 26;
    private static int numDraws = 3;
    public static Scanner input = new Scanner(System.in);;
    public static String player1;
    public static String whosTurn;
    public static boolean torf;
    public static String showSticks = new String(new char[26]).replace("\0", "|");;

    public static void twoPlayer() {
        System.out.print("Enter player1's name: ");
        String player1 = input.next();
        System.out.print("Enter player2's name: ");
        String player2 = input.next();

        // pick a random person to go first
        int rand = (int)Math.floor(Math.random() * 2);

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
                if (numSticks <= 0) {
                    System.out.println("\t(0) sticks remaining" + "\n\n" + whosTurn.toUpperCase() + " wins! GG!");
                    break;
                }
                showSticks = new String(new char[numSticks]).replace("\0", "|");
                System.out.println(showSticks.replace("", " ").trim() + "\t(" + showSticks.length() + ") sticks remaining" + "\n\n");
            }
        }
    }


    public static void singlePlayer() {
        // this is almost the exact same as two player except computer does basic math and wins
        System.out.print("Enter your name: ");
        String player1 = input.next();
        torf = true;

        System.out.println("\n" + showSticks.replace("", " ").trim() + "\t(26)\n");
        int guess = 0;

        // game while loop
        while (numSticks > 0) {
            whosTurn = torf ? "Computer" : player1;
            torf = !torf;
            
            if (whosTurn.equals("Computer")) {
                guess = numSticks == 26 ? 2 : (4 - guess);
                System.out.print(whosTurn + "'s turn: " + guess + "\n");
            } else {
                System.out.print(whosTurn + "'s turn: ");
                guess = input.nextInt();
            }
            if (guess > 3 || guess < 1) {
                System.out.println("3 is the max and 1 is the min");
                torf = !torf; // i dont want them to lose their turn
            } else {
                numSticks -= guess;
                if (numSticks <= 0) {
                    System.out.println("\t(0)" + "\n\n" + whosTurn.toUpperCase() + " wins! GG!");
                    break;
                }
                showSticks = new String(new char[numSticks]).replace("\0", "|");
                System.out.println(showSticks.replace("", " ").trim() + "\t(" + showSticks.length() + ")" + "\n\n");
            }
            
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Play against human or computer? [human/comp]");
        String response = scan.next();
        if (response.toLowerCase().equals("human")) {
            twoPlayer();
        } else if (response.toLowerCase().equals("comp")) {
            singlePlayer();
        } else {
            System.out.println("You didn't choose either of the options");
        }
    }
}