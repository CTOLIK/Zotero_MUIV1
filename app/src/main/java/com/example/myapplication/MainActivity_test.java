package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


public class MainActivity_test extends AppCompatActivity {

    OkHttpClient client;
    String getURL = "https://www.zotero.org/kekich/library";
    String postURL = "https://www.zotero.org/user/login/";
    TextView textView;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    ZoteroService zoteroService;

    public void setUp(String accesskey) throws Exception {
        RequestInterceptor authorizingInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION_BEARER_X + accesskey);
                request.addHeader(HttpHeaders.ZOTERO_API_VERSION, "3");

                System.out.print("||||||||||||||||||||||||||||||||||||");
                System.out.print(request.toString());
                System.out.print("------------------------------------");
            }
        };

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://api.zotero.org")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(authorizingInterceptor)
                .setConverter(new GsonConverter(new Gson()))
                .build();



        zoteroService = adapter.create(ZoteroService.class);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//






        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // New rows:

        // setContentView(R.layout.activity_main);

        setContentView(R.layout.fragment_home);
        client = new OkHttpClient();
        textView = findViewById(R.id.textView3);
        Button SignIn = findViewById(R.id.button_SignIn);
        Button SignUp = findViewById(R.id.button_SignUp);



        EditText login_text = findViewById(R.id.LoginText);
        EditText pass_text = findViewById(R.id.PassText);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                textView.setText("empty");
//                SignIn.setText("Блять");
//                get();
//                Toast.makeText(MainActivity_test.this, "это фиаско гет", Toast.LENGTH_SHORT).show();

                try {
                    setUp(pass_text.getText().toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("empty");
                SignIn.setText("Сука");
                post();
                Toast.makeText(MainActivity_test.this, "это фиаско пост", Toast.LENGTH_SHORT).show();
            }
        });




        }




    public void get(){
        okhttp3.Request request = new okhttp3.Request.Builder().url(getURL).build();
        Toast.makeText(MainActivity_test.this, "пиздец гет", Toast.LENGTH_SHORT).show();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity_test.this, "Хуй вообще гет", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            textView.setText(response.body().string());
                            Toast.makeText(MainActivity_test.this, "Ес гет", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity_test.this, "Хуй гет", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }



    public void post(){

        okhttp3.RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", "kekich")
                .addFormDataPart("password", "4755354561Aa!")
                .addFormDataPart("remember", "0")
                .build();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(postURL)
                .post(requestBody)
                .build();
        Toast.makeText(MainActivity_test.this, "пиздец пост", Toast.LENGTH_SHORT).show();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity_test.this, "Хуй вообще пост", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            textView.setText(response.body().string());
                            Toast.makeText(MainActivity_test.this, "Ес пост", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }

                    }
                });
            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}