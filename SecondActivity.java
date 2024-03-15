package com.example.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btn=findViewById(R.id.sharebtn);
        result=findViewById(R.id.textView3);


        // Receiving data from the main activity
        Intent i=getIntent();
        String name=i.getStringExtra("name");  // hare we need to specify the key name to store the String

        int randomNum=randomGenerator();
        result.setText(""+randomNum);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               shareData(name,randomNum);
            }
        });
    }

    public int randomGenerator()
    {
        Random random=new Random();
        int upperLimit=1000;
        int numberGenerated=random.nextInt(upperLimit);
        return numberGenerated;
    }

    public void shareData(String name, int randomNum )
    {
        Intent intent=new Intent(Intent.ACTION_SEND); // this is predefined constant that represents an action to send data typically used to share content with other applications on that device;
        intent.setType("text/plain"); // this lets the app know that

        // Addititional info
        intent.putExtra(Intent.EXTRA_SUBJECT,name+" got lucky today"); // this is the  subject that will appear when we share using email
        intent.putExtra(Intent.EXTRA_TEXT,"his lucky number is "+randomNum); //email body

        startActivity(Intent.createChooser(intent,"Choose a platform"));


    }
}