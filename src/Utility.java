import java.util.ArrayList;

public class Utility {
    private Utility() { }

    public static boolean sorted(ArrayList<Mushroom> mushrooms) {
        for (int i = 1; i < mushrooms.size(); i++) {
            if (mushrooms.get(i).getX() < mushrooms.get(i - 1).getX()) {
                return false;
            }
        }
        return true;
    }

//    public static int findIndexX(ArrayList<Mushroom> mushrooms, int x) {
//        int front = 0;
//        int back = mushrooms.size();
//        int search = (front + back) / 2;
//        int prevIdx = search;
//        int currentIdx = search;
//
//        while (front < back) {
//            if (mushrooms.get(search).getX() == x) {
//                for (int i = search; i < mushrooms.size(); i++) {
//                    if (mushrooms.get(i).getX() > x) {
//                        return i - 1;
//                    }
//                }
//                return mushrooms.size() - 1;
//            } else {
//                if (mushrooms.get(search).getX() > x) {
//                    back = search - 1;
//                } else if (mushrooms.get(search).getX() < x) {
//                    front = search + 1;
//                }
//                search = (front + back) / 2;
//            }
//            prevIdx = currentIdx;
//            currentIdx = search;
//        }
//        if (prevIdx == currentIdx) {
//            return prevIdx;
//        }
//
//    }
    public static int findIndexX(ArrayList<Mushroom> mushrooms, int x) {
        if (x <= mushrooms.get(0).getX()) {
            return 0;
        }
        if (x >= mushrooms.get(mushrooms.size() - 1).getX()) {
            return mushrooms.size();
        }
        for (int i = 1; i < mushrooms.size(); i++) {
            Mushroom prevMushroom = mushrooms.get(i - 1);
            Mushroom mushroom = mushrooms.get(i);
            if (prevMushroom.getX() <= x && x <= mushroom.getX()) {
                return ((2 * i - 1) / 2) + 1;
            }
        }

        // unreachable
        return -1;
    }
}
