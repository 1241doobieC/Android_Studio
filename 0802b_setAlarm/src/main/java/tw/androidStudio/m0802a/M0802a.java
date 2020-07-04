package tw.androidStudio.m0802a;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Calendar;

import tw.androidStudio.m0802a.M0802a;

public class M0802a extends AppCompatActivity implements View.OnClickListener {
    private TextView time,ans01,text001;
    private Handler handler = new Handler();
    private DatePicker date01;
    private TimePicker time01;
    private Button b001;
    private MediaPlayer startMusic;
    private Integer year01, months01,dates01,hours01,minutes01;
    private long startTime,endTime,hours,minutes ,seconds;
    private long spentTime;
    private Calendar cg;
    private Button cancel;
    private int year02;
    private int month02;
    private int day02;
    private int hour02;
    private int min02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802a);
        setUpViewComponent();
    }

    private void setUpViewComponent() {
     ans01=(TextView)findViewById(R.id.m0802a_ans01);
     date01=(DatePicker)findViewById(R.id.m0802a_calendar);
     time01=(TimePicker)findViewById(R.id.m0802a_time);
     b001= (Button)findViewById(R.id.m0802a_b001);
     time=(TextView)findViewById(R.id.m0802a_timer);
     text001=(TextView)findViewById(R.id.m0802a_t001);
     startMusic= MediaPlayer.create(M0802a.this, R.raw.s01);
     cancel=(Button)findViewById(R.id.m0802a_b002);

     b001.setOnClickListener(this);
     cancel.setOnClickListener(this);
     cancel.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {

//    //----------顯示選擇的日期和時間---------------
//        ans01.setText(
//                s+"    "+ year01+"年" +
//                        (months01+1)+"月"+
//                        dates01+"日"+
//                        hours01+"點" +
//                        minutes01+"分"
//        );
//    //--------------------------------------------------
//        cg= Calendar.getInstance(); //設定日曆新物件
//        cg.set(year01,months01,dates01,hours01,minutes01); //將日期及時間設定進去物件，此處months不+1，為系統運算用
//         endTime=cg.getTimeInMillis();//取得時間毫秒資料
//

        switch (v.getId()){
            case R.id.m0802a_b001:

        //---------------開啟關閉鬧鐘-----------------
                cancel.setVisibility(View.VISIBLE);
                b001.setVisibility(View.INVISIBLE);
        //----------------------------------------------
                String s= getString(R.string.txtResult);
                year01= date01.getYear();
                months01=date01.getMonth();
                dates01=date01.getDayOfMonth();
                hours01=time01.getHour();
                minutes01=time01.getMinute();

                // 顯示選擇的日期和時間
                ans01.setText(s +
                        year01 + getString(R.string.n_yy) +
                        (months01 + 1) + getString(R.string.n_mm)     +
                        dates01 + getString(R.string.m_dd) +
                        hours01 + getString(R.string.d_hh) +
                        minutes01      + getString(R.string.d_mm));
                //--------------------------------------
                cg = Calendar.getInstance();//設定日曆新物件
                cg.set(year01, months01, dates01, hours01, minutes01);//將日期及時間設定進去物件
                endTime = cg.getTimeInMillis();//取得時間毫秒資料

                //設定開始Delay時間
                handler.postDelayed(updateTime,1000);


                break;

            case R.id.m0802a_b002:

                b001.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"取消鬧鐘",Toast.LENGTH_SHORT).show();
                music_set();
                time.setText("88:88:88");
                text001.setText("鬧鐘倒數計時");
                setnoTime();
                handler.removeCallbacks(updateTime);
                break;
        }
    }

    private void setnoTime() {
    Calendar c= Calendar.getInstance();
    year02=c.get(Calendar.YEAR);
    month02=c.get(Calendar.MONTH);
    day02=c.get(Calendar.DAY_OF_MONTH);
    hour02=c.get(Calendar.HOUR_OF_DAY);
    min02=c.get(Calendar.MINUTE);
    date01.updateDate(year02,month02,day02);
    time01.setHour(hour02);
    time01.setMinute(min02);
    }

    private Runnable updateTime= new Runnable() {
        @Override
        public void run() {

            spentTime=endTime-System.currentTimeMillis();
            hours=(spentTime/1000)/60/60;
            minutes=(spentTime/1000)/60%60;
            seconds=(spentTime/1000)%60;

            if (spentTime<0 || hours>999){
                Toast.makeText(getApplicationContext(),getString(R.string.error), Toast.LENGTH_SHORT).show();
                time.setText(getString(R.string.m0802_timer));
                handler.removeCallbacks(updateTime);

            }
            else {
                text001.setText(getString(R.string.m0802_alerm));
                music_set(); //音樂重設

            //----format(%02d,minutes)格式化為兩位數
                time.setText(String.format("%02d",hours)+":"+String.format(String.format("%02d",minutes))+":"+
                        String.format("%02d",seconds));
                handler.postDelayed(this,1000); //延遲時間 每秒更新一次

                if (hours <=0 && minutes<=0 &&seconds<=0){
                    startMusic.start();
                    text001.setText(getString(R.string.m0802_play));
                    handler.removeCallbacks(updateTime);
                }
            }
        }


    };
    private void music_set() {
        if (startMusic.isPlaying()) {
            startMusic.stop();
            try {
                startMusic.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        startMusic.stop();
        handler.removeCallbacks(updateTime);
        this.finish();
    }
}
