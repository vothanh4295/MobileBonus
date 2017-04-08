package com.example.thanhvo.finalbonus;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> listLv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.listView);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJSON().execute("http://apilayer.net/api/live?access_key=d71082f727bea9a06accbe9b79fcb441&currencies=AUD,CAD,CHF,CNY,EUR,GBP,HKD,JPY,NZD,NPJ_DAB,SGD,THB,VND,XAU&source=USD&format=1");
            }
        });
    }

    class docJSON extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            try {
                listLv = new ArrayList<String>();

                JSONObject root = new JSONObject(s);

                JSONObject quotes = root.getJSONObject("quotes");

                listLv.add("USD to AUD");
                listLv.add(quotes.getString("USDAUD"));
                listLv.add("USD to CAD");
                listLv.add(quotes.getString("USDCAD"));
                listLv.add("USD to CHF");
                listLv.add(quotes.getString("USDCHF"));
                listLv.add("USD to CNY");
                listLv.add(quotes.getString("USDCNY"));
                listLv.add("USD to EUR");
                listLv.add(quotes.getString("USDEUR"));
                listLv.add("USD to GBP");
                listLv.add(quotes.getString("USDGBP"));
                listLv.add("USD to HKD");
                listLv.add(quotes.getString("USDHKD"));
                listLv.add("USD to JPY");
                listLv.add(quotes.getString("USDJPY"));
                listLv.add("USD to NZD");
                listLv.add(quotes.getString("USDNZD"));
                listLv.add("USD to SGD");
                listLv.add(quotes.getString("USDSGD"));
                listLv.add("USD to THB");
                listLv.add(quotes.getString("USDTHB"));
                listLv.add("USD to VND");
                listLv.add(quotes.getString("USDVND"));
                listLv.add("USD to XAU");
                listLv.add(quotes.getString("USDXAU"));

                ArrayAdapter adapter = new ArrayAdapter(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        listLv
                );

                lv.setAdapter(adapter);

                /*String success = root.getString("success");
                String terms = root.getString("terms");
                String privacy = root.getString("privacy");
                String timestamp = root.getString("timestamp");
                String source = root.getString("source");
                JSONObject quotes = root.JSONOject("quotes");*/




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }


}
