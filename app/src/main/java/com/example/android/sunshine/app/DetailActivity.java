package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.sunshine.app.R.id.container;

public class DetailActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(getBaseContext(), SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        else if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        public static final String FORECAST_TEXT = "FORECAST_TEXT";
        private String SHARE_HASHTAG = "#SunshineApp";

        public PlaceholderFragment() {
            setHasOptionsMenu(true);
        }

        private Intent createShareWeatherIntent() {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            return shareIntent;
        }

        private String getShareWeatherText() {
            TextView forecastText = (TextView)getActivity().findViewById(R.id.detail_forecasttext);
            return forecastText.getText() + " " + SHARE_HASHTAG;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            inflater.inflate(R.menu.share, menu);
            MenuItem item = menu.findItem(R.id.action_share);
            ShareActionProvider shareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(item);
            Intent shareIntent = createShareWeatherIntent();
            shareIntent.putExtra(Intent.EXTRA_TEXT, getShareWeatherText());
            if (shareActionProvider != null) {
                shareActionProvider.setShareIntent(shareIntent);
            }
            else {
                Toast.makeText(getContext(), "Could not find apps to share weather with!", 3).show();

            }
            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            TextView forecastText = (TextView)rootView.findViewById(
                    R.id.detail_forecasttext);
            forecastText.setText(getActivity().getIntent().getStringExtra(FORECAST_TEXT));
            return rootView;
        }
    }
}
