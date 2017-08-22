package zhangxuelei1506d.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

import zhangxuelei1506d.headline.R;
import zhangxuelei1506d.utitls.ImageLoaderUtils;

public class rightfragment extends Fragment implements View.OnTouchListener{
    private ImageView imageview_photo;
    private SHARE_MEDIA platform = null;
    private UMShareAPI mShareAPI = null;
    private TextView tv_right;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rightfragment, null);
        initView(view);

        mShareAPI = UMShareAPI.get(getActivity());



        imageview_photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                platform = SHARE_MEDIA.QQ;

                final UMAuthListener umAuthListener = new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        //回调成功，即登陆成功后这里返回Map<String, String> map，map里面就是用户的信息，可以拿出来使用了
                        Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
                        if (map != null) {
                            Set<String> set = map.keySet();
                            String string = "";
                            for (String str : set) {
                                String s = map.get(str);
                                string += s;
                            }
                            String profile_image_url = map.get("profile_image_url");
                            String screen_name = map.get("screen_name");
                            ImageLoaderUtils.setImageView(profile_image_url,getActivity(),imageview_photo);
                            tv_right.setText(screen_name);
                        }
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Toast.makeText(getActivity(), "授权取消", Toast.LENGTH_SHORT).show();
                    }
                };
                mShareAPI.getPlatformInfo(getActivity(), platform,umAuthListener);
            }
        });

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

    private void initView(View view) {
        imageview_photo = (ImageView) view.findViewById(R.id.imageview_photo);
        tv_right = (TextView) view.findViewById(R.id.tv_right);

    }
}
