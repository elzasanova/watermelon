import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class poleChudes{
    private static final String [] WORDS = {"tishina"};
    private static final String[] DESCRIPTIONS ={"silent in russian"};
    private ArrayList<String> players;
    private ArrayList<Integer> points;
    private int playerIndex;
    private String secretWord;
    private String currentWord;
    private String description;


    public poleChudes(){
        players = new ArrayList<>();
        points = new ArrayList<>();
        playerIndex=0;
        secretWord = WORDS[new Random().nextInt(WORDS.length)];
        currentWord= hideWord(secretWord);
        description = DESCRIPTIONS[new Random().nextInt(DESCRIPTIONS.length)];

    }
    private String hideWord(String word){
        StringBuilder hiddenword= new StringBuilder();
        for(int i=0;i< word.length();i++){
            hiddenword.append("▢");

        }
        return hiddenword.toString();
    }
    private boolean gameOver(){ return currentWord.equals(secretWord);}
    private void displayGameState(){
        System.out.println("     Description: "+ description);
        System.out.println("Word: "+ currentWord);
        System.out.println("Players: "+ players);
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player " + players.get(i) + ": " + points.get(i) + " points");
        }
        System.out.println("     Current player: "+ players.get(playerIndex));
    }

    private void writeAnyLetter( char letter){
        boolean correct = false;
        for(int i =0;i<secretWord.length();i++){
            if(secretWord.charAt(i)==letter){
                StringBuilder ef = new StringBuilder(currentWord);
                ef.setCharAt(i,letter);
                currentWord = ef.toString();
                correct=true;
                points.set(playerIndex, points.get(playerIndex) + 100);
            }
        }
        if(!correct){
            System.out.println("Incorrect,try again.");
            nextplayer();

        }

    }

    private void writeWord(String word) {
        if (word.equals(secretWord)) {
            System.out.println("Congratulations! You've guessed the word! '" + secretWord + "'");
            return;
        }     else {
            System.out.println("Incorrect guess. You are out of the game.");
            players.remove(playerIndex);
            points.remove(playerIndex);
            nextplayer();
            play();
            clearScreen();
        }
    }

    private void nextplayer(){
        playerIndex=(playerIndex + 1 ) % players.size();}

    private void play (){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Pole Chudes!");

        while (!gameOver()){
            displayGameState();
            System.out.print("Enter your  letter or the whole word: ");
            String guessLetter= scanner.nextLine();
            if(guessLetter.length() == 1){
                char letter = guessLetter.charAt(0);
                writeAnyLetter(letter);
                clearScreen();
            }
            else if ( guessLetter.equals(secretWord)){
                System.out.println("Congratulations!" +  "You've guessed the word!"+" '" + secretWord+ "'");
                return;
            }
            else {
                System.out.println("Incorrect guess!");
                nextplayer();
                clearScreen();
            }
        }
    }
    public static void main(String[] args) {
        poleChudes game = new poleChudes();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter player names (enter 'done' to start the game): ");
        String playerName = scanner.nextLine();
        while (!playerName.equals("done")) {

            game.players.add(playerName);
            game.points.add(0);

            System.out.print("Enter next player name: ");
            playerName = scanner.nextLine();


        }

        if (playerName.equals("done")) {
            clearScreen() ;
        }
        game.playerIndex = new Random().nextInt(game.players.size());
        game.play();


        int maxPoints = game.secretWord.length();
        int maxPointsPlayerIndex = game.points.indexOf(game.points.stream().max(Integer::compare).get());
        System.out.println("Player " + game.players.get(maxPointsPlayerIndex) +
                " reached or exceeded the maximum points!");
        System.out.println("Now other players will have the opportunity to guess the word or pass their turn.");
        for (int i = 0; i < game.players.size(); i++) {
            if (i != maxPointsPlayerIndex) {
                System.out.print("Player " + game.players.get(i) + ", enter your guess or pass: ");
                String guess = scanner.nextLine();
                if (guess.equals(game.secretWord)) {
                    System.out.println("Congratulations! " + game.players.get(i) + " won by guessing the word!");
                    return;
                }
            }
        }
        System.out.println("No one guessed the word. " + game.players.get(maxPointsPlayerIndex) +
                " wins with the highest score!");

    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}