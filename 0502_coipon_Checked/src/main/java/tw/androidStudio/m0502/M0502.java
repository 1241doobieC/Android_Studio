package tw.androidStudio.m0502;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class M0502 extends AppCompatActivity {
    private Button b001_Button;
    private RadioGroup rb01_Group;
    private RadioGroup rb02_Group;
    private RadioButton rb021_Button;
    private RadioButton rb022_Button;
    private RadioButton rb023_Button;
    private TextView suggestion;
    private Button.OnClickListener buttonON = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            String strSug = getString(R.string.m0502_f000);
            int iii = rb01_Group.getCheckedRadioButtonId();

            switch (iii) {
                case R.id.m0502_r001a:  //這裡屬於男人
                    switch (rb02_Group.getCheckedRadioButtonId()) {
                        case R.id.m0502_r002a:
                            strSug += getString(R.string.m0502_f001);
                            break;

                        case R.id.m0502_r002b:
                            strSug += getString(R.string.m0502_f002);
                            break;

                        case R.id.m0502_r002c:
                            strSug += getString(R.string.m0502_f003);
                            break;
                    }
                    break;

                case R.id.m0502_r001b:  //這裡屬於女人
                    switch (rb02_Group.getCheckedRadioButtonId()) {

                        case R.id.m0502_r002a:
                            strSug += getString(R.string.m0502_f004);
                            break;

                        case R.id.m0502_r002b:
                            strSug += getString(R.string.m0502_f005);
                            break;

                        case R.id.m0502_r002c:
                            strSug += getString(R.string.m0502_f006);
                            break;
                    }
                    break;
            }
            suggestion.setText(strSug); //""這裡是答案
        }
    };
    private RadioGroup.OnCheckedChangeListener genderS = new RadioGroup.
            OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                //checkedId 配置碼
                case R.id.m0502_r001a:
                    rb021_Button.setText(R.string.m0502_r002aa);
                    rb022_Button.setText(R.string.m0502_r002ab);
                    rb023_Button.setText(R.string.m0502_r002ac);
                    break;

                case R.id.m0502_r001b:
                    rb021_Button.setText(R.string.m0502_r002ba);
                    rb022_Button.setText(R.string.m0502_r002bb);
                    rb023_Button.setText(R.string.m0502_r002bc);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0502);
        setUpViewComponent();
    }

    private void setUpViewComponent() {
        //取得R.java配置碼
        b001_Button = (Button) findViewById(R.id.m0502_b001);
        rb01_Group = (RadioGroup) findViewById(R.id.m0502_r001); //gender
        rb02_Group = (RadioGroup) findViewById(R.id.m0502_r002); //age
        rb021_Button = (RadioButton) findViewById(R.id.m0502_r002a);
        rb022_Button = (RadioButton) findViewById(R.id.m0502_r002b);
        rb023_Button = (RadioButton) findViewById(R.id.m0502_r002c);
        //結果建議
        suggestion = (TextView) findViewById(R.id.m0502_f000);
        //監聽
        rb01_Group.setOnCheckedChangeListener(genderS);
        b001_Button.setOnClickListener(buttonON);

    }
}
