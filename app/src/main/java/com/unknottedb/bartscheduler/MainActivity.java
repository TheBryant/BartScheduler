package com.unknottedb.bartscheduler;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private static final String LOG = "Main Activity";

    Button mXMLButton;
    TextView mXMLTextView;
    List<BartStation> mStations;

    static final String url = "http://api.bart.gov/api/stn.aspx?cmd=stns&key=MW9S-E7SL-26DU-VV8V";

    //static final String ATTR_ID = "id";
    static final String NODE_INNER = "station";
    static final String NODE_NAME = "name";
    static final String NODE_ABBR = "abbr";
    static final String NODE_ADDR = "address";
    static final String NODE_CITY = "city";
    static final String NODE_COUNTY = "county";
    static final String NODE_ZIP = "zipcode";
    static final String NODE_LATI = "gtfs_latitude";
    static final String NODE_LONG = "gtfs_longitude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mXMLButton = (Button)findViewById(R.id.XML_button);
        mXMLTextView = (TextView)findViewById(R.id.XML_textView);
        mXMLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetXMLTask task = new GetXMLTask(MainActivity.this);
                task.execute(new String[]{url});
            }
        });
    }


    private class GetXMLTask extends AsyncTask<String, Void, String> {
        private Activity context;
        public GetXMLTask(Activity context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... urls) {
            String xml = null;
            for (String url : urls) {
                xml = getXmlFromUrl(url);
            }
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            XMLDOMParser parser = new XMLDOMParser();
            InputStream stream = new ByteArrayInputStream(xml.getBytes());
            Document doc = parser.getDocument(stream);

            NodeList nodeList = doc.getElementsByTagName(NODE_INNER);

            mStations = new ArrayList<BartStation>();
            BartStation Station = null;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Station = new BartStation();
                Element e = (Element) nodeList.item(i);
                Station.setName(parser.getValue(e,NODE_NAME));
                Station.setAbbr(parser.getValue(e,NODE_ABBR));
                mStations.add(Station);
            }
            mXMLTextView.setText(mStations.get(0).getName());
            //listViewAdapter = new CustomListViewAdapter(context, employees);
            //listView.setAdapter(listViewAdapter);
        }

        /* uses HttpURLConnection to make Http request from Android to download
         the XML file */
        private String getXmlFromUrl(String urlString) {
            StringBuffer output = new StringBuffer("");

            InputStream stream = null;
            URL url;
            try {
                url = new URL(urlString);
                URLConnection connection = url.openConnection();

                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(stream));
                    String s = "";
                    while ((s = buffer.readLine()) != null)
                        output.append(s);
                }
            } catch (MalformedURLException e) {
                Log.e("Error", "Unable to parse URL", e);
            } catch (IOException e) {
                Log.e("Error", "IO Exception", e);
            }

            return output.toString();
        }
    }

}
