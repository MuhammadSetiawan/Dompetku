package com.wawan.dompetku.ui.adapter.menuAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.wawan.dompetku.R;
import com.wawan.dompetku.model.menu.MenuModel;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Holder> {

    public static final int MAIN_MENU_LIST = 0;
    public static final int REPORT_MENU_LIST = 1;

    private Context context;
    private OnMainMenuAdapterItemClickListener listener;
    private ArrayList<MenuModel> list;

    public MenuAdapter(Context context,int menuType, OnMainMenuAdapterItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        switch (menuType){
            case MAIN_MENU_LIST:
                this.list = MenuModel.getMainMenuList(context);
                break;
            case REPORT_MENU_LIST:
                this.list = MenuModel.getReportMenuList(context);
                break;
                default:
                    break;
        }

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(((Activity)context).getLayoutInflater().inflate(R.layout.menu_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final MenuModel item = list.get(position);
        holder.text.setText(item.Text);
        holder.icon.setImageDrawable(ContextCompat.getDrawable(context,item.Icon));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView text;
        public LinearLayout layout;

        public Holder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon_imageview);
            text = itemView.findViewById(R.id.text_textview);
            layout = itemView.findViewById(R.id.menu_layout);
        }
    }

    public interface OnMainMenuAdapterItemClickListener {
        void onItemClick(@NonNull MenuModel m, int pos);
    }
}