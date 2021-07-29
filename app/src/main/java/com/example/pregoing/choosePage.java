package com.example.pregoing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class choosePage extends AppCompatActivity {

    public static final int sub = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_page);

        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.54917859202561, 126.91607660435088), 1, true);

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("스케줄합정");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.54917859202561, 126.91607660435088));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);

        Button preBtn = (Button)findViewById(R.id.pre_btn);

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FragmentHome.class);
                startActivityForResult(intent,sub);
            }
        });
    }
}