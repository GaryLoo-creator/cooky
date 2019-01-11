package com.weiqun.shixun13;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class users extends AppCompatActivity implements View.OnClickListener {
    private ListView mListView;

    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);
        mListView=findViewById(R.id.lv_lxr);
        btn=findViewById(R.id.btn_fh);

        btn.setOnClickListener(this);
        UtilsOk.GetData("http://123.207.85.214/chat/member.php", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(users.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<userInfo> userInfos = new Gson().fromJson(response.body().string(),new TypeToken<List<userInfo>>(){}.getType());
                SHOWData(userInfos);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fh:
                users.this.finish();
                break;
        }
    }

    private void SHOWData(final List<userInfo>userInfos){
        runOnUiThread(new Runnable() {//UI线程的更新
            @Override
            public void run() {
                //UI的更新
                BaseAdapter baseAdapter=new com.weiqun.shixun13.BaseAdapter(users.this,userInfos);
                mListView.setAdapter(baseAdapter);
                ((com.weiqun.shixun13.BaseAdapter) baseAdapter).setDataChanger(userInfos);
            }
        });
    }
    private Handler mHandler =new Handler(){
        public void handleMessage(android.os.Message msg){

        }
    };



    }



