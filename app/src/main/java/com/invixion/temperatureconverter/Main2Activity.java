package com.invixion.temperatureconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends Activity {

    private EditText usernameField,passwordField;
    private TextView status,role,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        usernameField = (EditText)findViewById(R.id.editText1);
        passwordField = (EditText)findViewById(R.id.editText2);

        status = (TextView)findViewById(R.id.textView6);
        role = (TextView)findViewById(R.id.textView7);
        method = (TextView)findViewById(R.id.textView9);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Get Method");
        new MysqlActivity(this,status,role,0).execute(username,password);

    }

    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Post Method");
        new MysqlActivity(this,status,role,1).execute(username,password);
    }

    public void irAlListado(View view) {
        Intent i = new Intent(this, Main3Activity.class);
        startActivity(i);
    }
}