package com.hospital.yilian.mobiledoctors.UI.other;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc_smart.www.mobiledoctor.R;
import com.hc_smart.www.mobiledoctor.UI.home.UIActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    TextView tvname;
    ArrayList<Map<String,Object>> namelist = new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inittestdata();

        GridView gvlogin = (GridView)findViewById(R.id.gridview_login);

         tvname = (TextView)findViewById(R.id.editText_name) ;
        ListViewAdapter sa = new ListViewAdapter(this);
        gvlogin.setNumColumns(sa.getCount());
        gvlogin.setAdapter(sa);
        gvlogin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position<namelist.size()) {
                    Map<String, Object> node = namelist.get(position);
                    tvname.setText((String) node.get("name"));
                }
            }
        });

        Button btlogin = (Button)findViewById(R.id.button_login);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, UIActivity.class);
                startActivity(intent);
            }
        });
    }

    void inittestdata()
    {
        Map<String,Object> node = new HashMap<>();
        node.put("image",R.drawable.male);
        node.put("name","doctor zhang");
        namelist.add(node);

        node = new HashMap<>();
        node.put("image",R.drawable.female);
        node.put("name","wang zhang");
        namelist.add(node);

    }


    private class ListViewAdapter extends BaseAdapter {
        private Context context;                        //运行上下文
        // ArrayList<MeasureDataDef>  listItems;
        private LayoutInflater listContainer;
        public final class ListItemView{                //自定义控件集合

            public TextView tvname;
            public ImageView image;


        }
        public ListViewAdapter(Context context) {
            this.context = context;

            // this.listItems = listItems;
            listContainer = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            int count=  namelist.size();
            if(count<5)
            {
                count = 5;
            }
            return count;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemView listItemView = null;
            if (convertView == null) {
                listItemView = new ListItemView();
                //获取list_item布局文件的视图
                convertView = listContainer.inflate(R.layout.login_cell, null);
                Rect frame = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Point screensize = new Point();
                wm.getDefaultDisplay().getSize(screensize);
                int screenWidth = screensize.x ;
                int screenHeight = screensize.y;


                int viewWidth = screenHeight/2*3/5;
                //int viewHeight = (screenHeight-statusBarHeight - layout_header.getHeight())/9;
                int viewHeight =viewWidth ;
//L.e("screenWidth:" + screenWidth);

                AbsListView.LayoutParams params = new AbsListView.LayoutParams(viewWidth,viewHeight);
                convertView.setLayoutParams(params);


                //获取控件对象
                listItemView.tvname = (TextView)convertView.findViewById(R.id.login_name);
                listItemView.image = (ImageView)convertView.findViewById(R.id.login_image);
                /*listItemView.image.setMaxHeight(200);
                listItemView.image.setMinimumHeight(200);
                listItemView.image.setMaxWidth(200);
                listItemView.image.setMinimumWidth(200);
*/
                //设置控件集到convertView
                convertView.setTag(listItemView);
            }else {
                listItemView = (ListItemView)convertView.getTag();
            }
            if(position<namelist.size())
            {
                Map<String,Object> node = namelist.get(position);
                listItemView.tvname.setText((String)node.get("name"));



                    listItemView.image.setImageResource((Integer) node.get("image"));


            }


            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
    }
}
