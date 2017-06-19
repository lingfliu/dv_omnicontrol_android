package cn.diaovision.omnicontrol.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import cn.diaovision.omnicontrol.core.model.device.endpoint.HiCamera;
import cn.diaovision.omnicontrol.core.model.device.matrix.io.Channel;

/**
 * Created by liulingfeng on 2017/4/24.
 */

public interface Config {
    String getMainName();
    String getMainPasswd();

    String getConfName();
    String getConfPasswd();

    String getMcuIp();
    int getMcuPort();
    String getMcuId();
    String getMcuKey();


    //Matrix attributes
    int getMatrixId();
    String getMatrixIp();
    int getMatrixUdpIpPort();
    String getMatrixPreviewIp();
    int getMatrixPreviewIpPort();

    //get the port where the preview channel is plugged on to the matrix
    int getMatrixPreviewPort();

    int getMatrixInputVideoNum();
    int getMatrixOutputVideoNum();

    byte getSubtitleFontSize();
    byte getSubtitleFontColor();

    Map<Integer,HiCamera> getMatrixCameras();
    Set<Channel> getMatrixChannels();

    Date getConfStartDate();
    Date getConfEndDate();
}
