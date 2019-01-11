package com.weiqun.shixun13;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class lxr extends AppCompatActivity implements View.OnClickListener {
    private Button btn;
    private EditText et;
    private TextView tv,lv;
    String name,user,chat;
    List<userInfo>info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lxr_activity);
        if (getIntent()!=null){
            Intent it=getIntent();
            name=it.getStringExtra("name");
            user=it.getStringExtra("user");
            Toast.makeText(this,name+"****"+user,Toast.LENGTH_SHORT).show();
        }

        btn=findViewById(R.id.btn_Go);
        btn.setOnClickListener(this);
        lv=findViewById(R.id.lv);

        lv.setOnClickListener(this);
        et=findViewById(R.id.et);
        tv=findViewById(R.id.tv_name);
        tv.setText("欢迎 "+name+" 来到我的聊天室");


    }
    @Override
    public void onClick(View v) {
        chat=et.getText().toString().trim();
        switch (v.getId()){
            case R.id.btn_Go:
        if (TextUtils.isEmpty(chat)){
            Toast.makeText(lxr.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        UtilsOk.httpPostUtils_Reg_lt("http://123.207.85.214/chat/chat1.php", user, chat, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(lxr.this, "数据请求失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                List<userInfo> userInfos = new Gson().fromJson(response.body().string(),new TypeToken<List<userInfo>>(){}.getType());
//                Looper.prepare();
//                Toast.makeText(lxr.this, userInfos.get(0).getChat(), Toast.LENGTH_SHORT).show();
//                Looper.loop();
               SHOWData(userInfos);

            }
        });
        break;
            case R.id.lv:
                Intent it=new Intent(this,users.class);
                startActivity(it);
                break;
        }
    }



    //UI更新
    private void SHOWData(final List<userInfo> userInfos){
        runOnUiThread(new Runnable() {//UI线程的更新
            @Override
            public void run() {
                //UI的更新

                //lv.setText("name:"+userInfos.get(5).getName()+"   "+"time:"+userInfos.get(5).getTime()+"\n");
                lv.setText(userInfos.get(5).getChat()+"\n");

                //lv.append("name:"+userInfos.get(4).getName()+"   "+"time:"+userInfos.get(4).getTime()+"\n");
                lv.append(userInfos.get(4).getChat()+"\n");

                //lv.append("name:"+userInfos.get(3).getName()+"   "+"time:"+userInfos.get(3).getTime()+"\n");
                lv.append(userInfos.get(3).getChat()+"\n");

                //lv.append("name:"+userInfos.get(2).getName()+"   "+"time:"+userInfos.get(2).getTime()+"\n");
                lv.append(userInfos.get(2).getChat()+"\n");

                //lv.append("name:"+userInfos.get(1).getName()+"   "+"time:"+userInfos.get(1).getTime()+"\n");
                lv.append(userInfos.get(1).getChat()+"\n");

                //lv.append("name:"+userInfos.get(0).getName()+"   "+"time:"+userInfos.get(0).getTime()+"\n");
                lv.append(userInfos.get(0).getChat()+"\n");
//                基本适配器



            }
        });
    }




}
