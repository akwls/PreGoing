package com.example.pregoing;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class FragmentHome extends Fragment {
    public static FragmentHome newInstance() {
        return new FragmentHome();
    }
    int cnt = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        MapView mapView = new MapView(getActivity());
        ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.55759443948931, 126.92446076240746), 1, true);

        ImageButton searchBtn = (ImageButton) v.findViewById(R.id.search_btn);
        ImageButton threeBtn = (ImageButton) v.findViewById(R.id.three_btn);
        final LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.pre_place);
        threeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("myapp", "클릭");
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myapp", "클릭");
                ((MainActivity)getActivity()).replaceFragment(FragmentSearch.newInstance());

            }

        });


        return v;
    }
}
