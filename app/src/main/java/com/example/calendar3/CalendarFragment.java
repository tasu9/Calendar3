package com.example.calendar3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CalendarView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private TextView selectedDate;
    private long selectedDateInMillis;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        selectedDate = view.findViewById(R.id.selectedDate);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            selectedDateInMillis = calendarView.getDate();
            selectedDate.setText("Selected Date: " + year + "/" + (month + 1) + "/" + dayOfMonth);
            showAddEventDialog();
        });

        return view;
    }

    private void showAddEventDialog() {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View dialogView = inflater.inflate(R.layout.dialog_add_event, null);

        EditText editTextEventTitle = dialogView.findViewById(R.id.editTextEventTitle);
        EditText editTextEventDescription = dialogView.findViewById(R.id.editTextEventDescription);

        new AlertDialog.Builder(requireContext())
                .setTitle("Add Event")
                .setView(dialogView)
                .setPositiveButton("Save", (dialog, which) -> {
                    String title = editTextEventTitle.getText().toString();
                    String description = editTextEventDescription.getText().toString();
                    saveEvent(selectedDateInMillis, title, description);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void saveEvent(long date, String title, String description) {
        EventRepository repository = new EventRepository(requireContext());
        repository.addEvent(date, title, description);
        System.out.println("Event saved: " + title + " on " + date);

    }

}