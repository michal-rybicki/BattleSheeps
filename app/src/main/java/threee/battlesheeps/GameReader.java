package threee.battlesheeps;

/**
 * Created by Michal on 2018-07-03.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.net.*;
import java.io.*;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

public class GameReader {
    public JSONObject wynik;

    GameReader()
    {
            wynik = new JSONObject();
        try {
            wynik.put("test",20);
            wynik.put("timeout",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject load (String user)
    {
        try {
            wynik = new MyTask2().execute("http://10.0.2.2:3000/fields/"+user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return wynik;
    }

    private static String readAll(Reader rd) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1)
        {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private class MyTask1 extends AsyncTask<String, Void, JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {

            try{
                try {
                    wynik = readJsonFromUrl(params[0]);
                    return wynik;
                }
                catch (IOException e)
                {
                    return wynik;
                }}
            catch (JSONException e)
            {
                return wynik;
            }
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            wynik=json;

        }


    }


    public class MyTask2 extends AsyncTask<String, Void, JSONObject> {

        HttpURLConnection urlConnection;

        @Override
        protected JSONObject doInBackground(String... params) {

            StringBuilder result = new StringBuilder();

            try {
                try{
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout( 3000 );
                urlConnection.setConnectTimeout( 3000 );
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                wynik = new JSONObject(result.toString());}
                catch (java.net.SocketTimeoutException e)
                {
                    wynik.put("timeout",1);
                }
            }catch( Exception e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }


            return wynik;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            wynik=json;

        }

    }
}
