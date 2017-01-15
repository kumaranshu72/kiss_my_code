package example.com.stimmung;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private MyPhoneReceiver reciever;
    static final int REQUEST_CODE=1;
    private String mood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            }
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE);
            }
            if (checkSelfPermission(Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS}, REQUEST_CODE);
            }
            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
            }
            if (checkSelfPermission(Manifest.permission.WAKE_LOCK) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WAKE_LOCK}, REQUEST_CODE);
            }
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
            }

        }
        TextView moodView =(TextView) findViewById(R.id.textView3);
        mood = (String)moodView.getText();
    }
    public void openDetails(View view)
    {
        RadioButton r1= (RadioButton) findViewById(R.id.radioButton);
        RadioButton r2= (RadioButton) findViewById(R.id.radioButton4);
        RadioButton r3= (RadioButton) findViewById(R.id.radioButton5);
        if(r1.isChecked())
        {
            mood =(String)r1.getText();
        }
        else if(r2.isChecked())
        {
            mood =(String)r2.getText();
        }
        else if(r3.isChecked())
        {
            mood =(String)r3.getText();
        }

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("mood",mood);
        startActivity(intent);
    }
}
