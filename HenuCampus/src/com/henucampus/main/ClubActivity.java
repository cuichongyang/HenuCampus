package com.henucampus.main;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;

public class ClubActivity extends SherlockActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_club);
		getSupportActionBar().setDisplayShowTitleEnabled(true);//���ñ���������ʾ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//���ñ��������ذ�ť, ��ô�����Ӧ��,
         														//Ҳ����дonOptionsItemSelected() ��������ؼ���id��android.R.id.home��
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
