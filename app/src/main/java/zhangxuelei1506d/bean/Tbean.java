package zhangxuelei1506d.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class Tbean {



    public int error_code;
    public String reason;
    public ResultBean result;

    public static class ResultBean {

        public String stat;
        public List<DataBean> data;

        public static class DataBean {


            public String author_name;
            public String category;
            public String date;
            public String thumbnail_pic_s;
            public String thumbnail_pic_s02;
            public String thumbnail_pic_s03;
            public String title;
            public String uniquekey;
            public String url;
        }
    }
}
