package com.example.myapplication.ui.gallery;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class LibAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_gallery);


        Bundle arguments = getIntent().getExtras();
        String data = arguments.get("data").toString();
        HashSet<String> hs_data = (HashSet<String>)arguments.get("hs_data");
        ArrayList<String> ar_data = new ArrayList<>(hs_data);

        Collections.sort(ar_data);

        System.out.print(data);
       // String[] ar_test = data.split("\n");
      //  String[] s_data = new String[hs_data.size()];
        // ar_data = hs_data.toArray(ar_data);

        ListView listView = findViewById(R.id.MyLib);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ar_data);

        listView.setAdapter(adapter);

    }
}