package com.henucampus.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.henucampus.object.Notice;
import com.henucampus.object.Sport;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SportActivity extends SherlockActivity {
	private ListView SportList;
	private SimpleAdapter SportAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sport);
		getSupportActionBar().setDisplayShowTitleEnabled(true);//设置标题文字显示
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置标题栏返回按钮, 那么如何响应呢,
         														//也是重写onOptionsItemSelected() ，这个返回键的id是android.R.id.home。
        searchSport();
	}

	//actionbarsherlock重写onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
  //标题栏按钮点击事件
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
    
	// 查询Sport表，通知信息
    public void searchSport() {
		BmobQuery<Sport> query = new BmobQuery<Sport>();
		query.order("-createdAt");
		query.findObjects(this, new FindListener<Sport>() {
			@Override
			public void onSuccess(List<Sport> sports) {
				List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < sports.size(); i++) {
					Map<String, Object> dataitem = new HashMap<String, Object>();
					dataitem.put("title", sports.get(i).getTitle());
					dataitem.put("from", "来自：" + sports.get(i).getFrom());
					dataitem.put("createAt", "发布于："+ sports.get(i).getCreatedAt());
					datalist.add(dataitem);
				}
				SportAdapter = new SimpleAdapter(SportActivity.this, datalist,
						R.layout.item_sport, new String[] { "title","from","createAt" },
						new int[] { R.id.sporttitle, R.id.sportfrom,R.id.sportcreateAt });
				SportList = (ListView) findViewById(R.id.Sportlist);
				SportList.setAdapter(SportAdapter);
			}

			@Override
			public void onError(int code, String arg0) {
				Toast.makeText(SportActivity.this, "通知信息列表加载失败~",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
