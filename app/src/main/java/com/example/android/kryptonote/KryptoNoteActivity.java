package com.example.android.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class KryptoNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

    public void onEncrypt(View v)
    {
        EditText x = (EditText) findViewById(R.id.key);
        String k = x.getText().toString();

        EditText y = (EditText) findViewById(R.id.data);
        String note = y.getText().toString();

        Cipher myCipher = new Cipher(k);
        String result1 = myCipher.encrypt(note);

        ((EditText) findViewById(R.id.data)).setText(result1);
    }

    public void onDecrypt(View v)
    {
        EditText second = (EditText) findViewById(R.id.key);
        String secondSting = second.getText().toString();

        EditText third = (EditText) findViewById(R.id.data);
        String third1 = third.getText().toString();

        Cipher myCipher2 = new Cipher(secondSting);
        String result2 = myCipher2.decrypt(third1);

        ((EditText) findViewById(R.id.data)).setText(result2);
    }

    public void onSave(View v)
    {
        try {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();

            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast label = Toast.makeText(this, "Note Saved.", Toast.LENGTH_LONG);
            label.show();
        }
        catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG);
            label.show();
        }
    }

    public void onLoad(View v)
    {
        try {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();

            File file = new File(dir, name);
            FileReader fr = new FileReader(file);
            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read()) {
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.data)).setText(show);
        }

        catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG);
            label.show();
        }
    }

}
