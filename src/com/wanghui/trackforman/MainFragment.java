package com.wanghui.trackforman;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFragment extends Fragment implements OnItemClickListener{
	
	private GridView mGridView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		initView(view);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	private void initView(View view) {
		mGridView = (GridView) view.findViewById(R.id.main_grid_view);
		mGridView.setOnItemClickListener(this);
		MainFragmentAdapter adapter = new MainFragmentAdapter(getActivity(), getItem());
		mGridView.setAdapter(adapter);
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}

	private ArrayList<Item> getItem(){
		ArrayList<Item> items = new ArrayList<MainFragment.Item>();
		Item item = new Item();
		item.mIconRes = R.drawable.btn_main_item_one;
		item.mTitleRes = getString(R.string.txt_main_one);
		items.add(item);
		item = new Item();
		item.mIconRes = R.drawable.btn_main_item_two;
		item.mTitleRes = getString(R.string.txt_main_two);
		items.add(item);
		item = new Item();
		item.mIconRes = R.drawable.btn_main_item_three;
		item.mTitleRes = getString(R.string.txt_main_three);
		items.add(item);
		item = new Item();
		item.mIconRes = R.drawable.btn_main_item_four;
		item.mTitleRes = getString(R.string.txt_main_four);
		items.add(item);
		item = new Item();
		item.mIconRes = R.drawable.btn_main_item_five;
		item.mTitleRes = getString(R.string.txt_main_five);
		items.add(item);
		item = new Item();
		item.mIconRes = R.drawable.btn_main_item_six;
		item.mTitleRes = getString(R.string.txt_main_six);
		items.add(item);
		return items;
	}

	private class MainFragmentAdapter extends ArrayAdapter<Item>{

		public MainFragmentAdapter(Context context,ArrayList<Item> list){
			super(context, 0, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if(null == convertView){
				holder = new Holder();
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_menu, parent, false);
				holder.mItemCover = (ImageView) convertView.findViewById(R.id.main_item_btn);
				holder.mItemName = (TextView) convertView.findViewById(R.id.main_item_tv);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			Item item = getItem(position);
			holder.mItemCover.setBackgroundResource(item.mIconRes);
			holder.mItemName.setText(item.mTitleRes);
			
			return convertView;
		}
		
		private class Holder{
			public ImageView mItemCover;
			public TextView mItemName;
		}
		
	}
	
	private class Item{
		int mIconRes;
		String mTitleRes;
	}

	
}
