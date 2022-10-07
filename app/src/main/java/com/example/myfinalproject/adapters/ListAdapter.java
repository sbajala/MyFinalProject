package com.example.myfinalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.myfinalproject.Book;
import com.example.myfinalproject.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Book> {

    private int idLayout;
    private Context context;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        idLayout = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Getting book at position x
        Book book = getItem(position);

        //If convertView is null, we are setting it
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(idLayout, parent, false);
        }

        TextView bookTitle = convertView.findViewById(R.id.bookTitle);
        bookTitle.setText(book.getTitle());

        TextView bookAuthor = convertView.findViewById(R.id.bookAuthor);
        bookAuthor.setText(book.getAuthor());

        TextView bookNote = convertView.findViewById(R.id.bookNote);
        bookNote.setText(book.getNote());

        ImageView notStartedIcon = convertView.findViewById(R.id.not_started_icon);
        ImageView inProgressIcon = convertView.findViewById(R.id.in_progress_icon);
        ImageView completedIcon = convertView.findViewById(R.id.completed_icon);

        //Setting progress icon color according to reading status
        String readingStatus = book.getReadingStatus().name();
        switch(readingStatus) {
            case "NOT_STARTED":
                notStartedIcon.setColorFilter(ContextCompat.getColor(context, R.color.blue));
                inProgressIcon.setColorFilter(ContextCompat.getColor(context, R.color.light_brown));
                completedIcon.setColorFilter(ContextCompat.getColor(context, R.color.light_brown));
                break;
            case "IN_PROGRESS":
                notStartedIcon.setColorFilter(ContextCompat.getColor(context, R.color.light_brown));
                inProgressIcon.setColorFilter(ContextCompat.getColor(context, R.color.yellow));
                completedIcon.setColorFilter(ContextCompat.getColor(context, R.color.light_brown));
                break;
            case "COMPLETED":
                notStartedIcon.setColorFilter(ContextCompat.getColor(context, R.color.light_brown));
                inProgressIcon.setColorFilter(ContextCompat.getColor(context, R.color.light_brown));
                completedIcon.setColorFilter(ContextCompat.getColor(context, R.color.green));
                break;
        }

        return convertView;
    }

}
