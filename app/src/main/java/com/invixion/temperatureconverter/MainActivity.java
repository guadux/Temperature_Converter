package com.invixion.temperatureconverter;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.inputValue);

        testDB();

    }

    private void testDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("MySQL Connection ok");
            Connection con = DriverManager.getConnection("jdbc:mysql://www.invixion.com:3306/invixion_consultorio", "invixion_consul", "dcin123:");
            //         System.out.println("Database connection success");
            System.out.println("Line 2");
            String result = ("");
            System.out.println(" Line 3");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from medicos");

            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
                result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
                result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
            }

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Error:" + e.toString());
            Log.w("Android-system","system get connection");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
                //metodoAdd()
                Toast.makeText(this, "Ha presionado add",
                        Toast.LENGTH_LONG).show();
                return true;
            case R.id.search:
                //metodoSearch()

                return true;
            case R.id.edit:
                //metodoEdit()

                return true;
            case R.id.delete:
                //metodoDelete()
                //escondemos la actionbar
                ActionBar actionBar = getSupportActionBar();
                actionBar.hide();
                return true;
            case R.id.action_settings:
                //metodoSettings()

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clickTemp(View view) {


                RadioButton celsiusButton = (RadioButton) findViewById(R.id.radio0);
                RadioButton fahrenheitButton = (RadioButton) findViewById(R.id.radio1);
                if (text.getText().length() == 0) {

                    Toast.makeText(this, "Please enter a valid number",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                float inputValue = Float.parseFloat(text.getText().toString());

                if (celsiusButton.isChecked()) {

                    text.setText(String
                            .valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)));
                    celsiusButton.setChecked(false);
                    fahrenheitButton.setChecked(true);

                } else {

                    text.setText(String
                            .valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
                    fahrenheitButton.setChecked(false);
                    celsiusButton.setChecked(true);
                }


    }

    public void listarClick(View view) {
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }
}
