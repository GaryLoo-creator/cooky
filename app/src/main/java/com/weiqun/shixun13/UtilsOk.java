package com.weiqun.shixun13;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 网络访问的封装类，
 */
//联系人
public class UtilsOk {
    public static void GetData(String url,Callback callback){
        OkHttpClient client = new OkHttpClient();   //第一步：创建okhttp对象
        Request build = new Request.Builder()      // 第二部： 请求request方式，添加网址，build建立
                .url(url)
                .build();
        client.newCall(build).enqueue(callback);    //enqueue,加入访问队列
    }
//POST 用户登录和注册
    public static void httpPostUtils_Reg_zc(String url,String name,String user,String password,Callback callback){
        OkHttpClient client = new OkHttpClient();
//PSOT 表单创建
        RequestBody body = new FormBody.Builder()
                .add("name",name)
                .add("user",user)
                .add("password",password)
                .build();
//访问请求
        Request request = new Request.Builder()
                .url(url)
//提交表单
                .post(body)
                .build();
// 网络异步回调
        client.newCall(request).enqueue(callback);
    }

    public static void httpPostUtils_Reg_dl(String url,String user,String password,Callback callback){
        OkHttpClient client = new OkHttpClient();
//PSOT 表单创建
        RequestBody body = new FormBody.Builder()
                .add("user",user)
                .add("password",password)
                .build();
//访问请求
        Request request = new Request.Builder()
                .url(url)
//提交表单
                .post(body)
                .build();
// 网络异步回调
        client.newCall(request).enqueue(callback);
    }

    public static void httpPostUtils_Reg_lt(String url,String user,String chat,Callback callback){
        OkHttpClient client = new OkHttpClient();
//PSOT 表单创建
        RequestBody body = new FormBody.Builder()
                .add("user",user)
                .add("chat",chat)
                .build();
//访问请求
        Request request = new Request.Builder()
                .url(url)
//提交表单
                .post(body)
                .build();
// 网络异步回调
        client.newCall(request).enqueue(callback);
    }

}
