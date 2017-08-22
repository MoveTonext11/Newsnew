package zhangxuelei1506d.headline;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.umeng.socialize.UMShareAPI;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;

import zhangxuelei1506d.Fragment.Duanfragment;
import zhangxuelei1506d.Fragment.Fourfragment;
import zhangxuelei1506d.Fragment.Fragmentmanager;
import zhangxuelei1506d.Fragment.Frvefragment;
import zhangxuelei1506d.Fragment.Threefragment;

/**
 * date 2017/8/3
 * author:仵文博(Administrator)
 * functinn:
 */

public class HomePage extends AppCompatActivity {

    private UMShareAPI mShareAPI = null;
    private TabLayout tablayout;
    private ViewPager viewpager_home;
    private DrawerLayout drawlayout;
    private ActionBarDrawerToggle toggle;
    //定义默认主题
    private int theme = R.style.AppTheme;
    private ImageView image_up;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            int theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.homepage);

        View inflate = LayoutInflater.from(this).inflate(R.layout.popuwindow, null);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        mShareAPI = UMShareAPI.get(this);

        //获取控件
        initView();

        //初始化actionbar
        initActionbar();
        //添加数据和集合
        initviewpager();


        //添加点击fragment
        image_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,up_down_activity.class));
                overridePendingTransition(R.anim.translate,0);
            }
        });
    }
    //实现夜间模式切换的方法


    //添加数据
    private void initviewpager() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FristNews());
        fragments.add(new Twofragment());
        fragments.add(new Threefragment());
        fragments.add(new Fourfragment());
        fragments.add(new Frvefragment());
        fragments.add(new Duanfragment());
        fragments.add(new Twofragment());
        fragments.add(new Twofragment());
        fragments.add(new Twofragment());
        fragments.add(new Twofragment());
        fragments.add(new Twofragment());
        fragments.add(new Twofragment());
        fragments.add(new Twofragment());

        Fragmentmanager adapter = new Fragmentmanager(getSupportFragmentManager());
        adapter.setFragments(fragments);

        viewpager_home.setAdapter(adapter);
        for (int i = 0; i < 13; i++) {
            tablayout.addTab(tablayout.newTab());
        }


        tablayout.setupWithViewPager(viewpager_home);
        tablayout.getTabAt(0).setText("头条");
        tablayout.getTabAt(1).setText("娱乐");
        tablayout.getTabAt(2).setText("大片");
        tablayout.getTabAt(3).setText("小片");
        tablayout.getTabAt(4).setText("中片");
        tablayout.getTabAt(5).setText("没了");
        tablayout.getTabAt(6).setText("真的");
        tablayout.getTabAt(7).setText("真的");
        tablayout.getTabAt(8).setText("真的");
        tablayout.getTabAt(9).setText("真的");
        tablayout.getTabAt(10).setText("真的");
        tablayout.getTabAt(11).setText("真的");
        tablayout.getTabAt(12).setText("真的");
    }

    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawlayout, R.string.open, R.string.colose);
        toggle.syncState();
        drawlayout.addDrawerListener(toggle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.look:
                onClick();
                Toast.makeText(this, "扫一扫", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage.this, CaptureActivity.class);
                startActivityForResult(intent, 5);
                return true;
            case R.id.login:
                Toast.makeText(this, "登陆", Toast.LENGTH_SHORT).show();
                drawlayout.openDrawer(GravityCompat.END);
                drawlayout.closeDrawer(GravityCompat.START);
                if (drawlayout.isDrawerOpen(GravityCompat.END)) {
                    drawlayout.closeDrawer(GravityCompat.END);//关闭抽屉
                    return super.onOptionsItemSelected(item);
                }
                if (drawlayout.isDrawerOpen(GravityCompat.START)) {
                    drawlayout.closeDrawer(GravityCompat.END);//关闭抽屉
                    drawlayout.closeDrawer(GravityCompat.START);//关闭抽屉
                    return super.onOptionsItemSelected(item);
                }
                return true;
            case R.id.tianqi:
                Intent intent1 = new Intent(HomePage.this, tianqi.class);
                startActivity(intent1);
                return true;
            case R.id.yejian:
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                recreate();
                return true;
            case R.id.find:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(HomePage.this, yuyin.class);
                startActivity(intent2);
                return true;
            case R.id.setting:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                return true;
            default:
                if (toggle.onOptionsItemSelected(item)) {
                    if (drawlayout.isDrawerOpen(GravityCompat.END)) {
                        drawlayout.closeDrawer(GravityCompat.START);
                        drawlayout.closeDrawer(GravityCompat.END);
                        return super.onOptionsItemSelected(item);
                    }
                    return true;
                }
                return super.onOptionsItemSelected(item);
        }

    }

    private void initView() {
        image_up = (ImageView) findViewById(R.id.image_up);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager_home = (ViewPager) findViewById(R.id.viewpager_home);
        drawlayout = (DrawerLayout) findViewById(R.id.drawlayout);
    }


    //在menu文件夹下创建的action类型的重写显示方法
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        theme = savedInstanceState.getInt("theme");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mShareAPI.onActivityResult(requestCode, resultCode, data);
        //处理二维码扫描结果,5就是对你在startActivityForResult给的标识进行判断,然后在执行相对应的逻辑.
        if (requestCode == 5) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Intent intent = new Intent(HomePage.this, Webview.class);
                    intent.putExtra("url", result);
                    startActivity(intent);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(HomePage.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    public void onClick() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //权限发生了改变 true  //  false 小米
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(this).setTitle("title")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 请求授权
                                ActivityCompat.requestPermissions(HomePage.this, new String[]{Manifest.permission.CAMERA}, 1);
                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
            } else {
                ActivityCompat.requestPermissions(HomePage.this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        } else {

//            camear();

        }


    }


    /**
     * @param requestCode
     * @param permissions  请求的权限
     * @param grantResults 请求权限返回的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            // camear 权限回调

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // 表示用户授权
                Toast.makeText(this, " user Permission", Toast.LENGTH_SHORT).show();
                camear();
            } else {
                //用户拒绝权限
                Toast.makeText(this, " no Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void camear() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
