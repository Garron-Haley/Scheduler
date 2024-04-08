package project;
import java.util.Scanner;
public class project {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number of days");
		int days = input.nextInt();
		System.out.print("Enter start time (1 to 24) (time is 24 hour clock)");
		//ST is for start time ET is for end time
		int ST = input.nextInt();
		int hours = 0;
		System.out.print("Enter end time (1 to 24)");
		int ET = input.nextInt();
		if (ST < ET) {
			hours = ET - ST;
		}
		if (ET < ST) {
			System.out.print("schedule can't be more than 24 hours");
			System.exit(0);
		}
		if ( ST == ET) {
			hours = 24;
		}
		final int st = ST;
		
	String[][] schedule = new String[days][hours];
	//d is for day h is for hour
	int d = 0;
	int h = 0;
	input.nextLine();
	while (d < days) {
		System.out.println("\nDay" + (d + 1));
		while (h < hours) {
			
			System.out.print("\nWhat is scheduled for hour " + ST + " to " + (ST + 1) + " or stop (day/hour) to stop day/hour");
			schedule[d][h] = input.nextLine();
			if (schedule[d][h].equalsIgnoreCase("stop day")) {
				schedule[d][h] = "nothing planned for this hour";
				break;
			}
			if (schedule[d][h].equalsIgnoreCase("stop hour")) {
				schedule[d][h] = "nothing planned for this hour";
			}
			ST++;
			h++;
			
			}
		ST = st;
		h = 0;
		d++;
		}
		
			
	
	d = 0;
	
	System.out.println("Your schedule is.");
	
	while (d < days) {
		
		System.out.println("Day " + (d + 1));
		
		while (h < hours) {
			if (!(schedule[d][h] != null)) {
				schedule[d][h]= "nothing planned for this hour";
			}
			System.out.println(ST + " to " + (ST + 1) + " " + schedule[d][h]);
			ST++;
			h++;
		}
		System.out.println();
		ST = st;
		d++;
		h = 0;
	}

	}

}
