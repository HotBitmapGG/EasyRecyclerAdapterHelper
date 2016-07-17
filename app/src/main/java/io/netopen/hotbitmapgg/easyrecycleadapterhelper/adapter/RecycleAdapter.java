package io.netopen.hotbitmapgg.easyrecycleadapterhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.netopen.hotbitmapgg.easyrecycleadapterhelper.R;
import io.netopen.hotbitmapgg.library.adapter.EasyBaseRecycleViewAdapter;

/**
 * Created by hcc on 16/7/17 14:39
 * 100332338@qq.com
 */
public class RecycleAdapter extends EasyBaseRecycleViewAdapter
{

    private List<String> datas = new ArrayList<>();

    public RecycleAdapter(RecyclerView recyclerView, List<String> datas)
    {

        super(recyclerView);
        this.datas = datas;
    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        bindContext(parent.getContext());
        return new ItemViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position)
    {

        if (holder instanceof ItemViewHolder)
        {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mTextView.setText(datas.get(position));

            //添加动画
            showItemAnim(itemViewHolder.mLayout, position);
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount()
    {

        return datas.size();
    }

    private class ItemViewHolder extends EasyBaseRecycleViewAdapter.ClickableViewHolder
    {

        public TextView mTextView;

        public LinearLayout mLayout;

        public ItemViewHolder(View itemView)
        {

            super(itemView);
            mTextView = $(R.id.text);
            mLayout = $(R.id.item_layout);
        }
    }
}
