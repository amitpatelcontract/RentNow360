//package com.firebase.samples.logindemo.adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//import com.firebase.samples.logindemo.R;
////import com.firebase.samples.logindemo.androidchat.IndividualChat;
//import com.firebase.samples.logindemo.androidchat.MaleUserList;
//import com.firebase.samples.logindemo.firebasereferenceurls.NCHATIntents;
//import com.firebase.samples.logindemo.models.User;
//
//import java.util.ArrayList;
//
//
//
///**
// * Created by arms on 2/8/16.
// */
//public class UserAdapter extends BaseAdapter {
//    private final ArrayList<User> userArrayList;
//    String[] result;
//    Context context;
////    int [] imageId;
//    private static LayoutInflater inflater=null;
//
//    private String userKey;
//
//    public UserAdapter(MaleUserList maleUserList, ArrayList<User> userArrayList) {
//        // TODO Auto-generated constructor stub
//        this.userArrayList=userArrayList;
//        context=maleUserList;
//
//        inflater = (LayoutInflater)context.
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//    @Override
//    public int getCount() {
//        // TODO Auto-generated method stub
//        return userArrayList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        // TODO Auto-generated method stub
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        // TODO Auto-generated method stub
//        return position;
//    }
//
//    public class Holder
//    {
//        TextView tv;
//        ImageView img;
//    }
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//        Holder holder=new Holder();
//        View rowView;
//        rowView = inflater.inflate(R.layout.user_list_item, null);
//        holder.tv=(TextView) rowView.findViewById(R.id.his_her_name);
//        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
////        holder.tv.setText(userArrayList.get(position).getName());
//////        holder.img.setImageResource(imageId[position]);
////        rowView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // TODO Auto-generated method stub
////                createAChatRoom(userArrayList.get(position).getName(), userArrayList.get(position).getBirthYear());
////                Toast.makeText(context, "You Clicked " + userArrayList.get(position).getName(), Toast.LENGTH_LONG).show();
////
////
////            }
////        });
//        return rowView;
//    }
//
//    private void createAChatRoom(String fullName, int birthYear) {
//
//        context.startActivity(new Intent(context, IndividualChat.class).putExtra(NCHATIntents.USER_KEY, userKey).putExtra(NCHATIntents.USER_NAME,  fullName).putExtra(NCHATIntents.USER_AGE,  birthYear));
//    }
//
//
//
//}