package com.thealteria.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.english) {
            setLang("English");
        }

        else if (item.getItemId() == R.id.spanish) {
            setLang("Spanish");
        }

        return true;
    }

    public void setLang(String lang) {

        sharedPreferences = this.getSharedPreferences("com.thealteria.languagepreferences", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("language", lang).apply();

        textView = (TextView) findViewById(R.id.text);
        textView.setText(lang);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.thealteria.languagepreferences", Context.MODE_PRIVATE);
        textView = (TextView) findViewById(R.id.text);
        String lang = sharedPreferences.getString("language", "");

        if (lang == "") {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Choose a language")
                    .setMessage("Which language which you choose?")
                    .setPositiveButton("Englsh", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLang("English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLang("Spanish");
                        }
                    }).show();
        }

        else {
            textView.setText(lang);
        }
    }
}
