/**********************************************************
 *                                                        *
 *  CSCI 470/502        Assignment 4         Summer 2021  *
 *                                                        *
 *  Developer(s):  Leonart Jaos                           *
 *                                                        *
 *  Section:  0Y01                                        *
 *                                                        *
 *  Due Date/Time:  July 12 @ 11:59 PM                    *
 *                                                        *
 *  Purpose:  The below code utilizes a variety of class  *
 *  objects for the purpose of displaying possible flights*
 *  for Frequent Flyer Miles Redemption. This is done by  *
 *  parsing file input of locations, miles required, off  *
 *  season miles required, upgrade miles required, and off*
 *  season months. This information is inserted into an   *
 *  object we define, "Destination". The parsing is done  *
 *  through the MileRedeemer class through "readDestinatio*
 *  ns". The cities are then output, and the user inputs  *
 *  their miles and month of departure so that the best   *
 *  utilization of the miles is applied to flights. The   *
 *  result is then printed and the user is prompted until *
 *  they terminate.                                       *
 *                                                        *
 **********************************************************/

// File parsing and user input imports
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class MileRedeemerApp
{
    public static void main(String[] args) throws IOException
    {
        String fileName; // user input file name used for parsing
        String ans; // user input for termination of program
        int mi; // user input for miles available
        int mon; // user input for month departure
        int a = 0; // used later to navigate City and class of flight array

        Scanner keyScan = new Scanner(System.in);
        System.out.print("Please enter the name of your file: ");
        fileName = keyScan.nextLine();

        // Assign file object, Create MileRedeemer object to parse info
        Scanner destinations = new Scanner(new File(fileName));
        MileRedeemer MR = new MileRedeemer();
        MR.readDestinations(destinations);

        System.out.print("-----------------------------------------------------------------\n");
        System.out.print("        WELCOME TO THE JAVA AIRLINES MILES REDEMPTION APP\n");
        System.out.print("-----------------------------------------------------------------\n\n");
        System.out.print("List of cities your client can travel to: \n\n");

        // Prints through string array from MR class for city names in file
        for(int i = 0; i < MR.getCityNames().length; i++)
        {
            System.out.println(MR.getCityNames()[i]);
        }
        // Driving loop that prompts user for miles, month of departure, and prints best utilization of
        // Frequent Flyer Miles
        do
        {
            // User input for Miles and Month of departure
            System.out.print("\n-----------------------------------------------------------------\n");
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter your client's accumulated Frequent Flyer Miles: ");
            mi = input.nextInt();
            System.out.print("\n\nPlease enter your client's month of departure (1-12): ");
            mon = input.nextInt();

            // Client results for printing of flight location and class type
            String[] clientRes = new String[MR.redeemMiles(mi,mon).length];
            clientRes = MR.redeemMiles(mi,mon);

            // Insufficient miles
            if(clientRes.length == 0)
            {
                System.out.print("*** Your client has not accumulated enough Frequent Flyer Miles ***\n\n");
                System.out.printf("Your client's remaining Frequent Flyer Miles: %d", MR.getRmls());
                System.out.print("\n-----------------------------------------------------------------\n\n");
                System.out.print("Do you want to continue (y/n)?\n\n");
                Scanner in = new Scanner(System.in);
                ans = in.nextLine();
            }
            // Best flight combination has been calculated
            // Prints remaining miles
            else
            {
                System.out.print("\nYour client's Frequent Flyer Miles can be used to redeem the following tickets: \n\n");
                a = 0;
                for(int i = 0; i < (clientRes.length)/2; i++)
                {
                    if(!clientRes[a + 1].equals("Not eligible"))
                    {
                        System.out.printf("* A trip to %s in %s class %n", clientRes[a],
                                clientRes[a+1]);
                    }
                    a = a + 2;
                }
                System.out.printf("%nYour client's remaining Frequent Flyer Miles: %d", MR.getRmls());
                System.out.print("\n-----------------------------------------------------------------\n\n");
                System.out.print("Do you want to continue (y/n)?\n\n");
                Scanner ins = new Scanner(System.in);
                ans = ins.nextLine();
            }

        }while(ans.equals("y") || ans.equals("Y") || ans.equals("Yes") || ans.equals("yes"));
        System.out.print("-------------------------------------------------------------------------\n");
        System.out.print("        THANK YOU FOR USING THE JAVA AIRLINES MILES REDEMPTION APP\n");
        System.out.print("-------------------------------------------------------------------------\n");
    }
}
