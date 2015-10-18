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
		getSupportActionBar().setDisplayShowTitleEnabled(true);//���ñ���������ʾ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//���ñ��������ذ�ť, ��ô�����Ӧ��,
         														//Ҳ����дonOptionsItemSelected() ��������ؼ���id��android.R.id.home��
        searchSport();
	}

	//actionbarsherlock��дonCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    
	// ��ѯSport��֪ͨ��Ϣ
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
					dataitem.put("from", "���ԣ�" + sports.get(i).getFrom());
					dataitem.put("createAt", "�����ڣ�"+ sports.get(i).getCreatedAt());
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
				Toast.makeText(SportActivity.this, "֪ͨ��Ϣ�б����ʧ��~",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
