package com.angel266489.fortuneteller.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.angel266489.fortuneteller.R;
import com.angel266489.fortuneteller.database.Wish;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Wish> wishes;

    public ListAdapter(List<Wish> wishes) {
        this.wishes = wishes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.wish.setText(wishes.get(position).getWish());

    }

    public void setWishes(List<Wish> wishes){
        this.wishes = wishes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return wishes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private String firstName;
        private String lastName;
        private String email;
        private String mergedName;
        MainActivity mainActivity;

        public void setUserParams() {
            if (!(mainActivity == null)) {
                firstName = mainActivity.getFirstName();
                lastName = mainActivity.getLastName();
                email = mainActivity.getEmail();
                mergedName = firstName + " " + lastName;
            }
        }

        TextView wish;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wish = itemView.findViewById(R.id.wish);

            ImageButton sendEmail = (ImageButton) itemView.findViewById(R.id.email_icon);
            final String mailto = "mailto:" + email +
                    "?cc=" + "angelilianov@gmail.com" +
                    "&subject=" + Uri.encode("Your fortune") +
                    "&body=" + Uri.encode("Test");
            sendEmail.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse(mailto));
                    try {
                        mainActivity.startActivity(emailIntent);
                        Toast toastError = new Toast(mainActivity.getApplicationContext());
                        toastError.setText("Testing!");
                        toastError.setDuration(Toast.LENGTH_LONG);
                        toastError.show();
                    } catch (ActivityNotFoundException e) {
                        Toast toastError = new Toast(mainActivity.getApplicationContext());
                        toastError.setText("Could not parse email details to external mail service!");
                        toastError.setDuration(Toast.LENGTH_LONG);
                        toastError.show();
                    }
                }
            });

            ImageButton makeFavorite = (ImageButton) itemView.findViewById(R.id.email_icon);
            makeFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : Pass Implicit Intent
                }
            });

        }


    }
}
