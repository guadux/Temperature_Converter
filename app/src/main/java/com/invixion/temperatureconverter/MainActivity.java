package com.invixion.temperatureconverter;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends AppCompatActivity {

    private EditText text;
    private TextView info;
//    private ActionBar mActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.inputValue);
        info =(TextView)findViewById(R.id.info);
//        mActionbar = getActionBar();
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
