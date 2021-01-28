package com.example.app.components;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.amplifyframework.core.Amplify;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.example.app.NavigationHost;
import com.example.app.R;

public class ValidateLoginFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.validate_login_fragment, container, false);
        final TextInputEditText validationCodeEditText = view.findViewById(R.id.validate_code_edit_text);
        final TextInputEditText validationUsernameEditText = view.findViewById(R.id.username_validate_code);

        MaterialButton nextButton = view.findViewById(R.id.validate_code_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = validationUsernameEditText.getText().toString();
                String validationCode = validationCodeEditText.getText().toString();

                Amplify.Auth.confirmSignUp(
                        email,
                        validationCode,
                        result -> {
                            ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false); // Navigate to the next Fragment
                            Log.i("ExampleAPP", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                        },
                        error -> {
                            Log.e("ExampleAPP", error.toString());
                        }
                );


            }
        });



        return view;
    }


}
