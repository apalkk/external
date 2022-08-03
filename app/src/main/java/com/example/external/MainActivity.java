package com.example.external;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    // Default file name
    private static final String FILE_NAME = "demo.txt";

    // UI components
    Button read;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referred UI components
        read = findViewById(R.id.button_save);
        mEditText = findViewById(R.id.edit_text);

        // Buttons on click functionality
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readfiletoExternalprivate();
            }
        });
    }

    private void readfiletoExternalprivate()
    {
        //Read new file and set text into TextView
        File file = new File(getExternalStorageDirectory(),FILE_NAME);
        String msg = load(file);
        mEditText.setText(msg);
    }

    private String load(File file) {
        //Get string from file and return
        FileInputStream fileInputStreams = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputStreams = new FileInputStream(file);
            int text;

            // while there is a line then append it
            while ((text = fileInputStreams.read()) != -1) {
                stringBuilder.append((char) text);
            }

            // paste string to Edit text
            mEditText.setText(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStreams != null) {
                try {
                    // close stream
                    fileInputStreams.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}