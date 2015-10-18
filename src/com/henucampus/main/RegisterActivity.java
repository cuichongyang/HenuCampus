package com.henucampus.main;

import cn.bmob.v3.listener.SaveListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.henucampus.object.Image;
import com.henucampus.object.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends SherlockActivity {
	private Button btn_ok;
	private Button btn_remove;
	private EditText name;
	private EditText password;
	private ImageView head;
	private EditText phone;
	private EditText qq;
	private RadioGroup rg;
	private String registersex = "��";
	private User user = null;
	private TextView selecthead;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		getSupportActionBar().setDisplayShowTitleEnabled(true);//���ñ���������ʾ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//���ñ��������ذ�ť, ��ô�����Ӧ��,
         														//Ҳ����дonOptionsItemSelected() ��������ؼ���id��android.R.id.home��
		btn_ok = (Button) findViewById(R.id.ok);
		btn_remove = (Button) findViewById(R.id.cancel);
		selecthead=(TextView) findViewById(R.id.selecthead);
		name = (EditText) findViewById(R.id.registername);
		password = (EditText) findViewById(R.id.registerpassword);
		head=(ImageView) findViewById(R.id.head);
		phone = (EditText) findViewById(R.id.registerphone);
		qq = (EditText) findViewById(R.id.registerqq);
		rg = (RadioGroup) findViewById(R.id.rg1);
		user = new User();
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				registersex = checkedId == R.id.mail ? "��" : "Ů";
			}
		});
		
		head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(RegisterActivity.this,ImageActivity.class);
				startActivityForResult(intent, 1000);
			}
		});
		
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				user.setName(name.getText().toString());
				user.setPassword(password.getText().toString());
				user.setSex(registersex);
				user.setPhone(phone.getText().toString());
				user.setQq(qq.getText().toString());
				user.save(RegisterActivity.this, new SaveListener() {

					@Override
					public void onSuccess() {
						Toast.makeText(
								RegisterActivity.this,
								"ע��ɹ�\n������" + name.getText().toString()
										+ "\n���룺"
										+ password.getText().toString()
										+ "\n�Ա�:" + registersex + "\n�绰��"
										+ phone.getText().toString() + "\nQQ:"
										+ qq.getText().toString() + "���ص�¼�����¼",
								Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(RegisterActivity.this,
								LoginActivity.class);
						startActivity(intent);
					}

					@Override
					public void onFailure(int arg0, String arg1) {
						Toast.makeText(RegisterActivity.this, "ע��ʧ�ܣ�������ע��",
								Toast.LENGTH_SHORT).show();
						return;
					}
				});

			}
		});
		
		btn_remove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(RegisterActivity.this, "ȡ��ע�ᣬ���ص�¼����",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
				startActivity(intent);

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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==1000&&resultCode==2000) {
			Image image=(Image) data.getSerializableExtra("image");
			head.setImageResource(image.getImage());		
		}
	}

}
