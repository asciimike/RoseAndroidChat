package com.google.mpmcdonald.androidchatslides;

import android.app.ListActivity;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends ListActivity implements View.OnClickListener{

    private EditText mMessageInput;
    private ImageButton mSendButton;
    private ArrayList<Message> mMessages;
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mMessageInput = (EditText)findViewById(R.id.message_edit_text);
        this.mSendButton = (ImageButton)findViewById(R.id.message_send_button);
        this.mMessages = new ArrayList<Message>();

        ListAdapter messageAdapter = new MessageAdapter(this , R.layout.message_layout , mMessages);
        this.setListAdapter(messageAdapter);

        Firebase.setAndroidContext(this);
        this.mRef = new Firebase("https://androidchat.firebaseio-demo.com");
        this.mRef.child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> data = (Map<String, Object>)dataSnapshot.getValue();
                Message addedMessage = new Message(data);
                MainActivity.this.mMessages.add(addedMessage);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        this.mSendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_send_button:
                String messageText = this.mMessageInput.getText().toString();
                Message newMessage = new Message("Username", messageText);
                this.mRef.child("chat").push().setValue(newMessage.toMap());
                this.mMessageInput.setText("");
                break;

            default:
                /* This will receive all other onClick() events */
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
