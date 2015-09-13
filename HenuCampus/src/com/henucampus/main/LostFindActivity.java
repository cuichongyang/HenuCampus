package com.henucampus.main;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.henucampus.object.Find;
import com.henucampus.object.Lost;

/*
 * ʧ�������Activity��extends SherlockActivity��Ҫ����actionbarsherlock��
 */
public class LostFindActivity extends SherlockActivity {
	private ToggleButton lostfindtbn;//ʧ�������Ѱ�����¼��ת��
	private ListView LostList;
	private SimpleAdapter LostAdapter;
	private ListView FindList;
	private SimpleAdapter FindAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostfind);
        Bmob.initialize(this, "cf86c7dc3bee0bba045e6d7c8b49cc1c");//Bmob����ID�����赼��������Ȩ��
        getSupportActionBar().setDisplayShowTitleEnabled(true);//���ñ���������ʾ
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//���ñ��������ذ�ť, ��ô�����Ӧ��,
         														//Ҳ����дonOptionsItemSelected() ��������ؼ���id��android.R.id.home��
        searchLost();//��ʼѰ������ 
        lostfindtbn=(ToggleButton) findViewById(R.id.lostfindtbn);//ʧ�������Ѱ�����¼��ת����ť
        lostfindtbn.setOnCheckedChangeListener(new OnCheckedChangeListener() {//����¼�
			@Override
        	public void onCheckedChanged(CompoundButton buttonView,  
                    boolean isChecked) {  
                if (isChecked) {  
                	searchFind();//Ѱ������  
                } else {  
                	searchLost();//ʧ������  
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
                addLostFind();     
                return true;
              
            default:
                return super.onOptionsItemSelected(item);
        }
    }
     
    
    //��ֹ�����ť�˳�dialog
	public void poaseBack(DialogInterface arg0){
		try {
			Field field = arg0.getClass().getSuperclass()
					.getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(arg0, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//��������ť�˳�dialog
	public void allowBack(DialogInterface arg0){
		try {
			Field field = arg0.getClass().getSuperclass()
					.getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(arg0, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    //���Lost��Find
    private void addLostFind() {
    	final TableLayout addlostfind;
    	
    	addlostfind= (TableLayout) getLayoutInflater().inflate(R.layout.dialog_addlostfind, null);
    	AlertDialog.Builder builder = new Builder(LostFindActivity.this);
    	builder.setTitle("��д��Ϣ")
		.setView(addlostfind)
		.setPositiveButton("ȷ��", new OnClickListener() {
			EditText addtitle = (EditText) addlostfind.findViewById(R.id.addtitle);
			EditText adddate = (EditText) addlostfind.findViewById(R.id.adddate);
			EditText adddescribe = (EditText) addlostfind.findViewById(R.id.adddescribe);
			EditText addphone = (EditText) addlostfind.findViewById(R.id.addphone);
			RadioButton addlost=(RadioButton) addlostfind.findViewById(R.id.addlost);
			private String titletext,datetext,describetext,phonetext;
			// ȷ���ύʧ��������Ϣ
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
				titletext=addtitle.getText().toString();
				datetext=adddate.getText().toString();
				describetext=adddescribe.getText().toString();
				phonetext=addphone.getText().toString();
				
				
				if (titletext.equals("")) {//��Ʒ����Ϊ��ʱ����ʾ���룬��ֹ�ر�
					Toast.makeText(LostFindActivity.this, "��������Ʒ����",Toast.LENGTH_SHORT).show();
					poaseBack(arg0);
				} else if (datetext.equals("")) {//��ʧ����Ϊ��ʱ����ʾ���룬��ֹ�ر�
					Toast.makeText(LostFindActivity.this, "�����붪ʧ/ʰ������",Toast.LENGTH_SHORT).show();
					poaseBack(arg0);
				} else if (describetext.equals("")) {//��Ʒ����Ϊ��ʱ����ʾ���룬��ֹ�ر�
					Toast.makeText(LostFindActivity.this, "��������Ʒ����",Toast.LENGTH_SHORT).show();
					poaseBack(arg0);
				} else if (phonetext.equals("")) {//��ϵ��ʽΪ��ʱ����ʾ���룬��ֹ�ر�
					Toast.makeText(LostFindActivity.this, "��������ϵ��ʽ",Toast.LENGTH_SHORT).show();
					poaseBack(arg0);
				} else {//��֤ͨ��������رգ��洢����
					if(addlost.isChecked()){
						Lost l = new Lost();
						l.setTitle(titletext);
						l.setDate(datetext);
						l.setDescribe(describetext);
						l.setPhone(phonetext);	
						l.save(LostFindActivity.this, new SaveListener() {
							@Override
							public void onSuccess() {
								Toast.makeText(LostFindActivity.this, "��Ϣ�洢�ɹ�",
										Toast.LENGTH_SHORT).show();

							}
							@Override
							public void onFailure(int arg0, String arg1) {
								Toast.makeText(LostFindActivity.this, "��Ϣ�洢ʧ��",
										Toast.LENGTH_SHORT).show();
							}
						});
					}else{
						Find f = new Find();
						f.setTitle(titletext);
						f.setDate(datetext);
						f.setDescribe(describetext);
						f.setPhone(phonetext);	
						f.save(LostFindActivity.this, new SaveListener() {
							@Override
							public void onSuccess() {
								Toast.makeText(LostFindActivity.this, "��Ϣ�洢�ɹ�",
										Toast.LENGTH_SHORT).show();

							}
							@Override
							public void onFailure(int arg0, String arg1) {
								Toast.makeText(LostFindActivity.this, "��Ϣ�洢ʧ��",
										Toast.LENGTH_SHORT).show();
							}
						});
					}
				}
			}
		}).setNegativeButton("ȡ��", new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// ȡ���ύ��Ϣ������ر�
				allowBack(arg0);
			}
		}).create().show();
		
	}

	// ��ѯLost��Ѱ������
    public void searchLost() {
		BmobQuery<Lost> query = new BmobQuery<Lost>();
		query.order("-createdAt");
		query.findObjects(this, new FindListener<Lost>() {
			@Override
			public void onSuccess(List<Lost> losts) {
				List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < losts.size(); i++) {
					Map<String, Object> dataitem = new HashMap<String, Object>();
					dataitem.put("photo", losts.get(i).getPhoto());
					dataitem.put("title", "��Ʒ��" + losts.get(i).getTitle());
					dataitem.put("date", "���ڣ�" + losts.get(i).getDate());
					dataitem.put("describe", "������"
							+ losts.get(i).getDescribe());
					dataitem.put("phone", "tel:" + losts.get(i).getPhone());
					dataitem.put("createAt", "�����ڣ�"
							+ losts.get(i).getCreatedAt());
					datalist.add(dataitem);
				}
				LostAdapter = new SimpleAdapter(LostFindActivity.this, datalist,
						R.layout.item_lost, new String[] { "photo", "title",
								"date", "describe", "phone", "createAt" },
						new int[] { R.id.lostphoto, R.id.losttitle, R.id.lostdate,
								R.id.lostdescribe, R.id.lostphone, R.id.lostcreateAt });
				LostList = (ListView) findViewById(R.id.LostOrFindList);
				LostList.setAdapter(LostAdapter);
			}

			@Override
			public void onError(int code, String arg0) {
				Toast.makeText(LostFindActivity.this, "Ѱ�������б����ʧ��~",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
    
    // ��ѯFind��,ʧ������
	public void searchFind() {
		BmobQuery<Find> query = new BmobQuery<Find>();
		query.order("-createdAt");
		query.findObjects(this, new FindListener<Find>() {
			@Override
			public void onSuccess(List<Find> finds) {
				List<Map<String, Object>> datalist = new ArrayList<Map<String, Object>>();
				for (int i = 0; i < finds.size(); i++) {
					Map<String, Object> dataitem = new HashMap<String, Object>();
					dataitem.put("photo", finds.get(i).getPhoto());
					dataitem.put("title", "��Ʒ��" + finds.get(i).getTitle());
					dataitem.put("date", "��ʧ�ڣ�" + finds.get(i).getDate());
					dataitem.put("describe", "������"
							+ finds.get(i).getDescribe());
					dataitem.put("phone", "tel:" + finds.get(i).getPhone());
					dataitem.put("createAt", "�����ڣ�"
							+ finds.get(i).getCreatedAt());
					datalist.add(dataitem);
				}
				FindAdapter = new SimpleAdapter(LostFindActivity.this, datalist,
						R.layout.item_find, new String[] { "photo", "title",
								"date", "describe", "phone", "createAt" },
						new int[] { R.id.findphoto, R.id.findtitle, R.id.finddate,
								R.id.finddescribe, R.id.findphone, R.id.findcreateAt });
				FindList = (ListView) findViewById(R.id.LostOrFindList);
				FindList.setAdapter(FindAdapter);
			}

			@Override
			public void onError(int code, String arg0) {
				Toast.makeText(LostFindActivity.this, "ʧ�������б����ʧ��~",
						Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	
	 
    
}