package tw.hsu_practice.m1004;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class main extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLaunchAct;
    private TextView t001;
    final private int LAUNCH_GAME=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpViewComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.Item3:
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpViewComponent() {
        mBtnLaunchAct=(Button)findViewById(R.id.m1004_b001);
        t001=(TextView)findViewById(R.id.m1004_t001);

        mBtnLaunchAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(main.this, M1004.class);
                startActivityForResult(it, LAUNCH_GAME); //Intent + bundle
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != LAUNCH_GAME)  return; //First time no void

        switch (resultCode){
            case RESULT_OK:
                Bundle bundle = data.getExtras();

                int iCountPlayerWin = bundle.getInt("KEY_COUNT_PLAYER_WIN");
                int iCountComWin = bundle.getInt("KEY_COUNT_COM_WIN");
                int iCountDraw = bundle.getInt("KEY_COUNT_DRAW");

                String s =getString(R.string.m1004_result)+"\n"+ getString(R.string.m1004_total) +
                        (iCountPlayerWin+iCountComWin+iCountDraw) +getString(R.string.m1004_table)+"\n "+
                        getString(R.string.m1004_PlayerWin) + iCountPlayerWin +getString(R.string.m1004_table)+"\n "+
                        getString(R.string.m1004_comWin)+ iCountComWin +getString(R.string.m1004_table)+"\n "+
                        getString(R.string.m1004_draw)+ iCountDraw +getString(R.string.m1004_table);
                t001.setText(s);

                break;

            case RESULT_CANCELED:
                    t001.setText("點擊啟動即可重新開始。");
                break;

        }
    }
}
