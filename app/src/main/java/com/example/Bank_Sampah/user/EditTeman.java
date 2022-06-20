package com.example.Bank_Sampah.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Bank_Sampah.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditTeman extends AppCompatActivity {
    TextView Id_UserText;
    EditText edNama, edAlamat;
    Button editBtn;
    String Id_User, Nama, Alamat, NamaEd, AlamatEd;
    int sukses;

    private  static  String url_update = "http://192.168.100.5/User12/updatetm.php";
    private  static  final  String TAG = EditTeman.class.getSimpleName();
    private static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teman);

        Id_UserText = findViewById(R.id.textId_User);
        edNama = findViewById(R.id.editNama);
        edAlamat = findViewById(R.id.editAlamat);
        editBtn = findViewById(R.id.buttonEdit);


        Bundle bundle = getIntent().getExtras();
        Id_User = bundle.getString("kunci1");
        Nama = bundle.getString("kunci2");
        Alamat = bundle.getString("kunci3");

        Id_UserText.setText("Id_User: "+ Id_User);
        edNama.setText(Nama);
        edAlamat.setText(Alamat);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditData();
            }
        });
    }

    public  void EditData()
    {
        NamaEd = edNama.getText().toString();
        AlamatEd = edAlamat.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringReq = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Respon: " + response.toString());

                try {
                    JSONObject jobj = new JSONObject(response);
                    sukses = jobj.getInt(TAG_SUCCES);
                    if (sukses == 1) {
                        Toast.makeText(EditTeman.this, "Sukses mengedit data", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditTeman.this, "gagal", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Error : "+error.getMessage());
                Toast.makeText(EditTeman.this,"Gagal Edit data",Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();

                params.put("Id_User",Id_User);
                params.put("Nama",NamaEd);
                params.put("Alamat",AlamatEd);

                return params;
            }
        };
        requestQueue.add(stringReq);
        CallHomeActivity();
    }

    public void CallHomeActivity()
    {
        Intent intent = new Intent(getApplicationContext(), DataActivity.class);
        startActivity(intent);
        finish();
    }
}