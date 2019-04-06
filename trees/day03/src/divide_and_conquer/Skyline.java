package divide_and_conquer;

import java.util.*;

import static java.lang.Integer.max ;

public class Skyline {

    public static class Point {
        public int x;
        public int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Building {
        private int l, r, h;

        public Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] array) {
        ArrayList<Point> skyline = new ArrayList<>();
        if (array.length == 0) {
            return skyline;
        }
        if (array.length == 1) { // givien (1,3,2) result (1,2)(3,0) as skyline
            skyline.add(new Point(array[0].l, array[0].h));
            skyline.add(new Point(array[0].r, 0));
            return skyline;
        } else { //Dividing Step
            int l_index = 0;
            int r_index = array.length - 1;
            int mid = array.length / 2;
            ;

            Building[] left = Arrays.copyOfRange(array, l_index, mid);
            Building[] right = Arrays.copyOfRange(array, mid, r_index + 1);

            return mergeSkylines(skyline(left), skyline(right));
        }
    }

    private static List<Point> mergeSkylines(List<Point> skyline1, List<Point> skyline2) {
        int s1_index = 0;
        int s2_index = 0;
        int h1 = 0;
        int h2 = 0;
        int count = 0;

        ArrayList<Point> result = new ArrayList<>();

        while (s1_index < skyline1.size() && s2_index < skyline2.size()) {

            // if sky1 and sky2 x coordinates overlap, merge by choosing higher y
            if (skyline1.get(s1_index).x == skyline2.get(s2_index).x) {
                h1 = skyline1.get(s1_index).y;
                h2 = skyline2.get(s2_index).y;
                int maxh = max(h1, h2);

                if (result.size() > 0) {
                    if (maxh != result.get(count - 1).y) { // if y is smaller than previous, no need to merge
                        result.add(new Point(skyline1.get(s1_index).x, maxh));
                        count++;
                    }

                } else { // if result array is empty, add
                    result.add(new Point(skyline1.get(s1_index).x, maxh));
                    count++;
                }

                s1_index++;
                s2_index++;

                // if element of skyline1 has smaller x coordinate
            } else if (skyline1.get(s1_index).x < skyline2.get(s2_index).x) {
                h1 = skyline1.get(s1_index).y;
                int maxh = max(h1, h2);

                if (result.size() > 0) {
                    if (maxh != result.get(count - 1).y) {
                        result.add(new Point(skyline1.get(s1_index).x, maxh));
                        count++;
                    }
                } else {
                    result.add(new Point(skyline1.get(s1_index).x, maxh));
                    count++;
                }
                s1_index++;

                // if element of skyline1 has smaller x coordinate
            } else {
                h2 = skyline2.get(s2_index).y;
                int maxh = max(h1, h2);
                if (result.size() > 0) {

                    if (maxh != result.get(count - 1).y) {
                        result.add(new Point(skyline2.get(s2_index).x, maxh));
                        count++;
                    }
                } else {
                    result.add(new Point(skyline2.get(s2_index).x, maxh));
                    count++;
                }
                s2_index++;
            }
        }

        // when skyline2 has finished implementing, add all left skyline1 element to result
        while (s1_index < skyline1.size()) {
            result.add(skyline1.get(s1_index));
            s1_index++;
        }

        // when skyline1 has finished implementing, add all left skyline2 element to result
        while (s2_index < skyline2.size()) {
            result.add(skyline2.get(s2_index));
            s2_index++;
        }

        return result;
    }

}

/*
* Runtime: O(nLogN) --> merge sort
* Divide the given set of building in two subsets. Recursively constrcut skyline for two halves a
* and finally merge the two skylines
* Merge: Start from first strips of two skylines, compare x coordinates. Pick the strip with
* smaller x coordinate and add it to result. The height of added strip is considered as
* maximum of current heights from skyline1 and skyline2
*
* Base: When length is 1 --> minimal stuff to perform code
* Divide: divide the given array in two arrays
* Conquer: Comparing sky1 and sky2 elements
* Merge: Merge skyline1 and skyline2 to result
* */