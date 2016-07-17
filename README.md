# EasyRecycleAdapterHelper

一个简单实用的RecycleViewAdapter帮助库


## 简介
 * 1.RecycleView的OnItemClick,OnItemLongClick的点击事件
 * 2.RecycleView的Item动画,默认实现了一种动画效果,可自行扩展
 * 3.添加头部尾部,分割线
 * 4.列表和表格布局的上拉加载更多

## 截图

![](https://github.com/HotBitmapGG/EasyRecycleAdapterHelper/blob/master/pic/01.gif?raw=true)

![](https://github.com/HotBitmapGG/EasyRecycleAdapterHelper/blob/master/pic/02.gif?raw=true)


## 使用说明

 * 首先用您的RecycleAdapter继承EasyBaseRecycleViewAdapter

 * public class RecycleAdapter extends EasyBaseRecycleViewAdapter

 * Adapter

    ```java
     @Override
     public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
     {

         //绑定上下文
         bindContext(parent.getContext());
         //创建ViewHolder
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
     ```


 * Activity

       ```java
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
          ```
          ```


  ## Other

  * 知了日报客户端: https://github.com/HotBitmapGG/RxZhiHu

  * 高仿BiliBili客户端: https://github.com/HotBitmapGG/OhMyBiliBili

  * Gank.IO客户端: https://github.com/HotBitmapGG/StudyProject

  * 妹子福利App: https://github.com/HotBitmapGG/MoeQuest

  * 圆环进度条:https://github.com/HotBitmapGG/RingProgressBar

  * 安卓学习代码练习:https://github.com/HotBitmapGG/AndroidEveryDayPractice

  ## License

   Copyright 2016 HotBitmapGG

   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.




