package com.example.app.components;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.example.app.API.Rest.APIInfo;
import com.example.app.interfaces.UserInfoRunInterface;
import com.example.app.models.User;
import com.example.app.R;

public class UserDataFragment extends Fragment {

    public UserDataFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_data, container, false);


        MaterialButton changeData = view.findViewById(R.id.button_change_user_data);

        TextInputEditText user_id = view.findViewById(R.id.user_data_user_id);
        TextInputEditText email = view.findViewById(R.id.user_data_user_email);
        TextInputEditText name = view.findViewById(R.id.user_data_user_name);
        TextInputEditText surname = view.findViewById(R.id.user_data_user_surname);


        APIInfo api = new APIInfo();

        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch(cognitoAuthSession.getIdentityId().getType()) {
                        case SUCCESS:


                            String accessToken = cognitoAuthSession.getUserPoolTokens().getValue().getIdToken();
                            api.getPersonalInfo(getContext(), accessToken, new UserInfoRunInterface() {
                                @Override
                                public void run(User user) {
                                    user_id.setText(user.getUserID());
                                    email.setText(user.getEmail());
                                    name.setText(user.getName());
                                    surname.setText(user.getSurname());
                                }
                            });
                            break;

                        case FAILURE:
                            Log.e("ExampleAPP", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
                    }
                },
                error -> {
                    Log.e("ExampleAPP", error.toString());
                }
        );


        changeData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String surnameText = surname.getText().toString();
                User u = new User(nameText, surnameText);

                Amplify.Auth.fetchAuthSession(
                        result -> {
                            AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                            switch(cognitoAuthSession.getIdentityId().getType()) {
                                case SUCCESS:
                                    String accessToken = cognitoAuthSession.getUserPoolTokens().getValue().getIdToken();
                                    api.postPersonalInfo(getContext(), accessToken, u, new UserInfoRunInterface() {
                                        @Override
                                        public void run(User user) {

                                        }
                                    });
                                    break;

                                case FAILURE:
                                    Log.e("ExampleAPP", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
                            }
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