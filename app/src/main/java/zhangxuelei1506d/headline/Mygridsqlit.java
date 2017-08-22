package zhangxuelei1506d.headline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/8/18.
 */

public class Mygridsqlit extends SQLiteOpenHelper {
    public Mygridsqlit(Context context) {
        super(context, "grida", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table grid(_id integer primary key autoincrement,title text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
