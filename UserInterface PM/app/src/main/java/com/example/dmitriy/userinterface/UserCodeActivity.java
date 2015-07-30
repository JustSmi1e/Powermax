package com.example.dmitriy.userinterface;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class UserCodeActivity extends Activity implements OnClickListener{
    EditText text;
    ViewGroup group;
    BaseInputConnection textFileInputConnection;
    ///final Button button1 = (Button)findViewById(R.id.digit1_button);
    ///final Button button2 = (Button)findViewById(R.id.digit2_button);
    ///final Button button3 = (Button)findViewById(R.id.digit3_button);
    ///final Button button4 = (Button)findViewById(R.id.digit4_button);
    ///final Button button5 = (Button)findViewById(R.id.digit5_button);
    ///final Button button6 = (Button)findViewById(R.id.digit6_button);
    ///final Button button7 = (Button)findViewById(R.id.digit7_button);
    ///final Button button8 = (Button)findViewById(R.id.digit8_button);
    ///final Button button9 = (Button)findViewById(R.id.digit9_button);
    ///final Button button_log = (Button)findViewById(R.id.digit0_button);
    ///final Button button_back = (Button)findViewById(R.id.digit0_button);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_code_screen_layout);
        ///button0 = (Button)findViewById(R.id.digit0_button);
        text = (EditText)findViewById(R.id.user_code_textview);
        text.requestFocus();
        textFileInputConnection = new BaseInputConnection(text, true);
        group = (ViewGroup)findViewById(R.id.button_grid);
        View v;
        for(int i = 0; i < group.getChildCount(); i++){
            v = group.getChildAt(i);
            if(v instanceof Button) v.setOnClickListener(this);
        }
        ///button0.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_code, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.digit0_button: text.append("0");
            case R.id.digit1_button: text.append("1");
            case R.id.digit2_button: text.append("2");
            case R.id.digit3_button: text.append("3");
            case R.id.digit4_button: text.append("4");
            case R.id.digit5_button: text.append("5");
            case R.id.digit6_button: text.append("6");
            case R.id.digit7_button: text.append("7");
            case R.id.digit8_button: text.append("8");
            case R.id.digit9_button: text.append("9");
            case R.id.backspace_button: textFileInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));



        }

    }
}
