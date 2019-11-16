/**
 * Longest common subsequence algorithm using dynamic programming,
 * optimised by trimming same letters at start and end to reduce
 * the number of comparisons. 
 */
class LCS {
  /**
   * @param {String} text1 first string to be compared
   * @param {String} text2 second string to be compared
   * @return {int} the number of LCS
   */
  public int longestCommonSubsequence(String text1, String text2) {
    if (text1 == null || text1.length() == 0) return 0;
    if (text2 == null || text2.length() == 0) return 0;
      
    // trim same letters at start and end to minimise dp array
    int sameCount = 0;
    int text1End = text1.length() - 1;
    int text2End = text2.length() - 1;
    int start = 0;
    while (start <= text1End && start <= text2End) {
      if (text1.charAt(start) == text2.charAt(start)) {
        start++;
        sameCount++;
      } else {
        break;
      }              
    }
      
    while(start <= text1End && start <= text2End) {
      if (text1.charAt(text1End) == text2.charAt(text2End)) {
        text1End--;
        text2End--;
        sameCount++;
      } else {
        break;
      }
    }
                  
    text1 = text1.substring(start, text1End + 1);
    text2 = text2.substring(start, text2End + 1);
      
    // dynamic programming part
    int m = text1.length();
    int n = text2.length();
      
    // init dp matrix and prefill column and row with 0s
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) {
      dp[i][0] = 0;
    }
    for (int i = 0; i <= n; i++) {
      dp[0][i] = 0;
    }
      
    // fill in matrix
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    
    return dp[m][n] + sameCount;
  }
}
