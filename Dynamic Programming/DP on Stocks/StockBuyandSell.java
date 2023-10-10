/*
 * Best time to buy and sell stock

 * We are given an array Arr[] of length n. It represents the price of a stock on ‘n’ days. The following guidelines need to be followed:

 * We can buy and sell a stock only once.
 * We can buy and sell the stock on any day but to sell the stock, we need to first buy it on the same or any previous day.
 * We need to tell the maximum profit one can get by buying and selling this stock.
 */
public class StockBuyandSell {
    static int maximumProfit(int[] Arr) {
        // Write your code here.
        int maxProfit = 0;
        int mini = Arr[0];

        for (int i = 1; i < Arr.length; i++) {
            int curProfit = Arr[i] - mini;
            maxProfit = Math.max(maxProfit, curProfit);
            mini = Math.min(mini, Arr[i]);
        }
        return maxProfit;
    }

    public static void main(String args[]) {

        int[] Arr = { 7, 1, 5, 3, 6, 4 };

        System.out.println("The maximum profit by selling the stock is " +
                maximumProfit(Arr));
    }
}
