package threee.battlesheeps;
//OZNACZENIA NA PLANSZY:
// -- na starcie:
// 0-> pole, na ktorym nie ma owcy i nikt jeszcze w nie nie celowal
// 1-> pole stada pojedynczej owcy
// 2-> pole podwójnego stada
// 3-> pole potrójnego stada
// 4-> pole poczwórnego stada

// -- oznaczane podczas gry juz:
// 5-> pole, na ktorym nie ma owcy i wilk juz wie, ze nie bedzie (bo sasiednie do odkrytego stada
// lub juz o nie pytal i wie, ze pudlo
// 6-> pole, na ktorym jest owca i jest ono juz odkryte


public class Table
{
	//friend class Gra; - chyba i tak sa zaprzyjaznione, bo w jednym pakiecie, to java juz ich kumpluje

	public static int size;
	//int Kratka [10][10];
	//int [][] Kratka = new int [s][s];
	int [][] Kratka;

	public Table(int s)
	{
		size=s;
		Kratka = new int [s][s];
		for(int g=0;g<s;g++)
		{
			for(int h=0;h<s;h++)
			{
				Kratka[g][h]=0;
				if (g==0 || g==size-1) Kratka[g][h]=5;
				if (h==0 || h==size-1) Kratka[g][h]=5;
			}
		}
	}
}
