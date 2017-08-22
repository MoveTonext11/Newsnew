package zhangxuelei1506d.headline;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */
public class Mygridbase extends BaseAdapter{
    private Context context;
    private List<String>list;

    public Mygridbase(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context,R.layout.griditem,null);
        TextView button_grid = (TextView) view.findViewById(R.id.button_grid);
        button_grid.setText(list.get(i));
        return view;
    }
}
