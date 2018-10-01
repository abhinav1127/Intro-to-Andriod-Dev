package edu.gatech.gtorg.gitmad.threads.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gatech.gtorg.gitmad.threads.R;

public class ThreadDetailsFragment extends Fragment {

    private static final String KEY_THREAD_NAME = "thread name instance state key";
    private static final String KEY_THREAD_DETAILS = "thread details instance state key";
    private static final String EMPTY_STRING = "";

    private String threadName;
    private String threadDetails;

    private TextView threadDescriptionTextView;


    public ThreadDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            threadName = savedInstanceState.getString(KEY_THREAD_NAME);
            threadDetails = savedInstanceState.getString(KEY_THREAD_DETAILS);

        } else {
            threadName = getString(R.string.choose_a_thread);
            threadDetails = EMPTY_STRING;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        setThreadUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_thread_details, container, false);

        threadDescriptionTextView = (TextView) rootView.findViewById(R.id.detailsTextView);

        return rootView;
    }

    public void setThread(String threadName, String threadDetails) {
        this.threadName = threadName;
        this.threadDetails = threadDetails;

        setThreadUI();
    }

    private void setThreadUI() {
        getActivity().setTitle(threadName);

        threadDescriptionTextView.setText(threadDetails);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_THREAD_NAME, threadName);
        outState.putString(KEY_THREAD_DETAILS, threadDetails);
    }
}
