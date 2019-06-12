package threee.battlesheeps;

public class Fourth extends Ship
{
    public
    Fourth(int liczba)
    {
        //liczba_pol=liczba;
        pozostale_owce=liczba;
    }

    void tworzenie(int K[][])
    {
        ///Pierwsze pole
        ///nie trzeba while'a, bo to pierwsze pole, jakie wgl zostanie wybrane - zaczynamy od cztromasztowca cale losowanie
        n=rand.nextInt(Table.size);
        m=rand.nextInt(Table.size);
        fixmn();
        K[n][m]=4;

        ///Drugie pole: warunek nie stapniecia na wybrane juz pole i zeby wybrac sasiada ow pola koniecznie
        do
        {
            p=n-1+rand.nextInt(n+1-(n-1)+1);
            r=m-1+rand.nextInt(m+1-(m-1)+1);
            fixpr();
        }
        while(K[p][r]!=0 ||
                (p==n+1 && r==m+1) || (p==n+1 && r==m-1) || (p==n-1 && r==m+1) || (p==n-1 && r==m-1)||
                n<0 || n>s-1 || m<0 || m>s-1 ||
                (K[p-1][r-1]!=0 && K[p-1][r-1]!=4) || (K[p][r-1]!=0 && K[p][r-1]!=4) || (K[p+1][r-1]!=0 && K[p+1][r-1]!=4) || (K[p-1][r]!=0 && K[p-1][r]!=4) || (K[p-1][r+1]!=0 && K[p-1][r+1]!=4) || (K[p][r+1]!=0 && K[p][r+1]!=4) || (K[p+1][r+1]!=0 && K[p+1][r+1]!=4) || (K[p+1][r]!=0 && K[p+1][r]!=4));
        /// dodatkowy warunek - nie moga byc wybrane po ukosie
        K[p][r]=4;

        /// Trzecie pole:
        do
        {
            n=p-1 + rand.nextInt(p+1-(p-1)+1);
            m=r-1+rand.nextInt(r+1-(r-1)+1);
            fixmn();
        }
        while(K[n][m]!=0 || (p==n+1 && r==m+1) || (p==n+1 && r==m-1) || (p==n-1 && r==m+1) || (p==n-1 && r==m-1)||
                n<0 || n>s-1 || m<0 || m>s-1 ||
                (K[n-1][m-1]!=0 && K[n-1][m-1]!=4) || (K[n][m-1]!=0 && K[n][m-1]!=4) || (K[n+1][m-1]!=0 && K[n+1][m-1]!=4) || (K[n-1][m]!=0 && K[n-1][m]!=4) || (K[n-1][m+1]!=0 && K[n-1][m+1]!=4) || (K[n][m+1]!=0 && K[n][m+1]!=4) || (K[n+1][m+1]!=0 && K[n+1][m+1]!=4) || (K[n+1][m]!=0 && K[n+1][m]!=4));
        K[n][m]=4;

        ///Czwarte pole:
        do
        {
            p=n-1 + rand.nextInt(n+1-(n-1)+1);
            r=m-1+rand.nextInt(m+1-(m-1)+1);
            fixpr();
        }
        while(K[p][r]!=0 ||
                (p==n+1 && r==m+1) || (p==n+1 && r==m-1) || (p==n-1 && r==m+1) || (p==n-1 && r==m-1)||
                n<0 || n>s-1 || m<0 || m>s-1 ||
                (K[p-1][r-1]!=0 && K[p-1][r-1]!=4) || (K[p][r-1]!=0 && K[p][r-1]!=4) || (K[p+1][r-1]!=0 && K[p+1][r-1]!=4) || (K[p-1][r]!=0 && K[p-1][r]!=4) || (K[p-1][r+1]!=0 && K[p-1][r+1]!=4) || (K[p][r+1]!=0 && K[p][r+1]!=4) || (K[p+1][r+1]!=0 && K[p+1][r+1]!=4) || (K[p+1][r]!=0 && K[p+1][r]!=4));
        K[p][r]=4;
    }

    public
    void trafienie()
    {
        pozostale_owce--;
        if(pozostale_owce==0)
        {
            //SetConsoleTextAttribute( kolor, 10 );
            //cout<<"Czteromasztowy trafiony zatopiony."<<endl;
            //System.out.println("Stado czterech owiec odkryte.");
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

