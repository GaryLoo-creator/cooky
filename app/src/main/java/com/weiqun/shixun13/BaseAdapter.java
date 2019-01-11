package com.weiqun.shixun13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BaseAdapter extends android.widget.BaseAdapter {
    private Context context;
    private List<userInfo>userInfos1;
    private LayoutInflater inflater;
    public BaseAdapter(Context context, List<userInfo> userInfos) {
        this.context = context;
        this.userInfos1 = userInfos;
        this.inflater = LayoutInflater.from(context);
    }
    public void setDataChanger(List<userInfo> userInfos){
        this.userInfos1 = userInfos;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return userInfos1.size();
    }

    @Override
    public Object getItem(int position) {
        return userInfos1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoldere viewHoderle=null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_users, parent, false);
            viewHoderle = new ViewHoldere();
            viewHoderle.name = convertView.findViewById(R.id.tv_name);
            viewHoderle.user = convertView.findViewById(R.id.tv_user);
            convertView.setTag(viewHoderle);
        }else {
            viewHoderle = (ViewHoldere) convertView.getTag();
        }
        viewHoderle.user.setText(userInfos1.get(position).getUser());
        viewHoderle.name.setText(userInfos1.get(position).getName());
        return convertView;
    }
    class ViewHoldere{
        TextView name,user;
    }
}
