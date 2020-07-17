package com.example.week14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.week14.databinding.ActivityMainBinding;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    final String filename = "test.txt";
    //File filename = new File("test.txt");
    final String path = "D:/2020-1/안드로이드/week14";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.load.setOnClickListener(v->{
            load();
            //startActivity(new Intent(this, SettingsActivity.class));
        });
        binding.save.setOnClickListener(v->save());
        /*binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });*/

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
    }
    void load(){ //data 읽어오기
        try{
            File file = new File(getExternalFilesDir(path),filename);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);

            String str="";
            for(byte tmp: data)
                str = str + (char)tmp;
            binding.editText.setText(str);
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        //String str = pref.getString("name", "");
    }
    void save(){ //data 저장하기
        String data = binding.editText.getText().toString();
        try{
            File file = new File(getExternalFilesDir(path), filename);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data.getBytes());
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        //SharedPreferences.Editor editor = getSharedPreferences("pref", 0).edit();
        //editor.putString("name", "value");
        //editor.apply();
    }
}