package com.henucampus.main;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.henucampus.object.Notice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NoticeItemActivity extends SherlockActivity {
	private TextView noticetitle;
	private TextView noticeinstruction;
	private TextView noticefrom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		Bmob.initialize(this, "cf86c7dc3bee0bba045e6d7c8b49cc1c");//Bmob����ID�����赼��������Ȩ��
		getSupportActionBar().setDisplayShowTitleEnabled(true);//���ñ���������ʾ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//���ñ��������ذ�ť, ��ô�����Ӧ��,
         														//Ҳ����дonOptionsItemSelected() ��������ؼ���id��android.R.id.home��
        Intent intent_item = new Intent();
        String position=intent_item.getStringExtra("position");
        BmobQuery<Notice> bmobQuery = new BmobQuery<Notice>();
        bmobQuery.getObject(NoticeItemActivity.this,"iVzK111L", new GetListener<Notice>() {
            @Override
            public void onSuccess(Notice notice) {
                // TODO Auto-generated method stub
            	noticetitle=(TextView) findViewById(R.id.noticetitle);
            	noticeinstruction=(TextView) findViewById(R.id.noticeinstruction);
            	noticefrom=(TextView) findViewById(R.id.noticefrom);
            	noticetitle.setText(notice.getTitle().toString());
            	//noticeinstruction.setText(notice.getInstruction().toString());
            	//noticefrom.setText(notice.getFrom());
            	Toast.makeText(NoticeItemActivity.this, "��ѯ�ɹ�~",
						Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int code, String msg) {
                // TODO Auto-generated method stub
                Toast.makeText(NoticeItemActivity.this, "��ѯʧ�ܣ�" + msg+ "~",
						Toast.LENGTH_SHORT).show();
            }
        });
        
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
	    
}
