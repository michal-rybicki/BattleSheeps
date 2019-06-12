package threee.battlesheeps;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PlanszaGry extends AppCompatActivity {

    Game gra;
    CheckBox tile; // kratki do odkrycia jako checkbox. stan początkowy odznaczony jako nieodkryte, zaznaczony jako odkryte. ustawianie parametru tag
    // do sprawdzenia czy znajduje się owca w danej kratce
    int size, i, j; // rozmiar planszy
    String user; //nazwa uzytkownika
    HashMap<String, Integer> bushSize; // wysokość, szerokość dla obrazka
    GridLayout gridLayout; // layout
    //TextView playerScoreText, wolfScoreText, playerScore, wolfScore, playerHitType, wolfHitType, playerHit, wolfHit;
    TextView
            playerScoreText,
            wolfScoreText,
            playerScoreValue,
            wolfScoreValue,
            playerHitType,
            wolfHitType,
            playerHit,
            wolfHit,
            winner,
            info;
    //LinearLayout playerInfo, wolfInfo, mainView, guziki;
    LinearLayout playerInfo, playerScore, playerHitInfo, wolfInfo, wolfScore, wolfHitInfo, finishInfo, mainView, guziki,informacje;
    Button zapiszButton, menuButton;
    int load, test;
    String playerColor;
    String wolfColor;
    int fixed[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plansza_gry);
        playerColor="#8A8A8A";
        wolfColor="#C8533E";
        mainView = findViewById(R.id.gameTable);
        mainView.setOrientation(LinearLayout.VERTICAL);

        gridLayout = new GridLayout(this); // pobieranie layoutu
        gridLayout.setBackgroundColor(Color.parseColor("#d2d2d2"));
        gridLayout.setOrientation(GridLayout.HORIZONTAL);
        tile = findViewById(R.id.bushTile); // pobieranie obiektu kratki
        size = getIntent().getIntExtra("size", 0); // pobieranie rozmiaru z poprzedniej aktywności
        user = getIntent().getStringExtra("user"); //pobieranie nazwy uzytkownika
        load = getIntent().getIntExtra("load",0);//1-wczytujemy gre, 0-nowa gra
        fixed=new int[size+1][size+1];


        //wyswietlanie punktow
        playerInfo = new LinearLayout(this);
        playerHitInfo = new LinearLayout(this);
        wolfInfo = new LinearLayout(this);
        wolfHitInfo = new LinearLayout(this);

        finishInfo = new LinearLayout(this);

        playerInfo.setOrientation(LinearLayout.VERTICAL);
        playerHitInfo.setOrientation(LinearLayout.HORIZONTAL);
        wolfInfo.setOrientation(LinearLayout.VERTICAL);
        wolfHitInfo.setOrientation(LinearLayout.HORIZONTAL);

        playerScore = new LinearLayout(this);
        wolfScore = new LinearLayout(this);
        playerScore.setOrientation(LinearLayout.HORIZONTAL);
        wolfScore.setOrientation(LinearLayout.HORIZONTAL);



        playerScoreText = new TextView(this);
        playerScoreValue = new TextView(this);
        playerHitType = new TextView(this);

        wolfScoreText = new TextView(this);
        wolfScoreValue = new TextView(this);
        wolfHitType = new TextView(this);

        wolfHit = new TextView(this);
        playerHit = new TextView(this);

        //wyswietlanie zwyciezcy
        winner = new TextView(this);
        winner.setTextSize(24);


        playerScoreText.setText(R.string.playerScoreText);
        wolfScoreText.setText(R.string.wolfScoreText);
        playerScoreText.setTextSize(20);
        playerScoreText.setTextColor(Color.parseColor("#FFFFFF"));
        wolfScoreText.setTextSize(20);
        wolfScoreText.setTextColor(Color.parseColor("#FFFFFF"));

        playerScoreValue.setText(" 0");
        playerScoreValue.setTextSize(20);
        playerScoreValue.setTextColor(Color.parseColor("#FFFFFF"));
        wolfScoreValue.setText(" 0");
        wolfScoreValue.setTextSize(20);
        wolfScoreValue.setTextColor(Color.parseColor("#FFFFFF"));

        wolfHit.setText(R.string.hitType);
        wolfHit.setTextColor(Color.parseColor(wolfColor));
        playerHit.setText(R.string.hitType);
        playerHit.setTextColor(Color.parseColor(playerColor));

        //playerInfo.setBackgroundColor(0xFF00FF00);
        playerInfo.setBackgroundColor(Color.parseColor(playerColor));

        playerInfo.addView(playerScore);
        playerInfo.addView(playerHitInfo);
        playerScore.addView(playerScoreText);
        playerScore.addView(playerScoreValue);
        playerHitInfo.addView(playerHit);
        playerHitInfo.addView(playerHitType);


        wolfInfo.setBackgroundColor(Color.parseColor(wolfColor));
        wolfInfo.addView(wolfScore);
        wolfInfo.addView(wolfHitInfo);
        wolfScore.addView(wolfScoreText);
        wolfScore.addView(wolfScoreValue);
        wolfHitInfo.addView(wolfHit);
        wolfHitInfo.addView(wolfHitType);

        mainView.addView(gridLayout);
        mainView.addView(playerInfo);
        mainView.addView(wolfInfo);
        //finishInfo.addView(winner);
        finishInfo.setGravity(1);
        mainView.addView(finishInfo);

        //guziki na dole
        guziki = new LinearLayout(this);
        guziki.setOrientation(LinearLayout.HORIZONTAL);
        guziki.setHorizontalGravity(1);
        mainView.addView(guziki);
        mainView.setBackgroundColor(Color.parseColor("#d2d2d2"));

        //informacja o zapisie
        informacje = new LinearLayout(this);
        informacje.setOrientation(LinearLayout.HORIZONTAL);
        informacje.setHorizontalGravity(1);
        mainView.addView(informacje);
        info= new TextView(this);

        zapiszButton = new Button(this);
        zapiszButton.setText("Zapisz grę");
        zapiszButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GameReader czytnik=new GameReader();
                JSONObject testowy=czytnik.load("test");
                try {
                    test=testowy.getInt("test");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (test==20)
                {
                    playerHit.setText("Brak połączenia z serwerem.");
                    playerHit.setTextColor(Color.parseColor("#FFFFFF"));
                    playerHitType.setText(" ");
                }
                else {
                    gra.saveToServer();
                    playerHit.setText("Zapis udany.");
                    playerHit.setTextColor(Color.parseColor("#FFFFFF"));
                    playerHitType.setText(" ");

                }
            }
        });

        menuButton = new Button(this);
        menuButton.setText("Wyjdź do menu");
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        guziki.addView(zapiszButton);
        //guziki.addView(menuButton);
        informacje.addView(info);

        gra = new Game(size, user);
        if (load==1)
        {
            gra.loadFromServer();
            size=gra.s-2;
        }
        /*
        * Pobieranie informacji o rozmiarze wyświetlacza, żeby móc na podstawie tego ustawić odpowiednią szerokość i wysokość obrazka kratki
        * */
        Display display = getWindowManager().getDefaultDisplay();
        Point screenSize = new Point();
        display.getSize(screenSize);
        Integer width = screenSize.x;
        Integer height = screenSize.y;

        i = 1;
        j = 1;


        // Hashmapa z ustawionymi wielkościami obrazka
        bushSize = new HashMap<>();
        bushSize.put("width", width/size);
        bushSize.put("height", height/size);
        menuButton.setWidth(width/3);
        zapiszButton.setWidth(width/3);
        final int[][] tilesIds = new int[size+1][size+1]; // tablica z View.id kratek

        // parametry layoutu
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        gridLayout.setColumnCount(size);
        gridLayout.setRowCount(size);

        //wczytywanie wynikow
        if (load==1)
        {
            playerScoreValue.setText(" "+String.valueOf(gra.UserScore));
            wolfScoreValue.setText(" "+String.valueOf(gra.WolfScore));
        }

        for (i = 1; i <= size; i++) {
            for(j = 1; j <= size; ++j){
                CheckBox tile = new CheckBox(this); // tworzenie "kratki" do dodania do layoutu
                tilesIds[i][j] = View.generateViewId(); // generowanie id kratek
                fixed[i][j]=0;

                // ustawianie szerokości, wysokości, obrazka, id, tagu dla kratki
                tile.setWidth(bushSize.get("width"));
                tile.setHeight(bushSize.get("height"));
                tile.setButtonDrawable(getDrawable(R.drawable.bush));
                if (load==1)
                {
                    switch (gra.savedSheeps[i][j])
                    {
                        //wczytywanie stanu planszy
                        case 1: tile.setButtonDrawable(getDrawable(R.drawable.sheep)); break;
                        case 2: tile.setButtonDrawable(getDrawable(R.drawable.sheep_killed)); break;
                        case 3: tile.setButtonDrawable(getDrawable(R.drawable.bush_checked)); break;
                        case 4: tile.setButtonDrawable(getDrawable(R.drawable.bush_wolf)); break;
                    }
                    if (gra.savedSheeps[i][j]!=0)
                    {
                        tile.setClickable(false);
                        fixed[i][j]=1;
                    }

                }
                tile.setId(tilesIds[i][j]);
                tile.setTag("tile_"+i); // tutaj będzie if który ustawia na podstawie informacji z klasy czy pole zawiera owcę
                if(gra.startingBoard[i][j] != 0) {
                    tile.setTag(gra.startingBoard[i][j]);
                } else {
                    tile.setTag("0");
                }

                gridLayout.addView(tile); // dodawanie kratki do layoutu

                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.setGravity(Gravity.TOP);
                tile.setLayoutParams(param);
            }
        }
//
        for (int y = 1; y <= size; ++y){
            for(int z = 1; z <= size; ++z){
                final CheckBox playerTileHit = findViewById(tilesIds[y][z]); // pobieranie kratki do listenera
                final int w = y;
                final int x = z;
                final int[][] tilesIds2 = new int[size+1][size+1]; // tablica idków kratek żeby można było użyć jej w funkcji poniżej

                for(int e = 1; e <= size; ++e){
                    for(int f = 1; f <= size; ++f)
                    {
                        tilesIds2[e][f] = tilesIds[e][f]; // zapis idków do nowej tablicy
                    }
                }

                playerTileHit.setOnClickListener(new View.OnClickListener() { // obsługa klikania w kartkę
                    @Override
                    public void onClick(View v) {
//                        Log.i("tileId", String.valueOf(playerTileHit.getTag()));
//                        Log.i("checked", "true");

                        if(gra.WolfScore + gra.UserScore < 10) {
                            playerTileHit.setChecked(true);
                            gra.playerMove(w,x); // sypie błędem
                            fixed[w][x]=1;


                            // jeżeli kratka:
                            // jest owcą,
                            // nie jest zabitą,
                            // znalezioną przez gracza i wilka,
                            // to odsłaniamy owcę i doliczamy punkty za znalezienie i ustawiamy tag na znalezioną
                            //if(playerTileHit.getTag() != "killed" && playerTileHit.getTag() != "0" && playerTileHit.getTag() != "playerReveal" && playerTileHit.getTag() != "wolfReveal" && playerTileHit.getTag() != "found") {
                            if(gra.savedSheeps[w][x]==1){
                                //playerHitType.setText(" "+String.valueOf(playerTileHit.getTag())+".");
                                playerHit.setText(R.string.hitType);
                                playerHitType.setText(" "+String.valueOf(gra.startingBoard[w][x])+".");
                                playerHitType.setTextColor(Color.parseColor("#FFFFFF"));
                                playerHit.setTextColor(Color.parseColor("#FFFFFF"));
                                playerScoreValue.setText(" "+String.valueOf(gra.UserScore));
                                playerTileHit.setButtonDrawable(getDrawable(R.drawable.sheep)); // zmiana obrazka na odkryte miejsce. docelowo if ze sprawdzeniem czy jest owca
                                playerTileHit.setTag("found");
                                playerTileHit.setClickable(false);
                            }

                            // jeżeli kratka:
                            // nie jest owcą, nie jest oznaczona jako znaleziona, nie została odkryta przez gracza i wilka, to zaznacza pole jako sprawdzone przez gracza
                            //if(
                                //playerTileHit.getTag() != "wolfReveal" &&
                                //playerTileHit.getTag() != "playerReveal" &&
                                //playerTileHit.getTag() == "0" &&
                                //playerTileHit.getTag() != "found" &&
                                //playerTileHit.getTag() != "killed"
                                //) {
                                if(gra.savedSheeps[w][x]==3){
                                playerTileHit.setButtonDrawable(getDrawable(R.drawable.bush_checked)); // zmiana obrazka na odkryte miejsce. docelowo if ze sprawdzeniem czy jest owca
                                playerTileHit.setTag("playerReveal");
                                playerTileHit.setClickable(false);
                            }

                            // sprawdzanie czy wilk nie wykonał ruchu na tej samej kratce co przed chwilą gracz i czy owca jest znaleziona lub kratka odsłonięta przez gracza inaczej wilk ma pustą turę
    //                        CheckBox playerAfterHit;
                            int xWolf, yWolf;
                            gra.wolfMove();
                            xWolf = gra.liczba1;
                            yWolf = gra.liczba2;
//                            Log.i("wolfHit", "x: " + String.valueOf(gra.liczba1) + ", y: " + String.valueOf(gra.liczba2));

                            CheckBox wolfTileHit = findViewById(tilesIds2[xWolf][yWolf]);
                            int p = 0;
                            //if (wolfTileHit.getTag() != "found" && wolfTileHit.getTag() != "0" && wolfTileHit.getTag() != "playerReveal" && wolfTileHit.getTag() != "wolfReveal" && wolfTileHit.getTag() != "killed") {
                            if(gra.savedSheeps[xWolf][yWolf]==2 && fixed[xWolf][yWolf]==0){
                                //wolfHitType.setText(" "+String.valueOf(wolfTileHit.getTag())+".");
                                wolfHitType.setText(" "+String.valueOf(gra.startingBoard[xWolf][yWolf])+".");
                                fixed[xWolf][yWolf]=1;
                                wolfHit.setTextColor(Color.parseColor("#FFFFFF"));
                                wolfHitType.setTextColor(Color.parseColor("#FFFFFF"));
                                wolfScoreValue.setText(" "+String.valueOf(gra.WolfScore));
                                wolfTileHit.setButtonDrawable(getDrawable(R.drawable.sheep_killed));
                                wolfTileHit.setClickable(false);
                                wolfTileHit.setTag("killed");
                            }

                            //if (wolfTileHit.getTag() != "playerReveal" && wolfTileHit.getTag() == "0" && wolfTileHit.getTag() != "found" && wolfTileHit.getTag() != "killed" && wolfTileHit.getTag() != "wolfReveal") {
                            if(gra.savedSheeps[xWolf][yWolf]==4 && fixed[xWolf][yWolf]==0) {
                                wolfTileHit.setButtonDrawable(getDrawable(R.drawable.bush_wolf));
                                wolfTileHit.setClickable(false);
                                fixed[xWolf][yWolf]=1;
                                wolfTileHit.setTag("wolfReveal");
                            }
                        /*} else {
                            if (gra.UserScore > gra.WolfScore) Log.i("finish", "Player won!");
                            else if(gra.UserScore == gra.WolfScore) Log.i("finish", "Tie!");
                            else Log.i("finish", "Wolf won!");*/
                            if(gra.WolfScore + gra.UserScore == 10)
                            {
                                zapiszButton.setVisibility(View.GONE);
                                finishInfo.addView(winner);
                                //guziki.addView(winner);
                                if (gra.UserScore > gra.WolfScore) {
                                    winner.setText("Wygrałeś!");
                                    winner.setTextColor(Color.parseColor("#000000"));
                                }
                                else if (gra.UserScore == gra.WolfScore) {
                                    winner.setText("Remis!");
                                    winner.setTextColor(Color.parseColor("#000000"));
                                }
                                else{
                                    winner.setText("Przegrałeś :(");
                                    winner.setTextColor(Color.parseColor(wolfColor));
                                }
                            }
                        }
                        //info.setText(String.valueOf(gra.rozdzieloneStada[3]+" "+gra.rozdzieloneStada[4]));
                    }
                });
            }
        }

    }
}
