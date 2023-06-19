package com.example.abox;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dhc.absdk.ABRet;
import com.dhc.absdk.ABSDK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getSupportActionBar().hide();

    }

    public void buttonClick(View view) {

        new LoginAsyncTask().execute("a", "a");
    }

    public void button2Click(View view) {

        new TempAsyncTask().execute();
    }

    public void button3Click(View view) {

        new SockAsyncTask().execute();
    }

    public void button4Click(View view) {

        new SockonAsyncTask().execute();
    }

    public void button5Click(View view) {

        new SockoffAsyncTask().execute();
    }

    public void button6Click(View view) {

        new HdAsyncTask().execute();
    }
    public void imageClick(View view) {
        ImageView imageView = (ImageView)view;
        imageView.setImageResource(R.drawable.photo);
    }

    public class LoginAsyncTask extends AsyncTask<String, Void, ABRet> {

        @Override
        protected ABRet doInBackground(String... strings) {
            System.out.println(Arrays.toString(strings));
            return ABSDK.getInstance().loginWithUsername("a", "a");
        }

        @Override
        protected void onPostExecute(ABRet abRet) {
            super.onPostExecute(abRet);
            System.out.println(abRet.getCode());
        }
    }

    public class TempAsyncTask extends AsyncTask<String, Void, ABRet> {

        @Override
        protected ABRet doInBackground(String... strings) {
            return ABSDK.getInstance().getThStatus("中间温湿度传感器");
        }

        @Override
        protected void onPostExecute(ABRet abRet) {
            super.onPostExecute(abRet);
            System.out.println(abRet.getCode());
            System.out.println(abRet.getDicDatas());
        }
    }

    public class SockAsyncTask extends AsyncTask<String, Void, ABRet> {

        @Override
        protected ABRet doInBackground(String... strings) {
            return ABSDK.getInstance().getSockStatus("中间智能插座");
        }

        @Override
        protected void onPostExecute(ABRet abRet) {
            super.onPostExecute(abRet);
            System.out.println(abRet.getCode());
            System.out.println(abRet.getDicDatas());
        }
    }

    public class SockonAsyncTask extends AsyncTask<String, Void, ABRet> {

        @Override
        protected ABRet doInBackground(String... strings) {
            return ABSDK.getInstance().sockCtrl("中间智能插座","1");
        }

        @Override
        protected void onPostExecute(ABRet abRet) {
            super.onPostExecute(abRet);
            System.out.println(abRet.getCode());
            System.out.println(abRet.getDicDatas());
        }
    }

    public class SockoffAsyncTask extends AsyncTask<String, Void, ABRet> {

        @Override
        protected ABRet doInBackground(String... strings) {
            return ABSDK.getInstance().sockCtrl("中间智能插座","0");
        }

        @Override
        protected void onPostExecute(ABRet abRet) {
            super.onPostExecute(abRet);
            System.out.println(abRet.getCode());
            System.out.println(abRet.getDicDatas());
        }
    }

    public class HdAsyncTask extends AsyncTask<String, Void, ABRet> {

        @Override
        protected ABRet doInBackground(String... strings) {
            return ABSDK.getInstance().getHdStatus("人体活动传感器");
        }

        @Override
        protected void onPostExecute(ABRet abRet) {
            super.onPostExecute(abRet);
            System.out.println(abRet.getCode());
            System.out.println(abRet.getDicDatas());
        }
    }

    public class UrlAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL("http://www.baidu.com");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
                conn.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
