package com.example.Bank_Sampah.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.Bank_Sampah.Adapter.TemanAdapter;
import com.example.Bank_Sampah.R;
import com.example.Bank_Sampah.model.ModelDatabase;
import com.example.Bank_Sampah.model.user.user;
import com.example.Bank_Sampah.ui.history.HistoryAdapter;
import com.example.Bank_Sampah.viewmodel.HistoryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    List<user> temanArrayList = new ArrayList<>();

    private static final String TAG = DataActivity.class.getSimpleName();
    private static String url_select        = "http://192.168.100.5/User12/bacateman.php";

    public static final String TAG_ID_USER       = "Id_User";
    public static final String TAG_NAMA     = "Nama";
    public static final String TAG_ALAMAT   = "Alamat";

    List<ModelDatabase> modelDatabaseList = new ArrayList<>();
    HistoryAdapter historyAdapter;
    HistoryViewModel historyViewModel;
    Toolbar toolbaruser;
    RecyclerView rvUser;
    TextView tvNotFoundUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        bacaData();
        //setStatusBar();
        //setToolbar();
        //setInitLayout();
    }

    private void setToolbar() {
        toolbaruser = findViewById(R.id.toolbaruser);

        setSupportActionBar(toolbaruser);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void setInitLayout() {
        rvUser = findViewById(R.id.rvUser);
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

    public  void  bacaData(){
        temanArrayList.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jArr = new JsonArrayRequest(url_select,new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response){
                Log.d(TAG, response.toString());

                //Parsing json
                for (int i=0; i<response.length();i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        user item = new user();

                        item.setId(obj.getString(TAG_ID_USER));
                        item.setNama(obj.getString(TAG_NAMA));
                        item.setAlamat(obj.getString(TAG_ALAMAT));

                        // menambah item ke array
                        temanArrayList.add(item);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                //adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(DataActivity.this,"gagal",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jArr);
    }

}