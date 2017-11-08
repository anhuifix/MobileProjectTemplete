package com.hospital.yilian.mobiledoctors.UI.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hc_smart.www.mobiledoctor.PatientDetailBoardActivity;
import com.hc_smart.www.mobiledoctor.R;
import com.hc_smart.www.mobiledoctor.TableAdapterPatientCardInfo;
import com.hc_smart.www.mobiledoctor.base.BaseActivity;
import com.hc_smart.www.mobiledoctor.common.PatientInfoDef;
import com.hc_smart.www.mobiledoctor.common.UlityTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UIActivity extends BaseActivity {

    private  int focusid = 0;
    private ArrayList<PatientInfoDef> patientlist = null;
    List<Map<String,String>> bedNolist = new ArrayList<>();
    List<Map<String,String>> PatientArealist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inittestdata();

        TableAdapterPatientCardInfo tapc = new TableAdapterPatientCardInfo(this,patientlist);
        GridView gv = (GridView)findViewById(R.id.gridview_patient_card);



        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                focusid = position;

                switch (position) {
                    case 0:
                        break;


                }
                Intent intent=new Intent();
                if(position < patientlist.size())
                {
                    Bundle data= new Bundle();
                    data.putSerializable("patient",patientlist.get(position));
                    intent.putExtras(data);
                }
                intent.setClass(UIActivity.this, PatientDetailBoardActivity.class);
                startActivity(intent);

            }
        });
        gv.setAdapter(tapc);


        ImageView iv = (ImageView) findViewById(R.id.imageView_photo);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tvname = (TextView) findViewById(R.id.textView_doctorname);
        tvname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initView() {

    }

    public  void doctor_clicked(View v)
    {

    }

    private Handler dlghandler =new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bundle data = msg.getData();
                    int position = data.getInt("position");
                    String txt = PatientArealist.get(position).get("text");
                    Toast.makeText(getApplicationContext(),String.format("%s is selected!",txt),Toast.LENGTH_LONG).show();

                    Button btpatient = (Button)findViewById(R.id.button5);
                    btpatient.setText(txt);
                    break;
                case 2:
                     data = msg.getData();
                     position = data.getInt("position");
                     txt = bedNolist.get(position).get("text");
                    //Toast.makeText(getApplicationContext(),String.format("%s is selected!",txt),Toast.LENGTH_LONG).show();
                    Button btbedno = (Button)findViewById(R.id.button4);
                    btbedno.setText(txt);
                    break;
            }

        }


    };

    public  void patient_area_clicked(View v)
    {

        int idtag = 0;
        try{
            idtag = Integer.valueOf( v.getTag().toString());
        }catch (Exception e)
        {
            return;
        }

        Point origin = new Point();
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        origin.set((int)(location[0]),(int)(location[1]));
        show_selectingdlg(PatientArealist,origin,idtag);
    }
    public  void bedNo_clicked(View v)
    {

        int idtag = 0;
        try{
            idtag = Integer.valueOf( v.getTag().toString());
        }catch (Exception e)
        {
            return;
        }

        Point origin = new Point();
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        origin.set((int)(location[0]),(int)(location[1]));
        show_selectingdlg(bedNolist,origin,idtag);
    }
    public  void department_clicked(View v)
    {
        int idtag = 0;
        try{
            idtag = Integer.valueOf( v.getTag().toString());
        }catch (Exception e)
        {
            return;
        }
        Button bt_person = (Button)findViewById(R.id.button);
        Button bt_team = (Button)findViewById(R.id.button2);
        Button bt_depart = (Button)findViewById(R.id.button3);
        switch (idtag)
        {
            case 1:
                bt_person.setBackground( getResources().getDrawable(R.color.button_focused,this.getTheme()));
                bt_team.setBackground( getResources().getDrawable(R.color.button_unfocused,this.getTheme()));
                bt_depart.setBackground( getResources().getDrawable(R.color.button_unfocused,this.getTheme()));
                break;
            case 2:
                bt_person.setBackground( getResources().getDrawable(R.color.button_unfocused,this.getTheme()));
                bt_team.setBackground( getResources().getDrawable(R.color.button_focused,this.getTheme()));
                bt_depart.setBackground( getResources().getDrawable(R.color.button_unfocused,this.getTheme()));

                break;
            case 3:
                bt_person.setBackground( getResources().getDrawable(R.color.button_unfocused,this.getTheme()));
                bt_team.setBackground( getResources().getDrawable(R.color.button_unfocused,this.getTheme()));
                bt_depart.setBackground( getResources().getDrawable(R.color.button_focused,this.getTheme()));
                break;
        }
    }

    public  void canceldlg(View v)
    {
        if(wm != null)
        {
            wm.removeView(selectingdlg);
            return;
        }
    }

    WindowManager wm = null;

    View selectingdlg;
    private void show_selectingdlg(List<Map<String,String>> datalist,Point origin,int tag) {
        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Point screensize = new Point();
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;


        wm.getDefaultDisplay().getSize(screensize);
        WindowManager.LayoutParams mWmlp = UlityTools.getWinLayParams(0, (int) (screensize.x * 0.5), (int) (screensize.y * 0.6));
        mWmlp.x = origin.x -(int)(screensize.x * 0.5/2);
       mWmlp.y = origin.y - (int) (screensize.y * 0.4/2) + statusBarHeight;
        LayoutInflater listContainer = LayoutInflater.from(this);

        selectingdlg = listContainer.inflate(R.layout.select_dialog, null, false);

        ListView lvselector = (ListView)selectingdlg.findViewById(R.id.listview_sel_dlg);

        SimpleAdapter sa = new SimpleAdapter(this,datalist,R.layout.selectdlg_item, new String[]{"text"}, new int[]{R.id.textView_select_item});
        lvselector.setAdapter(sa);
        lvselector.setId(tag);
        lvselector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                              @Override
                                              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                  wm.removeView(selectingdlg);
                                                  wm = null;
                                                  Message msg = new Message();
                                                  msg.what = parent.getId();
                                                  Bundle data = new Bundle();
                                                  data.putInt("position",position);
                                                  msg.setData(data);

                                                  dlghandler.sendMessage(msg);

                                              }
                                          }
        );


    try {
        wm.addView(selectingdlg, mWmlp);
    }catch (Exception e)
    {
        System.out.print(e.toString());
    }

    }





    void inittestdata()
    {
        patientlist = new ArrayList<>();

        PatientInfoDef rec0 = new PatientInfoDef();
        rec0.name = "患者0";
        rec0.sex = 0;
        rec0.age =  10;
        rec0.bedNo = 10001;
        rec0.doctorname  = "张医生";
        rec0.hospitalNo = 2001;
        rec0.indate = "2017-05-03";
        rec0.opdate = "";
        rec0.type = 2;
        rec0.zhenduan  = "无结果";




        PatientInfoDef rec01 = new PatientInfoDef();
        rec01.name = "患者0";
        rec01.sex = 1;
        rec01.age =  10;
        rec01.bedNo = 10001;
        rec01.doctorname  = "张医生";
        rec01.hospitalNo = 2001;
        rec01.indate = "2017-05-03";
        rec01.opdate = "";
        rec01.type = 0;
        rec01.zhenduan  = "无结果";


        PatientInfoDef rec1 = new PatientInfoDef();
        rec1.name = "患者1";
        rec1.sex = 1;
        rec1.age =  40;
        rec1.bedNo = 10002;
        rec1.doctorname  = "wang医生";
        rec1.hospitalNo = 2002;
        rec1.indate = "2017-06-03";
        rec1.opdate = "";
        rec1.type = 1;
        rec1.zhenduan  = "sss结果";


        PatientInfoDef rec11 = new PatientInfoDef();
        rec11.name = "患者1";
        rec11.sex = 0;
        rec11.age =  40;
        rec11.bedNo = 10002;
        rec11.doctorname  = "wang医生";
        rec11.hospitalNo = 2002;
        rec11.indate = "2017-06-03";
        rec11.opdate = "";
        rec11.type = 1;
        rec11.zhenduan  = "sss结果";


        patientlist.add(rec0);
        patientlist.add(rec01);
        patientlist.add(rec1);
        patientlist.add(rec11);
        patientlist.add(rec0);
        patientlist.add(rec1);


        bedNolist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map unit = new HashMap();

            unit.put("text", String.format(" 床 %d",i));
            bedNolist.add(unit);
        }
         PatientArealist = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map unit = new HashMap();

            unit.put("text", String.format("病区 %d",i));
            PatientArealist.add(unit);
        }
    }
}
