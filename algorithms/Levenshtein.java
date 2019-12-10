/**
 * Levenshtein Distance - DP approach
 * @param {String} s Sting 1 to be compared
 * @param {String} t String 2 to be compared
 * @return {int} moves (insertion, deletion, substitution) to
 * change string 1 to string 2
 */
class Levenshtein {
    int findDistance(String s, String t) {
	if (s.length() == 0) return t.length();
        if (t.length() == 0) return s.length();
	
	int[][] dp = new int[s.length() + 1][t.length() + 1];
	for (int i = 0; i <= s.length(); i++) dp[i][0] = i;
	for (int j = 0; j <= t.length(); j++) dp[0][j] = j;

	for (int i = 1; i <= s.length(); i++) {
	    for (int j = 1; j <= t.length(); j++) {
		int subCost = (s.charAt(i - 1) == t.charAt(j - 1)) ? 0 : 1;
		int min = Math.min(dp[i - 1][j - 1] + subCost, Math.min(
			 dp[i - 1][j] + 1, dp[i][j - 1] + 1));
		dp[i][j] = min;
	    }
	}

	return dp[s.length()][t.length()];
    }
}