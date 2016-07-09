package com.firebase.samples.logindemo.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.samples.logindemo.R;
import com.firebase.samples.logindemo.adapters.HeaderNumberedAdapter;
import com.firebase.samples.logindemo.extra.MarginDecoration;

/**
 * Created by arms on 2/22/16.
 */
public class MyProductTabs extends Fragment {
private Context mContext;
    public MyProductTabs() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.my_product_list_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_product);
        recyclerView.addItemDecoration(new MarginDecoration(mContext));
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(manager);

        View header = LayoutInflater.from(mContext).inflate(R.layout.header, recyclerView, false);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), R.string.grid_layout_header, Toast.LENGTH_SHORT).show();
            }
        });
        final HeaderNumberedAdapter adapter = new HeaderNumberedAdapter(header, 30);
        recyclerView.setAdapter(adapter);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isHeader(position) ? manager.getSpanCount() : 1;
            }
        });
        return view;
    }

    }


