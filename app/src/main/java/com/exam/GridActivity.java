package com.exam;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exam.constant.BundleKey;
import com.exam.databinding.ActivityGridBinding;

import static com.exam.MainActivity.RESULT_RESTART;

public class GridActivity extends AppCompatActivity {

    ActivityGridBinding binding;
    private int totalCol, totalRow, pickedPosition = -1;
    private TextView tvPicked, tvOkPicked;
    private View vHighLightPicked;
    private static final int MARGIN_CELL = 5;
    private Handler handler = new Handler();

    String TAG = "***debug***";

    private View.OnClickListener clickOk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cleanPanel();
        }
    };

    private Runnable pickRandomCell = new Runnable() {
        @Override
        public void run() {
            cleanPanel();

            int randomCol = Utility.getRandomNum(1, totalCol);
            int randomRow = Utility.getRandomNum(1, totalRow);
            pickedPosition = (randomRow - 1) * totalCol + randomCol - 1;
            Log.d(TAG, "run: pickedPosition=" + pickedPosition + ", randomCol=" + randomCol + ", randomRow=" + randomRow);

            LinearLayout llPicked = (LinearLayout) binding.glPanel.getChildAt(pickedPosition);
            tvPicked = (TextView) llPicked.getChildAt(0);
            tvPicked.setVisibility(View.VISIBLE);

            int pickedOkPosition = (totalRow) * totalCol + randomCol - 1;
            LinearLayout llOkPicked = (LinearLayout) binding.glPanel.getChildAt(pickedOkPosition);
            Log.d(TAG, "run: pickedOkPosition=" + pickedOkPosition);
            tvOkPicked = (TextView) llOkPicked.getChildAt(0);
            tvOkPicked.setOnClickListener(clickOk);

            vHighLightPicked = binding.glHighlight.getChildAt(pickedPosition % totalCol);
            vHighLightPicked.setVisibility(View.VISIBLE);

            handler.postDelayed(pickRandomCell, 5000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_grid);
        totalCol = getIntent().getExtras().getInt(BundleKey.COLUMN);
        totalRow = getIntent().getExtras().getInt(BundleKey.ROW);

        for (int row = 1; row <= totalRow + 1; row++) {
            int color = Utility.getRandomColor();

            for (int col = 1; col <= totalCol; col++) {
                GridLayout.Spec colSpec = GridLayout.spec(col, GridLayout.FILL, 1f);
                GridLayout.Spec rowSpec = GridLayout.spec(row, GridLayout.FILL, 1f);
                GridLayout.LayoutParams paramsGL = new GridLayout.LayoutParams(rowSpec, colSpec);

                if (row == 1) {
                    paramsGL.topMargin = MARGIN_CELL;
                }
                if (col == 1) {
                    paramsGL.leftMargin = MARGIN_CELL;
                }
                paramsGL.bottomMargin = MARGIN_CELL;
                paramsGL.rightMargin = MARGIN_CELL;

                LinearLayout llCell = new LinearLayout(this);
                llCell.setGravity(Gravity.CENTER);
                llCell.setLayoutParams(paramsGL);

                TextView tvCell = new TextView(this);
                LinearLayout.LayoutParams paramsLL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                tvCell.setLayoutParams(paramsLL);
                tvCell.setGravity(Gravity.CENTER);

                if (row <= totalRow) {
                    tvCell.setText("random");
                    tvCell.setTextColor(Color.BLACK);
                    tvCell.setVisibility(View.INVISIBLE);

                    llCell.setBackgroundColor(color);
                } else {
                    tvCell.setText("確定");
                    tvCell.setTextColor(Color.WHITE);

                    llCell.setBackgroundColor(Color.DKGRAY);
                }

                llCell.addView(tvCell);
                binding.glPanel.addView(llCell);

                // create high light
                if (row == 1) {
                    GridLayout.LayoutParams paramsGL4HighLight = new GridLayout.LayoutParams(rowSpec, colSpec);
                    TextView vHighLightCell = new TextView(this);
                    vHighLightCell.setBackgroundResource(R.drawable.bg_highlight);
                    vHighLightCell.setVisibility(View.INVISIBLE);
                    vHighLightCell.setGravity(Gravity.CENTER);
                    vHighLightCell.setLayoutParams(paramsGL4HighLight);
                    binding.glHighlight.addView(vHighLightCell);
                }
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(pickRandomCell, 5000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(pickRandomCell);
    }

    private void cleanPanel() {
        if (pickedPosition > -1) {
            tvPicked.setVisibility(View.INVISIBLE);
            tvOkPicked.setOnClickListener(null);
            vHighLightPicked.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_RESTART);
        super.onBackPressed();
    }
}
