package com.example.asus.fragment_viewpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager pager;
    private Button go_first;
    private Button go_last;
    static final int NUM_TIME=10;
    private Myadapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager=(ViewPager)findViewById(R.id.page);
        go_first=(Button) findViewById(R.id.goto_first);
        go_last=(Button) findViewById(R.id.goto_last);
        myadapter=new Myadapter(getSupportFragmentManager());
        pager.setAdapter(myadapter);
        go_first.setOnClickListener(this);
        go_last.setOnClickListener(this);
    }
    public static class  Myadapter extends FragmentStatePagerAdapter{

        public Myadapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUM_TIME;
        }
    }
    public static class ArrayListFragment extends ListFragment{
        int pageNum;
         public static ArrayListFragment newInstance(int num) {

            Bundle args = new Bundle();
            args.putInt("num",num+1);
            ArrayListFragment fragment = new ArrayListFragment();
            fragment.setArguments(args);
            return fragment;
        }
        public List<String> getData(){
            List<String> list=new ArrayList<>();
            for(int i=0;i<20;i++){
                list.add("data"+i);
            }
            return list;
        }
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            pageNum=getArguments()!=null?getArguments().getInt("num"):1;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.fragment_pager_list,null);
            TextView tv = (TextView)view.findViewById(R.id.text);
            tv.setText("Fragment #" + pageNum);
            return view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getData()));
        }

        @Override
        public void onPause() {
            super.onPause();
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Toast.makeText(getActivity(),pageNum+":"+l.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.goto_first:
                pager.setCurrentItem(0);
                break;
            case R.id.goto_last:
                pager.setCurrentItem(NUM_TIME-1);
                break;

        }
    }
}
