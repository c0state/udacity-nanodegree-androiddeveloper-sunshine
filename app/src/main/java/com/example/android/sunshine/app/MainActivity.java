package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;

import static com.example.android.sunshine.app.R.id.container;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Logger.v("onRestoreInstanceState() called...");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.v("onSaveInstanceState() called...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.v("onRestart() called...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.v("onDestroy() called...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.v("onResume() called...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.v("onStop() called...");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.v("onStart() called...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.v("onPause() called...");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.v("onCreate() called...");

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Logger.v("onCreateOptionsMenu() called...");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.v("onOptionsItemSelected() called...");

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(
                   getBaseContext(), SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
