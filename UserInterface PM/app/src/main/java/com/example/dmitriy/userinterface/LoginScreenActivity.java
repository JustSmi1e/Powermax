package com.example.dmitriy.userinterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


public class LoginScreenActivity extends Activity {

    private View.OnFocusChangeListener ofchange = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if((v.getId() == R.id.host_address || v.getId() == R.id.panel_id) && !hasFocus ){
                InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            else if ((v.getId() == R.id.host_address || v.getId() == R.id.panel_id) && hasFocus ){
                InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);

            }
        }
    }; ///set OnFocusChangeListener to close keyboard when clicked on the empty space

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credentials_screen_layout);
        EditText address = (EditText)findViewById(R.id.host_address);
        EditText panelID = (EditText)findViewById(R.id.panel_id);
        address.setOnFocusChangeListener(ofchange);
        panelID.setOnFocusChangeListener(ofchange);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_screen, menu);
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

    public void onNextButtonClick(View view){
        Intent intent = new Intent(this, UserCodeActivity.class);
        startActivity(intent);

    }

}
