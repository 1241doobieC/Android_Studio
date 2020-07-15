package tw.hsu_practice.m1004;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class M1004 extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer win;
    private MediaPlayer lose;
    private MediaPlayer draw;
    private MediaPlayer startMusic;
    private ImageButton ImageButton_Scissor;
    private ImageButton ImageButton_Stone;
    private ImageButton ImageButton_cloth;
    private ImageView choiceFromComp;
    private TextView choiceFromUser;
    private TextView result;
    private String user_selected;
    private String answer;
    private Button m1004_showResult,m1004_btnCancel,m1004_btnOk;
    private int playerWIN=0;
    private int comWIN=0;
    private int DRAW=0;
    private int miCountSet=0;
    private Menu menu;

    @Override
    //結束程式
    protected void onStop() {
        super.onStop();
        music(4);
        m1004_btnCancel.performClick(); //重新開始
    }

    //音樂設定
    private void music(int i) {
        if(startMusic.isPlaying())startMusic.stop();
        if(win.isPlaying()) win.pause();
        if (draw.isPlaying()) draw.pause();
        if (lose.isPlaying()) lose.pause();

        switch (i){

            case 1:
                win.start();
                playerWIN++;
                Toast.makeText(getApplicationContext(), getText(R.string.m1004_f001), Toast.LENGTH_SHORT).show();
                break;

            case 2:
                draw.start();
                DRAW++;
                Toast.makeText(getApplicationContext(), getText(R.string.m1004_f003), Toast.LENGTH_SHORT).show();
                break;

            case 3:
                lose.start();
                comWIN++;
                Toast.makeText(getApplicationContext(), getText(R.string.m1004_f002), Toast.LENGTH_SHORT).show();
                break;

        }

    }

    //設定透明度
    private void u_setalpha() {
            ImageButton_Scissor.getBackground().setAlpha(0);
            ImageButton_Stone.getBackground().setAlpha(0);
            ImageButton_cloth.getBackground().setAlpha(0);
        }

    @Override
    //強迫使用者能不能按返回 :)
    public void onBackPressed() {
/*********************************************************************************************
 *      方法擇一        //讓電腦協助執行button(完成遊戲)的功能                                                                *
*       super.onBackPressed();                                                                                                                     *
*        m1004_btnOk.performClick();                                                                                                         *
*       Toast.makeText(getApplicationContext(),"您已按返回。", Toast.LENGTH_SHORT).show();   *
*        M1004.this.finish();                                                                                                                            *
*                                                                                                                                                                      *
 *********************************************************************************************/
    menu.performIdentifierAction(R.id.Item2,0);   //讓電腦協助執行Item2的功能
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1004);
        setUpViewComponent();
    }

    @Override
//    設定右上選單
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.m1004, menu);
        this.menu=menu;  //宣告menu
        return true;
    }

    //設定主畫面
    private void setUpViewComponent() {

        ImageButton_Scissor = (ImageButton) findViewById(R.id.m1004_b001); //剪刀
        ImageButton_Stone = (ImageButton) findViewById(R.id.m1004_b002); //石頭
        ImageButton_cloth = (ImageButton) findViewById(R.id.m1004_b003); //布

        //-------新增按鈕與監聽-------
        m1004_showResult=(Button)findViewById(R.id.btnShowResult);
        m1004_btnCancel=(Button)findViewById(R.id.m1004_btnCancel);
        m1004_btnOk=(Button)findViewById(R.id.m1004_btnOk);

        m1004_showResult.setOnClickListener(showResult);  //顯示結果
        m1004_btnCancel.setOnClickListener(cancelAT);  // 你選擇取消遊戲
        m1004_btnOk.setOnClickListener(btn_restart); //重新開始

        m1004_showResult.setVisibility(View.INVISIBLE);
        m1004_btnCancel.setVisibility(View.INVISIBLE);
        m1004_btnOk.setVisibility(View.INVISIBLE);
        //--------新增按鈕結束--------

        choiceFromComp = (ImageView) findViewById(R.id.m1004_t004); //電腦選擇
        choiceFromUser = (TextView) findViewById(R.id.m1004_t005); // 你選擇
        result = (TextView) findViewById(R.id.m1004_t006); //結果

        //-----開啟片頭音樂-----//
        startMusic = MediaPlayer.create(getApplication(), R.raw.guess);
        startMusic.start();
        win = MediaPlayer.create(getApplication(), R.raw.vctory);
        lose = MediaPlayer.create(getApplication(), R.raw.lose);
        draw = MediaPlayer.create(getApplication(), R.raw.haha);
//----------------------------------------------------------
        ImageButton_Scissor.setOnClickListener(this);
        ImageButton_Stone.setOnClickListener(this);
        ImageButton_cloth.setOnClickListener(this);

        //------------Menu Item 宣告-------------------------

    }


//    監聽-->完成遊戲
private Button.OnClickListener cancelAT= new Button.OnClickListener(){

    @Override
    public void onClick(View v) {
        Intent it = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("KEY_COUNT_PLAYER_WIN", playerWIN);
        bundle.putInt("KEY_COUNT_COM_WIN", comWIN);
        bundle.putInt("KEY_COUNT_DRAW", DRAW);
        it.putExtras(bundle);

        setResult(RESULT_OK, it);
        finish();
    }
};
//監聽-->重新開始
private  Button.OnClickListener btn_restart = new Button.OnClickListener(){

    @Override
    public void onClick(View v) {
        setResult(RESULT_CANCELED);
        playerWIN=0;
        DRAW=0;
        comWIN=0;
        finish();
    }
};

//監聽-->顯示結果
    private ImageButton.OnClickListener showResult = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View whatever) {
            Intent it = new Intent();
            it.setClass(getApplicationContext(), GameResult.class);

            Bundle bundle=new Bundle();
            bundle.putInt("KEY_COUNT_PLAYER_WIN",playerWIN );  //自己創key與value
            bundle.putInt("KEY_COUNT_COM_WIN",comWIN );
            bundle.putInt("KEY_COUNT_DRAW",DRAW );
            it.putExtras(bundle);
            startActivity(it);
        }
    };


    @Override
    //右上選單功能
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.Item1:
//                showResult.onClick(null); //程式設定、點擊監聽
                m1004_showResult.performClick();  //程式按物件-->顯示結果
                break;

            case R.id.Item2:
                cancelAT.onClick(null); //完成遊戲

                this.finish();
                break;

            case R.id.Item3:
                btn_restart.onClick(null);  //重新開始
//                setResult(RESULT_CANCELED); //-1
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void u_SetAlpht(int i) {
        ImageButton_Scissor.getBackground().setAlpha(0);
        ImageButton_Stone.getBackground().setAlpha(0);
        ImageButton_cloth.getBackground().setAlpha(0);
        ImageButton_Stone.setBackgroundColor(ContextCompat.getColor(this, R.color.Black));
        ImageButton_Scissor.setBackgroundColor(ContextCompat.getColor(this, R.color.Black));
        ImageButton_cloth.setBackgroundColor(ContextCompat.getColor(this, R.color.Black));
    }

    @Override
    //設定簡都石頭布的功能與遊戲
    public void onClick(View v) {
        int iComPlay_3_Difference = 1 + (int) (Math.random() * 3);//
        //1-剪刀, 2-石頭, 3-布
        switch (v.getId()) {
            case R.id.m1004_b001: //你出剪刀
                u_setalpha();
                ImageButton_Scissor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Red));
                ImageButton_Scissor.getBackground().setAlpha(150);

                user_selected = getString(R.string.m1004_s001) +
                        getString(R.string.m1004_b001);
                switch (iComPlay_3_Difference) {
                    case 1: //電腦出剪刀
//                            Toast.makeText(getApplicationContext(), getText(R.string.m1004_f003), Toast.LENGTH_SHORT);
                        choiceFromComp.setImageResource(R.drawable.scissors);
                        answer = getString(R.string.m1004_f000) +
                                getString(R.string.m1004_f003);
                        music(2);
                        result.setText(answer);
                        result.setTextColor(getColor(R.color.Yellow));
                        break;

                    case 2: //電腦出石頭
//                            Toast.makeText(getApplicationContext(), getText(R.string.m1004_f002), Toast.LENGTH_SHORT);
                        choiceFromComp.setImageResource(R.drawable.stone);
                        answer = getString(R.string.m1004_f000) +
                                getString(R.string.m1004_f002);
                        music(3);
                        result.setText(answer);
                        result.setTextColor(getColor(R.color.Red));
                        break;

                    case 3: //電腦出布
//                            Toast.makeText(getApplicationContext(), getText(R.string.m1004_f001), Toast.LENGTH_SHORT);
                        choiceFromComp.setImageResource(R.drawable.net);
                        answer = getString(R.string.m1004_f000) +
                                getString(R.string.m1004_f001);
                        music(1);
                        result.setText(answer);
                        result.setTextColor(getColor(R.color.Lime));
                        break;
                }
                break;

            case R.id.m1004_b002:  //你出石頭
                u_setalpha();
                ImageButton_Stone.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Red));
                ImageButton_Stone.getBackground().setAlpha(255);
                user_selected = getString(R.string.m1004_s001) +
                        getString(R.string.m1004_b002);
                switch (iComPlay_3_Difference) {
                    case 1: //電腦出剪刀
//                            Toast.makeText(getApplicationContext(), getText(R.string.m1004_f001), Toast.LENGTH_SHORT);
                        choiceFromComp.setImageResource(R.drawable.scissors);
                        answer = getString(R.string.m1004_f000) +
                                getString(R.string.m1004_f001);
                        music(1);
                        result.setText(answer);
                        result.setTextColor(getColor(R.color.Lime));
                        break;

                    case 2: //電腦出石頭
//                            Toast.makeText(getApplicationContext(), getText(R.string.m1004_f003), Toast.LENGTH_SHORT);
                        choiceFromComp.setImageResource(R.drawable.stone);
                        answer = getString(R.string.m1004_f000) +
                                getString(R.string.m1004_f003);
                        music(2);
                        result.setText(answer);
                        result.setTextColor(getColor(R.color.Yellow));
                        break;

                    case 3: //電腦出布
//                            Toast.makeText(getApplicationContext(), getText(R.string.m1004_f002), Toast.LENGTH_SHORT);
                        choiceFromComp.setImageResource(R.drawable.net);
                        answer = getString(R.string.m1004_f000) +
                                getString(R.string.m1004_f002);
                        music(3);
                        result.setText(answer);
                        result.setTextColor(getColor(R.color.Red));
                        break;
                }

                break;

            case R.id.m1004_b003: //你出布
                u_setalpha();
                ImageButton_cloth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Red));
                ImageButton_cloth.getBackground().setAlpha(150);
                user_selected = getString(R.string.m1004_s001) +
                        getString(R.string.m1004_b003);
                switch (iComPlay_3_Difference) {
                    case 1: //電腦出剪刀
//                            Toast.makeText(getApplicationContext(), getText(R.string.m1004_f002), Toast.LENGTH_SHORT);
                        choiceFromComp.setImageResource(R.drawable.scissors);
                        answer = getString(R.string.m1004_f000) +
                                getString(R.string.m1004_f002);
                        music(3);
                        result.setText(answer);
                        result.setTextColor(getColor(R.color.Red));
                        break;

                    case 2: //電腦出石頭
                        choiceFromComp.setImageResource(R.drawable.stone);
                        answer = getString(R.string.m1004_f000) +
                                getString(R.string.m1004_f001);
                        music(1);
                        result.setText(answer);
                        result.setTextColor(getColor(R.color.Lime));
                        break;

                    case 3: //電腦出布

                        choiceFromComp.setImageResource(R.drawable.net);
                        answer = getString(R.string.m1004_f000) +
                                getString(R.string.m1004_f003);
                        music(2);
                        result.setText(answer);
                        result.setTextColor(getColor(R.color.Yellow));
                        break;
                }
                break;

        }
        choiceFromUser.setText(user_selected);

        choiceFromUser.setText(user_selected);

        result.setText(answer);
    }
}
