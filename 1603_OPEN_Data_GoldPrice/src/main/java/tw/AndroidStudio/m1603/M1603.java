package tw.AndroidStudio.m1603;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import tw.AndroidStudio.m1603.R;


public class M1603 extends AppCompatActivity {


    private ListView list001;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1603);
        setUpViewComponent();
    }

    private void setUpViewComponent() {
        // 動態調整高度 抓取使用裝置尺寸
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int newscrollheight = displayMetrics.heightPixels * 95 / 100; // 設定ScrollView使用尺寸的4/5
        //
        list001 = (ListView) findViewById(R.id.listView1);
        list001.getLayoutParams().height = newscrollheight;
        list001.setLayoutParams(list001.getLayoutParams()); // 重定ScrollView大小
        time = (TextView) findViewById(R.id.time);
        // 解析 json
        try {
            String a = new TransTask().execute("http://www.tpex.org.tw/storage/gold/gold.txt").get();
            List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
            // 解析 json
            JSONObject jsonData = new JSONObject(a);
            String aaData = jsonData.getString("aaData");
            JSONArray jsonArray2 = new JSONArray(aaData);

            for (int j=0; j<jsonArray2.length(); j++){
                JSONObject jsonData2= jsonArray2.getJSONObject(j);
                String goldprice1 = jsonData2.getString("2");//前日買進報價
                String goldprice2 = jsonData2.getString("3");//今日買進報價
                String goldprice3 = jsonData2.getString("13");//當日最高
                String goldprice4 = jsonData2.getString("14");//當日最低
                String goldprice5 = jsonData2.getString("17");//最近一筆成交價
                String bank = jsonData2.getString("1");
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("t001",
                        "\n銀行：" + bank +
                                "\n前日買進報價：" + goldprice1 +
                                "\n今日買進報價：" + goldprice2 +
                                "\n當日最高：" + goldprice3 +
                                "\n當日最低：" + goldprice4 +
                                "\n最近一筆成交價：" + goldprice5);
                mList.add(item);
            }
            String datetime = jsonData.getString("datetime");
            time.setText("更新時間：" + datetime);
            SimpleAdapter adapter1 = new SimpleAdapter(
                    this,
                    mList,
                    R.layout.list,
                    new String[]{"t001"},
                    new int[]{R.id.t001});
            list001.setAdapter(adapter1);
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------
    private class TransTask extends AsyncTask<String, Void, String> {
        String ans;
        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                String line = in.readLine();

                while(line!=null){
                    Log.d("HTTP", line);

                    sb.append(line);
                    line = in.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ans =sb.toString();
            //------------
            return ans;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("s", "s:"+s);
            parseJson(s);
        }

        private void parseJson(String s) {

        }
    }
}
