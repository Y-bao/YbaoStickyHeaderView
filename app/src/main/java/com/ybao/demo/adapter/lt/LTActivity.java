package com.ybao.demo.adapter.lt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.ybao.demo.R;
import com.ybao.sticky.StickyHeaderView;

public class LTActivity extends AppCompatActivity {
    ListView listView;
    StickyHeaderView stickyHeaderView;
    LtStickyHeaderCacheViewAdapter ltStickyHeaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lt);
        listView = (ListView) findViewById(R.id.listview);
        stickyHeaderView = (StickyHeaderView) findViewById(R.id.stickyheaderview);
        ltStickyHeaderAdapter = new LtStickyHeaderCacheViewAdapter();
        listView.setAdapter(ltStickyHeaderAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view.getChildCount() > 0) {
                    stickyHeaderView.update(view.getChildAt(0), firstVisibleItem, ltStickyHeaderAdapter.getStickyHeaderHelper());
                } else {
                    stickyHeaderView.setVisibility(View.GONE);
                }
            }
        });
    }
}
