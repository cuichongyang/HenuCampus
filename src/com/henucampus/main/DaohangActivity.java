package com.henucampus.main;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.henucampus.ArcView.ArcMenu;
import com.henucampus.ArcView.ArcMenu.OnMenuItemClickListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DaohangActivity extends SherlockActivity implements LocationSource,AMapLocationListener { 
	//���ǲ˵�
	private ArcMenu                                 mArcMenu;
	//AMap �ǵ�ͼ�Ķ���AMap ��ͼ����ͨ�� MapFragment �� MapView �����������֡�
	private AMap                                      aMap;
	private MapView                                 mapView;
	// ��λ���
	private OnLocationChangedListener      mListener;
	private LocationManagerProxy             mAMapLocationManager;
	private Context                                   context;
	//��ͼ�ؼ�
	private  UiSettings                               mUiSetting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daohang);
		getSupportActionBar().setDisplayShowTitleEnabled(true);//���ñ���������ʾ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//���ñ��������ذ�ť, ��ô�����Ӧ��,
         														//Ҳ����дonOptionsItemSelected() ��������ؼ���id��android.R.id.home��
		
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// �˷���������д
		this.context = this;	
		init(); 
		initView();
		initEvent();
		mUiSetting.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_CENTER);// ���õ�ͼlogo��ʾ�ڵײ�����
		mUiSetting.setScaleControlsEnabled(true);//������ʾ��ͼ��Ĭ�ϱ�����
	}

    //��������ť����¼�
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.add:    
                    
                return true;
              
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	/*
	 * ���ǲ˵�����¼�
	 */
	private void initEvent()
	{
		mArcMenu.setOnMenuItemClickListener(new OnMenuItemClickListener()
		{
			@Override
			public void onClick(View view, int pos)
			{				
				switch (pos)
				{
				case 1 :
					 aMap.setMapType(AMap.MAP_TYPE_NORMAL);// ʸ����ͼģʽ
					Toast.makeText(context, "��ͨģʽ",
							Toast.LENGTH_SHORT).show();
					break;
				case 2:
					aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// ���ǵ�ͼģʽ
					Toast.makeText(context, "����ģʽ",
							Toast.LENGTH_SHORT).show();
					break;
				case 3:				
					 aMap.setMapType(AMap.MAP_TYPE_NIGHT);//ҹ����ͼģʽ
						Toast.makeText(context, "ҹ����ͼģʽ",
								Toast.LENGTH_SHORT).show();
					break;
				
				case 4:
					Toast.makeText(context, "����",
							Toast.LENGTH_SHORT).show();
					break;
				case 5:
					Toast.makeText(context, "������·",
							Toast.LENGTH_SHORT).show();
					break;
				case 6:
					 if (aMap.isTrafficEnabled()) {
					        aMap.setTrafficEnabled(true);
					        Toast.makeText(context, "ʵʱ��ͨ�Ѵ�",
									Toast.LENGTH_SHORT).show();
					    }
					 else{
						 aMap.setTrafficEnabled(false);
					Toast.makeText(context, "ʵʱ��ͨ�ѹر�",
							Toast.LENGTH_SHORT).show();
					break;
					 }
				}
			}
		});
	}

	//actionbarsherlock��дonCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	private void initView()
	{
		
		mArcMenu = (ArcMenu) findViewById(R.id.id_menu);
		
		
	}

	/**
	 * ��ʼ��AMap����
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
			  mUiSetting=aMap.getUiSettings() ;
		}
	}

	/**
	 * ����һЩamap������
	 */
	private void setUpMap() {
		// �Զ���ϵͳ��λС����
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory
				.fromResource(R.drawable.location_marker));// ����С�����ͼ��
		myLocationStyle.strokeColor(Color.BLACK);// ����Բ�εı߿���ɫ
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// ����Բ�ε������ɫ
		// myLocationStyle.anchor(int,int)//����С�����ê��
		myLocationStyle.strokeWidth(1.0f);// ����Բ�εı߿��ϸ
		aMap.setMyLocationStyle(myLocationStyle);
		aMap.setLocationSource(this);// ���ö�λ����
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// ����Ĭ�϶�λ��ť�Ƿ���ʾ
		aMap.setMyLocationEnabled(true);// ����Ϊtrue��ʾ��ʾ��λ�㲢�ɴ�����λ��false��ʾ���ض�λ�㲢���ɴ�����λ��Ĭ����false
	   // aMap.setMyLocationType()
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * ����������д
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	/**
	 * �˷����Ѿ�����
	 */
	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	/**
	 * ��λ�ɹ���ص�����
	 */
	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		if (mListener != null && aLocation != null) {
			mListener.onLocationChanged(aLocation);// ��ʾϵͳС����
			Toast.makeText(context, aLocation.getAddress(),
					Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(context, "��λʧ��",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * ���λ
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			/*
			 * mAMapLocManager.setGpsEnable(false);
			 * 1.0.2�汾��������������true��ʾ��϶�λ�а���gps��λ��false��ʾ�����綨λ��Ĭ����true Location
			 * API��λ����GPS�������϶�λ��ʽ
			 * ����һ�������Ƕ�λprovider���ڶ�������ʱ�������2000���룬������������������λ���ף����ĸ������Ƕ�λ������
			 */
			mAMapLocationManager.requestLocationData(
					LocationProviderProxy.AMapNetwork,60000, 10, this);
		}
	}

	/**
	 * ֹͣ��λ
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destroy();
		}
		mAMapLocationManager = null;
	}

}


