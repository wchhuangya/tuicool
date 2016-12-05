package com.ch.wchhuangya.dzah.tuicool.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by wchya on 2016-11-23 16:45
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    // 在加载更多之前，当前滚动位置以下元素的最小数目
    private int visibleThreshold = 2;
    // 相对于起始页，当前页数的偏移量
    private int curPage = 0;
    // 最后一次加载后，数据集中元素的总数
    private int preTotalItemCount = 0;
    // 正在等待最新数据集合的加载为 ture，其余 false
    private boolean loading = true;
    // 设置起始页索引
    private int startingPageIndex = 0;

    RecyclerView.LayoutManager mLayoutManager;

    public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public EndlessRecyclerViewScrollListener(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    public EndlessRecyclerViewScrollListener(StaggeredGridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    /**
     * 在滚动中，该方法在每秒会被调用很多次。因此，在这里写代码一定要谨慎。
     * 如果需要加载更多，这些参数比较有用。
     * 但是首先，得判断上次加载是否成功。
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();

        if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);
            // get maximum element within the list
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
        } else if (mLayoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        } else if (mLayoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        }

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        // 如果元素总数为 0 并且原先元素就不存在，就假定列表不可用并应该被重置为初始状态
        if (totalItemCount < preTotalItemCount) {
            this.curPage = this.startingPageIndex;
            this.preTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        // 如果当前仍然处于加载状态，就检查数据集是否改变。
        // 如果推断出加载已经完成，就更新当前的页数偏移量和元素总数
        if (loading && (totalItemCount > preTotalItemCount)) {
            loading = false;
            preTotalItemCount = totalItemCount;
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        // 如果当前没有加载数据，就检查是否已经到达我们所设定的阈值，是否需要加载数据
        // 如果需要加载数据，就执行 onLoadMore 方法加载
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            curPage++;
            onLoadMore(curPage, totalItemCount, recyclerView);
            loading = true;
        }
    }

    // Call this method whenever performing new searches
    // 当需要执行全新的查找操作时，调用该方法
    public void resetState() {
        this.curPage = this.startingPageIndex;
        this.preTotalItemCount = 0;
        this.loading = true;
    }

    // Defines the process for actually loading more data based on page
    // 定义真正用于加载更多数据的方法声明
    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);
}
