package com.ybao.demo.adapter.rc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ybao.demo.R;
import com.ybao.sticky.StickyHeaderView;

public class RCActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    StickyHeaderView stickyHeaderView;
    RcStickyHeaderCacheViewAdapter rcGroupHeaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        stickyHeaderView = (StickyHeaderView) findViewById(R.id.stickyheaderview);
        rcGroupHeaderAdapter = new RcStickyHeaderCacheViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rcGroupHeaderAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getChildCount() > 0) {
                    int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    stickyHeaderView.update(recyclerView.getChildAt(0), firstVisibleItem, rcGroupHeaderAdapter.getStickyHeaderHelper());
                } else {
                    stickyHeaderView.setVisibility(View.GONE);
                }
            }
        });
    }
}
