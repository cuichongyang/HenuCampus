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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class NoticeActivity extends SherlockActivity {
	private ListView NoticeList;
	private SimpleAdapter NoticeAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		getSupportActionBar().setDisplayShowTitleEnabled(true);//���ñ���������ʾ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//���ñ��������ذ�ť, ��ô�����Ӧ��,
         														//Ҳ����дonOptionsItemSelected() ��������ؼ���id��android.R.id.home��
        NoticeList = (ListView) findViewById(R.id.Noticelist);
        
        
        
        searchNotice();
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
    
	// ��ѯNotice��֪ͨ��Ϣ
    public void searchNotice() {
		BmobQuery<Notice> query = new BmobQuery<Notice>();
		query.order("-createdAt");
		query.findObjects(this, new FindListener<Notice>() {
			@Override
			public void onSuccess(final List<Notice> notices) {
				List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < notices.size(); i++) {
					Map<String, Object> dataitem = new HashMap<String, Object>();
					dataitem.put("title", notices.get(i).getTitle());
					dataitem.put("from", "���ԣ�" + notices.get(i).getFrom());
					dataitem.put("createAt", "�����ڣ�"+ notices.get(i).getCreatedAt());
					datalist.add(dataitem);
				}
				NoticeAdapter = new SimpleAdapter(NoticeActivity.this, datalist,
						R.layout.item_notice, new String[] { "title","from","createAt" },
						new int[] { R.id.noticetitle, R.id.noticefrom,R.id.noticecreateAt });
				NoticeList.setAdapter(NoticeAdapter);
				
				NoticeList.setOnItemClickListener(new OnItemClickListener(){
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
						Intent intent_item = new Intent();
						intent_item.setClass(NoticeActivity.this, NoticeItemActivity.class);
						intent_item.putExtra("position", notices.get(position).getObjectId());
		                startActivity(intent_item);
					}
		        });
				
			}

			@Override
			public void onError(int code, String arg0) {
				Toast.makeText(NoticeActivity.this, "֪ͨ��Ϣ�б����ʧ��~",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
    
}
