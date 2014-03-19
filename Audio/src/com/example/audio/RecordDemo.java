package com.example.audio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * 
 * @author jankey
 * 
 */
public class RecordDemo extends Activity {


private Button record;
private MyDialog dialog;
private AudioRecorder mr;
private LinearLayout linear;
private MediaPlayer mediaPlayer;
private File directory;
private Button btn = null;


@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.record_audio);
mr = new AudioRecorder("jankey");
record = (Button) this.findViewById(R.id.record);
linear = (LinearLayout) this.findViewById(R.id.showViews);
record.setOnLongClickListener(new OnLongClickListener() {
@Override
public boolean onLongClick(View v) {
dialog = new MyDialog(RecordDemo.this, "正在录音");
try {
record.setText("正在录音...");
mr.start();
} catch (IOException e) {
e.printStackTrace();
}
dialog.show();
return false;
}
});
record.setOnTouchListener(new OnTouchListener() {
@Override
public boolean onTouch(View v, MotionEvent event) {
switch (event.getAction()) {
case MotionEvent.ACTION_UP:
try {
mr.stop();
record.setText("录音停止!");
} catch (IOException e) {
e.printStackTrace();
}
dialog.dismiss();
showView();
break;
}
return false;
}
});
}


private void showView() {
for (int i = 0; i < apklist.size(); i++) {
// num++;
btn = new Button(this);
btn.setBackgroundColor(Color.GRAY);
btn.setWidth(200);
btn.setHeight(50);
btn.setText("点击倾听");
btn.setOnClickListener(new OnClickListener() {
@Override
public void onClick(View v) {
playFile();
mediaPlayer = new MediaPlayer();
try {
mediaPlayer.setDataSource(directory.getAbsolutePath());
mediaPlayer.prepare();
} catch (IllegalArgumentException e) {
e.printStackTrace();
} catch (SecurityException e) {
e.printStackTrace();
} catch (IllegalStateException e) {
e.printStackTrace();
} catch (IOException e) {
e.printStackTrace();
}
mediaPlayer.start();
btn.setText("正在播放");
}
});
linear.addView(btn);
}
}


private void playFile() {
List<String> getFiles = GetFiles(
Environment.getExternalStorageDirectory() + "/", ".3gp", true);
for (String string : getFiles) {
System.out.println(string);
}
}


private List<String> apklist = new ArrayList<String>(); 
public List<String> GetFiles(String Path, String Extension,  
       boolean IsIterative) 
{  
   File[] files = new File(Path).listFiles();  
   for (int i = 0; i < files.length; i++) {  
       File f = files[i];  
       if (f.isFile()) {  
           if (f.getPath()  
                   .substring(f.getPath().length() - Extension.length())  
                   .equals(Extension))
               apklist.add(f.getPath());  
           if (!IsIterative)  
               break;  
       } else if (f.isDirectory() && f.getPath().indexOf("/.") == -1)   
           GetFiles(f.getPath(), Extension, IsIterative); 
   }  
   return apklist;  
}  
}
