package io.netopen.hotbitmapgg.easyrecycleadapterhelper;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.netopen.hotbitmapgg.easyrecycleadapterhelper.adapter.RecycleAdapter;
import io.netopen.hotbitmapgg.library.adapter.EasyGridSpacingItemDecoration;
import io.netopen.hotbitmapgg.library.adapter.EasyRecycleAdapter;
import io.netopen.hotbitmapgg.library.adapter.EasyRecycleOnScrollListener;

/**
 * Created by hcc on 16/7/17 15:21
 * 100332338@qq.com
 */
public class GridActivity extends AppCompatActivity
{

    private RecyclerView mRecyclerView;

    private List<String> datas = new ArrayList<>();

    private View footView;

    private GridLayoutManager mGridLayoutManager;

    private EasyRecycleAdapter mEasyRecycleAdapter;

    private int page = 1;

    private int pageNum = 30;

    private RecycleAdapter mRecycleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.grid_recycle);

        assert mRecyclerView != null;
        mRecyclerView.setHasFixedSize(true);

        //设置LayoutManager
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mGridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        //设置分割线
        int spacingInPixels = 26;
        mRecyclerView.addItemDecoration(new EasyGridSpacingItemDecoration(3, spacingInPixels, true, 0));

        //初始化适配器
        mRecycleAdapter = new RecycleAdapter(mRecyclerView, datas);

        //使用EasyAdapter包装一层
        mEasyRecycleAdapter = new EasyRecycleAdapter(mRecycleAdapter);

        // 添加尾部 上拉加载更多
        createFoot();
        mEasyRecycleAdapter.addFooterView(footView);

        //设置适配器
        mRecyclerView.setAdapter(mEasyRecycleAdapter);

        //设置item的宽度
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {

            @Override
            public int getSpanSize(int position)
            {

                return ((mEasyRecycleAdapter.getItemCount() - 1) == position) ? mGridLayoutManager.getSpanCount() : 1;
            }
        });
        //添加滑动监听
        mRecyclerView.addOnScrollListener(new EasyRecycleOnScrollListener(mGridLayoutManager)
        {

            @Override
            public void onLoadMore(int currentPage)
            {

                page++;
                loadMoreData();
            }
        });
    }

    private void loadMoreData()
    {

        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run()
            {

                addData();
            }
        }, 2000);
    }


    private void addData()
    {

        for (int i = 0; i < 30; i++)
        {
            datas.add("更多数据" + i);
        }


        if (page * pageNum - pageNum - 1 > 0)
            mRecycleAdapter.notifyItemRangeChanged(page * pageNum - pageNum - 1, pageNum);
        else
            mRecycleAdapter.notifyDataSetChanged();
    }

    private void createFoot()
    {

        footView = LayoutInflater.from(this).inflate(R.layout.load_more_foot_layout, mRecyclerView, false);
    }


    private void initData()
    {

        for (int i = 0; i < 30; i++)
        {
            datas.add("item" + i);
        }

    }
}
