package edu.gatech.seclass.tourneymanager.utils;


public class TourneyCalcAlgorithm {
    public static int calcHouseCut(int totalAmount, int housePercentage) {
        return (int) Math.round(totalAmount * (double) housePercentage / 100);
    }

    public static int[] calcPrizes(int totalAmount, int houseCut) {
        if (!(totalAmount > 0 && houseCut > 0)) {
            throw new IllegalArgumentException("Both arguments must be greater than 0");
        }

        int[] prizes = new int[3];
        int remainingAmount = totalAmount - houseCut;

        prizes[0] = (int) Math.round(remainingAmount * 0.5);
        prizes[1] = (int) Math.round(remainingAmount * 0.3);
        prizes[2] = (int) Math.round(remainingAmount * 0.2);

        return prizes;
    }

    public static int calcTotalAmount(int entranceFee, int numEntrants) {
        if (!(entranceFee > 0 && numEntrants > 0)) {
            throw new IllegalArgumentException("Both arguments must be greater than 0");
        }

        return entranceFee * numEntrants;
    }
}
