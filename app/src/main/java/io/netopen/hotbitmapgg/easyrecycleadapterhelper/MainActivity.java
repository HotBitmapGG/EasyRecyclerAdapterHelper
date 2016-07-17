package io.netopen.hotbitmapgg.easyrecycleadapterhelper;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.netopen.hotbitmapgg.easyrecycleadapterhelper.adapter.RecycleAdapter;
import io.netopen.hotbitmapgg.library.adapter.EasyBaseRecycleViewAdapter;
import io.netopen.hotbitmapgg.library.adapter.EasyRecycleAdapter;
import io.netopen.hotbitmapgg.library.adapter.EasyRecycleOnScrollListener;

public class MainActivity extends AppCompatActivity
{

    private RecyclerView mRecyclerView;

    private List<String> datas = new ArrayList<>();

    private RecycleAdapter mRecycleAdapter;

    private View headView;

    private View footView;

    private int page = 1;

    private int pageNum = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        //初始化RecycleView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        assert mRecyclerView != null;
        mRecyclerView.setHasFixedSize(true);

        //设置LayoutManager
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //设置分割线
        //mRecyclerView.addItemDecoration(new EasyDividerItemDecoration(this ,EasyDividerItemDecoration.VERTICAL_LIST));

        //创建Adapter
        mRecycleAdapter = new RecycleAdapter(mRecyclerView, datas);

        //使用EasyAdapter包装一层 添加头部 尾部
        EasyRecycleAdapter mEasyRecycleAdapter = new EasyRecycleAdapter(mRecycleAdapter);

        //添加头部
        createHead();
        mEasyRecycleAdapter.addHeaderView(headView);

        //添加尾部 上拉加载更多
        createFoot();
        mEasyRecycleAdapter.addFooterView(footView);

        //设置适配器
        mRecyclerView.setAdapter(mEasyRecycleAdapter);

        //添加Scroll监听 上拉加载更多数据
        mRecyclerView.addOnScrollListener(new EasyRecycleOnScrollListener(mLinearLayoutManager)
        {

            @Override
            public void onLoadMore(int currentPage)
            {
                //这里是上拉更多数据的逻辑
                page++;
                loadMoreData();
            }
        });

        //设置item点击事件
        mRecycleAdapter.setOnItemClickListener(new EasyBaseRecycleViewAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(int position, EasyBaseRecycleViewAdapter.ClickableViewHolder holder)
            {

                Toast.makeText(MainActivity.this, datas.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData()
    {

        for (int i = 0; i < 20; i++)
        {
            datas.add("item" + i);
        }
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

        for (int i = 0; i < 20; i++)
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

    private void createHead()
    {

        headView = LayoutInflater.from(this).inflate(R.layout.layout_recycle_head, mRecyclerView, false);
    }
}
