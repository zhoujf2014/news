package com.zhoujf.news.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zhoujf.news.R;
import com.zhoujf.news.bean.CatagreBean;
import com.zhoujf.news.ui.BaseTabPage;
import com.zhoujf.news.network.JsonBeanRequest;
import com.zhoujf.news.network.JsonBeanRequestListener;
import com.zhoujf.news.network.NetworkManager;
import com.zhoujf.news.util.SP;

import java.util.List;

import static com.zhoujf.news.util.SP.getBooleanData;

/**
 * Created by ZhouJF on 2017-05-16.
 */

public class CenterTabPage extends BaseTabPage {

    private static final String TAG = "CenterTabPage";
    private List<CatagreBean.DataBean.ChildrenBean> mDataBean;

    public CenterTabPage(Context context) {
        super(context);
    }

    public CenterTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);


    }


    @Override
    public void loadDataFromServer() {
        Log.d(TAG, "loadDataFromServer: ");
        String url = "http://192.168.21.99:8080/zhbj/categories.json";
        JsonBeanRequest<CatagreBean> beanRequest = new JsonBeanRequest<CatagreBean>(CatagreBean.class, url,
                new JsonBeanRequestListener<CatagreBean>() {
                    @Override
                    public void onResponse(CatagreBean response) {
                        List<CatagreBean.DataBean.ChildrenBean> data = response.getData().get(0).getChildren();
                        mDataBean = data;
                        loadNewsPage();
                        
                    }
                });
        NetworkManager.sRequestQueue.add(beanRequest);


    }

   @Override
    public void setItemClick(int position) {
        switch (position) {
            //点击新闻按钮
            case 0:
                loadNewsPage();
                break;
            case 1:
                mIvPicType.setVisibility(View.GONE);
                break;
            case 2:
                loadPhotoPage();


                break;
            case 3:
                mIvPicType.setVisibility(View.GONE);
                break;
        }
    }

    private void loadNewsPage() {

        mIvPicType.setVisibility(View.GONE);
        TextIndicator textIndicator = new TextIndicator(getContext());
        textIndicator.setData(mDataBean);
        Log.d(TAG, "loadNewsPage: ");
        mFlContent.removeAllViewsInLayout();
        mFlContent.addView(textIndicator);

    }

    private void loadPhotoPage() {
        final PhotoPage photoPage = new PhotoPage(getContext());
        boolean showType = getBooleanData("showType", getContext());
        mIvPicType.setImageResource(showType?R.mipmap.icon_pic_list_type: R.mipmap.icon_pic_grid_type);
        photoPage.setshowType(showType);
        mIvPicType.setVisibility(View.VISIBLE);
        mIvPicType.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean showType = getBooleanData("showType", getContext());
                showType = !showType;
                mIvPicType.setImageResource(showType?R.mipmap.icon_pic_list_type: R.mipmap.icon_pic_grid_type);
                SP.setBooleanData("showType",showType,getContext());
                photoPage.setshowType(showType);
            }
        });
        mFlContent.removeAllViewsInLayout();
        mFlContent.addView(photoPage);

    }

}
