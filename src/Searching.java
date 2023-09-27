public class Searching <T extends Comparable<T>> {
    public static <T> boolean linearSearching(T[] data,int min, int max, T target){
        boolean found=false;
        int index=min;
        while(!found && index<=max){
            found=data[index].equals(target);
            index++;
        }

        return found;
    }

    public static <T extends Comparable<T>> boolean binarySearching(T[] data, int min, int max, T target){

        int mid=(min+max)/2;
        int comparedResult=data[mid].compareTo(target);

        if(min>max) return false;
        else if(comparedResult==0) return true;
        else if(comparedResult>0){
            max=mid-1;
            return binarySearching(data,min,max,target);
        } else if(comparedResult<0){
            min=mid+1;
            return binarySearching(data,min,max,target);
        }

        return false;
    }



    public static void main(String[] args){
        Integer[] data={10,12,18,22,31,34,40,46,59,67,69,72,80,84,98};
        boolean result=Searching.<Integer>binarySearching(data,0,data.length-1,10);
        System.out.println(result);



    }
}
