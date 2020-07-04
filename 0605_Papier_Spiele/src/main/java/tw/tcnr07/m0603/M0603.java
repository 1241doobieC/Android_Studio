package tw.tcnr07.m0603;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class M0603 extends AppCompatActivity {
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
    private ImageButton.OnClickListener ImageButtonON = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View whatever) {
            int iComPlay_3_Difference = 1 + (int) (Math.random() * 3);//
            //1-剪刀, 2-石頭, 3-布
            switch (whatever.getId()) {
                case R.id.m0602_b001: //你出剪刀
                    u_setalpha();
                    ImageButton_Scissor.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Red));
                    ImageButton_Scissor.getBackground().setAlpha(150);

                    user_selected = getString(R.string.m0602_s001) +
                            getString(R.string.m0602_b001);
                    switch (iComPlay_3_Difference) {
                        case 1: //電腦出剪刀
//                            Toast.makeText(getApplicationContext(), getText(R.string.m0602_f003), Toast.LENGTH_SHORT);
                            choiceFromComp.setImageResource(R.drawable.scissors);
                            answer = getString(R.string.m0602_f000) +
                                    getString(R.string.m0602_f003);
                            music(2);
                            result.setText(answer);
                            result.setTextColor(getColor(R.color.Yellow));
                            break;

                        case 2: //電腦出石頭
//                            Toast.makeText(getApplicationContext(), getText(R.string.m0602_f002), Toast.LENGTH_SHORT);
                            choiceFromComp.setImageResource(R.drawable.stone);
                            answer = getString(R.string.m0602_f000) +
                                    getString(R.string.m0602_f002);
                            music(3);
                            result.setText(answer);
                            result.setTextColor(getColor(R.color.Red));
                            break;

                        case 3: //電腦出布
//                            Toast.makeText(getApplicationContext(), getText(R.string.m0602_f001), Toast.LENGTH_SHORT);
                            choiceFromComp.setImageResource(R.drawable.net);
                            answer = getString(R.string.m0602_f000) +
                                    getString(R.string.m0602_f001);
                            music(1);
                            result.setText(answer);
                            result.setTextColor(getColor(R.color.Lime));
                            break;
                    }
                    break;

                case R.id.m0602_b002:  //你出石頭
                    u_setalpha();
                    ImageButton_Stone.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Red));
                    ImageButton_Stone.getBackground().setAlpha(255);
                    user_selected = getString(R.string.m0602_s001) +
                            getString(R.string.m0602_b002);
                    switch (iComPlay_3_Difference) {
                        case 1: //電腦出剪刀
//                            Toast.makeText(getApplicationContext(), getText(R.string.m0602_f001), Toast.LENGTH_SHORT);
                            choiceFromComp.setImageResource(R.drawable.scissors);
                            answer = getString(R.string.m0602_f000) +
                                    getString(R.string.m0602_f001);
                            music(1);
                            result.setText(answer);
                            result.setTextColor(getColor(R.color.Lime));
                            break;

                        case 2: //電腦出石頭
//                            Toast.makeText(getApplicationContext(), getText(R.string.m0602_f003), Toast.LENGTH_SHORT);
                            choiceFromComp.setImageResource(R.drawable.stone);
                            answer = getString(R.string.m0602_f000) +
                                    getString(R.string.m0602_f003);
                            music(2);
                            result.setText(answer);
                            result.setTextColor(getColor(R.color.Yellow));
                            break;

                        case 3: //電腦出布
//                            Toast.makeText(getApplicationContext(), getText(R.string.m0602_f002), Toast.LENGTH_SHORT);
                            choiceFromComp.setImageResource(R.drawable.net);
                            answer = getString(R.string.m0602_f000) +
                                    getString(R.string.m0602_f002);
                            music(3);
                            result.setText(answer);
                            result.setTextColor(getColor(R.color.Red));
                            break;
                    }

                    break;

                case R.id.m0602_b003: //你出布
                    u_setalpha();
                    ImageButton_cloth.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Red));
                    ImageButton_cloth.getBackground().setAlpha(150);
                    user_selected = getString(R.string.m0602_s001) +
                            getString(R.string.m0602_b003);
                    switch (iComPlay_3_Difference) {
                        case 1: //電腦出剪刀
//                            Toast.makeText(getApplicationContext(), getText(R.string.m0602_f002), Toast.LENGTH_SHORT);
                            choiceFromComp.setImageResource(R.drawable.scissors);
                            answer = getString(R.string.m0602_f000) +
                                    getString(R.string.m0602_f002);
                            music(3);
                            result.setText(answer);
                            result.setTextColor(getColor(R.color.Red));
                            break;

                        case 2: //電腦出石頭
                            choiceFromComp.setImageResource(R.drawable.stone);
                            answer = getString(R.string.m0602_f000) +
                                    getString(R.string.m0602_f001);
                            music(1);
                            result.setText(answer);
                            result.setTextColor(getColor(R.color.Lime));
                            break;

                        case 3: //電腦出布

                            choiceFromComp.setImageResource(R.drawable.net);
                            answer = getString(R.string.m0602_f000) +
                                    getString(R.string.m0602_f003);
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




    };

    @Override
    protected void onStop() {
        super.onStop();
        music(4);
    }

    private void music(int i) {
        if(startMusic.isPlaying())startMusic.stop();
        if(win.isPlaying()) win.pause();
        if (draw.isPlaying()) draw.pause();
        if (lose.isPlaying()) lose.pause();

        switch (i){

            case 1:
                win.start();
                Toast.makeText(getApplicationContext(), getText(R.string.m0602_f001), Toast.LENGTH_SHORT).show();
                break;

            case 2:
                draw.start();
                Toast.makeText(getApplicationContext(), getText(R.string.m0602_f003), Toast.LENGTH_SHORT).show();
                break;

            case 3:
                lose.start();
                Toast.makeText(getApplicationContext(), getText(R.string.m0602_f002), Toast.LENGTH_SHORT).show();
                break;

        }

    }
    private void u_setalpha() {
            ImageButton_Scissor.getBackground().setAlpha(0);
            ImageButton_Stone.getBackground().setAlpha(0);
            ImageButton_cloth.getBackground().setAlpha(0);
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0602);
        setUpViewComponent();
    }



    private void setUpViewComponent() {

        ImageButton_Scissor = (ImageButton) findViewById(R.id.m0602_b001); //剪刀
        ImageButton_Stone = (ImageButton) findViewById(R.id.m0602_b002); //石頭
        ImageButton_cloth = (ImageButton) findViewById(R.id.m0602_b003); //布


        choiceFromComp = (ImageView) findViewById(R.id.m0602_t004); //電腦選擇
        choiceFromUser = (TextView) findViewById(R.id.m0602_t005); // 你選擇
        result = (TextView) findViewById(R.id.m0602_t006); //結果

        //-----開啟片頭音樂-----//
        startMusic = MediaPlayer.create(getApplication(), R.raw.guess);
        startMusic.start();
        win = MediaPlayer.create(getApplication(), R.raw.vctory);
        lose = MediaPlayer.create(getApplication(), R.raw.lose);
        draw = MediaPlayer.create(getApplication(), R.raw.haha);
//        startMusic.stop();

        ImageButton_Scissor.setOnClickListener(ImageButtonON);
        ImageButton_Stone.setOnClickListener(ImageButtonON);
        ImageButton_cloth.setOnClickListener(ImageButtonON);
    }

    private void u_SetAlpht(int i) {
        ImageButton_Scissor.getBackground().setAlpha(0);
        ImageButton_Stone.getBackground().setAlpha(0);
        ImageButton_cloth.getBackground().setAlpha(0);
        ImageButton_Stone.setBackgroundColor(ContextCompat.getColor(this, R.color.Black));
        ImageButton_Scissor.setBackgroundColor(ContextCompat.getColor(this, R.color.Black));
        ImageButton_cloth.setBackgroundColor(ContextCompat.getColor(this, R.color.Black));
    }
}
