class Asset{
    String name; double ret;
    Asset(String n,double r){name=n;ret=r;}
}

public class P4{
    static void mergeSort(Asset[] a,int l,int r){
        if(l>=r)return;
        int m=(l+r)/2;
        mergeSort(a,l,m);
        mergeSort(a,m+1,r);

        Asset[] t=new Asset[r-l+1];
        int i=l,j=m+1,k=0;

        while(i<=m && j<=r)
            t[k++]=a[i].ret<=a[j].ret?a[i++]:a[j++];
        while(i<=m)t[k++]=a[i++];
        while(j<=r)t[k++]=a[j++];

        for(i=0;i<t.length;i++) a[l+i]=t[i];
    }

    public static void main(String[] args){
        Asset[] a={
            new Asset("AAPL",12),
            new Asset("TSLA",8),
            new Asset("GOOG",15)
        };

        mergeSort(a,0,a.length-1);
        for(Asset x:a)
            System.out.println(x.name+" "+x.ret);
    }
}