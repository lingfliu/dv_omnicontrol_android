package cn.diaovision.omnicontrol.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.diaovision.omnicontrol.R;
import cn.diaovision.omnicontrol.core.model.device.matrix.MediaMatrix;
import cn.diaovision.omnicontrol.core.model.device.matrix.io.Port;
import cn.diaovision.omnicontrol.widget.CircleCharView;
import cn.diaovision.omnicontrol.widget.adapter.PortItemAdapter;

/**
 * Created by liulingfeng on 2017/2/24.
 */

public class VideoFragment extends Fragment{

    @BindView(R.id.input)
    RecyclerView inputPorts;
    PortItemAdapter inputPortAdapter;


    @BindView(R.id.output)
    RecyclerView outputPorts;
    PortItemAdapter outputPortAdapter;

    /***********
     *Datum
     ************/
    MediaMatrix mediaMatrix = new MediaMatrix();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, v);

       List<Port> ports = new ArrayList<>();
        for (int m = 0; m < 32; m ++){
            Port port = new Port();
            port.alias = "测试"+String.valueOf(m);
            port.idx = m;
            port.dir = Port.DIR_IN;
            port.state = m%4;
            ports.add(port);
        }
        inputPortAdapter = new PortItemAdapter(ports, R.layout.item_port);
        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 6, GridLayoutManager.VERTICAL, false);
        inputPorts.setLayoutManager(layoutManager);
        inputPorts.setAdapter(inputPortAdapter);



        inputPortAdapter.setOnItemClickedListener(new PortItemAdapter.OnItemClickListener() {
            @Override
            public void onLongClick(View v, int position) { }

            @Override
            public void onUnselect(View v, int position) {
                Toast.makeText(getActivity().getApplicationContext(), "port unselected = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSelect(View v, int position) {
                Toast.makeText(getActivity().getApplicationContext(), "port selected = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

        });

//        outputPortAdapter = new PortItemAdapter(ports, R.layout.item_port);
//        final RecyclerView.LayoutManager outputPortlayoutMgr = new GridLayoutManager(getActivity().getApplicationContext(), 6, GridLayoutManager.VERTICAL, false);
//        outputPorts.setLayoutManager(outputPortlayoutMgr);
//        outputPorts.setAdapter(outputPortAdapter);
//
//        outputPortAdapter.setOnItemClickedListener(new PortItemAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(View v, int position) { }
//            @Override
//            public void onLongClick(View v, int position) { }
//
//            @Override
//            public void onUnselect(View v, int position) {
//                Toast.makeText(getActivity().getApplicationContext(), "port unselected = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onSelect(View v, int position) {
//                Toast.makeText(getActivity().getApplicationContext(), "port selected = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLongSelect(View v, int position) {
//
//            }
//        });

        return v;
    }
}