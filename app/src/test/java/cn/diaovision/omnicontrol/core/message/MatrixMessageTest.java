package cn.diaovision.omnicontrol.core.message;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by liulingfeng on 2017/3/27.
 */
public class MatrixMessageTest {
//    int id = 1;
//    int portIn = 1;
//    int portOut = 6;
//    int camIdx = 1;

    @Test
    public void toBytes() throws Exception {
    }

    @Test
    public void createSwitchMessage() throws Exception {
        byte[] msg = {0x01, '0', '1', 'W', 'R', 'M', '0', '0', '6', '0', '0', '1', '4', 'E', 0x04};
        byte[] a = MatrixMessage.buildSwitchMessage(1, 1, 6).toBytes();
        for (int m = 0; m < msg.length; m ++) {
            assertEquals(a[m], msg[m]);
        }
    }

    @Test
    public void buildMultiSwitchMessage() throws Exception {
        int portIn = 6;
        int[] portOut = {10, 11, 12, 13};
        byte[] msg = {0x01, '1', '0', 'W', 'M', 'M', '0', '0', '6', '0', '0', 'A', '0', '0', 'B', '0', '0', 'C', '0', '0', 'D', '6', '4', 0x04};
        byte[] a = MatrixMessage.buildMultiSwitchMessage(16, portIn, portOut).toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildPortInquiryMessage() throws Exception {

    }

    @Test
    public void buildLockPortMessage() throws Exception {

    }

    @Test
    public void buildUnlockPortMessage() throws Exception {

    }

    @Test
    public void buildSetIdMessage() throws Exception {
        byte[] msg = {0x01, 'F', 'S', 'I', 'D', '0', 'A', '6', '9', 0x04};
        byte[] a = MatrixMessage.buildSetIdMessage(16, 10).toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildGetIdMessage() throws Exception {
        byte[] msg = {0x01, 'F', 'S', 'I', 'D', 'F', 'S', '0', 'D', 0x04};
        byte[] a = MatrixMessage.buildGetIdMessage().toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildStartCameraGoMessage() throws Exception {
        byte[] msg = {0x01, '0', '1', 'C', 'D', '2', '1', '0', '0', '0', '2', '0', '6', '3', '8', '0', '3', 'A', 0x04};
        byte[] a = MatrixMessage.buildStartCameraGoMessage(1, 2400, MatrixMessage.CAM_PROTO_PELCO_D, 0, MatrixMessage.CAM_LEFT, 99).toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildStopCameraGoMessage() throws Exception {
        byte[] msg = {0x01, '1', '0', 'C', 'S', '2', '1', '0', '0', '0', '2', '2', 0x04};
        byte[] a = MatrixMessage.buildStopCameraGoMessage(16, 2400, MatrixMessage.CAM_PROTO_PELCO_D, 0).toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildGetCameraInfoMessage() throws Exception {
        byte[] msg = {0x01, '1', '0', 'C', 'A', '0', '0', '0', 'F', 'S', '2', '6', 0x04};
        byte[] a = MatrixMessage.buildGetCameraInfoMessage(16, 0).toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildSetCameraInfoMessage() throws Exception {
        byte[] msg = {0x01, '1', '0', 'C', 'A', '0', '0', '0', '0', '2', '3', '1', 0x04};
        byte[] a = MatrixMessage.buildSetCameraInfoMessage(16, 0, 2).toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildSetCameraPresetMessgae() throws Exception {
        byte[] msg = {0x01, '1', '0', 'C', 'P', '2', '1', '0', '0', '0', '0', '0', '1', '1', '0', 0x04};
        byte[] a = MatrixMessage.buildSetCameraPresetMessgae(16, 2400, MatrixMessage.CAM_PROTO_PELCO_D, 0, 1).toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildLoadCameraPresetMessgae() throws Exception {
        byte[] msg = {0x01, '1', '0', 'C', 'P', '2', '1', '0', '0', '0', '2', '0', '1', '1', '2', 0x04};
        byte[] a = MatrixMessage.buildLoadCameraPresetMessgae(16, 2400, MatrixMessage.CAM_PROTO_PELCO_D, 0, 1).toBytes();
        assertArrayEquals(a, msg);

    }

    @Test
    public void buildClearCameraPresetMessgae() throws Exception {
        byte[] msg = {0x01, '1', '0', 'C', 'P', '2', '1', '0', '0', '0', '1', '0', '1', '1', '1', 0x04};
        byte[] a = MatrixMessage.buildClearCameraPresetMessgae(16, 2400, MatrixMessage.CAM_PROTO_PELCO_D, 0, 1).toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildSetResoluMessage() throws Exception {
        byte[] msg = {0x01, '1', '0', 'M', 'S', 'O', '0', '0', '2', '3', '0', 'A', '2', '0', 0x04};
        byte[] a = MatrixMessage.buildSetResoluMessage(16, 2, "1080p").toBytes();
        assertArrayEquals(a, msg);
    }

    @Test
    public void buildGetResoluMessage() throws Exception {

    }

    @Test
    public void buildStitchMessage() throws Exception {
        int[] ports = {2,1,3,4};
        byte[] msg = {0x01, '0', '1', 'S', 'P', 'P', 'W', '0', '2', '0', '2', '0', '0', '2', '0', '0', '1', '0', '0', '3', '0', '0', '4', '0', '1', 0x04};
        byte[] a = MatrixMessage.buildStitchMessage(1, 2, 2, ports).toBytes();
        for (int m = 0; m < msg.length; m ++) {
            assertEquals(a[m], msg[m]);
        }
    }

    @Test
    public void buildCropMessage() throws Exception {

    }

    @Test
    public void buildSetSubtitleMessage() throws Exception {
        byte[] msg = {0x01, '0', '1', 'O', 'S', '0', '0', '2', '0', '0', '4', (byte) 0xB5, (byte) 0xF1, (byte) 0xCA, (byte) 0xD3, (byte) 0xB2, (byte) 0xE2, (byte) 0xCA, (byte) 0xD4, '0', '8', 0x04};
        byte[] a = MatrixMessage.buildSetSubtitleMessage(1, 2, "雕视测试").toBytes();
        assertArrayEquals(msg, a);

    }

    @Test
    public void buildSetSubtitleFormatMessage() throws Exception {
        byte[] msg = {0x01, '0', '1', 'O', 'C', '0', '0', '1', '0', '4', '3', '0', '0', '0', '0', '0', 'B', 0x04};
        byte[] a = MatrixMessage.buildSetSubtitleFormatMessage(1, 1, 4, (byte) '3', (byte) '0').toBytes();
        assertArrayEquals(msg, a);
    }

    @Test
    public void toGBK() throws Exception {

    }

}