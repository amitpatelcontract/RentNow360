//package com.firebase.samples.logindemo.adapters;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.firebase.samples.logindemo.R;
//import com.firebase.samples.logindemo.models.UserModel;
//
//import java.util.List;
//
///**
// * Created by arms on 6/6/16.
// */
//public class UserRecycleAdapter  extends RecyclerView.Adapter<UserRecycleAdapter.MyViewHolder> {
//
//    private List<UserModel> userList;
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView title, year, genre;
//
//        public MyViewHolder(View view) {
//            super(view);
//            title = (TextView) view.findViewById(R.id.username);
////            genre = (TextView) view.findViewById(R.id.genre);
////            year = (TextView) view.findViewById(R.id.year);
//        }
//    }
//
//
//    public UserRecycleAdapter(List<UserModel> moviesList) {
//        this.userList = moviesList;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.user_list_item, parent, false);
//
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        UserModel userModel = userList.get(position);
//        holder.title.setText(userModel.getDisplayName());
////        holder.genre.setText(userModel.getGender());
////        holder.year.setText(userModel.getYear());
//    }
//
//    @Override
//    public int getItemCount() {
//        return userList.size();
//    }
//}