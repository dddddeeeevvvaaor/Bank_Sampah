package com.example.Bank_Sampah.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.Bank_Sampah.model.user.user;
import com.example.Bank_Sampah.R;
import com.example.Bank_Sampah.app.AppController;

import com.example.Bank_Sampah.user.DataActivity;
import com.example.Bank_Sampah.user.EditTeman;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    List<user> listData;

    public TemanAdapter(ArrayList<user> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman, parent, false);
        return new  TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TemanViewHolder holder, int position) {
        String Id_User,Nama, Alamat, Email, Password;

        Id_User = listData.get(position).getId_User();
        Nama = listData.get(position).getNama();
        Alamat = listData.get(position).getAlamat();
        Email = listData.get(position).getEmail();
        Password = listData.get(position).getPassword();

        holder.NamaTxt.setTextColor(Color.BLUE);
        holder.NamaTxt.setTextSize(20);
        holder.NamaTxt.setText(Nama);
        holder.AlamatTxt.setText(Alamat);

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu pm = new PopupMenu(v.getContext(), v);
                pm.inflate(R.menu.popup1);

                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.edit:
                                Bundle bendel = new Bundle();
                                bendel.putString("kunci1", Id_User);
                                bendel.putString("kunci2", Nama);
                                bendel.putString("kunci3", Alamat);
                                bendel.putString("kunci3", Email);
                                bendel.putString("kunci3", Password);

                                Intent intent = new Intent(v.getContext(), EditTeman.class);
                                intent.putExtras(bendel);
                                v.getContext().startActivity(intent);
                                break;

                            case R.id.hapus:
                                AlertDialog.Builder alertdb = new AlertDialog.Builder(v.getContext());
                                alertdb.setTitle("Yakin "+Nama+" akan dihapus?");
                                alertdb.setMessage("Tekan Ya untuk menghapus");
                                alertdb.setCancelable(false);
                                alertdb.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        HapusData(Id_User);
                                        Toast.makeText(v.getContext(), "Data "+Id_User+" telah dihapus", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(v.getContext(), DataActivity.class);
                                        v.getContext().startActivity(intent);
                                    }
                                });
                                alertdb.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog adlg = alertdb.create();
                                adlg.show();
                                break;
                        }
                        return false;
                    }
                });
                pm.show();
                return true;
            }
        });
    }

    private void HapusData(final String idx){
        String url_update = "http://192.168.100.5/User12/deletetm.php";
        final String TAG = DataActivity.class.getSimpleName();
        final String TAG_SUCCES = "success";
        final int[] sukses = new int[1];

        StringRequest stringReq = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Respon: " + response.toString());

                try {
                    JSONObject jobj = new JSONObject(response);
                    sukses[0] = jobj.getInt(TAG_SUCCES);

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }}
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Error : "+error.getMessage());
            }
        })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();

                params.put("Id_User",idx);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringReq);
    }

    @Override
    public int getItemCount() {
        return (listData != null) ? listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView NamaTxt, AlamatTxt;
        public TemanViewHolder(View view) {
            super(view);
            cardku =(CardView) view.findViewById(R.id.kartuku);
            NamaTxt = (TextView) view.findViewById(R.id.textNama);
            AlamatTxt = (TextView) view.findViewById(R.id.textAlamat);
        }
    }

}