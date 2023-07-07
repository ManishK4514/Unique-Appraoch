Q. Maximum Non Negative Product in a Matrix

Practice: https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/description/

class Solution {
    long[][] dpMax = new long[20][20];
    long[][] dpMin = new long[20][20];
    public long minimum(int[][] grid, int row, int col){
        if(row == 0 && col == 0) return grid[row][col];
        
        if(dpMin[row][col] != Long.MAX_VALUE) return dpMin[row][col];
        
        long ans = (long)(1e18);
        if(row > 0) {
            ans = Math.min(ans, Math.min(grid[row][col] * minimum(grid, row - 1, col), grid[row][col] * maximum(grid, row - 1, col)));
        }
        if(col > 0) {
            ans = Math.min(ans, Math.min(grid[row][col] * minimum(grid, row, col - 1), grid[row][col] * maximum(grid, row, col - 1)));
        }

        return dpMin[row][col] = ans;
    }
    public long maximum(int[][] grid, int row, int col){
        if(row == 0 && col == 0) return grid[row][col];
        
        if(dpMax[row][col] != Long.MIN_VALUE) return dpMax[row][col];

        long ans = (long)(-1e18);
        if(row > 0) {
            ans = Math.max(ans, Math.max(grid[row][col] * minimum(grid, row - 1, col), grid[row][col] * maximum(grid, row - 1, col)));
        }
        if(col > 0) {
            ans = Math.max(ans, Math.max(grid[row][col] * minimum(grid, row, col - 1), grid[row][col] * maximum(grid, row, col - 1)));
        }

        return dpMax[row][col] = ans;
    }
    public int maxProductPath(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        for(long[] it : dpMin) Arrays.fill(it, Long.MAX_VALUE);
        for(long[] it : dpMax) Arrays.fill(it, Long.MIN_VALUE);

        long ans = maximum(grid, n - 1, m - 1);
        return ans < 0 ? -1 : (int)(ans % 1000_000_007);
    }
}
