package com.ruoyi.tool;

/**
 * @author：乔石磊
 * @date：16:05 2024/1/12
 * 莱温斯坦距离类
 */
public class LevenshteinDistance {

    private static final int insertionCost = 1;
    private static final int deletionCost = 1;
    private static final int substitutionCost = 1;


    public static int calculateDistance(CharSequence source, CharSequence target) {

        int sourceLength = source.length();
        int targetLength = target.length();

        int[][] matrix = new int[sourceLength + 1][targetLength + 1];
        matrix[0][0] = 0;

        for (int row = 1; row <= sourceLength; ++row) {
            matrix[row][0] = row;
        }

        for (int col = 1; col <= targetLength; ++col) {
            matrix[0][col] = col;
        }

        for (int row = 1; row <= sourceLength; ++row) {
            for (int col = 1; col <= targetLength; ++col) {
                matrix[row][col] = calcMinCost(source, target, matrix, row, col);
            }
        }

        return matrix[sourceLength][targetLength];
    }
    private static int calcMinCost(CharSequence source, CharSequence target,
                                   int[][] matrix, int row, int col) {
        return Math.min(
                calcSubstitutionCost(source, target, matrix, row, col), Math.min(
                        calcDeletionCost(matrix, row, col),
                        calcInsertionCost(matrix, row, col))
        );
    }
    private static int calcInsertionCost(int[][] matrix, int row, int col) {
        return matrix[row][col - 1] + insertionCost;
    }

    private static int calcDeletionCost(int[][] matrix, int row, int col) {
        return matrix[row - 1][col] + deletionCost;
    }

    private static int calcSubstitutionCost(CharSequence source, CharSequence target,
                                            int[][] matrix, int row, int col) {
        int cost = 0;
        if (source.charAt(row - 1) != target.charAt(col - 1)) {
            cost = substitutionCost;
        }
        return matrix[row - 1][col - 1] + cost;
    }

}
