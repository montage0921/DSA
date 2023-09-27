import java.util.Arrays;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;


public class  Sorting <T extends Comparable<T>> {
    public static <T extends Comparable<T>> void selectionSort(T[] data) {

        for (int i = 0; i < data.length; i++) {
            T min = data[i];
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j].compareTo(min) < 0) {
                    min = data[j];
                    minIndex = j;
                }
            }
            swap(data, i, minIndex);
        }
    }

    private static <T extends Comparable<T>> void swap(T[] data, int a, int b) {
        T temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }


    public static <T extends Comparable<T>> void insertionSort(T[] data) {
        T temp;
        for (int i = 1; i < data.length; i++) {
            temp = data[i];
            int j = i - 1;
            while (j >= 0 && temp.compareTo(data[j]) < 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = temp;
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] data) {
        int length = data.length;

        while (length > 0) { //using length>=0 works same as length>0 but would go through an unnecessary outer loop iteration of length=-1
            for (int i = 0; i < length - 1; i++) {
                if (data[i + 1].compareTo(data[i]) < 0) {
                    swap(data, i, i + 1);
                }
            }
            length--;
        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] data, int lowIndex, int highIndex) {

        //recursive basic:
        //when it's a single element, it's automatically sorted, then the recursion can stop.
        if (lowIndex >= highIndex) return;

        //1. CHOOSE THE PIVOT
        int pivotIndex = (highIndex + lowIndex) / 2;
        T pivot = data[pivotIndex];

        //swap the pivot with the last number in the range
        //(it's better to put pivot at the END...)
        swap(data, pivotIndex, highIndex);

        //2. PARTITION

        int lp = partition(data, lowIndex, highIndex, pivot);


        //3. SORTING LEFT AND RIGHT PART OF PIVOT RECURSIVELY
        quickSort(data, lowIndex, lp - 1);
        quickSort(data, lp + 1, highIndex);


    }

    private static <T extends Comparable<T>> int partition(T[] data, int lowIndex, int highIndex, T pivot) {
        //move everything smaller than pivot to left of the pivot
        //move everything bigger than pivot to right of the pivot
        //using two pointers: left pointer and right pointer
        int lp = lowIndex;
        int rp = highIndex;

        //when two pointers meet, we can stop the loop. The location
        //where they meet also indicates where the partition element (which isn’t moved
        //from its initial location until the end) will ultimately reside.
        while (lp < rp) {

            //keep going until find an element bigger than pivot
            while (data[lp].compareTo(pivot) <= 0 && lp < rp) {
                lp++;
            }

            //keep going until find an element smaller than pivot
            while (data[rp].compareTo(pivot) >= 0 && lp < rp) {
                rp--;
            }

            swap(data, lp, rp);
        }


        swap(data, lp, highIndex);

        return lp;
    }

    public static <T extends Comparable<T>> void mergeSort(T[] data) {
        int dataLength = data.length;
        if (dataLength < 2) return; //data is a single-element array or empty array

        int mid = dataLength / 2;

        //way to create generic type array.
        T[] leftPart = (T[]) (new Comparable[mid]);
        T[] rightPart = (T[]) (new Comparable[dataLength - mid]);

        //move left part and right part into new array.
        for (int i = 0; i < mid; i++) {
            leftPart[i] = data[i];
        }

        for (int i = mid; i < dataLength; i++) {
            rightPart[i - mid] = data[i];
        }

        //keep mering left and right parts
        mergeSort(leftPart);
        mergeSort(rightPart);

        //then do merge sort of them;
        merge(data, leftPart, rightPart);

    }

    private static <T extends Comparable<T>> void merge(T[] data, T[] leftPart, T[] rightPart) {
        int i = 0, j = 0, k = 0;

        while (i < leftPart.length && j < rightPart.length) {

            if (leftPart[i].compareTo(rightPart[j]) <= 0) {
                data[k] = leftPart[i];
                i++;
            } else {
                data[k] = rightPart[j];
                j++;
            }

            k++;

        }

        if (i < leftPart.length) {
            while (i < leftPart.length) {
                data[k] = leftPart[i];
                k++;
                i++;
            }
        } else if (j < rightPart.length) {
            while (j < rightPart.length) {
                data[k] = rightPart[j];
                k++;
                j++;
            }
        }

    }


    public static void main(String[] args) {
//        Integer[] data={9,6,8,12,3,1,7};
//
//        Sorting.<Integer>mergeSort(data);
//
//        System.out.println(Arrays.toString(data));

            /**
             * Performs a radix sort on a set of numeric values.
             */

                int[] list = {7843, 4568, 8765, 6543, 7865, 4532, 9987, 3241,
                        6589, 6622, 1211};
                String temp;
                Integer numObj;
                int digit, num;

                //create a queue array digitQueues which can contain 10 linked lists
                //linked list in java implements queue interface default.
                //(LinkedList<Integer>[]): a cast to make sure the linked list only store integers.
                Queue<Integer>[] digitQueues = (LinkedList<Integer>[])(new LinkedList[10]);

                //put a link list in each position of the queue array.
                for (int digitVal = 0; digitVal <= 9; digitVal++)
                    digitQueues[digitVal] = (Queue<Integer>)(new LinkedList<Integer>());

                // sort the list
                //there are 4 digits so position from 0 to 3
                //each outer loop is for one digit
                for (int position=0; position <= 3; position++)
                {
                    //inner loop: put value into linked list at position in the queue array
                    // which is same as the value of its current digit.

                    for (int scan=0; scan < list.length; scan++){
                        temp = String.valueOf(list[scan]); //change integer to string so we can get its digits
                        digit = Character.digit(temp.charAt(3-position), 10); //get digit at specific position and radix
                        digitQueues[digit].add(new Integer(list[scan]));
                    }
                    // gather numbers back into list
                    num = 0;
                    //this loop put numbers back into original list
                    //the list here acts as temporary storage
                    for (int digitVal = 0; digitVal <= 9; digitVal++)
                    {
                        while (!(digitQueues[digitVal].isEmpty()))
                        {
                            numObj = digitQueues[digitVal].remove();
                            //the stored value is string digit, convert it back to integer
                            list[num] = numObj.intValue();
                            num++;
                        }
                    }
                }
                // output the sorted list
                for (int scan=0; scan < list.length; scan++)
                    System.out.println(list[scan]);
            }
        }




