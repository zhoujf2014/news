package com.zhoujf.news.widget;


import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.zhoujf.news.R;
import com.zhoujf.news.ui.BaseActivity;
import com.zhoujf.news.util.SP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZhouJF on 2017-05-19.
 */

public class WepActivity extends BaseActivity {

    private final String[] ITEMS = {"一号", "二号", "三号", "四号", "五号", "六号"};
    @BindView(R.id.wv_webview)
    WebView mWvWebview;
    @BindView(R.id.iv_web_back)
    ImageView mIvWebBack;
    @BindView(R.id.iv_web_share)
    ImageView mIvWebShare;
    @BindView(R.id.iv_web_TextSize)
    ImageView mIvWebTextSize;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void init() {
        mContext = this;
        super.init();
        String webUrl = getIntent().getStringExtra("webUrl");
        mWvWebview.loadUrl(webUrl);
    }

    @OnClick({R.id.iv_web_back, R.id.iv_web_share, R.id.iv_web_TextSize})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_web_back:
                finish();
                break;
            case R.id.iv_web_share:
                break;
            case R.id.iv_web_TextSize:
                int testSize = SP.getIntData("TestSize", mContext);
                new AlertDialog.Builder(mContext).setTitle("请选择字体大小")
                        .setSingleChoiceItems(ITEMS, testSize, new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                WebSettings settings = mWvWebview.getSettings();
                                switch (which) {
                                    case 0:
                                        settings.setTextSize(WebSettings.TextSize.LARGEST);
                                        break;
                                    case 1:
                                        settings.setTextSize(WebSettings.TextSize.LARGER);
                                        break;
                                    case 2:
                                        settings.setTextSize(WebSettings.TextSize.NORMAL);
                                        break;
                                    case 3:
                                        settings.setTextSize(WebSettings.TextSize.SMALLER);
                                        break;
                                    case 4:
                                        settings.setTextSize(WebSettings.TextSize.SMALLEST);
                                        break;

                                }
                                SP.setIntData("TestSize", which, mContext);
                                dialog.dismiss();
                            }
                        }).show();
                break;
        }
    }
}
