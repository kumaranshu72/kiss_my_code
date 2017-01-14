package example.com.stimmung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity {
    public static ArrayAdapter<String> mForecastAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
    }
    private void getList() {
        String[] forcastArray = {"kathmandu","the dark knights","velle log"};
        List<String> moviesForcast = new ArrayList<String>(Arrays.asList(forcastArray));
        mForecastAdapter = new ArrayAdapter<String>(this, R.layout.list_item_forcast, R.id.list_item_forcast_text_view, moviesForcast);
        ListView listView = (ListView) findViewById(R.id.list_view_forcast_articles);
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
    }
}
