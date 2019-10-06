package com.hazemelmamlouk.paggingmvvm.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hazemelmamlouk.paggingmvvm.R;
import com.hazemelmamlouk.paggingmvvm.model.User;

public class UserAdapter extends PagedListAdapter<User, UserAdapter.UserViewHolder> {
    public UserAdapter() {
        super(USER_COMPARATOR);
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView userName;
        private TextView userEmail;
        private ImageView userPic;
        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userEmail = itemView.findViewById(R.id.user_email);
            userPic = itemView.findViewById(R.id.user_avatar);
            userPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), userName.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        void bind(User user) {
            if (user.getFirstName() != null && user.getLastName() != null) {
                userName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
            }
            if (user.getEmail() != null) {
                userEmail.setText(user.getEmail());
            }
            Glide.with(itemView.getContext())
                    .load(user.getAvatar())
                    .placeholder(R.drawable.ic_warning_black_24dp)
                    .into(userPic);
        }
    }
    private static final DiffUtil.ItemCallback<User> USER_COMPARATOR = new DiffUtil.ItemCallback<User>() {
        @Override public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @SuppressLint("DiffUtilEquals")
        @Override public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem == newItem;
        }
    };
}
