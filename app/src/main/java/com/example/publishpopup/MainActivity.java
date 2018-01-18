package com.example.publishpopup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout llBtnMenu;
    PublishDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBtnMenu();
        initNews();
    }

    @SuppressLint("SetTextI18n")
    private void initNews() {
        TextView tvNews = (TextView) findViewById(R.id.tv_news);
        tvNews.setText("1月12日下午，奇瑞汽车华东大区2018媒体试驾品鉴暨新春团拜会如期举行。行程从杭州市江干区的奇瑞浙江隆中4S店到位于淳安县的千岛湖喜来登度假酒店，全程170公里，国内众多媒体朋友参加了这一趣味十足的年会。\n" +
                "\n" +
                "开车还是坐大巴？\n" +
                "\n" +
                "当然是开车和坐大巴结合，由于举办方还安排了“趣味节油挑战赛”，开自家的车，晒油耗成绩也成为十分有意义的一环。试驾车辆是3台瑞虎7和3台瑞虎5x.征集令在群里一公布。报名的选手瞬间就超员了。试驾车型自然是由奇瑞2.0时代的SUV“双子星”2018款瑞虎7和瑞虎5x来担任，在30多公里的城市道路和近140公里的高速公路行程中，媒体老师们还可以全面体验两款车动力性、整车NVH性以及燃油经济性。\n" +
                "\n" +
                "瑞虎7和瑞虎5x是奇瑞家族当前的“当家花旦”，2018款瑞虎7还经过38项升级，品质再上一层楼。两款车的动力系统是最大的亮点：搭载奇瑞自主研发的代号为SQRE4T15B的1.5T发动机，热效率高达37.1%，为国产发动机热效率之最；传动系统匹配的6速干式双离合变速箱是奇瑞和格特拉克共同研，属于第二代DCT产品，换挡迅捷，平顺性、燃油经济性都非常不错。\n" +
                "\n" +
                "最后，两个组别的冠军，百公里油耗分别为6.4L和6.3L，成绩相当不错。");
    }

    private void initBtnMenu() {
        llBtnMenu = (LinearLayout) findViewById(R.id.llBtnMenu);
        llBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pDialog == null) {
                    pDialog = new PublishDialog(MainActivity.this);
                    pDialog.setArticleBtnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                        }
                    });
                    pDialog.setMiniBlogBtnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                        }
                    });
                    pDialog.setPhotoBtnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                        }
                    });
                    pDialog.setLetterBtnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "显示相册发布界面", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                pDialog.show();
            }
        });
    }
}
