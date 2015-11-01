package com.henucampus.fragment;

import com.henucampus.main.ClubActivity;
import com.henucampus.main.ForumActivity;
import com.henucampus.main.LectureActivity;
import com.henucampus.main.LostFindActivity;
import com.henucampus.main.NoticeActivity;
import com.henucampus.main.R;
import com.henucampus.main.SecondaryActivity;
import com.henucampus.main.SportActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Page2Fragment extends Fragment {
	private Button lostfindbn,lecturebn,sportbn,clubbn,noticebn,forumbn,secondarybn;
	private View itview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		itview=inflater.inflate(R.layout.fragment_page2, container,false);
		return itview;
		
	}
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	//ʧ��Ѱ�ﰴť�¼�ע��
    	OnClickListener clickListener = new OnClickListener(){
        	@Override
        	public void onClick(View v) {
        		switch(v.getId()){
        		case R.id.lostfindbn:
        			Intent it_lostfind=new Intent(getActivity(),LostFindActivity.class);
        			getActivity().startActivity(it_lostfind);
        			break;
        		case R.id.sportbn:
        			Intent it_sport=new Intent(getActivity(),SportActivity.class);
        			getActivity().startActivity(it_sport);
        			break;
        		case R.id.lecturebn:
        			Intent it_lecture=new Intent(getActivity(),LectureActivity.class);
        			getActivity().startActivity(it_lecture);
        			break;
        		case R.id.clubbn:
        			Intent it_club=new Intent(getActivity(),ClubActivity.class);
        			getActivity().startActivity(it_club);
        			break;
        		case R.id.noticebn:
        			Intent it_notice=new Intent(getActivity(),NoticeActivity.class);
        			getActivity().startActivity(it_notice);
        			break;
        		case R.id.forumbn:
        			Intent it_forum=new Intent(getActivity(),ForumActivity.class);
        			getActivity().startActivity(it_forum);
        			break;
        		case R.id.secondarybn:
        			Intent it_secondary=new Intent(getActivity(),SecondaryActivity.class);
        			getActivity().startActivity(it_secondary);
        			break;
        		}
        	}

    	};
		lostfindbn = (Button)itview.findViewById(R.id.lostfindbn);
		lecturebn= (Button)itview.findViewById(R.id.lecturebn);
		sportbn= (Button)itview.findViewById(R.id.sportbn);
		clubbn=(Button)itview.findViewById(R.id.clubbn);
		noticebn=(Button)itview.findViewById(R.id.noticebn);
		forumbn=(Button)itview.findViewById(R.id.forumbn);
		secondarybn=(Button)itview.findViewById(R.id.secondarybn);;
		lostfindbn.setOnClickListener(clickListener);
		lecturebn.setOnClickListener(clickListener);
		sportbn.setOnClickListener(clickListener);
		clubbn.setOnClickListener(clickListener);
		noticebn.setOnClickListener(clickListener);
		forumbn.setOnClickListener(clickListener);
		secondarybn.setOnClickListener(clickListener);

    }
    
}
