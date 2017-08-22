package zhangxuelei1506d.headline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import zhangxuelei1506d.bean.Tbean;
import zhangxuelei1506d.utitls.ImageLoaderUtils;



/**
 * Created by Administrator on 2017/8/9.
 */

public class Twofragment extends Fragment {

    private ListView listview_frist;
    private Banner banner;
    private String path2 = "http://v.juhe.cn/toutiao/index?type=yule&key=e76b62dbe5ce78645516fe866dc7058b";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fristnews,null);
        listview_frist = (ListView) view.findViewById(R.id.listview_frist);
        banner = (Banner) view.findViewById(R.id.banner);

        //添加数据   通过网络进行解析
        initdata();

        return view;
    }

    private void initdata() {
        new Thread() {

            private List<Tbean.ResultBean.DataBean> data;

            @Override
            public void run() {

                try {
                    URL url = new URL(path2);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(1024 * 8);
                    connection.setConnectTimeout(1024 * 8);
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int len = 0;
                        byte[] by = new byte[1024];
                        while ((len = inputStream.read(by)) != -1) {
                            baos.write(by, 0, len);
                        }
                        String string = baos.toString();

                        Gson gson = new Gson();
                        Tbean tbean = gson.fromJson(string, Tbean.class);
                        //获取解析出来的集合
                        data = tbean.result.data;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Mybase adapter = new Mybase(data);
                                listview_frist.setAdapter(adapter);
                                banner.setImageLoader(new Appbanner());
                                List<String> mlist = new ArrayList<String>();
                                for (int i = 0; i <5 ; i++) {
                                    mlist.add(data.get(i).thumbnail_pic_s);
                                }
                                banner.setImages(mlist);
                                banner.start();
                                listview_frist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        String url1 = data.get(i).url;
                                        Intent intent = new Intent(getActivity(),Webview.class);
                                        intent.putExtra("url",url1);
                                        startActivity(intent);
                                    }
                                });
                            }
                        });

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    private class Mybase extends BaseAdapter {
        private List<Tbean.ResultBean.DataBean> data;

        public Mybase(List<Tbean.ResultBean.DataBean> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(getActivity(), R.layout.item1, null);
            ImageView image_item1 = (ImageView) view.findViewById(R.id.image_item1);
            TextView textview_item1 = (TextView) view.findViewById(R.id.textview_item1);
            TextView tv_count_item1 = (TextView) view.findViewById(R.id.tv_count_item1);
            TextView tv_time_item1 = (TextView) view.findViewById(R.id.tv_time_item1);
            ImageLoaderUtils.setImageView(data.get(i).thumbnail_pic_s,getActivity(),image_item1);
            textview_item1.setText(data.get(i).title);
            tv_time_item1.setText(data.get(i).date);
            tv_count_item1.setText("暂无数据");

            return view;
        }
    }
}