package threee.battlesheeps;

public class First extends Ship
{
    public
    First(int liczba)
    {
        //liczba_pol=liczba;
        pozostale_owce=liczba;
    }

///Potrzebny tez warunek na nie wyjscie poza tablice, bo juz nie losuje od 0 do 9, a obok wybranego wczesniej pola,
///ktore moglo byc przy krawedzi

    public void  trafienie()
    {
        pozostale_owce--;
        //System.out.println("Stado pojedynczej owcy odkryte.");
    }

    void tworzenie(int K[][])
    {
        ///Pierwsze pole
        do
        {
            n=rand.nextInt(Table.size);
            m=rand.nextInt(Table.size);
            fixmn();
            //K[n-1][m-1]=1;

        }
        while(K[n][m]!=0 ||
                K[n-1][m-1]!=0  || K[n][m-1]!=0  || K[n+1][m-1]!=0  || K[n-1][m]!=0  || K[n-1][m+1]!=0  || K[n][m+1]!=0  || K[n+1][m+1]!=0 || K[n+1][m]!=0 );
        K[n][m]=1;
    }
}
