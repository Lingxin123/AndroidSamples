package com.sdwfqin.sample.activitytransition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sdwfqin.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 跳转动画介绍
 * http://www.sdwfqin.com/2017/02/27/Activity%E8%B7%B3%E8%BD%AC%E5%8A%A8%E7%94%BB/
 * http://www.sdwfqin.com/2017/04/01/Animation%E5%8A%A8%E7%94%BB/
 */
public class T1Activity extends AppCompatActivity {

    @BindView(R.id.anim_list)
    ListView animList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t1);
        ButterKnife.bind(this);

        mContext = this;

        initView();
    }

    private void initView() {
        String[] strings = getResources().getStringArray(R.array.anim_type);
        animList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, R.id.tv_items, strings));

        animList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, T2Activity.class);
                startActivity(intent);
                switch (position) {
                    case 0:
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                        break;
                    case 1:
                        overridePendingTransition(R.anim.my_scale_action,
                                R.anim.my_alpha_action);
                        break;
                    case 2:
                        overridePendingTransition(R.anim.scale_rotate,
                                R.anim.my_alpha_action);
                        break;
                    case 3:
                        overridePendingTransition(R.anim.scale_translate_rotate,
                                R.anim.my_alpha_action);
                        break;
                    case 4:
                        overridePendingTransition(R.anim.scale_translate,
                                R.anim.my_alpha_action);
                        break;
                    case 5:
                        overridePendingTransition(R.anim.hyperspace_in,
                                R.anim.hyperspace_out);
                        break;
                    case 6:
                        overridePendingTransition(R.anim.push_left_in,
                                R.anim.push_left_out);
                        break;
                    case 7:
                        overridePendingTransition(R.anim.push_up_in,
                                R.anim.push_up_out);
                        break;
                    case 8:
                        overridePendingTransition(R.anim.slide_left,
                                R.anim.slide_right);
                        break;
                    case 9:
                        overridePendingTransition(R.anim.wave_scale,
                                R.anim.my_alpha_action);
                        break;
                    case 10:
                        overridePendingTransition(R.anim.zoom_enter,
                                R.anim.zoom_exit);
                        break;
                    case 11:
                        overridePendingTransition(R.anim.slide_up_in,
                                R.anim.slide_down_out);
                        break;
                }
            }
        });
    }
}