package com.kproduce.imitativedouban.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kproduce.imitativedouban.Constants;
import com.kproduce.imitativedouban.R;
import com.kproduce.imitativedouban.bean.Movie;
import com.kproduce.imitativedouban.utils.DensityUtil;
import com.kproduce.imitativedouban.utils.ScreenUtil;
import com.kproduce.imitativedouban.utils.StatusBarUtil;
import com.kproduce.roundcorners.RoundImageView;

import java.math.BigDecimal;

import butterknife.BindView;

/**
 * @author kuanggang
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarBack(this, getResources().getColor(R.color.colorPrimary), false);

        init();
    }

    private void init() {
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(new MovieAdapter(this));
    }

    class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

        private Context mContext;

        MovieAdapter(Context context) {
            this.mContext = context;
        }

        @NonNull
        @Override
        public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, viewGroup, false);
            return new MovieHolder(view, mContext);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieHolder movieHolder, int position) {
            Movie movie = Constants.DATAS.get(position);
            movieHolder.ivCover.setImageResource(movie.getCoverId());
            movieHolder.tvName.setText(movie.getTitle());
            movieHolder.tvDesc.setText(movie.getSecTitle());
            movieHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("data", movie);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return Constants.DATAS.size();
        }
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        RoundImageView ivCover;
        TextView tvName;
        TextView tvDesc;

        MovieHolder(@NonNull View itemView, Context context) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDesc = itemView.findViewById(R.id.tv_desc);

            ViewGroup.LayoutParams layoutParams = ivCover.getLayoutParams();
            int width = (ScreenUtil.getScreenWidth(context) - DensityUtil.dip2px(context, 50)) / 3;
            int height = (BigDecimal.valueOf(width).divide(BigDecimal.valueOf(7), 5, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(10))).intValue();
            layoutParams.width = width;
            layoutParams.height = height;
        }
    }
}
