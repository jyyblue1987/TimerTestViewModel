package com.live.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LiveDataViewModel viewModel;
    EditText editInterval;
    Button btnStart;
    Boolean m_bStartFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editInterval = (EditText) findViewById(R.id.edit_interval);
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);

    }
    private void subscribe() {
        final Observer<Long> delayTimeObserver = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable final Long aLong) {
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
                Date resultdate = new Date(aLong);
                String newText = sdf.format(resultdate);
                ((TextView) findViewById(R.id.tm_result)).setText(newText);
            }
        };

        viewModel.getDelayTime().observe(this, delayTimeObserver);
    }

    @Override
    public void onClick(View view) {
        if(m_bStartFlag)
        {
            int ninterval = 0;
            try {
                ninterval = Integer.parseInt(String.valueOf(editInterval.getText()));
            }catch (Exception er){
                return;
            }

            m_bStartFlag = false;
            editInterval.setEnabled(false);
            btnStart.setText("Stop");

            viewModel = new LiveDataViewModel(ninterval);
            subscribe();
        }
        else
        {
            btnStart.setText("Start");
            m_bStartFlag = true;
            editInterval.setEnabled(true);

            viewModel.onCleared();
        }
    }
}