import java.util.*;

class Transaction{
    String id; double fee; String ts;
    Transaction(String i,double f,String t){id=i;fee=f;ts=t;}
}

public class P1{
    static void bubble(List<Transaction>a){
        for(int i=0;i<a.size()-1;i++)
            for(int j=0;j<a.size()-i-1;j++)
                if(a.get(j).fee>a.get(j+1).fee)
                    Collections.swap(a,j,j+1);
    }

    public static void main(String[] args){
        List<Transaction> a=new ArrayList<>();
        a.add(new Transaction("id1",10.5,"10:00"));
        a.add(new Transaction("id2",25.0,"09:30"));
        a.add(new Transaction("id3",5.0,"10:15"));

        bubble(a);
        for(Transaction t:a)
            System.out.println(t.id+" "+t.fee);

        System.out.println("Outliers:");
        for(Transaction t:a)
            if(t.fee>50) System.out.println(t.id);
    }
}