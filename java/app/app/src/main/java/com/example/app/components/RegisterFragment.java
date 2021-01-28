package com.example.app.components;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.example.app.NavigationHost;
import com.example.app.R;

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);

        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text_register);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text_register);

        MaterialButton registerButton = view.findViewById(R.id.register_ok_button);
        MaterialButton codeButton = view.findViewById(R.id.code_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Amplify.Auth.signUp(
                        email,
                        password,
                        AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
                        result -> {
                            ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false); // Navigate to the next Fragment
                            Log.i("ExampleAPP", "Result: " + result.toString());
                        },
                        error -> {
                            Log.e("ExampleAPP", "Sign up failed", error);
                        }
                );

            }
        });

        codeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new ValidateLoginFragment(), false); // Navigate to the next Fragment
            }
        });

        return view;

    }
}