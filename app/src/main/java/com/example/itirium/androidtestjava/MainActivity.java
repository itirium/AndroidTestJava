package com.example.itirium.androidtestjava;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    TextView txtMy;
    Button btnMy;
    private static final String LogTag = "myDebugLog";
    private static final String LogStates = "States";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LogTag, "Init view-elements");
        txtMy = (TextView) findViewById(R.id.txtMy);
        btnMy = (Button) findViewById(R.id.btnMy);

        Log.d(LogTag, "Attaching onClickListener to Button");
        btnMy.setOnClickListener(this);

        registerForContextMenu(txtMy);

        Log.d(LogStates,"MainActivity: OnCreate!");

    }

    @Override
    public void onClick(View v) {
        Log.d(LogTag, "Clicked on some element");
        switch (v.getId()) {
            case R.id.btnMy:
                Log.d(LogTag, "My Button pressed!");
                Toast.makeText(this, "Eat my shorts!", Toast.LENGTH_SHORT).show();
                Animation animation=null;
                //int anim_index = new Random().nextInt(4);
                //Log.d(LogTag,"int anim_index: "+anim_index);
                switch(new Random().nextInt(4)){
                    case 0:
                        Log.d(LogTag, "Random 0");
                        animation= AnimationUtils.loadAnimation(this,R.anim.alpha);
                        break;
                    case 1:
                        Log.d(LogTag, "Random 1");
                        animation= AnimationUtils.loadAnimation(this,R.anim.rotate);
                        break;
                    case 2:
                        Log.d(LogTag, "Random 2");
                        animation= AnimationUtils.loadAnimation(this,R.anim.scale);
                        break;
                    case 3:
                        Log.d(LogTag, "Random 3");
                        animation= AnimationUtils.loadAnimation(this,R.anim.trans);
                        break;
                    case 4:
                        Log.d(LogTag, "Random 4");
                        animation= AnimationUtils.loadAnimation(this,R.anim.combo);
                        break;
                    default:
                        Log.d(LogTag, "Random Default");
                        animation= AnimationUtils.loadAnimation(this,R.anim.combo);
                        break;
                }
                if(animation!=null)  btnMy.startAnimation(animation);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        StringBuilder sb = new StringBuilder();
        sb.append(txtMy.getText());
        sb.append("\r\n Menu pressed : " + item.getTitle());
        txtMy.setText(sb.toString());
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_open_second_activity://явний визов актівіті
                intent= new Intent(this, ActivitySecond.class);
                startActivity(intent);
                break;
            case R.id.menu_show_time://неявний визов актівіті
                intent = new Intent("itiri.showtime");
                startActivity(intent);
                break;
            case R.id.menu_show_date://ще неявний
                intent = new Intent("itiri.showdate");
                startActivity(intent);
                break;
            case R.id.menu_info_time://ще неявний
                intent = new Intent("itiri.info.time");
                startActivity(intent);
                break;
            case R.id.menu_info_date://ще неявний
                intent = new Intent("itiri.info.date");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    final int MENU_COLOR_RED = 0;
    final int MENU_COLOR_GREEN = 1;
    final int MENU_COLOR_BLUE = 2;


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        switch (v.getId()){
            case R.id.txtMy:
                menu.add(0, MENU_COLOR_RED, 0, "Make Red");
                menu.add(0, MENU_COLOR_GREEN, 0, "Make Green");
                menu.add(0, MENU_COLOR_BLUE, 0, "Make Blue");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case MENU_COLOR_RED:
                txtMy.setTextColor(Color.RED);
                break;
            case MENU_COLOR_GREEN:
                txtMy.setTextColor(Color.GREEN);
                break;
            case MENU_COLOR_BLUE:
                txtMy.setTextColor(Color.BLUE);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LogStates,"MainActivity: OnRestart!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LogStates,"MainActivity: OnStart!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LogStates,"MainActivity: OnPause!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LogStates,"MainActivity: OnStop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LogStates,"MainActivity: OnDestroy!");
    }
}