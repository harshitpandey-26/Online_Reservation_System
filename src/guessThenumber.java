import java.util.Random;
import java.util.Scanner;

public class guessThenumber {

    static int Max_Attempts = 5;
    static int compInp;
    static int userInp;

    static int no_of_Round = 0;

    public void main(String[] args) {
        System.out.println("Welcome to Guess The Number Game");
        System.out.println("You have " +  Max_Attempts+ " attempts to choose the correct number.");
        System.out.println("Good Luck!!");
        Game();
    }

    public static void Game(){
        no_of_Round++;


        Random ran = new Random();
        compInp = ran.nextInt(100) + 1;

        Scanner sc = new Scanner(System.in);


        int no_of_attempts = 0;
        System.out.println("Round: " + no_of_Round + " Begin!!");


        while(no_of_attempts<Max_Attempts){
            System.out.print("Enter a number between 1 to 100: ");
            userInp = sc.nextInt();

            if (userInp==compInp){
                System.out.println("Congratulations!! You guessed the number correctly!");
                no_of_attempts++;
                System.out.println("No. of attempts: " + no_of_attempts);
                break;
            }
            else if (userInp<compInp) {
                System.out.println("You have guessed small number.");
                no_of_attempts++;
            }
            else {
                System.out.println("You have guessed large number.");
                no_of_attempts++;
            }
        }

        if (no_of_attempts==Max_Attempts && userInp!=compInp){
            System.out.println("Sorry you have reached maximum attempts.");
            System.out.println("The correct number is: " + compInp);
        }
        System.out.println("Round " + no_of_Round + " Finished!!");
        System.out.print("Do you want to play again(Y/N): ");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("Y")){
            Game();
        }
        else{
            System.out.println("Thank you for playing!!");
            System.out.println("Have a nice day ahead!!");
        }
    }
}
