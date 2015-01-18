package com.google.mpmcdonald.androidchatslides;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mpmcdonald on 1/17/15.
 */
public class MessageAdapter extends ArrayAdapter<Message> {
    Context context;
    int layoutResourceId;
    ArrayList<Message> messages;

    public MessageAdapter(Context context, int layoutResourceId, ArrayList<Message> messages) {
        super(context, layoutResourceId, messages);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.messages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MessageHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new MessageHolder();
            holder.user = (TextView)row.findViewById(R.id.user_text_view);
            holder.text = (TextView)row.findViewById(R.id.text_text_view);

            row.setTag(holder);
        }
        else
        {
            holder = (MessageHolder)row.getTag();
        }

        Message message = this.messages.get(position);
        Resources res = this.context.getResources();
        holder.user.setText(String.format(res.getString(R.string.user_format), message.getUser()));
        holder.text.setText(String.format(res.getString(R.string.text_format), message.getText()));

        return row;
    }

    static class MessageHolder
    {
        TextView user;
        TextView text;
    }
}
