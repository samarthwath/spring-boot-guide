public class Test {
    //[1,2,2,2,2,3]
    //target=2

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 2, 2, 2, 3};
        int targetElement = 2;
        int startPosition = -1;
        int endPosition = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == targetElement && startPosition == -1) {
                //startPosition
                startPosition = i;
                endPosition = i;
            } else if(arr[i]==targetElement && endPosition != i) {
                endPosition=i;
                i++;
            }
        }
        System.out.println(startPosition);
        System.out.println(endPosition);
    }
}
