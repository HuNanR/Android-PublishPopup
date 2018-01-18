package com.example.publishpopup;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Author by Jaiky, Email jaikydota@163.com, Date on 3/2/2015.
 * PS: Not easy to write code, please indicate.
 */
public class PublishDialog extends Dialog {

    private RelativeLayout rlRoot, rlMain;

    private Context context;

    private LinearLayout llBtnArticle, llBtnMiniBlog, llBtnLetter, llBtnPhoto, llBtnMenu;

    private Handler handler;

    private ImageView ivMenu;

    public PublishDialog(Context context) {
        this(context, R.style.main_publishdialog_style);
    }

    private PublishDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        handler = new Handler();
        //填充视图
        setContentView(R.layout.main_dialog_publish);
        rlRoot = (RelativeLayout) findViewById(R.id.mainPublish_dialog_rl_root);
        rlMain = (RelativeLayout) findViewById(R.id.mainPublish_dialog_rlMain);

        llBtnArticle = (LinearLayout) findViewById(R.id.mainPublish_dialog_llBtnArticle);
        llBtnMiniBlog = (LinearLayout) findViewById(R.id.mainPublish_dialog_llBtnMiniBlog);
        llBtnLetter = (LinearLayout) findViewById(R.id.mainPublish_dialog_llBtnLetter);
        llBtnPhoto = (LinearLayout) findViewById(R.id.mainPublish_dialog_llBtnPhoto);
        llBtnMenu = (LinearLayout) findViewById(R.id.mainPublish_dialog_llBtnMenu);
        ivMenu = (ImageView) findViewById(R.id.mainPublish_dialog_ivMenu);


        llBtnMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                outputDialog();
            }
        });
        rlMain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                outputDialog();
            }
        });
    }


    /**
     * 进入对话框（带动画）
     */
    private void inputDialog() {
        llBtnArticle.setVisibility(View.INVISIBLE);
        llBtnMiniBlog.setVisibility(View.INVISIBLE);
        llBtnLetter.setVisibility(View.INVISIBLE);
        llBtnPhoto.setVisibility(View.INVISIBLE);
        circularAnimIn();
        //背景动画
        rlMain.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_fade_in));
        //菜单按钮动画
        ivMenu.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_rotate_right));
        //选项动画
        llBtnArticle.setVisibility(View.VISIBLE);
        llBtnArticle.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_push_bottom_in));
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                llBtnMiniBlog.setVisibility(View.VISIBLE);
                llBtnMiniBlog.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_push_bottom_in));
            }
        }, 100);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                llBtnLetter.setVisibility(View.VISIBLE);
                llBtnLetter.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_push_bottom_in));
            }
        }, 200);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                llBtnPhoto.setVisibility(View.VISIBLE);
                llBtnPhoto.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_push_bottom_in));
            }
        }, 300);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isShowing()) {
            outputDialog();
            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }
    }


    /**
     * 取消对话框（带动画）
     */
    private void outputDialog() {
        //退出动画
        circularAnimOut();
        rlMain.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_fade_out));
        ivMenu.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_rotate_left));
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                dismiss();
            }
        }, 400);
        llBtnArticle.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_push_bottom_out));
        llBtnArticle.setVisibility(View.INVISIBLE);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                llBtnMiniBlog.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_push_bottom_out));
                llBtnMiniBlog.setVisibility(View.INVISIBLE);
            }
        }, 50);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                llBtnLetter.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_push_bottom_out));
                llBtnLetter.setVisibility(View.INVISIBLE);
            }
        }, 100);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                llBtnPhoto.startAnimation(AnimationUtils.loadAnimation(context, R.anim.mainactivity_push_bottom_out));
                llBtnPhoto.setVisibility(View.INVISIBLE);
            }
        }, 150);

    }

    private void circularAnimIn() {
        rlRoot.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                // 圆形动画的x坐标  位于View的中心
                int cx = (llBtnMenu.getLeft() + llBtnMenu.getRight()) / 2;
                //圆形动画的y坐标  位于View的中心
                int cy = (llBtnMenu.getTop() + llBtnMenu.getBottom()) / 2;
                //起始大小半径
                float startX=0f;
                //结束大小半径 大小为图片对角线的一半
                float startY= rlRoot.getBottom();
                Animator animator = ViewAnimationUtils.createCircularReveal(rlRoot, cx, cy, startX, startY);
                //在动画开始的地方速率改变比较慢,然后开始加速
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(400);
                animator.start();
            }
        });
    }

    private void circularAnimOut() {
        rlRoot.post(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                // 圆形动画的x坐标  位于View的中心
                int cx = (llBtnMenu.getLeft() + llBtnMenu.getRight()) / 2;
                //圆形动画的y坐标  位于View的中心
                int cy = (llBtnMenu.getTop() + llBtnMenu.getBottom()) / 2;
                //起始大小半径
                float startX=0f;
                //结束大小半径 大小为图片对角线的一半
                float startY= rlRoot.getBottom();
                Animator animator = ViewAnimationUtils.createCircularReveal(rlRoot, cx, cy, startY, startX);;
                //在动画开始的地方速率改变比较慢,然后开始加速
                animator.setInterpolator(new AccelerateInterpolator());
                animator.setDuration(400);
                animator.start();
            }
        });
    }


    @Override
    public void show() {
        super.show();
        inputDialog();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }


    public PublishDialog setArticleBtnClickListener(android.view.View.OnClickListener clickListener) {
        llBtnArticle.setOnClickListener(clickListener);
        return this;
    }

    public PublishDialog setMiniBlogBtnClickListener(android.view.View.OnClickListener clickListener) {
        llBtnMiniBlog.setOnClickListener(clickListener);
        return this;
    }

    public PublishDialog setLetterBtnClickListener(android.view.View.OnClickListener clickListener) {
        llBtnLetter.setOnClickListener(clickListener);
        return this;
    }

    public PublishDialog setPhotoBtnClickListener(android.view.View.OnClickListener clickListener) {
        llBtnPhoto.setOnClickListener(clickListener);
        return this;
    }


}
