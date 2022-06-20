package com.example.Bank_Sampah.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class TambahTeman extends AppCompatActivity {
    private EditText editNama,editAlamat, editEmail, editPassword;
    private Button simpanBtn;
    String Nama,Alamat,Email,Password;
    int success;

    private static String url_insert = "http://192.168.100.5/User12/tambahtm.php";
    private static final String TAG = TambahTeman.class.getSimpleName();
    private static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_teman);

        editNama = findViewById(R.id.edNama);
        editAlamat = findViewById(R.id.edAlamat);
        editEmail = findViewById(R.id.edEmail);
        editPassword = findViewById(R.id.edPassword);
        simpanBtn = findViewById(R.id.btnSimpan);

        simpanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpanData();
                Intent inten = new Intent(getApplicationContext(), DataActivity.class);
                startActivity(inten);
                finish();
            }
        });
    }

    public void SimpanData()
    {
        if (editNama.getText().toString().equals("")||editAlamat.getText().toString().equals("")||editEmail.getText().toString().equals("")||editPassword.getText().toString().equals("")){
            Toast.makeText(TambahTeman.this, "Semua harus diisi data",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Nama = editNama.getText().toString();
            Alamat = editAlamat.getText().toString();
            Email = editEmail.getText().toString();
            Password = editPassword.getText().toString();

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    Log.d(TAG,"Response: "+response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCES);
                        if (success == 1){
                            Toast.makeText(TambahTeman.this, "Sukses simpan data", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TambahTeman.this,"gagal",Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    } }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.e(TAG, "Error"+error.getMessage());
                    Toast.makeText(TambahTeman.this,"Gagal simpan data",Toast.LENGTH_SHORT).show();
                }

            }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<>();

                    params.put("Nama",Nama);
                    params.put("Alamat",Alamat);
                    params.put("Alamat",Email);
                    params.put("Alamat",Password);

                    return params;
                }
            };
            requestQueue.add(strReq);
        }
    }
}