package cinema;

import java.util.Scanner;

public class Cinema {

    int rows;
    int cols;
    char[][] seats;
    private int totalIncome = 0;
    int  currentIncome = 0, purchasedTicket = 0;
    static Scanner sc = new Scanner(System.in);
    boolean flag = true;

    Cinema(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        seats = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = 'S';
            }
        }
        setTotalIncome();
    }

    public void display() {
        System.out.println("\nCinema:\n ");
        for (int j = 1; j <= cols; j++) {
            System.out.print(" " + j);
        }

        for (int i = 0; i < rows; i++) {
            System.out.print("\n" + (i + 1));
            for (int j = 0; j < cols; j++) {
                System.out.print(" " + seats[i][j]);
            }
        }
        System.out.println("\n");
    }

    public void staticalStatus() {
        System.out.println("Number of purchased tickets: " + purchasedTicket);
        float per = (float)(purchasedTicket * 100) / (rows * cols);
        System.out.printf("Percentage: %.2f", per);
        System.out.println("%\nCurrent income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public void setTotalIncome() {
        if (rows * cols <= 60) {
            totalIncome = rows * cols * 10;
        } else {
            totalIncome += (rows / 2) * cols * 10;
            totalIncome += (rows - (rows / 2)) * cols * 8;
        }
    }


    void fairCharge(int row_no){
        int charge = 0;
        if(rows * cols <= 60) {
            charge = 10;
        }
        else if(row_no <= rows / 2){
            charge = 10;
        }
        else if(row_no > rows / 2){
            charge = 8;
        }
        currentIncome += charge;
        System.out.println("Ticket price: $" + charge);
    }

    void ticketSelection() {
        int row_no, seat_no;
        System.out.println("Enter a row number:");
        row_no = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        seat_no = sc.nextInt();
        if(row_no > rows || seat_no > cols){
            System.out.println("Wrong input!");
            ticketSelection();
        }
        else if(seats[row_no-1][seat_no-1] == 'B'){
            System.out.println("That ticket has already been purchased!");
            ticketSelection();
        }
        else{
            fairCharge(row_no);
            bookingConfirm(row_no-1, seat_no-1);
            purchasedTicket++;
        }
    }

    void bookingConfirm(int row, int col) {
        seats[row][col] = 'B';

    }

    public void menu(Cinema cinObj, int inp){
        switch (inp) {
            case 1:
                cinObj.display();
                break;
            case 2:
                cinObj.ticketSelection();
                break;
            case 3:
                cinObj.staticalStatus();
                break;
            case 0:
                flag = false;
        }
    }

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int r = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int c = sc.nextInt();
        Cinema cinObj = new Cinema(r, c);

        int inp;
        while (cinObj.flag) {
            System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            inp =  sc.nextInt();
            if(inp > 3 || inp < 0) {
                System.out.println("Wrong input!");
            }
            else {
                cinObj.menu(cinObj, inp);
            }
        }

    }
}


