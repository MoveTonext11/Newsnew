package zhangxuelei1506d.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import zhangxuelei1506d.headline.R;

/**
 * Created by Administrator on 2017/8/8.
 */

public class LiftFragment extends Fragment implements View.OnTouchListener,View.OnClickListener{

    private TextView news_tv1,news_tv2,news_tv3,news_tv4,news_tv5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listfragment,null);
        news_tv1 = (TextView) view.findViewById(R.id.news_tv1);
        news_tv2 = (TextView) view.findViewById(R.id.news_tv2);
        news_tv3 = (TextView) view.findViewById(R.id.news_tv3);
        news_tv4 = (TextView) view.findViewById(R.id.news_tv4);
        news_tv5 = (TextView) view.findViewById(R.id.news_tv5);
        news_tv1.setOnClickListener(this);
        news_tv2.setOnClickListener(this);
        news_tv3.setOnClickListener(this);
        news_tv4.setOnClickListener(this);
        news_tv5.setOnClickListener(this);
        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.news_tv1:
                Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();

                break;
            case R.id.news_tv2:
                Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();

                break;
            case R.id.news_tv3:
                Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();

                break;
            case R.id.news_tv4:
                Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();

                break;
            case R.id.news_tv5:
                Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
