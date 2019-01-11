package com.weiqun.shixun13;

import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class zc extends AppCompatActivity implements View.OnClickListener {
    private EditText name,user,psw;
    private TextView tv;
    private Button btn;
    private ImageView btn_tc;
    private String user1,psw1,name1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zc_activity);
        name=findViewById(R.id.et_name);
        user=findViewById(R.id.et_user);
        psw=findViewById(R.id.et_psw);
        btn=findViewById(R.id.btn_zc);
        btn.setOnClickListener(this);
        btn_tc=findViewById(R.id.btn_tc);
        btn_tc.setOnClickListener(this);
        //tv=findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        name1=name.getText().toString();
        user1=user.getText().toString();
        psw1=psw.getText().toString();
        switch (v.getId()){
            case R.id.btn_zc:

                if(TextUtils.isEmpty(name1)){
                    Toast.makeText(zc.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(user1)){
                    Toast.makeText(zc.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(psw1)){
                    Toast.makeText(zc.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                UtilsOk.httpPostUtils_Reg_zc("http://123.207.85.214/chat/register.php", name1, user1, psw1, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Looper.prepare();
                        Toast.makeText(zc.this, "数据请求失败，请重试", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        userInfo zc = new Gson().fromJson(response.body().string(), userInfo.class);
                        SHOWData(zc.getStatus());
                    }
                });
                break;
                case R.id.btn_tc:
                    Toast.makeText(zc.this, "你点击了退出按钮", Toast.LENGTH_SHORT).show();
                    zc.this.finish();
                    break;
            }
        }

    private void SHOWData(final String msg){
        runOnUiThread(new Runnable() {//UI线程的更新
            @Override
            public void run() {
                //UI的更新
                //tv.setText(msg);
            }
        });
    }


}
