package service;

import service.Seat;

public class Venue {

    private int rows;
    private int cols;
    private int availableSeats;
    private int firstRowWithEmptySeats;
    private int colOfFirstEmptySeat;

    private Seat[][] venueSeats;

    //main.Venue constructor: Create ArrayList
    public Venue(int r, int c) {
        rows = r;
        cols = c;
        firstRowWithEmptySeats = 0;
        colOfFirstEmptySeat = 0;
        availableSeats = r * c;
        venueSeats = new Seat[r][c];

        addSeatsToVenue();
    }


    private void addSeatsToVenue(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                Seat aSeat = new Seat(i,j);
                venueSeats[i][j] = aSeat;
            }
        }
    }

    //Return number of rows in the venue
    public int getNumberOfRows(){
        return rows;
    }
    //Return number of columns in the venue
    public int getNumberOfCols(){
        return cols;
    }
    //Return first row with empty seats
    public int getFirstRowWithEmptySeats() {
        return firstRowWithEmptySeats;
    }
    //Return column of last used seat
    public int getColOfFirstEmptySeat(){
        return colOfFirstEmptySeat;
    }
    //Get number of available seats
    public int getAvailableSeats(){
        return availableSeats;
    }

    public Seat[][] getVenueSeats() {
        return venueSeats;
    }


    //Add this to ticket implementation

    public void updateFirstRowWithEmptySeats(int r, boolean delete){
        if(colOfFirstEmptySeat == 0 && delete == false)
            firstRowWithEmptySeats += 1;
        else
            firstRowWithEmptySeats = r;
    }


    public void updateColOfFirstEmptySeat(int c, boolean delete){
        if(delete == false){
            if(c + 1 == cols){
                colOfFirstEmptySeat = 0;
            }else{
                colOfFirstEmptySeat = c + 1;
            }
        }else if(delete == true){
            colOfFirstEmptySeat = c;
        }
    }

    //Update number of available seats
    public void updateAvailableSeats(int occupied, boolean delete){
        if(delete == true && availableSeats <= rows * cols){
            availableSeats += occupied;
        }else{
            availableSeats -= occupied;
        }
    }
}