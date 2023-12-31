package dp;

public class min_path_sum {
  public static int minPathSum1(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }
    int row = matrix.length;
    int col = matrix[0].length;
    int[][] dp = new int[row][col];
    dp[0][0] = matrix[0][0];
    for (int i = 1; i < row; i++)
      dp[i][0] = dp[i - 1][0] + matrix[i][0];

    for (int j = 1; j < col; j++)
      dp[0][j] = dp[0][j - 1] + matrix[0][j];

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
      }
    }
    return dp[row - 1][col - 1];
  }

  /**
   * 空间压缩
   * 
   * @param matrix
   * @return
   */
  public static int dp(int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }
    int row = matrix.length;
    int col = matrix[0].length;
    int[] arr = new int[col];
    arr[0] = matrix[0][0]; // 起点
    for (int j = 1; j < col; j++) {
      arr[j] = arr[j - 1] + matrix[0][j]; // 0列特殊处理
    }
    for (int i = 1; i < row; i++) {
      arr[0] += matrix[i][0];
      for (int j = 1; j < col; j++) {
        arr[j] = Math.min(arr[j - 1], arr[j]) + matrix[i][j];
      }
    }

    return arr[col - 1];
  }

  // for test
  public static int[][] generateRandomMatrix(int rowSize, int colSize) {
    if (rowSize < 0 || colSize < 0) {
      return null;
    }
    int[][] result = new int[rowSize][colSize];
    for (int i = 0; i != result.length; i++) {
      for (int j = 0; j != result[0].length; j++) {
        result[i][j] = (int) (Math.random() * 100);
      }
    }
    return result;
  }

  // for test
  public static void printMatrix(int[][] matrix) {
    for (int i = 0; i != matrix.length; i++) {
      for (int j = 0; j != matrix[0].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int rowSize = 10;
    int colSize = 10;
    int[][] matrix = generateRandomMatrix(rowSize, colSize);
    System.out.println(minPathSum1(matrix));
    System.out.println(dp(matrix));

  }
}
