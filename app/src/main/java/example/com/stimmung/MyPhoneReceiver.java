package example.com.stimmung;

/**
 * Created by Anshu Kumar on 1/15/2017.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyPhoneReceiver extends BroadcastReceiver{
    MediaRecorder recorder;
    TelephonyManager telephony;
    boolean recordStarted;
    static Context context;
    File audioFile = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            //Toast.makeText(MyPhoneReceiver.context, "ringing", Toast.LENGTH_LONG).show();
        }
        if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
           // Toast.makeText(MyPhoneReceiver.context, "idle", Toast.LENGTH_LONG).show();
            //Toast.makeText(context, "recordStarted: " + recordStarted, Toast.LENGTH_SHORT).show();
            if (recordStarted) {
                recorder.stop();
                recorder.release();
                recordStarted = false;
            }
        }
        if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
           // Toast.makeText(MyPhoneReceiver.context, "offhook", Toast.LENGTH_LONG).show();
            recordCall();
        }

    }

    public void onDestroy() {
        telephony.listen(null, PhoneStateListener.LISTEN_NONE);
    }

    private void recordCall() {
        File sampleDir = Environment.getExternalStorageDirectory();
        try {
            audioFile = File.createTempFile("sound" + System.currentTimeMillis(), ".3gp", sampleDir);
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            /*recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
            recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);*/
            recorder.setOutputFile(audioFile.getAbsolutePath());
            try {
                recorder.prepare();
                recorder.start();
            } catch(Exception e) {
                e.printStackTrace();
            }
            //Toast.makeText(context, "started recording", Toast.LENGTH_LONG).show();
            recordStarted = true;
            //Toast.makeText(context, "recordStarted: " + recordStarted, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(context, "Unable to create file", Toast.LENGTH_LONG).show();
            return;
        }
    }
}

