package com.example.calendar3;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class EventRepository {

    private EventDatabaseHelper dbHelper;

    public EventRepository(Context context) {
        dbHelper = new EventDatabaseHelper(context);
    }

    public void addEvent(long date, String title, String description) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("title", title);
        values.put("description", description);
        db.insert("events", null, values);
    }
}
