public class P6{
    static int floor(int[] a,int x){
        int l=0,h=a.length-1,res=-1;
        while(l<=h){
            int m=(l+h)/2;
            if(a[m]<=x){res=a[m];l=m+1;}
            else h=m-1;
        }
        return res;
    }

    static int ceil(int[] a,int x){
        int l=0,h=a.length-1,res=-1;
        while(l<=h){
            int m=(l+h)/2;
            if(a[m]>=x){res=a[m];h=m-1;}
            else l=m+1;
        }
        return res;
    }

    public static void main(String[] args){
        int[] a={10,25,50,100};
        System.out.println("Floor:"+floor(a,30));
        System.out.println("Ceil:"+ceil(a,30));
    }
}