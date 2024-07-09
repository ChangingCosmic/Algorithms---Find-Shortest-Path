import java.util.LinkedList;

public class FindingArkenstone {
    int[][] treasureRoom = {
            {35, 89, 52, 66, 82, 20, 95, 21},
            {79, 5, 14, 23, 78, 37, 40, 74},
            {32, 59, 17, 25, 31, 4, 16, 63},
            {91, 11, 77, 48, 13, 71, 92, 15},
            {56, 70, 47, 64, 22, 88, 67, 12},
            {83, 97, 94, 27, 65, 51, 30, 7},
            {10, 41, 1, 86, 46, 24, 53, 93},
            {96, 33, 44, 98, 75, 68, 99, 84},
    };
    int numRows = 8, numCols = 8;

    public void mostPreciousPath(){
        int[][] maxNums = new int[numRows][numCols];

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++){
                int behind = 0, left = 0, right = 0;
                if(i == 0) { // first row
                    maxNums[i][j] = treasureRoom[i][j];
                } else if(j == 0) { // if j IS against the left wall
                    behind = maxNums[i-1][j];
                    right = maxNums[i-1][j+1];
                } else if(j == numCols - 1){ // if j IS against the right wall
                    behind = maxNums[i-1][j];
                    left = maxNums[i-1][j-1];
                } else { // middle value!
                    behind = maxNums[i-1][j];
                    left = maxNums[i-1][j-1];
                    right = maxNums[i-1][j+1];
                }
                maxNums[i][j] = Math.max(behind+treasureRoom[i][j], Math.max(left+treasureRoom[i][j], right+treasureRoom[i][j]));
            }
        }

        // Compute the total gems collected and the path taken by Bilbo
        int total = 0;
        LinkedList<Integer> path = new LinkedList<>();

        // Find the cell in the 8th row with the maximum gems
        int maxIndex = 0;
        for (int j = 1; j < numCols; j++) {
            if (maxNums[numRows - 1][j] > maxNums[numRows - 1][maxIndex]) {
                maxIndex = j;
            }
        }

        path.add(maxIndex);
        int currentRow = numRows - 1;
        for (int i = numRows - 1; i > 0; i--) {
            int left = (maxIndex > 0) ? maxNums[currentRow - 1][maxIndex - 1] : 0;
            int ahead = maxNums[currentRow - 1][maxIndex];
            int right = (maxIndex < numCols - 1) ? maxNums[currentRow - 1][maxIndex + 1] : 0;

            // Determine which direction has the maximum gems
            if (ahead >= left && ahead >= right) {
                // Bilbo moved straight ahead
                // No change to maxIndex
            } else if (left >= ahead && left >= right) {
                // Bilbo moved to the left
                maxIndex--;
            } else {
                // Bilbo moved to the right
                maxIndex++;
            }
            currentRow--;
            path.addFirst(maxIndex);
        }

        // Compute the total gems collected
        total = maxNums[numRows - 1][path.getFirst()];
        for (int j = 1; j < numCols; j++) {
            total = Math.max(total, maxNums[numRows - 1][j]);
        }

        // Print the path and total gems collected
        System.out.println("Bilbo goes to Row 1, vault " + (path.getFirst() + 1));
        for (int i = 1; i < numRows; i++) {
            System.out.println("Row " + (i + 1) + ", vault " + (path.get(i) + 1));
        }
        System.out.println("Total amount of gems Bilbo stole is " + total);
        System.out.println("The Arkenstone is hidden in Vault " + (path.getLast() + 1) + "!!!!!!! Take it quick!");
    }
}
