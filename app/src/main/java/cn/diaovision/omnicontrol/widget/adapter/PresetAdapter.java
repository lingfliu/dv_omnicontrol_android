package cn.diaovision.omnicontrol.widget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.diaovision.omnicontrol.R;
import cn.diaovision.omnicontrol.core.model.device.endpoint.HiCamera;
import cn.diaovision.omnicontrol.widget.ItemSelectionSupport;

/**
 * Created by TaoYimin on 2017/6/8.
 */

public class PresetAdapter extends RecyclerView.Adapter<PresetAdapter.PresetViewHolder> {
    private Context context;
    private List<HiCamera.Preset> data;
    private ItemSelectionSupport mSelectionSupport;

    public PresetAdapter(List<HiCamera.Preset> data, ItemSelectionSupport mSelectionSupport) {
        this.data = data;
        this.mSelectionSupport = mSelectionSupport;
    }

    public ItemSelectionSupport getSelectionSupport() {
        return mSelectionSupport;
    }

    @Override
    public PresetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new PresetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_preset, parent, false));
    }

    @Override
    public void onBindViewHolder(PresetViewHolder holder, int position) {
        if (data == null) {
            return;
        }
        holder.presetBg.setBackgroundResource(R.drawable.button_blue_selector);
        //设置预置位编号背景颜色
        holder.presetBadge.setTextColor(context.getResources().getColorStateList(R.color.port_badge_blue_selector));
        if (position < data.size()) {
            //设置预置位编号
            holder.presetBadge.setText(position + 1 + "");
            //设置预置位别名
            holder.presetAlias.setText(data.get(position).getName());
            //设置item的选中状态
            boolean checked = getSelectionSupport().isItemChecked(position);
            holder.checkBox.setChecked(checked);
            if (checked) {
                holder.presetBg.setSelected(true);
                holder.presetBadge.setSelected(true);
            } else {
                holder.presetBg.setSelected(false);
                holder.presetBadge.setSelected(false);
            }
        } else {
            holder.presetBadge.setText("+");
            holder.presetAlias.setText("添加预置位");
        }
    }

    @Override
    public int getItemCount() {
        if(data==null){
            return 0;
        }else {
            return data.size() + 1;
        }
    }

    public class PresetViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.preset_bg)
        RelativeLayout presetBg;
        @BindView(R.id.preset_checkbox)
        CheckBox checkBox;
        @BindView(R.id.preset_alias)
        TextView presetAlias;
        @BindView(R.id.preset_badge)
        TextView presetBadge;

        public PresetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
