package cinema;

import java.util.Scanner;

public class Cinema {
    public static Scanner scanner;
    public static int rows;
    public static int seatsPerRow;
    public static String[][] cinema;
    public static int purchasedTickets;
    public static int currentIncome;
    public static int totalIncome;
    public static double percentage;
    public static int frontHalfSeats;
    public static int rowNumber;
    public static int seatNumber;
    public static int price;
    public static int totalSeats;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();
        cinema = new String[rows + 1][seatsPerRow + 1];
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0 && j == 0) {
                    cinema[i][j] = " ";
                } else if (i == 0) {
                    cinema[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    cinema[i][j] = String.valueOf(i);
                } else {
                    cinema[i][j] = "S";
                }
            }
        }
        program();
    }

    public static void program() {
        int option;
        while (true) {
            System.out.println("\n");
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            option = scanner.nextInt();
            if (option == 1) {
                showSeats();
            } else if (option == 2) {
                buyTickets();
            } else if (option == 3) {
                statistics();
            } else if (option == 0) {
                break;
            } else {
                System.out.println("Please select a valid option");
            }
        }
    }

    public static void showSeats() {
        System.out.println("\n");
        System.out.println("cinema.Cinema:");
        for (int x = 0; x < cinema.length; x++) {
            for (int y = 0; y < cinema[x].length; y++) {
                System.out.print(cinema[x][y] + " ");
            }
            if (x < cinema.length - 1) {
                System.out.println();
            }
        }
    }

    public static void buyTickets() {
        while (true) {
            System.out.println("\n");
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber < 0 || rowNumber > cinema.length - 1 || seatNumber < 0 || seatNumber > cinema.length - 1) {
                System.out.println("Wrong input");
            } else {
                if (cinema[rowNumber][seatNumber].equalsIgnoreCase("B")) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    cinema[rowNumber][seatNumber] = "B";
                    break;
                }
            }
        }
        System.out.println("\n");
        System.out.printf("Ticket price: $%d", getPrice());
        showSeats();
        purchasedTickets++;
        currentIncome += getPrice();
    }

    public static int getPrice() {
        totalSeats = rows * seatsPerRow;
        if (totalSeats > 60) {
            frontHalfSeats = rows / 2;
            if (rowNumber > frontHalfSeats) {
                price = 8;
            } else {
                price = 10;
            }
        } else {
            price = 10;
        }
        return price;
    }

    private static void statistics() {
        totalSeats = rows * seatsPerRow;
        percentage = (double) purchasedTickets / totalSeats * 100;
        frontHalfSeats = rows / 2;
        int backHalfSeats = rows - frontHalfSeats;
        totalIncome = ((frontHalfSeats * 10) * seatsPerRow) + ((backHalfSeats * 8) * seatsPerRow);

        System.out.println("\n");
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}