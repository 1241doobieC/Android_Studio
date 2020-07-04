package tw.animation.m0701;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import tw.tcnr07.m0701.R;


public class M0701 extends AppCompatActivity implements
        ViewSwitcher.ViewFactory,
        AdapterView.OnItemClickListener {


    private GridView gridView;
    private ImageSwitcher imgSwi;

    private Integer[] ImgArr={
      R.drawable.img01, R.drawable.img02,
      R.drawable.img03,R.drawable.img04,
      R.drawable.img05,R.drawable.img06,
      R.drawable.img07,R.drawable.img08,
      R.drawable.img09,R.drawable.img10,
      R.drawable.img11,R.drawable.img12,
      R.drawable.img13,R.drawable.img14,
      R.drawable.img15,R.drawable.img16,
    };

    private Integer[] thumImgArr={
            R.drawable.img01th,R.drawable.img02th,
            R.drawable.img03th,R.drawable.img04th,
            R.drawable.img05th,R.drawable.img06th,
            R.drawable.img07th,R.drawable.img08th,
            R.drawable.img09th,R.drawable.img10th,
            R.drawable.img11th,R.drawable.img12th,
            R.drawable.img13th,R.drawable.img14th,
            R.drawable.img15th,R.drawable.img16th,
    };
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0701);
        setUpViewComponent();

    }

    private void setUpViewComponent() {

        //-----------從資源類別R中取得介面元件--------
        gridView=(GridView)findViewById(R.id.m0701_g001);
        imgSwi = (ImageSwitcher)findViewById(R.id.m0701_img01);

        //-------------將縮圖填入GridView-----------
        gridView.setAdapter(new GridAdapter(this,thumImgArr));

        //-----------GridView元件的事件處理----------
        gridView.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        //--------------取得GridView元件------------
        imgSwi.setFactory((ViewSwitcher.ViewFactory) this); //必須implements ViewSwitcher.ViewFactory
    }

    @Override
    public View makeView() {
        ImageView v = new ImageView(getApplicationContext());


        //----------------------------顏色這邊-------------------------------
        v.setBackgroundColor(0xFF000000);

        //------------------------------------------------------------------------
        v.setLayoutParams(new ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        imgSwi.setImageResource(ImgArr[position]);
    }

}
