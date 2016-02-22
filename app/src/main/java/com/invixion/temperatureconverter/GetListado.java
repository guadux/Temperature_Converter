package com.invixion.temperatureconverter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.MarginLayoutParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class GetListado extends AsyncTask<String,Void,String> {

    private Context context;
    private int byGetOrPost = 0;
    private LinearLayout mLayout;
    private TextView btn;
    private int top;

    public GetListado(Context context,int flag, LinearLayout mLayout, TextView tv) {
        this.context = context;
        byGetOrPost = flag;
        this.mLayout = mLayout;
        this.btn = tv;
        top = 100;
    }

    @Override
    protected String doInBackground(String... arg0) {
        try {

            if (byGetOrPost != 0) { //Post Method

                String link = "http://invixion.com/listado_medicos.php";
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null) {
                    System.out.println(line);
                    sb.append(line);
                }
                return sb.toString();
            }
            else {
                return null;
            }

        }
        catch (Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result){
        System.out.println(result);//[{"0":"1","id_medico":"1","1":"guada","nombre":"guada","2":"1","ciudades_id_ciudad":"1"},{"0":"2","id_medico":"2","1":"gu","nombre":"gu","2":"1","ciudades_id_ciudad":"1"}]

        JSONArray ja;

        try
        {
            ja = new JSONArray(result);
            int len = ja.length();
            for (int i = 0; i < len; i++)
            {
                JSONObject json_data = ja.getJSONObject(i);

                String id_medico = json_data.getString("id_medico");
                String nombre = json_data.getString("nombre");
                String ciudad = json_data.getString("ciudades_id_ciudad");

                //creamos un texteditor por cada usr
                mLayout.addView(createNewTextView(id_medico+" - "+nombre+" - "+ciudad));

                //y podriamos llenar un listado

                //eliminamos el boton
                mLayout.removeView(btn);

                //this.roleField.setText(nombre+" - "+ciudad);
            }
        }
        catch (Exception e)
        {
            //e.toString();
        }

    }

    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(10,top,10,10);

        top += 10;
        final TextView textView = new TextView(context);
        textView.setLayoutParams(lparams);
        textView.setText(text);
        return textView;
    }
}
