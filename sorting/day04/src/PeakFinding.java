public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        int mid = (low + high) / 2;

        while(mid > 0 && mid < nums.length - 1 ) {
            if (peakOneD(mid, nums) == 0) {
                return mid; // when return 0, means peak
            } else if (peakOneD(mid, nums) == -1) {
                high = mid - 1; // left is higher
            } else if (peakOneD(mid, nums) == 1) {
                low = mid + 1; // right is higher
            }
            mid = (low + high) / 2; // runs while loop
        }

        return mid;
    }


    public static int[] findTwoDPeak(int[][] nums) {
        int Xlow = 0;
        int Ylow = 0;

        int Yhigh = nums.length - 1;
        int Xhigh = nums[0].length - 1;

        int Xmid = (Xlow + Xhigh) / 2;
        int Ymid = (Ylow + Yhigh) / 2;

        int [] answer = new int[2];

        int x_pt = peakX(Xmid, Ymid, nums);
        int y_pt = peakY(Xmid, Ymid, nums);

        while ((Xmid > 0 || Xmid < nums.length - 1) && (Ymid > 0 || Ymid < nums.length - 1)) {

            if (x_pt == 0 && y_pt == 0) {
                answer[1] = Xmid;
                answer[0] = Ymid;
                return answer;
            }

            else if (x_pt == -1) {
                Xhigh = Xmid - 1;
            }

            else if (x_pt == 1) {
                Xlow = Xmid + 1;
            }

            else if (x_pt == 0) {
                if (y_pt == 1) {
                    Ylow = Ymid + 1;
                }
                else if (y_pt == -1) {
                    Yhigh = Ymid - 1;
                }

            }

            Xmid = (Xlow + Xhigh) / 2;
            Ymid = (Ylow + Yhigh) / 2;

            x_pt = peakX(Xmid, Ymid, nums);
            y_pt = peakY(Xmid, Ymid, nums);
        }

        return answer;
    }

}
/*
* Base Case: when peak functions equal zero // no base case
* Divide: Divide the given array in the middle and compare from there
* Conquer: Base on the result from divide, recursion to find a peak or find more peak
* Combine: Combine the result of X_dir and Y_dir // no
*
* Time Complexity - O(n)
* */