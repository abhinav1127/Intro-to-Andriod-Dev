package edu.gatech.gtorg.gitmad.threads.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import edu.gatech.gtorg.gitmad.threads.R;
import edu.gatech.gtorg.gitmad.threads.fragment.ThreadDetailsFragment;
import edu.gatech.gtorg.gitmad.threads.fragment.ThreadsListFragment;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity implements ThreadsListFragment.OnThreadClickedListener {

    private static final String TAG_THREADS_LIST_FRAGMENT = "threads list fragment in main activity tag";


    private String[] threadDetailsStrings;

    private ThreadDetailsFragment threadDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeToolbar();

        if (isScreenLandscape()) {
            threadDetailsFragment = (ThreadDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.threadDetailsFragment);
        }

        loadThreadDetailsStrings();
    }

    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!isScreenLandscape()) {
            setTitle(getString(R.string.choose_a_thread));
        }
    }

    private void loadThreadDetailsStrings() {
        threadDetailsStrings = getResources().getStringArray(R.array.thread_descriptions);
    }

    @Override
    public void threadClicked(String threadName, int threadIndex) {
        Toast.makeText(this, threadName + " was clicked", Toast.LENGTH_SHORT).show();

        if (isScreenLandscape()) {
            threadDetailsFragment.setThread(threadName, threadDetailsStrings[threadIndex]);

        } else {
            startThreadDetailsActivity(threadName, threadDetailsStrings[threadIndex]);
        }
    }

    private void startThreadDetailsActivity(String threadName, String threadDescription) {
        Intent threadDetailsActivityIntent = new Intent(this, ThreadDetailsActivity.class);

        threadDetailsActivityIntent.putExtra(ThreadDetailsActivity.KEY_THREAD_NAME, threadName);
        threadDetailsActivityIntent.putExtra(ThreadDetailsActivity.KEY_THREAD_DESCRIPTION, threadDescription);

        startActivity(threadDetailsActivityIntent);
    }

    private boolean isScreenLandscape() {
        return getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE;
    }
}
