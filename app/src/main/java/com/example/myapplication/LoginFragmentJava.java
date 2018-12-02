package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginFragmentJava extends Fragment {

    // Objects for tracking text changes (filled username and password)
    EditText username, password;
    Button login_button;
    View view;

    // Firebase reference
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable editable) {
            // Check input fields for completion
            checkFields();
        }
    };

    // Check if both username and password fields are populated, and enable login button
    void checkFields() {

        Boolean complete = username.getText().toString().trim().length() > 0
                && password.getText().toString().trim().length() > 0;

        if (complete) {
            login_button.setEnabled(true);
        } else {
            login_button.setEnabled(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.login_screen, container, false);

        // Get references to screen objects
        username = view.findViewById(R.id.login_id_input);
        password = view.findViewById(R.id.password_input);
        login_button = view.findViewById(R.id.login_button);

        // Disable login button to start
        login_button.setEnabled(false);

        // Add text change listener to text fields
        username.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);

        // When the login button is clicked
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get username and password values from fields
                final String username_val = username.getText().toString();
                // String password_val = username.getText().toString();

                // Check username is in database (no password check)
                Query query_ref = database.child("users").orderByChild("username").equalTo(username_val);

                // Read relevant values from the database
                query_ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast toast = Toast.makeText(getContext(), "Welcome "+username_val, Toast.LENGTH_SHORT);
                            toast.show();

                            // TODO WILL: Set user information in UserPreferences

                            // Move to home screen on successful login
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        // No result found, not a valid user
                        else {
                            Toast toast = Toast.makeText(getContext(), "ChorePojo Not Found", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast toast = Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.w("DB", "Failed to read value.", error.toException());
                    }
                });
            }
        });

        return view;
    }

}