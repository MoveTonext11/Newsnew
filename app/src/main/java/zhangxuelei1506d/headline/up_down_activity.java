package zhangxuelei1506d.headline;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/19.
 */

public class up_down_activity extends AppCompatActivity {
    private TextView tv_uodown;
    private ImageView image_up;
    private GridView grid1;
    private GridView grid2;
    private Mygridbase adapter;
    private Mygridbase adapter2;
    private SQLiteDatabase db;
    private List<String> list1;
    private List<String> list2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.up_down_activity);
        initView();

        grid1 = (GridView) findViewById(R.id.grid1);
        grid2 = (GridView) findViewById(R.id.grid2);

        //创建gridview的适配器集合
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        //创建数据库  存储数据
        Mygridsqlit mygridsqlit = new Mygridsqlit(this);
        db = mygridsqlit.getWritableDatabase();

        list1.add("头条");
        list1.add("首页");
        list1.add("娱乐");
        list1.add("图片");
        list1.add("游戏");
        list1.add("体育");
        list1.add("北京");
        list1.add("军事");
        list1.add("历史");
        list1.add("教育");
        list2.add("热点");
        list2.add("财经");
        list2.add("科技");
        list2.add("段子");
        list2.add("汽车");
        list2.add("时尚");
        list2.add("房产");
        list2.add("彩票");
        list2.add("独家");
        adapter = new Mygridbase(this, list1);
        adapter2 = new Mygridbase(this, list2);
        grid1.setAdapter(adapter);
        grid2.setAdapter(adapter2);

        image_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
                overridePendingTransition(0,R.anim.translate1);
            }
        });



        grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("wwb","测试测试测试+++++++++啊");
                String item = (String) adapter.getItem(i);
                ContentValues values = new ContentValues();
                values.put("title", item);
                db.insert("grid", null, values);
                list1.remove(i);
                adapter.notifyDataSetChanged();
                Cursor cursor = db.query("grid", null, null, null, null, null, null);
                String title = null;
                while (cursor.moveToNext()) {
                    title = cursor.getString(cursor.getColumnIndex("title"));
                }
                list2.add(title);
                adapter2.notifyDataSetChanged();
                db.delete("grid", null, null);
            }
        });
        grid2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapter2.getItem(i);
                ContentValues values = new ContentValues();
                values.put("title", item);
                db.insert("grid", null, values);
                list2.remove(i);
                adapter2.notifyDataSetChanged();
                Cursor cursor = db.query("grid", null, null, null, null, null, null);
                String title = null;
                while (cursor.moveToNext()) {
                    title = cursor.getString(cursor.getColumnIndex("title"));
                }
                list1.add(title);
                adapter.notifyDataSetChanged();
                db.delete("grid", null, null);
            }
        });

    }




    private void initView() {
        tv_uodown = (TextView) findViewById(R.id.tv_uodown);
        image_up = (ImageView) findViewById(R.id.image_up);
    }


}
