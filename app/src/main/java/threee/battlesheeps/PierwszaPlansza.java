package threee.battlesheeps;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

import org.json.JSONException;
import org.json.JSONObject;

public class PierwszaPlansza extends AppCompatActivity {

    private Button B_0_0;
    private Button B_8x8;
    private Button B_10x10;
    private Button B_1_7;
    private Button B_4_1;
    private EditText user;
    private TextView info, nazwauzyt;
    private int test, startingGame, width, timeout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pierwsza_plansza);

        //Flaga do sterowania przyciskiem
        startingGame=0;

        //Szrokosc
        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);
        width = screenSize.x;

        B_0_0 = (Button) findViewById(R.id.button);
        B_0_0.setText("Nowa gra");
        B_0_0.setWidth(width/3);
        B_1_7 = (Button) findViewById(R.id.button2);
        B_1_7.setText("Wczytaj grę");
        B_1_7.setWidth(width/3);
        B_8x8 = (Button) findViewById(R.id.button4);
        B_8x8.setText("8 x 8");
        B_8x8.setVisibility(View.INVISIBLE);
        B_10x10 = (Button) findViewById(R.id.button3);
        B_10x10.setText("10 x 10");
        B_10x10.setVisibility(View.INVISIBLE);
        nazwauzyt=(TextView)findViewById(R.id.textView);
        nazwauzyt.setText("Nazwa użytkownika:");
        info = (TextView) findViewById(R.id.textView3);
        info.setTextSize(16);
        info.setText("Podaj nazwę użytkownika by rozpocząć.");
        //B_4_1 = (Button) findViewById(R.id.button3);
        //B_4_1.setText("Return to game");
        user = (EditText)  findViewById(R.id.editText);
        //B_4_1.setClickable(false);

        //przycisk odpala nowa gra na planszy 10x10
        B_8x8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame(8);
            }
        });

        //przycisk odpala nowa gra na planszy 10x10
        B_10x10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame(10);
            }
        });

        //patrz metoda starGame
        B_0_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        //przycik wczytuje gre
        B_1_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadGame(10, "Player");
            }
        });
    }

    //pokazuje przyciski wyboru wielkosci planszy i odpala nowa gre 9x9
    private void startGame() {
        if (startingGame==1)
        {
            newGame(9);
        }
        else if (poprawnaNazwaNowaGra()==0)
        {
            startingGame=1;
            B_0_0.setText("9 x 9");
            B_0_0.setWidth(width/4);
            B_8x8.setWidth(width/4);
            B_10x10.setWidth(width/4);
            B_8x8.setVisibility(View.VISIBLE);
            B_10x10.setVisibility(View.VISIBLE);
            info.setText("Wybierz wielkość planszy.");
            user.setVisibility(View.INVISIBLE);
            B_1_7.setVisibility(View.INVISIBLE);
            nazwauzyt.setVisibility(View.INVISIBLE);
        }

    }

    //tworzy nowa gre
    private void newGame(int size)
    {
        //Porzodkuje bo potem wracam do tej aktywnosci
        B_0_0.setText("Nowa gra");
        B_0_0.setWidth(width/3);
        B_8x8.setVisibility(View.INVISIBLE);
        B_10x10.setVisibility(View.INVISIBLE);
        info.setText("Podaj nazwę użytkownika by rozpocząć.");
        user.setVisibility(View.VISIBLE);
        B_1_7.setVisibility(View.VISIBLE);
        nazwauzyt.setVisibility(View.VISIBLE);
        startingGame=0;

        //tworze nowa aktywnosc
        Intent i = new Intent(this, PlanszaGry.class);
        i.putExtra("size", size);
        i.putExtra("user",user.getText().toString());
        i.putExtra("load", 0);
        startActivity(i);
    }

    //wczytuje gre
    private void loadGame(int size, String playerName) {
        if (poprawnaNazwaWczytanaGra()==0) {
            Intent i = new Intent(this, PlanszaGry.class);
            i.putExtra("size", size);
            i.putExtra("user", user.getText().toString());
            i.putExtra("load", 1);
            startActivity(i);
        }
    }

    //sprawdza ogolna poprawnosc nazwy uzytkownika
    private int poprawnaNazwa()
    {
        int werdykt=0;
        if (user.getText().toString().length()<3)
        {
            info.setText("Nazwa użytkownika za krótka!");
            return 1;
        }
        return werdykt;
    }

    //sprawdza popawnosc nazwy uzytkownika pod katem nowej gry
    private int poprawnaNazwaNowaGra()
    {
        int werdykt=0;
        if (poprawnaNazwa()==1) return 1;
        return werdykt;
    }

    //sprawdza poprawnosc nazwy uzytkownika pod katem wczytania gry
    private int poprawnaNazwaWczytanaGra()
    {
        int werdykt=0;
        if (poprawnaNazwa()==1) return 1;

        GameReader czytnik=new GameReader();
        JSONObject testowy=czytnik.load(user.getText().toString());
        try {
            test=testowy.getInt("test");
            timeout=testowy.getInt("timeout");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (test==20 && timeout==1)
        {
            info.setText("Brak połączenia z serwerem.");
            return 1;
        }
        if (test==20 )
        {
            info.setText("Brak zapisu gry dla podanego użytkownika.");
            return 1;
        }
        return werdykt;
    }


}