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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class dl extends AppCompatActivity implements View.OnClickListener {
    private EditText user;
    private EditText psw;
    private Button btn;
    private TextView btn_zc;
    private String user1,psw1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dl_activity);
        user=findViewById(R.id.et_user);
        psw=findViewById(R.id.et_psw);
        btn=findViewById(R.id.btn_dl);
        btn.setOnClickListener(this);
        btn_zc=findViewById(R.id.btn_zc);
        btn_zc.setOnClickListener(this);
//        tv_tc=findViewById(R.id.tv_tc);
//        tv_tc.setOnClickListener(this);
//        tv=findViewById(R.id.tv);

    }
    public void onClick(View v) {
        user1=user.getText().toString();
        psw1=psw.getText().toString();
        switch (v.getId()){
            case R.id.btn_dl:
            if(TextUtils.isEmpty(user1)){
                Toast.makeText(dl.this, "请输入账号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(psw1)){
                Toast.makeText(dl.this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }
            UtilsOk.httpPostUtils_Reg_dl("http://123.207.85.214/chat/login.php",user1, psw1, new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    Looper.prepare();
                    Toast.makeText(dl.this, "登录失败，请重新登录", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    //Toast.makeText(dl.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    userInfo dl = new Gson().fromJson(response.body().string(), userInfo.class);
                    SHOWData("姓名："+dl.getName()+"\n用户名："+dl.getUser()+"\n登录状态："+dl.getStatus());
                    if (dl.getName()!=null){
                        Intent it=new Intent(dl.this,lxr.class);
                        it.putExtra("user",dl.getUser());
                        it.putExtra("name",dl.getName());
                        startActivity(it);
                    }
                }
            });
            break;
            case R.id.btn_zc:
               Intent it=new Intent(this,zc.class);
               startActivity(it);
                break;

        }
    }
    private void SHOWData(final String msg){
        runOnUiThread(new Runnable() {//UI线程的更新
            @Override
            public void run() {
                //UI的更新
                ;
            }
        });
    }

}
