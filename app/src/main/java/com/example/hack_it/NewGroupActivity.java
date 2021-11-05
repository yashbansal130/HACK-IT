package com.example.hack_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewGroupActivity extends AppCompatActivity {
    ArrayList<String> members;
    Button buttonSave;
    EditText editTextGroupName;
    Button submitGroup;
    ArrayList<String> groupName;
    ArrayList<ArrayList<String>> data;
    EditText newMemberEdit;
    TextView membersTextview;
    Button newMemberButton;
    private int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        buttonSave = findViewById(R.id.save_group_name);
        editTextGroupName = findViewById(R.id.new_group_edit);
        submitGroup = findViewById(R.id.submit_group);
        newMemberEdit = findViewById(R.id.new_member_edit);
        membersTextview = findViewById(R.id.member_text_view);
        newMemberButton = findViewById(R.id.add_new_member);
        c=0;
        members = new ArrayList<>();
        groupName = new ArrayList<>();
        data = new ArrayList<>();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=editTextGroupName.getText().toString();
                if(message!=null){
                    editTextGroupName.setEnabled(false);
                    groupName.add(message);
                }
                else{
                    Toast.makeText(NewGroupActivity.this, "Group Name can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        submitGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(groupName.isEmpty()){
                    Toast.makeText(NewGroupActivity.this, "Enter Group Name", Toast.LENGTH_SHORT).show();
                }
                if(members.isEmpty()){
                    Toast.makeText(NewGroupActivity.this, "Group Members cannot be None", Toast.LENGTH_SHORT).show();
                }
                else{
//                    Intent intent=new Intent();
//                    data.add(groupName);
//                    data.add(members);
//                    intent.putExtra("MESSAGE",data);
//                    setResult(2,intent);
//                    finish();//finishing activity
                }
            }
        });
        newMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newMember=newMemberEdit.getText().toString();
                String member = membersTextview.getText().toString();
                if(!Patterns.EMAIL_ADDRESS.matcher(newMember).matches()){
                    Toast.makeText(NewGroupActivity.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
                }else{
                    newMemberEdit.setText("");
                    members.add(member);
                    member+="\n"+newMember;
                    membersTextview.setText(member);
                    c+=1;
                }
                if(c==2){
                    newMemberButton.setClickable(false);
                    newMemberButton.setEnabled(false);
                }
            }
        });
    }
}