package example.com.stimmung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setOnClickListeners();

    }
    private void setOnClickListeners(){
        LinearLayout linearLayout =(LinearLayout) findViewById (R.id.movies_layer);
        listen(linearLayout);
        linearLayout =(LinearLayout) findViewById (R.id.songs_layer);
        listen(linearLayout);
        linearLayout =(LinearLayout) findViewById (R.id.articles_layer);
        listen(linearLayout);
        linearLayout =(LinearLayout) findViewById (R.id.set_ringtone_layer);
        listen(linearLayout);
    }
    private void listen(View view)
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "hello", Toast.LENGTH_LONG).show();
            }
        });
    }
}
