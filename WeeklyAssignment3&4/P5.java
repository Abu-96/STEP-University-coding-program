import java.util.*;

public class P5{
    static int linear(String[] a,String k){
        for(int i=0;i<a.length;i++)
            if(a[i].equals(k)) return i;
        return -1;
    }

    static int binary(String[] a,String k){
        int l=0,h=a.length-1;
        while(l<=h){
            int m=(l+h)/2;
            if(a[m].equals(k)) return m;
            if(a[m].compareTo(k)<0) l=m+1;
            else h=m-1;
        }
        return -1;
    }

    public static void main(String[] args){
        String[] a={"accB","accA","accB","accC"};
        System.out.println("Linear:"+linear(a,"accB"));
        Arrays.sort(a);
        System.out.println("Binary:"+binary(a,"accB"));
    }
}