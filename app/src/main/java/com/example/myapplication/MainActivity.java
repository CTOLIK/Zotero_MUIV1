package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.ui.gallery.LibAct;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashSet;

import okhttp3.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;
import rx.Observable;
import rx.functions.Func1;


public class MainActivity extends AppCompatActivity {

    OkHttpClient client;
    TextView textView;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;


    ZoteroService zoteroService;

    ArrayList<String> Test = new ArrayList<>();
    String body = "";
    String str_resp="";

    public void imtry(int USER_ID)
    {


    }


    public void setUp(String accesskey, int USER_ID) throws Exception {
        try {
        RequestInterceptor authorizingInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION_BEARER_X + accesskey);
                request.addHeader(HttpHeaders.ZOTERO_API_VERSION, "3");

            }


        };


        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://api.zotero.org")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(authorizingInterceptor)
                .setConverter(new GsonConverter(new Gson()))
                .build();



        zoteroService = adapter.create(ZoteroService.class);


            zoteroService.getItemsNotAsAList(LibraryType.USER, USER_ID, null, null)
                    .flatMap(new Func1<Response, Observable<Header>>() {
                        @Override
                        public Observable<Header> call(Response response) {
                            //if (body[0] == '[') {body.}
                            body = new String(((TypedByteArray) response.getBody()).getBytes());
                            body = body+"\n]";
//
//                        body = body.split("    },\n" +
//                                "    {" + ")[0];
//
//                        Test.add(body);
                            //System.out.print(Observable.from(response.getHeaders()).toString());
                            return Observable.from(response.getHeaders());
                        }
                    })
                    .filter(new Func1<Header, Boolean>() {
                        @Override
                        public Boolean call(Header header) {
                            // System.out.print(HttpHeaders.LAST_MODIFIED_VERSION.equalsIgnoreCase(header.getName()));

                            return HttpHeaders.LAST_MODIFIED_VERSION.equalsIgnoreCase(header.getName());
                        }
                    })
//                .forEach(s->Test.add(String.valueOf(s)));
                    .map(new Func1<Header, Integer>() {
                        @Override
                        public Integer call(Header header) {
                            //   System.out.print(Integer.valueOf(header.getValue()).toString());

                            return Integer.valueOf(header.getValue());
                        }
                    })
                    .toBlocking()
                    .first();

            // return str_resp;
        } catch (Exception e){
            Toast.makeText(this, "Не удалось авторизоваться.", Toast.LENGTH_SHORT).show();
        }


    }

    String jsonFile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

       // setContentView(R.layout.fragment_login);
        client = new OkHttpClient();
        textView = findViewById(R.id.textView3);
        Button SignIn = findViewById(R.id.button_SignIn);
        Button SignUp = findViewById(R.id.button_SignUp);



        EditText login_text = findViewById(R.id.LoginText);
        EditText pass_text = findViewById(R.id.PassText);

        login_text.setText("17116393");
        pass_text.setText("vrNSxghdhbqT82b1TPLsjVn5");



        HashSet<String> ar_Items = new HashSet<>();
        HashSet<String> all_Data = new HashSet<>();
//        ArrayList<String> ar_meta_numChildren = new ArrayList<>();
//        ArrayList<String> ar_meta_creatorSummary = new ArrayList<>();
//        ArrayList<String> ar_Key = new ArrayList<>();
//        ArrayList<String> ar_Version = new ArrayList<>();
//        ArrayList<String> ar_itemType = new ArrayList<>();
//        ArrayList<String> ar_title = new ArrayList<>();
//        ArrayList<String> ar_creators_firstName = new ArrayList<>();
//        ArrayList<String> ar_creators_lastName = new ArrayList<>();
//        ArrayList<String> ar_series = new ArrayList<>();
//        ArrayList<String> ar_seriesNumber = new ArrayList<>();
//        ArrayList<String> ar_volume = new ArrayList<>();
//        ArrayList<String> ar_numberOfVolumes = new ArrayList<>();
//        ArrayList<String> ar_edition = new ArrayList<>();
//        ArrayList<String> ar_place = new ArrayList<>();
//        ArrayList<String> ar_publisher = new ArrayList<>();
//        ArrayList<String> ar_date = new ArrayList<>();
//        ArrayList<String> ar_numPages = new ArrayList<>();
//        ArrayList<String> ar_language = new ArrayList<>();
//        ArrayList<String> ar_ISBN = new ArrayList<>();
//        ArrayList<String> ar_shortTitle = new ArrayList<>();
//        ArrayList<String> ar_url = new ArrayList<>();
//        ArrayList<String> ar_accessDate = new ArrayList<>();
//        ArrayList<String> ar_archive = new ArrayList<>();
//        ArrayList<String> ar_archiveLocation = new ArrayList<>();
//        ArrayList<String> ar_libraryCatalog = new ArrayList<>();
//        ArrayList<String> ar_callNumber = new ArrayList<>();
//        ArrayList<String> ar_rights = new ArrayList<>();
//        ArrayList<String> ar_extra = new ArrayList<>();
//        ArrayList<String> ar_tags = new ArrayList<>();
//        ArrayList<String> ar_collections = new ArrayList<>();
//        ArrayList<String> ar_relations = new ArrayList<>();
//        ArrayList<String> ar_dateAdded = new ArrayList<>();
//        ArrayList<String> ar_dateModified = new ArrayList<>();


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((pass_text.length() > 0) && (login_text.length() > 0)){
                    try {
                        setUp(pass_text.getText().toString(), Integer.parseInt(login_text.getText().toString()));
                            //imtry(Integer.parseInt(login_text.getText().toString()));
                        body = body.replace("[[","");
                        String[] st = body.split("\n");
                        jsonFile = "";

                        String itemKey = "";
                        for (int i=0; i<st.length; i++){
                            if (st[i].matches("\\D{1,}:\\s[^{]{1,}.")) {
                                //Toast.makeText(MainActivity.this, st[i], Toast.LENGTH_SHORT).show();
                                if (st[i].split(":")[0].contains("\"key\"")){
                                    itemKey = st[i].split(":")[1];
                                    ar_Items.add(itemKey);
                                    //itemKey = itemKey.replace("\"","");
                                }

                                st[i] = st[i].trim();
                                all_Data.add(itemKey + "|" + st[i]);
                                jsonFile += itemKey + "|" + st[i] + "\n";
                            }
                        }

                        //jsonFile = all_Data.toArray()

                        if (!jsonFile.isEmpty()){
                            Intent intent = new Intent(MainActivity.this, LibAct.class);
                            intent.putExtra("data", jsonFile);
                            intent.putExtra("hs_data", all_Data);
                            startActivity(intent);
                        }






//                        if (getAllDataZotero(Integer.parseInt(login_text.getText().toString()),pass_text.getText().toString()).length()>10) {
//
//
//                            Intent intent = new Intent(MainActivity.this, LibAct.class);
//                            intent.putExtra("data", jsonFile);
////                            intent.putExtra("company", company);
////                            intent.putExtra("age", age);
//                            startActivity(intent);




                            //startActivity(new Intent(MainActivity.this, LibAct.class));



//



                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Авторизация провалена.", Toast.LENGTH_SHORT).show();
                        throw new RuntimeException(e);

                    }

                } else {
                    Toast.makeText(MainActivity.this, "Заполните поля для авторизации.", Toast.LENGTH_SHORT).show();
                }

            }
        });


//        SignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    setUp(pass_text.getText().toString(), Integer.parseInt(login_text.getText().toString()));
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });

    }


//    public String getAllDataZotero(Integer login_text, String pass_text) throws Exception {
//        ArrayList<String> ar_Items = new ArrayList<>();
//        setUp(pass_text,login_text);
//        imtry(login_text);
//
//        body = body.replace("[[","");
//
//        String[] st = body.split("\n");
//        jsonFile = "";
//
//        String itemKey = "";
//
//        for (int i=0; i<st.length; i++){
//            if (st[i].matches("\\D{1,}:\\s[^{]{1,}.")) {
//                //Toast.makeText(MainActivity.this, st[i], Toast.LENGTH_SHORT).show();
//
//                if (st[i].split(":")[0].contains("\"key\"")){
//                    itemKey = st[i].split(":")[1];
//                //    ar_Items.add(itemKey);
//                    //itemKey = itemKey.replace("\"","");
//
//                }
//
//                jsonFile += itemKey + "|" + st[i] + "\n";
//
//            }
//
//        }
//
//        return jsonFile;
//        //System.out.print(jsonFile);
//    }

    public ArrayAdapter<String> listad(ArrayList<String> al){
        String[] catNames = new String[al.size()];
        al.toArray(catNames);

// используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNames);
        return adapter;

    }

    public String getData(){
        return jsonFile;
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