package threee.battlesheeps;

/**
 * Created by Michal on 2018-07-03.
 */
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.*;
import java.io.*;
import java.io.DataOutputStream.*;
import java.io.FilterOutputStream.*;

public class Writer {
    private static int test;

    public static void sendPostRequest(Game game,int test)  {

        try {
            HttpURLConnection conn;
            OutputStream os;
            JSONObject send = game.zamienNaJSON();
            URL url = new URL("http://10.0.2.2:3000/fields/");
            String message = send.toString();
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout( 3000 );
            conn.setConnectTimeout( 3000 );
            conn.setRequestMethod("POST");

                if (test==42)
                {
                    conn.setRequestMethod("PUT");
                }
                else
                {
                    conn.setRequestMethod("POST");
                }
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            conn.connect();
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            os.flush();
            os.close();
            conn.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static class MyTask extends AsyncTask<Game, Void, Void>
    {
        @Override
        protected Void doInBackground(Game... params)
        {
            {

                sendPostRequest(params[0],test);

            }
            return null;
        }
    }



    public static void save(Game dane)
    {
        GameReader czytnik=new GameReader();
        JSONObject testowy=czytnik.load(dane.user);
        try {
            test=testowy.getInt("test");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new MyTask().execute(dane);
    }
}
