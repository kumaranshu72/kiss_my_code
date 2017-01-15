package example.com.stimmung;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.resource;

public class MoviesActivity extends AppCompatActivity {
    public static MoviesAdapter mForecastAdapter;
    private String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        mood=getIntent().getStringExtra("mood");
        getMovies(mood);
        //getList();
    }

    /*private void getList() {
        String[] forcastArray = {"kathmandu","the dark knights","velle log"};
        List<String> moviesForcast = new ArrayList<String>(Arrays.asList(forcastArray));
        mForecastAdapter = new ArrayAdapter<String>(this, R.layout.list_item_forcast, R.id.list_item_forcast_text_view, moviesForcast);
        ListView listView = (ListView) findViewById(R.id.list_view_forcast_movies);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String forecast = mForecastAdapter.getItem(position);
                    Toast.makeText(getApplicationContext(),"list click",Toast.LENGTH_SHORT).show();
//                Intent detail = new Intent(getApplicationContext(),WeatherDetailActivity.class);
//                detail.putExtra(Intent.EXTRA_INTENT,forecast);
//                startActivity(detail);
            }
        });
    }*/
    void getMovies(String genre) {

        String apiKeyForMovies = "c5536ad3-087f-4816-bd38-26683401de08";
        String url = "http://imdb.wemakesites.net/api/search?q=" + genre + "&api_key=" + apiKeyForMovies;
        System.out.println("url" + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    String movieTitle, movieThumbnail, movieUrl;

                    @Override
                    public void onResponse(String response) {
                        JSONObject jsn = null;

                        //tx.setText(response);

                        try {
                            jsn = new JSONObject(response);
                            if (jsn.getString("status").equalsIgnoreCase("success")) {
                                JSONArray j = jsn.getJSONObject("data").getJSONObject("results").getJSONArray("titles");
                                System.out.println("here is the data" + j);
                                ArrayList<MovieItem> movieItems = new ArrayList<MovieItem>();

                                for (int i = 0; i < j.length(); i++) {
                                    JSONObject title = j.getJSONObject(i);
                                    movieTitle = title.getString("title");
                                    movieUrl = title.getString("url");
                                    movieThumbnail = title.getString("thumbnail");
                                    movieItems.add(i, new MovieItem(movieTitle, movieUrl, movieThumbnail));
                                    Log.v("List" + i + ": ", movieTitle + " " + movieUrl + " " + movieThumbnail);

                                }
                                mForecastAdapter = new MoviesAdapter(getApplicationContext(),R.layout.movie_list_item, movieItems);
                                ListView listView = (ListView) findViewById(R.id.list_view_forcast_movies);
                                listView.setAdapter(mForecastAdapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        MovieItem forecast = mForecastAdapter.getItem(position);
                                        Toast.makeText(getApplicationContext(), "list click", Toast.LENGTH_SHORT).show();
//                                    Intent detail = new Intent(getApplicationContext(),WeatherDetailActivity.class);
//                                    detail.putExtra(Intent.EXTRA_INTENT,forecast);
//                                    startActivity(detail);
                                    }
                                });


                            } else {
                                //give an error message instead
                            }

                        } catch (JSONException e) {
                            Log.v("Error in json converion", e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

class MoviesAdapter extends ArrayAdapter<MovieItem> {
    private ArrayList<MovieItem> dataSet;
    Context mContext;
    public MoviesAdapter(Context context, int resource, ArrayList<MovieItem> items) {
        super(context, resource, items);
        this.dataSet=items;
        mContext = context;
    }

    public MoviesAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);
        }

        MovieItem currMovieItem = getItem(position);
        Log.v("pos" + position, " --");
        TextView movieTitle = (TextView) listItemView.findViewById(R.id.movie_title);
        TextView movieUrl = (TextView) listItemView.findViewById(R.id.movie_url);
        ImageView movieThumbnail = (ImageView) listItemView.findViewById(R.id.movie_thumbnail);
        movieTitle.setText(currMovieItem.getTitle());
        movieUrl.setText(currMovieItem.getUrl());
        Log.v("list item:" + position, currMovieItem.getTitle() + " " + currMovieItem.getUrl());
        return listItemView;
    }
}

class MovieItem {
    String nTitle, nUrl, nThumbnail;

    MovieItem(String title, String url, String thumbnail) {
        nTitle = title;
        nUrl = url;
        nThumbnail = thumbnail;
    }

    String getTitle() {
        return nTitle;
    }

    String getUrl() {
        return nUrl;
    }

    String getThumbnail() {
        return nThumbnail;
    }
}