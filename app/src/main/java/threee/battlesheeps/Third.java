package threee.battlesheeps;

public class Third extends Ship
{
    public
    Third(int liczba)
    {
        //liczba_pol=liczba;
        pozostale_owce=liczba;
    }


    void tworzenie(int K[][])
    {
        ///Pierwsze pole
        do
        {
            n=rand.nextInt(Table.size);
            m=rand.nextInt(Table.size);
            fixmn();
        }
        while(K[n][m]!=0 ||
                (K[n-1][m-1]!=0 && K[n-1][m-1]!=3) || (K[n][m-1]!=0 && K[n][m-1]!=3) || (K[n+1][m-1]!=0 && K[n+1][m-1]!=3) || (K[n-1][m]!=0 && K[n-1][m]!=3) || (K[n-1][m+1]!=0 && K[n-1][m+1]!=3) || (K[n][m+1]!=0 && K[n][m+1]!=3) || (K[n+1][m+1]!=0 && K[n+1][m+1]!=3) || (K[n+1][m]!=0 && K[n+1][m]!=3));
        K[n][m]=3;

        ///Drugie pole: warunek nie stapniecia na wybrane juz pole i zeby wybrac sasiada ow pola koniecznie
        do
        {
            p=n-1 + rand.nextInt(n+1-(n-1)+1);
            r=m-1+rand.nextInt(m+1-(m-1)+1);
            fixpr();

        }
        while(K[p][r]!=0 ||
                (p==n+1 && r==m+1) || (p==n+1 && r==m-1) || (p==n-1 && r==m+1) || (p==n-1 && r==m-1)||
                n<0 || n>s-1 || m<0 || m>s-1 ||
                (K[p-1][r-1]!=0 && K[p-1][r-1]!=3) || (K[p][r-1]!=0 && K[p][r-1]!=3) || (K[p+1][r-1]!=0 && K[p+1][r-1]!=3) || (K[p-1][r]!=0 && K[p-1][r]!=3) || (K[p-1][r+1]!=0 && K[p-1][r+1]!=3) || (K[p][r+1]!=0 && K[p][r+1]!=3) || (K[p+1][r+1]!=0 && K[p+1][r+1]!=3) || (K[p+1][r]!=0 && K[p+1][r]!=3));
        /// dodatkowy warunek - nie moga byc wybrane po ukosie
        K[p][r]=3;

        /// Trzecie pole:
        do
        {
            n=p-1 + rand.nextInt(p+1-(p-1)+1);
            m=r-1+rand.nextInt(r+1-(r-1)+1);
            fixmn();
        }
        while(K[n][m]!=0 ||
                (p==n+1 && r==m+1) || (p==n+1 && r==m-1) || (p==n-1 && r==m+1) || (p==n-1 && r==m-1)||
                n<0 || n>s-1 || m<0 || m>s-1 ||
                (K[n-1][m-1]!=0 && K[n-1][m-1]!=3) || (K[n][m-1]!=0 && K[n][m-1]!=3) || (K[n+1][m-1]!=0 && K[n+1][m-1]!=3) || (K[n-1][m]!=0 && K[n-1][m]!=3) || (K[n-1][m+1]!=0 && K[n-1][m+1]!=3) || (K[n][m+1]!=0 && K[n][m+1]!=3) || (K[n+1][m+1]!=0 && K[n+1][m+1]!=3) || (K[n+1][m]!=0 && K[n+1][m]!=3));
        K[n][m]=3;
    }

    public

        //void trafienie();
    void trafienie()
    {
        pozostale_owce--;
        //if(!pozostale_maszty)
        if(pozostale_owce==0)
        {
            //SetConsoleTextAttribute( kolor, 10 );
            //System.out.println("Stado trzech owiec odkryte.");
            //Beep(600,300);
            //Beep(600,300);
        }
        else
        {
            //SetConsoleTextAttribute( kolor, 2 );
            //System.out.println("Trafiona");
            //Beep(600,900);
        }
    }
}