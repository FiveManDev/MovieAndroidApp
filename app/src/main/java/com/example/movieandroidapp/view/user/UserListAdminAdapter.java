package com.example.movieandroidapp.view.user;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieandroidapp.R;
import com.example.movieandroidapp.Utility.Extension;
import com.example.movieandroidapp.contract.user.ListenerActionUser;
import com.example.movieandroidapp.model.User;

import java.util.List;

public class UserListAdminAdapter extends RecyclerView.Adapter<UserAdminHolder> {

    private List<User> userList;
    private Context context;
    private String type;
    private ListenerActionUser listenerAction;

    public UserListAdminAdapter(List<User> list, Context context, String type, ListenerActionUser listenerAction) {
        this.userList = list;
        this.context = context;
        this.type = type;
        this.listenerAction = listenerAction;
    }

    public UserListAdminAdapter(List<User> list, Context context,String type) {
        this.userList = list;
        this.context = context;
        this.type = type;
    }

    public void setMovieList(List<User> userList) {
        this.userList = userList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserAdminHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_admin_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdminHolder holder, int position) {
        if (userList.size() > 0) {
            User user = userList.get(position);
            holder.row_user_item_admin_id.setText(user.getUserID());
            holder.row_user_item_admin_name.setText(user.getProfile().getFirstName()+ " "+user.getProfile().getLastName());
            holder.row_user_item_admin_email.setText(user.getProfile().getEmail());
            holder.row_user_item_admin_userName.setText(user.getUserName());
            holder.row_user_item_admin_className.setText(user.getProfile().getClassification().getClassName());
            if(type.equals("user")){
                holder.row_user_item_admin_review.setText(user.getNumberOfReviews().toString());
                if (user.getStatus()) {
                    holder.row_user_item_admin_visible.setText("Active");
                    holder.row_user_item_admin_visible.setTextColor(Color.parseColor("#5bceae"));
                } else {
                    holder.row_user_item_admin_visible.setText("Banned");
                    holder.row_user_item_admin_visible.setTextColor(Color.parseColor("#ff5860"));
                }
                holder.row_user_item_admin_createAt.setText(Extension.formatDate(user.getCreateAt()));
                //listener
                holder.btn_block_user_admin.setOnClickListener(t-> {
                    AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                            .setMessage("Do you want to block")
                            .setPositiveButton("Block", (dialog, whichButton) -> {
                                listenerAction.ListenerClicked("block", user);
                                dialog.dismiss();
                            })
                            .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                            .create();
                    myQuittingDialogBox.show();
                });
                holder.btn_delete_user_admin.setOnClickListener(t-> {
                    AlertDialog myQuittingDialogBox = new AlertDialog.Builder(context)
                            .setMessage("Do you want to Delete")
                            .setPositiveButton("Delete", (dialog, whichButton) -> {
                                listenerAction.ListenerClicked("delete", user);
                                dialog.dismiss();
                            })
                            .setNegativeButton("cancel", (dialog, which) -> dialog.dismiss())
                            .create();
                    myQuittingDialogBox.show();
                });
            }
            else{
                holder.row_user_item_admin_review.setVisibility(View.GONE);
                holder.row_user_item_admin_visible.setVisibility(View.GONE);
                holder.row_user_item_admin_visible.setVisibility(View.GONE);
                holder.row_user_item_admin_createAt.setVisibility(View.GONE);
                holder.container_user_action.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}
