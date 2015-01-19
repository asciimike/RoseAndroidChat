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

import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends ListActivity implements View.OnClickListener{

    private EditText mMessageInput;
    private ImageButton mSendButton;
    private ArrayList<Message> mMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mMessageInput = (EditText)findViewById(R.id.message_edit_text);
        this.mSendButton = (ImageButton)findViewById(R.id.message_send_button);
        this.mMessages = new ArrayList<Message>();

        ListAdapter messageAdapter = new MessageAdapter(this , R.layout.message_layout , mMessages);
        this.setListAdapter(messageAdapter);

        this.mSendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_send_button:
                String messageText = this.mMessageInput.getText().toString();
                Message newMessage = new Message("Username", messageText);
                this.mMessages.add(newMessage);
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
