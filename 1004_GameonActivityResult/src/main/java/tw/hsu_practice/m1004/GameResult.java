package tw.hsu_practice.m1004;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameResult extends AppCompatActivity implements View.OnClickListener {
    private TextView mEdtCountSet;
    private TextView mEdtCountPlayerWin,mEdtCountComWin,mEdtCountDraw;
    private Button btnBackToGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_result);
        setUpViewComponent();
        showResult();
    }

    private void setUpViewComponent() {
        mEdtCountSet = (TextView)findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (TextView)findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (TextView)findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (TextView)findViewById(R.id.edtCountDraw);
        btnBackToGame = (Button)findViewById(R.id.btnBackToGame);
        btnBackToGame.setOnClickListener(this);
    }

    public void showResult() {
        Bundle bundle= this.getIntent().getExtras();  //從bundle中取出資料

        int playerWIN_K=bundle.getInt("KEY_COUNT_PLAYER_WIN");
        int comWIN_K =bundle.getInt("KEY_COUNT_COM_WIN");
        int Draw =bundle.getInt("KEY_COUNT_DRAW");

        int total= playerWIN_K+comWIN_K+Draw;

        mEdtCountSet.setText(Integer.toString(total));
        mEdtCountPlayerWin.setText(Integer.toString(playerWIN_K));
        mEdtCountComWin.setText(Integer.toString(comWIN_K));
        mEdtCountDraw.setText(Integer.toString(Draw));
    }
    @Override
    public void onClick(View v) {
        GameResult.this.finish();   //這方法比較好!

        //--------↓這方法會開啟一個新的layoutp 進入時會再重新計算，然舊的並不會不見，只是被系統隱藏起來而已-------

//        Intent it01= new Intent();
//        it01.setClass(GameResult.this,M1003.class);
//        startActivity(it01);
    }
}
