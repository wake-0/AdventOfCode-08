package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.reverseOrder;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    record Tree(int row, int column, int value) { }

    final String path = "TODO";
    List<List<Tree>> trees = new ArrayList<>();

    void run() throws IOException {
        this.readTrees();
        this.printVisibleTrees();
    }

    void printVisibleTrees() {
        int maxScencicValue = 0;
        for (int row = 1; row < trees.size()-1; row++) {
            for (int col = 1; col < trees.get(0).size()-1; col++) {

                Tree tree = this.trees.get(row).get(col);

                // left
                int maxValue = tree.value;
                int leftCount = 0;
                for (int currentCol = col-1; currentCol >= 0; currentCol--) {
                    leftCount++;
                    int compareValue = this.trees.get(row).get(currentCol).value;
                    if (maxValue <= compareValue) {
                        break;
                    }
                }

                int rightCount = 0;
                for (int currentCol = col+1; currentCol < trees.get(row).size(); currentCol++) {
                    rightCount++;
                    int compareValue = this.trees.get(row).get(currentCol).value;
                    if (maxValue <= compareValue) {
                        break;
                    }
                }

                int topCount = 0;
                for (int currentRow = row-1; currentRow >= 0; currentRow--) {
                    topCount++;
                    int compareValue = this.trees.get(currentRow).get(col).value;
                    if (maxValue <= compareValue) {
                        break;
                    }
                }

                int bottomCount = 0;
                for (int currentRow = row+1; currentRow < trees.size(); currentRow++) {
                    bottomCount++;
                    int compareValue = this.trees.get(currentRow).get(col).value;
                    if (maxValue <= compareValue) {
                        break;
                    }
                }

                int scenicValue = leftCount * rightCount * topCount * bottomCount;
                if (scenicValue > maxScencicValue) {
                    maxScencicValue = scenicValue;
                }
            }
        }

        // 1376 too low
        // 1376 too low
        System.out.println(maxScencicValue);
    }

    void readTrees() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.path + "input.txt"))) {

            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {

                String[] colEntries = line.split("");
                ArrayList<Tree> treeRow = new ArrayList<>();
                for (int col = 0; col < colEntries.length; col++) {
                    Tree tree = new Tree(row, col, Integer.parseInt(colEntries[col]));
                    treeRow.add(tree);
                }

                trees.add(treeRow);
                row++;
            }
        }
    }

}