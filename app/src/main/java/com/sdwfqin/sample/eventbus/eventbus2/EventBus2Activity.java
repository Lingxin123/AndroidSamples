package com.sdwfqin.sample.eventbus.eventbus2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sdwfqin.sample.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 通过 postSticky 发送粘性事件，这个事件不会只被消费一次就消失，而是一直存在系统中，
 * 直到被removeStickyEvent 删除掉。那么只要订阅了该粘性事件的所有方法，
 * 只要被register 的时候，就会被检测到，并且执行。
 * 订阅的方法需要添加 sticky = true 属性
 */
public class EventBus2Activity extends AppCompatActivity {

    @BindView(R.id.btn_event2_send)
    Button btnEvent2Send;
    @BindView(R.id.btn_event2_remove)
    Button btnEvent2Remove;
    @BindView(R.id.tv_event2_msg)
    TextView tvEvent2Msg;

    private static final String TAG = "EventBus2Activity";
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus2);
        ButterKnife.bind(this);

        btnEvent2Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string = "EventBus2Activity发出的粘性事件，不删除退出这个activity再进来或者到EventBus1Activity仍然可以看到我";
                EventBus.getDefault().postSticky(string);
            }
        });
        btnEvent2Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 移除全部粘性事件
                EventBus.getDefault().removeAllStickyEvents();
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN, priority = 100)
    public <T> void onMessageEvent(T t) {
        try {
            tvEvent2Msg.setText(t.toString());
        } catch (Exception e) {
            Log.e(TAG, "onMessageEvent: ", e);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // 注册
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        // 反注册
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

}
