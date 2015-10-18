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
import com.henucampus.object.Club;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ClubActivity extends SherlockActivity {
	private ListView ClubList;
	private SimpleAdapter ClubAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_club);
		getSupportActionBar().setDisplayShowTitleEnabled(true);//设置标题文字显示
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置标题栏返回按钮, 那么如何响应呢,
         														//也是重写onOptionsItemSelected() ，这个返回键的id是android.R.id.home。
        searchClub();
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
    
 // 查询Club表，通知信息
    public void searchClub() {
		BmobQuery<Club> query = new BmobQuery<Club>();
		query.order("-createdAt");
		query.findObjects(this, new FindListener<Club>() {
			@Override
			public void onSuccess(List<Club> clubs) {
				List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < clubs.size(); i++) {
					Map<String, Object> dataitem = new HashMap<String, Object>();
					dataitem.put("title", clubs.get(i).getTitle());
					dataitem.put("from", "来自：" + clubs.get(i).getFrom());
					dataitem.put("createAt", "发布于："+ clubs.get(i).getCreatedAt());
					datalist.add(dataitem);
				}
				ClubAdapter = new SimpleAdapter(ClubActivity.this, datalist,
						R.layout.item_club, new String[] { "title","from","createAt" },
						new int[] { R.id.clubtitle, R.id.clubfrom,R.id.clubcreateAt });
				ClubList = (ListView) findViewById(R.id.Clublist);
				ClubList.setAdapter(ClubAdapter);
			}

			@Override
			public void onError(int code, String arg0) {
				Toast.makeText(ClubActivity.this, "通知信息列表加载失败~",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
