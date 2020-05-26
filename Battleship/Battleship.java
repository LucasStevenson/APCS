import java.util.*;

class Battleship {
  private static String[][] displayMap = new String[13][13];
  private static String[][] gameMap = new String[13][13];

  public static void initMap(String[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (i == 0) map[i][j] = Integer.toString(j);
        else if (j == 0) map[i][j] = Integer.toString(i);
        else map[i][j] = "[]";
      }
    }
    map[0][0] = "";
  }

  public static boolean shipCheck(String dir, int firstRand, int secondRand) {
    // this function makes sure ships do not intersect when being randomly generated
    if (dir.toLowerCase() == "v") {
      // check vertically
      if (gameMap[firstRand][secondRand].equals("S") || gameMap[firstRand][secondRand+1].equals("S") || gameMap[firstRand][secondRand+2].equals("S")) {
        return false;
      }
    }

    if (dir.toLowerCase() == "h") {
      // check horizontally
      if (gameMap[firstRand][secondRand].equals("S") || gameMap[firstRand+1][secondRand].equals("S") || gameMap[firstRand+2][secondRand].equals("S")) {
        return false;
      }
    }
    return true;
  }

  public static void setupGameMap() {
    // each ship spans three points vertically or horizontally
    int NUMSHIPS = 6;
    // there will always be 6 ships to begin with
    int shipCount = 0;
    while (shipCount < NUMSHIPS) {
      // generate two random numbers
      try {
        int firstRand = (int)(Math.random()*(gameMap.length-3))+1;
        int secondRand = (int)(Math.random()*(gameMap.length-3))+1;

        int whichDir = (int)(Math.random()*2)+1;
        // check
        if (whichDir == 1) {
          if (shipCheck("v", firstRand, secondRand)) {
            gameMap[firstRand][secondRand] = "S";
            gameMap[firstRand][secondRand+1] = "S";
            gameMap[firstRand][secondRand+2] = "S";
            shipCount++;
          } else {
            continue;
          }
        } else if (whichDir == 2) {
          if (shipCheck("h", firstRand, secondRand)) {
            gameMap[firstRand][secondRand] = "S";
            gameMap[firstRand+1][secondRand] = "S";
            gameMap[firstRand+2][secondRand] = "S";
            shipCount++;
          } else {
            continue;
          }
        }
      } catch(Exception e) {
        //System.out.println(e);
        continue;
      }
    }
  }

  public static void printMap(String[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        System.out.print(map[i][j] + "\t");
      }
      System.out.println();
    }
  }

  public static void gameLoop(int numGuesses) {
    int counter = 0;
    Scanner sc;
    while (counter <= numGuesses) {
      printMap(displayMap);
      System.out.print("Enter a coordinate to attack: ");
      sc = new Scanner(System.in);
      String userInput = sc.nextLine();
      String[] userInputArr = userInput.split(",");

      if (userInputArr.length < 2) {
        System.out.println("Invalid input. Please enter two numbers seperated by a comma");
        continue;
      }

      int yCoord = Integer.parseInt(userInputArr[0].trim());
      int xCoord = Integer.parseInt(userInputArr[1].trim());

      if (xCoord == 0 || yCoord == 0 || xCoord > displayMap.length || yCoord > displayMap.length) {
        System.out.println("You can't guess there!");
        continue;
      }

      if (!displayMap[xCoord][yCoord].equals("[]")) {
        System.out.println("You already guessed that spot!");
        continue;
      }

      if (gameMap[xCoord][yCoord].equals("S")) {
        System.out.println("Good Job! It's a hit!");
        displayMap[xCoord][yCoord] = "X";
      } else {
        System.out.println("Aw darn! It's NOT a hit!");
        displayMap[xCoord][yCoord] = "O";
      }
      // increment the counter
      counter++;
    }
    System.out.println("You ran out of tries! Here was the map");
    printMap(gameMap);
  }

  public static void main(String[] args) {
    System.out.println("Welcome to a game of Battleship.\nEnter where you would like to attack by entering: xLocation, yLocation");

    // Initializing the maps
    initMap(displayMap);
    initMap(gameMap);
    setupGameMap();

    gameLoop(20);
  }
}
