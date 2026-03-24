class Client{
    String name; int risk;
    Client(String n,int r){name=n;risk=r;}
}

public class P2{
    static void bubble(Client[] a){
        for(int i=0;i<a.length-1;i++)
            for(int j=0;j<a.length-i-1;j++)
                if(a[j].risk>a[j+1].risk){
                    Client t=a[j];a[j]=a[j+1];a[j+1]=t;
                }
    }

    public static void main(String[] args){
        Client[] a={
            new Client("C",80),
            new Client("A",20),
            new Client("B",50)
        };

        bubble(a);
        for(Client c:a)
            System.out.println(c.name+" "+c.risk);
    }
}