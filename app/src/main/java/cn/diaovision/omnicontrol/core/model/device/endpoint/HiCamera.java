package cn.diaovision.omnicontrol.core.model.device.endpoint;

import java.util.ArrayList;
import java.util.List;

import cn.diaovision.omnicontrol.core.model.device.State;

/**
 * Hi-definition cameras connected to the matrix, for controlling
 * Created by liulingfeng on 2017/3/2.
 */

public class HiCamera {
    //摄像头的控制协议类型
    public static final int PROTO_FELICA_D = 0;
    public static final int PROTO_FELICA_A = 1;
    public static final int PROTO_PILSA = 2;

    String alias;
    int portIdx; //which port the camera is plugged
    int idx; //camera address
    int baudrate; //baudrate for controlling
    int proto; //camera protocols used to control the device

    int state = State.ON;

    List<Preset> presetList;

    public HiCamera(int portIdx, int idx, int baudrate, int proto) {
        this.portIdx = portIdx;
        this.idx = idx;
        this.baudrate = baudrate;
        this.proto = proto;
        this.presetList = new ArrayList<>();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getPortIdx() {
        return portIdx;
    }

    public void setPortIdx(int portIdx) {
        this.portIdx = portIdx;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(int baudrate) {
        this.baudrate = baudrate;
    }

    public int getProto() {
        return proto;
    }

    public void setProto(int proto) {
        this.proto = proto;
    }

    public List<Preset> getPresetList() {
        return presetList;
    }

    public void setPresetList(List<Preset> presetList) {
        this.presetList = presetList;
    }

    public void updatePreset(Preset preset){
        int idx = -1;
        for (int m = 0; m < presetList.size(); m ++){
            if (presetList.get(m).getIdx() == preset.getIdx()){
                idx = m;
            }
        }

        //new preset
        if (idx < 0){
            presetList.add(preset);
        }
        //old preset
        else {
            presetList.remove(idx);
            presetList.add(preset);
        }
    }

    public int getPreset(int idx){
        for (int m = 0; m < presetList.size(); m ++){
            if (presetList.get(m).getIdx() == idx){
                return m;
            }
        }
        return -1;
    }

    public void deletePreset(int pos){
        if (pos >= 0 && pos < presetList.size()) {
            presetList.remove(pos);
        }
    }

    static public class Preset{
        private int idx; //idx of the preset
        private String name;

        public Preset(String name, int idx) {
            this.name = name;
            this.idx = idx;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }
    }
}
