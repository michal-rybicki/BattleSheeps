package threee.battlesheeps;

import java.util.Random;

public class Ship
{
	//protected int liczba_pol;
	protected int pozostale_owce;
	public static int s;// rozmiar planszy
	//protected boolean odgadniety; /// czy wgl potrzebne
	protected int p;
	protected int r;
	protected int m;//pierwsza wspolrzedna na planszy
	protected int n;// druga wspolrzedna na planszy
	protected Random rand = new Random();//moze starczy dla wszystkich pochodnych xd

	protected void fixmn()
	{
		if (n==0) n=1;
		if (n==s-1) n=s-2;
		if (m==0) m=1;
		if (m==s-1) m=s-2;
	}
	protected void fixpr()
	{
		if (p==0) p=1;
		if (p==s-1) p=s-2;
		if (r==0) r=1;
		if (r==s-1) r=s-2;
	}
}