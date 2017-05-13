package com.suctan.huigang.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.androidbase.utils.ToastTool;
import com.suctan.huigang.R;
import com.suctan.huigang.activity.login.LoginActivity;
import com.suctan.huigang.util.Utils;
import com.suctan.huigang.widget.dialog.AlertDialog;

/**
 * Created by haily on 2017/5/9.
 */

public class ShopMap extends CheckPermissionsActivity implements AMap.OnMapClickListener, LocationSource, AMapLocationListener, PoiSearch.OnPoiSearchListener {
    public static final String TAG = "ShopMap";
    MapView mMapView;
    EditText edit_query;
    Button searchMap;
    AMap aMap = null;
    //    private AMapLocationClient locationClient = null;
//    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    double latitude, longitude;
    String area;
    PoiSearch poiSearch;

    /**
     * 提交信息到MainActivity
     */
    private void submitLocation() {
        if (TextUtils.isEmpty(edit_query.getText().toString().trim())) {
            Toast.makeText(this, getResources().getString(R.string.inputshequ), Toast.LENGTH_SHORT).show();
            return;
        } else {

            showDialog();
        }
    }

    private AlertDialog mDialog;

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("确定要你选择的社区和位置信息嘛！")
                .setTopImage(R.drawable.icon_tanchuang_wenhao)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(new Intent(ShopMap.this, LoginActivity.class));
                        intent.putExtra("latitude", latitude);
                        intent.putExtra("longitude", longitude);
                        intent.putExtra("area", area);
                        Log.i(TAG, "onClick: 提交的信息是"+area+longitude+latitude);
                        setResult(2, intent);
                        mDialog.dismiss();
                        finish();
//                        requstDeleteFood(position);
                    }
                });
        mDialog = builder.create();
        mDialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        searchMap = (Button) findViewById(R.id.searchMap);
        searchMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLocation();
//keyWord表示搜索字符串，
//第二个参数表示POI搜索类型，二者选填其一，
//POI搜索类型共分为以下20种：汽车服务|汽车销售|
//汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
//住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
//金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
//cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索

//                String qu = edit_query.getText().toString();
//                PoiSearch.Query query = new PoiSearch.Query(qu, "", "");
//                query.setPageSize(10);// 设置每页最多返回多少条poiitem
//                query.setPageNum(0);//设置查询页码
//                poiSearch = new PoiSearch(ShopMap.this, query);
//                poiSearch.setOnPoiSearchListener(ShopMap.this);
//                poiSearch.searchPOIAsyn();


            }


        });
        edit_query = (EditText) findViewById(R.id.edit_query);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        init();
        //初始化定位
//        initLocation();
//        startLocation();
        //初始化地图控制器对象

//        if (aMap == null) {
//            aMap = mMapView.getMap();
////            aMap.getUiSettings().setRotateGesturesEnabled(false);
////            aMap.moveCamera(CameraUpdateFactory.zoomBy(6));
//            Log.i(TAG, "onCreate: ");
//            setUpMap();
//        }
    }

    void init() {
        if (aMap == null) {
            aMap = mMapView.getMap();
//            aMap.getUiSettings().setRotateGesturesEnabled(true);
            mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
//            mUiSettings.setZoomPosition(20);
            mUiSettings.setAllGesturesEnabled(true);
            aMap.setOnMapClickListener(this);
//            mUiSettings.setCompassEnabled(true);
//            aMap.moveCamera(CameraUpdateFactory.zoomBy(6));
            // 设置定位参数
            setUpMap();

        }
    }
    // 根据控件的选择，重新设置定位参数
//    private void resetOption() {
//        // 设置是否需要显示地址信息
//        locationOption.setNeedAddress(true);
////        locationOption.setNeedAddress(cbAddress.isChecked());
//        /**
//         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
//         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
//         */
////        locationOption.setGpsFirst(cbGpsFirst.isChecked());
////        // 设置是否开启缓存
////        locationOption.setLocationCacheEnable(cbCacheAble.isChecked());
////        // 设置是否单次定位
////        locationOption.setOnceLocation(cbOnceLocation.isChecked());
////        //设置是否等待设备wifi刷新，如果设置为true,会自动变为单次定位，持续定位时不要使用
////        locationOption.setOnceLocationLatest(cbOnceLastest.isChecked());
////        //设置是否使用传感器
////        locationOption.setSensorEnable(cbSensorAble.isChecked());
//        //设置是否开启wifi扫描，如果设置为false时同时会停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
////        String strInterval = etInterval.getText().toString();
//        if (!TextUtils.isEmpty("1000")) {
//            try{
//                // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
//                locationOption.setInterval(Long.valueOf("1000"));
//            }catch(Throwable e){
//                e.printStackTrace();
//            }
//        }
//
////        String strTimeout = etHttpTimeout.getText().toString();
//        if(!TextUtils.isEmpty("30000")){
//            try{
//                // 设置网络请求超时时间
//                locationOption.setHttpTimeOut(Long.valueOf("30000"));
//            }catch(Throwable e){
//                e.printStackTrace();
//            }
//        }
//    }


//    /**
//     * 开始定位
//     *
//     * @since 2.8.0
//     * @author hongming.wang
//     *
//     */
//    private void startLocation(){
//        //根据控件的选择，重新设置定位参数
//        resetOption();
//        // 设置定位参数
//        locationClient.setLocationOption(locationOption);
//        // 启动定位
//        locationClient.startLocation();
//    }
//    /**
//     * 默认的定位参数
//     * @since 2.8.0
//     * @author hongming.wang
//     *
//     */
//    private AMapLocationClientOption getDefaultOption(){
//        AMapLocationClientOption mOption = new AMapLocationClientOption();
//        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
//        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
//        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
//        mOption.setInterval(3000);//可选，设置定位间隔。默认为2秒
//        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
//        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
//        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
//        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
//        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
//        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
//        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
//        return mOption;
//    }
//
//    /**
//     * 定位监听
//     */
//    AMapLocationListener locationListener = new AMapLocationListener() {
//        @Override
//        public void onLocationChanged(AMapLocation loc) {
//            if (null != loc) {
//                //解析定位结果
//                String result = Utils.getLocationStr(loc);
//                Log.i(TAG, "onLocationChanged: "+result);
//                ToastTool.showToast(result ,1);
//                mListener.onLocationChanged(loc);// 显示系统小蓝点
//                // 停止定位
//                locationClient.stopLocation();
//            } else {
//                Log.i(TAG, "onLocationChanged: "+"定位失败，loc is null");
//                ToastTool.showToast("定位失败，loc is null" ,1);
////                tvResult.setText("定位失败，loc is null");
//            }
//        }
//    };


    /**
     * 初始化定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
//    private void initLocation(){
//        //初始化client
//        locationClient = new AMapLocationClient(this.getApplicationContext());
//        //设置定位参数
//        locationClient.setLocationOption(getDefaultOption());
//        // 设置定位监听
//        locationClient.setLocationListener(locationListener);
//    }


    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        aMap.setOnMapClickListener(this);
        aMap.setLocationSource(this);// 设置定位监听
        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
        mUiSettings.setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        mUiSettings.setScaleControlsEnabled(true);//控制比例尺控件是否显示
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(
                BitmapDescriptorFactory.fromResource(R.drawable.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(0);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    Marker marker;

    @Override
    public void onMapClick(LatLng latLng) {
        if (marker != null) {
            marker.remove();
        }
        marker = aMap.addMarker(new MarkerOptions().position(latLng).title("确定选择这儿吗！").snippet(""));
        marker.showInfoWindow();
        marker.setVisible(true);
        marker.isVisible();
//        double d1 = CurrentUser.getInstance().getUserBean().getLatitude();
//        double d2 = CurrentUser.getInstance().getUserBean().getLongitude();
        double d3 = latLng.latitude;
        double d4 = latLng.longitude;
        LatLng startLatlng = new LatLng(latitude, longitude);
        LatLng endLatlng = new LatLng(d3, d4);
        int juli = (int) AMapUtils.calculateLineDistance(startLatlng, endLatlng); //来计算两点距离，单位：米。
        if (juli > 1000) {
            ToastTool.showToast("你选择的点误差超过1000米,请重新选择一个点", 0);
        } else {
            latitude = d3;
            longitude = d4;
        }


        Log.i("123132132", "getView: " + "距离" + juli + "米");
        Log.i(TAG, "onMapClick: 纬度是" + latLng.latitude + "经度是" + latLng.longitude);
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

//    @Override
//    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//        if (null == locationOption) {
//            locationOption = new AMapLocationClientOption();
//        }
//        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
////        switch (checkedId) {
////            case R.id.rb_batterySaving :
////                locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
////                break;
////            case R.id.rb_deviceSensors :
////                locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
////                break;
////            case R.id.rb_hightAccuracy :
////                locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
////                break;
////            default :
////                break;
////        }
//    }

    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                String result = Utils.getLocationStr(amapLocation);
                area = amapLocation.getCountry() + amapLocation.getProvince() + amapLocation.getCity() + amapLocation.getDistrict()+edit_query.getText().toString().trim();
                latitude = amapLocation.getLatitude();
                longitude = amapLocation.getLongitude();

//                sb.append("经    度    : " + location.getLongitude() + "\n");
//                sb.append("纬    度    : " + location.getLatitude() + "\n");
//                LatLng latLng = new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());
//                final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(amapLocation.getProvince()).snippet("DefaultMarker").visible(true));
                Log.i(TAG, "onLocationChanged: " + result);
//                double longitude = amapLocation.getLongitude();//经度
//                double latitude = amapLocation.getLatitude();//纬度
//                ToastTool.showToast(result ,1);
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                mlocationClient.stopLocation();

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
//        Utils.getLocationStr(poiResult.getPois().get(i));
        Log.i(TAG, "onPoiSearched:响应码是 " + i);
        for (int j = 0; j < poiResult.getPois().size(); j++) {
            String cityName = Utils.getLocationString(poiResult.getPois().get(j));
            Log.i(TAG, "信息是: " + cityName);

        }


    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        Log.i(TAG, "onPoiItemSearched: " + poiItem + i);
    }
}