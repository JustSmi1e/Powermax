package com.example.dmitriy.userinterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class UserCodeActivity extends Activity implements OnClickListener{
    EditText text;
    ViewGroup group;
    BaseInputConnection textFileInputConnection;
    private View.OnTouchListener otl = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }; ///set onTouchListener to block keyboard when text is focused;

    private View.OnLongClickListener olc = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (v.getId() == R.id.backspace_button){
                EditText edit = (EditText)findViewById(R.id.user_code_textview);
                edit.setText("");
            }
            return false;
        }
    };//set onLongClickListener to clear code field when backspace is pressed for a brief time


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_code_screen_layout);
        text = (EditText)findViewById(R.id.user_code_textview);
        text.setOnTouchListener(otl);
        Button backspace = (Button)findViewById(R.id.backspace_button);
        backspace.setOnLongClickListener(olc);
        textFileInputConnection = new BaseInputConnection(text, true);
        group = (ViewGroup)findViewById(R.id.button_grid);
        View v;
        for(int i = 0; i < group.getChildCount(); i++){
            v = group.getChildAt(i);
            if(v instanceof Button) v.setOnClickListener(this);
        }
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
                break;
            case R.id.digit1_button: text.append("1");
                break;
            case R.id.digit2_button: text.append("2");
                break;
            case R.id.digit3_button: text.append("3");
                break;
            case R.id.digit4_button: text.append("4");
                break;
            case R.id.digit5_button: text.append("5");
                break;
            case R.id.digit6_button: text.append("6");
                break;
            case R.id.digit7_button: text.append("7");
                break;
            case R.id.digit8_button: text.append("8");
                break;
            case R.id.digit9_button: text.append("9");
                break;
            case R.id.backspace_button: textFileInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
               break;
            case R.id.login_button_user: Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }




}
