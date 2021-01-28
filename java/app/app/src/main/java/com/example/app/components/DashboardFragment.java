package com.example.app.components;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;
import com.android.volley.RequestQueue;
import com.example.app.API.Rest.APIInfo;
import com.example.app.R;
import com.example.app.components.SubjectList.SubjectListGridItemDecoration;
import com.example.app.components.SubjectList.SubjectListRecyclerViewAdapter;
import com.example.app.interfaces.SubjectListRunInterface;
import com.example.app.interfaces.UserInfoRunInterface;
import com.example.app.models.Subject;
import com.example.app.models.User;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    TextView welcomeUser;
    RequestQueue queue;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //Set up TextView with a default message.
        welcomeUser = view.findViewById(R.id.textview_welcome_user);

        APIInfo api = new APIInfo();

        Amplify.Auth.fetchAuthSession(
                result -> {

                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    switch(cognitoAuthSession.getIdentityId().getType()) {
                        case SUCCESS:
                            // Get a valid user JWT.
                            String accessToken = cognitoAuthSession.getUserPoolTokens().getValue().getIdToken();
                            api.getPersonalInfo(getContext(), accessToken, new UserInfoRunInterface() {
                                @Override
                                public void run(User user) {
                                    // Refresh the TextView with a personalized message with it's name.
                                    welcomeUser.setText("Hola, " + user.getName().toUpperCase() + " \uD83D\uDC4B");
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


        /** SUBJECTS LIST **/
        ArrayList<Subject> subjectArrayList = new ArrayList<>();

        RecyclerView subjectRecyclerView = view.findViewById(R.id.subject_list_recycler_view);
        subjectRecyclerView.setHasFixedSize(true);
        subjectRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        SubjectListRecyclerViewAdapter adapterSubjectLIst = new SubjectListRecyclerViewAdapter(subjectArrayList);

        api.getSubjectList(getContext(), new SubjectListRunInterface() {
            @Override
            public void run(ArrayList<Subject> subject) {

                for (Subject each : subject){
                    subjectArrayList.add(each);
                }

                subjectRecyclerView.setAdapter(adapterSubjectLIst);
                adapterSubjectLIst.notifyDataSetChanged();

            }
        });

        int largeSubjectsList = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        int smallSubjectsLists = getResources().getDimensionPixelSize(R.dimen.grid_spacing_small);
        subjectRecyclerView.addItemDecoration(new SubjectListGridItemDecoration(largeSubjectsList, smallSubjectsLists));

        /** END SUBJECTS LIST **/

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

}