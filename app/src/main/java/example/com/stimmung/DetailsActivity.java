package example.com.stimmung;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.provider.MediaStore.MediaColumns;
import java.io.File;

public class DetailsActivity extends AppCompatActivity {
    final static int RQS_OPEN_DOCUMENT = 1;
    final static int RQS_GET_CONTENT = 2;
    final static int RQS_PICK = 3;
    final static int RQS_PERMISSION_READ_EXTERNAL_STORAGE = 4;
    final static int MY_PERMISSIONS_REQUEST_WRITE_SETTINGS = 5;
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
            public void onClick(View view) {
                Intent openDetail;
                if(view.getId()==R.id.movies_layer)
                {
                    openDetail = new Intent(getApplication(),MoviesActivity.class);
                    Toast.makeText(getApplication(), "Movies", Toast.LENGTH_LONG).show();
                    startActivity(openDetail);
                }
                else if(view.getId()==R.id.songs_layer){
                    openDetail = new Intent(getApplication(),SongsActivity.class);
                    Toast.makeText(getApplication(), "Songs", Toast.LENGTH_LONG).show();
                    startActivity(openDetail);
                }
                else if(view.getId()==R.id.articles_layer){
                    openDetail = new Intent(getApplication(),ArticlesActivity.class);
                    Toast.makeText(getApplication(), "articles", Toast.LENGTH_LONG).show();
                    startActivity(openDetail);
                }
                else if(view.getId()==R.id.set_ringtone_layer){
                    String path = Environment.getExternalStorageDirectory()+"/musicc";
                    File k = new File(path, "localtrainaogetumkabhi.mp3"); // path is a file to /sdcard/media/ringtone
                    if(k.exists()) {
                        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select ringtone for notifications:");
                        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
                        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
                        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                        startActivityForResult(intent, 999);
                    }
//                        if(Build.VERSION.SDK_INT >=23  ){
//
//                            if (checkSelfPermission(Manifest.permission.WRITE_SETTINGS)
//                                    != PackageManager.PERMISSION_GRANTED) {
//                                Toast.makeText(getApplicationContext(),"Granting permissions",Toast.LENGTH_SHORT).show();
//                                requestPermissions(new String[]{Manifest.permission.WRITE_SETTINGS},
//                                        MY_PERMISSIONS_REQUEST_WRITE_SETTINGS);
//
//                                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                                // app-defined int constant
//
//
//                            }

//                        }
//
//                        Toast.makeText(getApplicationContext(),"exists",Toast.LENGTH_SHORT).show();
//                        ContentValues values = new ContentValues();
//                        values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
//                        values.put(MediaStore.MediaColumns.TITLE, "localtrainaogetumkabhi");
//
//                        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
//                        values.put(MediaStore.Audio.Media.ARTIST, "Local train");
//                        values.put(MediaStore.Audio.Media.DURATION, k.length());
//                        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
//                        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
//                        values.put(MediaStore.Audio.Media.IS_ALARM, false);
//                        values.put(MediaStore.Audio.Media.IS_MUSIC, false);
//                        openAndroidPermissionsMenu();
////Insert it into the database
//                        Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
//                        Uri newUri = getApplication().getContentResolver().insert(uri, values);
//
//                        RingtoneManager.setActualDefaultRingtoneUri(
//                                getApplication(),
//                                RingtoneManager.TYPE_RINGTONE,
//                                newUri
//                        );
//                        newUri =RingtoneManager.getActualDefaultRingtoneUri(getApplication(),RingtoneManager.TYPE_RINGTONE);
//                        Toast.makeText(getApplicationContext(),"ringtone really set"+newUri.toString(),Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"not exist ",Toast.LENGTH_SHORT).show();

                    }
//                    Intent intent;
//                    intent = new Intent();
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
////                    intent.addCategory(Intent.CATEGORY_OPENABLE);
//                    intent.setType("audio/*");
//                    startActivityForResult(Intent.createChooser(intent, "Title"), 1);
                    Toast.makeText(getApplication(), "ringtone set", Toast.LENGTH_LONG).show();
                }

        });
    }

    private int openAndroidPermissionsMenu() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getApplication().getPackageName()));
//        startActivity(intent);
        startActivityForResult(intent,1);
        return 1;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_SETTINGS: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"permission granted",Toast.LENGTH_SHORT).show();
                    String path = Environment.getExternalStorageDirectory()+"/musicc";
                    File k = new File(path, "localtrainaogetumkabhi.mp3");
                    ContentValues value=new ContentValues();
                    value.put(MediaColumns.DATA, k.getAbsolutePath());
                    value.put(MediaColumns.TITLE, k.getName());
                    value.put(MediaColumns.SIZE, k.length());
                    value.put(MediaColumns.MIME_TYPE,"audio/*");
                    value.put(Audio.Media.ARTIST, "artist");
                    value.put(Audio.Media.DURATION, 500);
                    value.put(Audio.Media.IS_ALARM, false);
                    value.put(Audio.Media.IS_MUSIC, false);
                    value.put(Audio.Media.IS_NOTIFICATION, false);
                    value.put(Audio.Media.IS_RINGTONE, true);
                    ContentResolver cr=getContentResolver();
                    Uri url=Audio.Media.getContentUriForPath(k.getAbsolutePath());
                    Uri addedUri=cr.insert(url, value);
                    // Set default ring tone

                    RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_RINGTONE,addedUri);


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if (uri != null) {
                String ringTonePath = uri.toString();
                RingtoneManager.setActualDefaultRingtoneUri(
                        getApplication(),
                        RingtoneManager.TYPE_RINGTONE,
                        uri);
            }
        }
    }
//        if (requestCode == 1) {
//            if(requestCode == RQS_OPEN_DOCUMENT
//                    || requestCode == RQS_GET_CONTENT
//                    || requestCode == RQS_PICK){
//                String result=data.getData().getPath();
//                Toast.makeText(getApplication(),result,Toast.LENGTH_SHORT).show();
//            }
//            if (resultCode == Activity.RESULT_CANCELED) {
//                Toast.makeText(getApplication(),"11134234",Toast.LENGTH_SHORT).show();
//            }
//
//            ContentValues values = new ContentValues();
//            values.put(MediaStore.MediaColumns.TITLE, "My Song title");
//
//            values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
//
//            values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
//            values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
//            values.put(MediaStore.Audio.Media.IS_ALARM, false);
//            values.put(MediaStore.Audio.Media.IS_MUSIC, false);
//            Uri newUri = this.getContentResolver().insert(data.getData(), values);
//            RingtoneManager.setActualDefaultRingtoneUri(
//                    getApplication(),
//                    RingtoneManager.TYPE_RINGTONE,
//                    newUri
//            );
//        }
//
//    }
}
