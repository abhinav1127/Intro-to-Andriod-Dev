package edu.gatech.gtorg.gitmad.threads.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import edu.gatech.gtorg.gitmad.threads.R;

public class ThreadsListFragment extends Fragment {

    private static final String KEY_THREAD_NAMES = "thread names key";


    private OnThreadClickedListener clickListener;
    private String[] threadNames;

    /*
        public default constructor necessary for the Android OS to manage
        the application's state. See
        http://stackoverflow.com/questions/25984054/android-fragments-is-empty-constructor-really-required
     */
    public ThreadsListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof OnThreadClickedListener) {
            clickListener = (OnThreadClickedListener) getActivity();

        } else {
            throw new ClassCastException("Activity that contains Fragment ThreadsListFragment must implement OnThreadClickedListener in order to receive click events");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        threadNames = getActivity().getResources().getStringArray(R.array.thread_names);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_threads_list, container, false);

        final String[] threadNames = {"1", "2", "3"};

        ListAdapter threadTitleAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, threadNames);


        ListView threadsListView = (ListView) rootView.findViewById(R.id.threadsListView);

        threadsListView.setAdapter(threadTitleAdapter);

        threadsListView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickListener.threadClicked(threadNames[position], position);

            }
        });


        // Create an array adapter that allows threadNames[] to be displayed in our listView
        // Set our Adapter to be used by our ListView

        // set the OnItemClickedListener to call clickListener.threadClicked();

        return rootView;
    }

    public interface OnThreadClickedListener {
        public void threadClicked(String threadName, int threadIndex);
    }
}
