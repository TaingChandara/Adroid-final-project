package com.lik.project;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RegisterCustomAdapter extends RecyclerView.Adapter<RegisterCustomAdapter.MyViewHolder> {

    private Activity activity;
    private ArrayList<String> Id, Name, Email, Current_Pass, New_Pass, Confirm_New_Pass;

    public RegisterCustomAdapter(
            RegisterDataActivity mainActivity,
            Activity activity,
            ArrayList<String> Id,
            ArrayList<String> Name,
            ArrayList<String> Email,
            ArrayList<String> Current_Pass,
            ArrayList<String> New_Pass,
            ArrayList<String> Confirm_New_Pass){
        this.activity = activity;
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
        this.Current_Pass = Current_Pass;
        this.New_Pass = New_Pass;
        this.Confirm_New_Pass = Confirm_New_Pass;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_register_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.IdTxt.setText(Id.get(position));
        holder.NameTxt.setText(Name.get(position));
        holder.EmailTxt.setText(Email.get(position));
        holder.CurrentPassTxt.setText(Current_Pass.get(position));
        holder.NewPassTxt.setText(New_Pass.get(position));
        holder.ConfirmNewPassTxt.setText(Confirm_New_Pass.get(position));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, RegisterUpdateActivity.class);
                intent.putExtra("Id", Id.get(position));
                intent.putExtra("Name", Name.get(position));
                intent.putExtra("Email", Email.get(position));
                intent.putExtra("Current_Pass", Current_Pass.get(position));
                intent.putExtra("New_Pass", New_Pass.get(position));
                intent.putExtra("Confirm_New_Pass", Confirm_New_Pass.get(position));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView IdTxt, NameTxt, EmailTxt, CurrentPassTxt, NewPassTxt, ConfirmNewPassTxt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IdTxt = itemView.findViewById(R.id.id_txt);
            NameTxt = itemView.findViewById(R.id.name_txt);
            EmailTxt = itemView.findViewById(R.id.email_txt);
            CurrentPassTxt = itemView.findViewById(R.id.current_pass_txt);
            NewPassTxt = itemView.findViewById(R.id.new_pass_txt);
            ConfirmNewPassTxt = itemView.findViewById(R.id.confirm_new_pass_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
