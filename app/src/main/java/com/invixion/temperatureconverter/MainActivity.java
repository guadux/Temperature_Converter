package com.invixion.temperatureconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

//dialogo
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.inputValue);
    }

    public void clickTemp(View view) {

//        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
//        dialogo1.setCancelable(false);
//        dialogo1.setPositiveButton("Confirmar", null);
//        dialogo1.setMessage("click");
//        dialogo1.show();


                RadioButton celsiusButton = (RadioButton) findViewById(R.id.radio0);
                RadioButton fahrenheitButton = (RadioButton) findViewById(R.id.radio1);
                if (text.getText().length() == 0) {
//                    dialogo1.setMessage("long =0");
//                    dialogo1.show();
                    Toast.makeText(this, "Please enter a valid number",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                float inputValue = Float.parseFloat(text.getText().toString());
//                dialogo1.setMessage("valor: "+inputValue);
//                dialogo1.show();
                if (celsiusButton.isChecked()) {
//                    dialogo1.setMessage("celsius check");
//                    dialogo1.show();
                    text.setText(String
                            .valueOf(ConverterUtil.convertFahrenheitToCelsius(inputValue)));
                    celsiusButton.setChecked(false);
                    fahrenheitButton.setChecked(true);

                } else {
//                    dialogo1.setMessage("faren check");
//                    dialogo1.show();
                    text.setText(String
                            .valueOf(ConverterUtil.convertCelsiusToFahrenheit(inputValue)));
                    fahrenheitButton.setChecked(false);
                    celsiusButton.setChecked(true);
                }



        //}
    }
}
