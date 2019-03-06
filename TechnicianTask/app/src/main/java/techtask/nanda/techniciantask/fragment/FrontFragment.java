package techtask.nanda.techniciantask.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import techtask.nanda.techniciantask.LoginActivity;
import techtask.nanda.techniciantask.R;
import techtask.nanda.techniciantask.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrontFragment extends Fragment {
    TextView tvSignIn, tvJoinNow;

    public FrontFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_front, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvJoinNow = view.findViewById(R.id.tv_join_now);
        tvSignIn = view.findViewById(R.id.tv_sign_in);

        init();
    }

    private void init() {
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame_content, new LoginFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        tvJoinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
