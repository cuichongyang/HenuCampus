package com.henucampus.adapter;


import com.henucampus.main.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private int [] images=null;
	private Context context;
	ViewHolder holder=null;
	public ImageAdapter(Context context,int []images) {
		this.context = context;
		this.images=images;
	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null) {
			//�������ļ�ת��Ϊview����
			convertView=LayoutInflater.from(context).inflate(R.layout.item_image,null);
			holder=new ViewHolder();
			//�ҵ�view������ڲ��ؼ�
			holder.imageview=(ImageView) convertView.findViewById(R.id.image_item);
			//��holder��Ϊ�����Ӹ�convertView
			convertView.setTag(holder);
		}		
		//convertView�Ѿ���ȡ����ô���и���
		else{
			//ͨ��getTag��������ȡ����ȡholder
			holder=(ViewHolder) convertView.getTag();					
		}	
		//����Ӧ��Դ�ŵ��ؼ�����ʾ		
		holder.imageview.setImageResource(images[position]);
		return convertView;
	}
	class ViewHolder{
		ImageView imageview;
	} 
}
