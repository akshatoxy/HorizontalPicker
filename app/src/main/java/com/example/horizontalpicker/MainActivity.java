package com.example.horizontalpicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        textView = findViewById(R.id.textView);

        new LinearSnapHelper().attachToRecyclerView(recyclerView);

        PickerLinearLayoutManager plm = new PickerLinearLayoutManager(this);
        plm.callback = pos -> {
            Log.d("tzuyu", String.valueOf(pos));
            textView.setText(getPriorityText(pos));
        };

        recyclerView.setLayoutManager(plm);

        PickerAdapter pa = new PickerAdapter();
        pa.callback = v -> {
            int pos = recyclerView.getChildLayoutPosition(v);
            recyclerView.smoothScrollToPosition(pos);
        };

        recyclerView.setAdapter(pa);

        int padding = ScreenUtils.getScreenWidth(this)/2 -
                ScreenUtils.dpToPx(this, 63);

        recyclerView.setPadding(padding, 0, padding, 0);

    }

    private String getPriorityText(int pos){
        if(pos == 0) {
            return "Very High";
        }else if(pos == 1){
            return "High";
        }else if(pos == 2){
            return "Medium";
        }else if(pos == 3){
            return "Low";
        }else {
            return "Very Low";
        }
    }
}