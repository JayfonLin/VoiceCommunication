package com.example.audio;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


/**
 * 
 * @author jankey
 *
 */
public class MyDialog extends Dialog{


private Context context;
private String ly;
private Button lyss;

public MyDialog(Context context,String str) {
super(context);
this.context = context;
this.ly = str;
}


@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
requestWindowFeature(Window.FEATURE_NO_TITLE);
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
WindowManager.LayoutParams.FLAG_FULLSCREEN);
setContentView(R.layout.my_dialog);
lyss = (Button) this.findViewById(R.id.lys);
lyss.setText(ly);
lyss.setBackgroundColor(Color.BLUE);
}

}
