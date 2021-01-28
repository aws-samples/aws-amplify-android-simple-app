package com.example.app.components;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amplifyframework.core.Amplify;
import com.example.app.InitActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.example.app.HomeActivity;
import com.example.app.NavigationHost;
import com.example.app.R;

/**
 * LoginFragment is the first fragment to be rendered in the {@link InitActivity}
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        MaterialButton registerButton = view.findViewById(R.id.register_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // When button is pressed, save the data in two variables and passed it to Amplify.
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Amplify.Auth.signIn(
                        email,
                        password,
                        result -> {
                            if ( result.isSignInComplete() ){
                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                // If your code reach this if, it's because the user is already registered
                                // but has not validated email or password if MFA is enable.

                                Log.d("Amplify-Login", "User Sign In is not complete.");
                            }
                        },
                        error -> {
                            Log.e("Amplify-Login", error.toString());
                        }
                );



            }
        });

        // When button Registered is pressed, render RegisterFragment in the activity.
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new RegisterFragment(), false); // Navigate to the next Fragment
            }
        });

        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });
        return view;
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }

}
