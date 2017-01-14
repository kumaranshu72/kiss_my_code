package example.com.stimmung;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;


public class SetRingtoneActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button buttonPlayPause, buttonQuit;
    TextView textState;

    private int stateMediaPlayer;
    private final int stateMP_Error = 0;
    private final int stateMP_NotStarter = 1;
    private final int stateMP_Playing = 2;
    private final int stateMP_Pausing = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ringtone);
        openFile();
    }
    void openFile()
    {
        String path = getApplication().getFilesDir().getAbsolutePath();
    }
//    void setRingtone()
//    {
//        File k = new File(path, "mysong.mp3"); // path is a file to /sdcard/media/ringtone
//
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
//        values.put(MediaStore.MediaColumns.TITLE, "My Song title");
//        values.put(MediaStore.MediaColumns.SIZE, 215454);
//        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
//        values.put(MediaStore.Audio.Media.ARTIST, "Madonna");
//        values.put(MediaStore.Audio.Media.DURATION, 230);
//        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
//        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
//        values.put(MediaStore.Audio.Media.IS_ALARM, false);
//        values.put(MediaStore.Audio.Media.IS_MUSIC, false);
//
////Insert it into the database
//        Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
//        Uri newUri = this.getContentResolver().insert(uri, values);
//
//        RingtoneManager.setActualDefaultRingtoneUri(
//                myActivity,
//                RingtoneManager.TYPE_RINGTONE,
//                newUri
//        );
//    }
}

