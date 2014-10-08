package com.coding4lif3.sunshine.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.coding4lif3.sunshine.DetailActivity;
import com.coding4lif3.sunshine.R;
import com.coding4lif3.sunshine.SettingsActivity;
import com.coding4lif3.sunshine.data.Weather;
import com.coding4lif3.sunshine.data.WeatherDescription;
import com.coding4lif3.sunshine.network.NetworkManager;
import com.coding4lif3.sunshine.network.service.WeatherService;
import com.coding4lif3.sunshine.util.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by federicomonaco on 9/28/14.
 */
public class ForecastFragment extends Fragment {

    private ArrayAdapter<String> mForecastAdapter;

    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Adding for handle menu events
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //initializing listView
        mForecastAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.list_item_forecast_textview, new ArrayList<String>());
        final ListView mForecastListView = (ListView) rootView.findViewById(R.id.listview_forecast);
        mForecastListView.setAdapter(mForecastAdapter);

        mForecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String forecast = mForecastAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT, forecast);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        //update weather info
        updateWeather();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_refresh:
                //update weather info
                updateWeather();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * AsyncTask for fetch wheater
     */
    private class AsyncWheatherFetcher extends AsyncTask<String, Void, String[]> {

        String LOG_TAG = AsyncWheatherFetcher.class.getSimpleName();

        @Override
        protected String[] doInBackground(String... info) {
            Log.d(LOG_TAG, "Fetching data from remote");
            String[] weatherDesc = null;

            //retrieving location from preferences
            String location = info[0];
            String tUnits = info[1];

            //fetching and parsing weather data from server
            WeatherService mWeatherService = NetworkManager.getInstance(getActivity()).getAdapter().create(WeatherService.class);

            //weather without callback
            Weather weather = mWeatherService.weatherRequest(Long.parseLong(location), "json", tUnits, 7);

            return getFormattedWeatherInfos(weather.getWeatherDesc());
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        protected void onPostExecute(String... weatherDescriptions) {
            Log.d(LOG_TAG, "data fetched!");

            mForecastAdapter.clear();
            mForecastAdapter.addAll(new ArrayList<String>(Arrays.asList(weatherDescriptions)));
        }
    }

    /**
     * @param wdList
     * @return a String Array made of weather description like: "Day, Month number - State(clear) - maxTemp/minTemp"
     */
    private String[] getFormattedWeatherInfos(ArrayList<WeatherDescription> wdList) {
        String[] weatherDescriptionDays = new String[wdList.size()];
        int i = 0;
        for (WeatherDescription wd : wdList) {
            // For now, using the format "Day, description, hi/low"
            String day;
            String description;
            String highAndLow;

            //getting day
            day = getReadableDateString(wd.getDt());
            description = wd.getWeatherDetail().get(0).getMain();
            highAndLow = formatHighLows(wd.getTemp().getMax(), wd.getTemp().getMin());

            //creating entry
            weatherDescriptionDays[i] = day + " - " + description + " - " + highAndLow;
            i++;
        }

        return weatherDescriptionDays;

    }

    /**
     * The date/time conversion code is going to be moved outside the asynctask later,
     * so for convenience we're breaking it out into its own method now.
     *
     * @param time
     * @return
     */
    private String getReadableDateString(long time) {
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("E, MMM d");
        return format.format(date).toString();
    }

    /**
     * Prepare the weather high/lows for presentation.
     *
     * @param high
     * @param low
     * @return
     */
    private String formatHighLows(double high, double low) {
        // For presentation, assume the user doesn't care about tenths of a degree.
        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        String highLowStr = roundedHigh + "/" + roundedLow;
        return highLowStr;
    }

    /**
     * Update the current weather
     */
    private void updateWeather() {
        new AsyncWheatherFetcher().execute(Utility.getPreferenceLocation(getActivity()), Utility.getPreferenceTempUnits(getActivity()));
    }
}