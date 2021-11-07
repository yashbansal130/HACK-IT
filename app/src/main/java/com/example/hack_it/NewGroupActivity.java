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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    RequestQueue requestQueue;
    Map<String , String> mails;
    Map<String , String> newMembers;
    String url = "http://192.168.1.10:5000/users";
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
        newMembers = new HashMap<>();
        getAllUsers();
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
                else if(newMembers.isEmpty()){
                    Toast.makeText(NewGroupActivity.this, "Group Members cannot be None", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent();
                    data.add(groupName);
                    int n = newMembers.size();
                    for (Map.Entry mapElement : newMembers.entrySet()) {
                        String id = (String) mapElement.getValue();
                        members.add(id);
                    }
                    data.add(members);
                    intent.putExtra("MESSAGE",data);
                    setResult(2,intent);
                    finish();//finishing activity
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
                }
                else if(newMember.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                    Toast.makeText(NewGroupActivity.this, "Email cannot be of the group leader", Toast.LENGTH_SHORT).show();
                }
                else if(!newMembers.containsKey(newMember) && mails.containsKey(newMember)){
                    newMemberEdit.setText("");
                    newMembers.put(newMember, mails.get(newMember));
                    member+="\n"+newMember;
                    membersTextview.setText(member);
                    c+=1;
                }
                else{
                    Toast.makeText(NewGroupActivity.this, "This user does not exist or already addded please try again", Toast.LENGTH_SHORT).show();
                }
                if(c==2){
                    newMemberButton.setClickable(false);
                    newMemberButton.setEnabled(false);
                }
            }
        });
    }

    private void getAllUsers(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = response.names();
                mails = new HashMap<>();
                int n = jsonArray.length();
                for(int i=0;i<n;i++){
                    try {
                        String id =jsonArray.getString(i);
                        JSONObject jsonObject = response.getJSONObject(id);
                        mails.put(jsonObject.getString("email"), jsonObject.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}