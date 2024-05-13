package com.tt98.server.common.snowflake;

import com.tt98.server.common.IdGenerator;
import com.tt98.server.common.util.AsyncUtil;
import com.tt98.server.common.util.DateUtil;
import com.tt98.server.common.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * 自定义实现的雪花算法生成器
 * 时间+数据中心(3位)+机器id(7位)+序列号(12位)
 */
@Slf4j
public class SnowflakeIdGenerator implements IdGenerator {
    /**
     * 自增序号位数
     */
    private static final long SEQUENCE_BITS = 10L;

    /**
     * 机器位数
     */
    private static final long WORKER_ID_BITS = 7L;
    private static final long DATA_CENTER_BITS = 3L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;
    private static final long DATACENTER_LEFT_SHIFT_BITS = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS + DATA_CENTER_BITS;
    /**
     * 机器id (7位）
     */
    private long workId = 1;
    /**
     * 数据中心 (3位)
     */
    private long dataCenter = 1;

    /**
     * 上次的访问时间
     */
    private long lastTime;
    /**
     * 自增序号
     */
    private long sequence;

    private byte sequenceOffset;

    public SnowflakeIdGenerator(){
        try {
            String ip = IpUtil.getLocalIp4Address();
            String[] cells = StringUtils.split(ip,".");
            this.dataCenter = Integer.parseInt(cells[0]) & ((1<<DATA_CENTER_BITS)-1);
            this.workId = Integer.parseInt(cells[3]) >> 16 & ((1 << WORKER_ID_BITS)-1);
        } catch (Exception e){
            this.dataCenter = 1;
            this.workId = 1;
        }
    }

    public SnowflakeIdGenerator(int workId, int dataCenter){
        this.workId = workId;
        this.dataCenter = dataCenter;
    }

    /**
     * 生成趋势自增的id
     * @return
     */
    @Override
    public synchronized Long nextId() {
        long nowTime = waitToIncrDiffIfNeed(getNowTime());
        if (lastTime == nowTime) {
            if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
                // 表示当前这一时刻的自增数被用完了；等待下一时间点
                nowTime = waitUntilNextTime(nowTime);
            }
        } else {
            // 上一毫秒若以0作为序列号开始值，则这一秒以1为序列号开始值
            vibrateSequenceOffset();
            sequence = sequenceOffset;
        }
        lastTime = nowTime;
        long ans = ((nowTime % DateUtil.ONE_DAY_SECONDS) << TIMESTAMP_LEFT_SHIFT_BITS) | (dataCenter << DATACENTER_LEFT_SHIFT_BITS) | (workId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
        if (log.isDebugEnabled()) {
            log.debug("seconds:{}, datacenter:{}, work:{}, seq:{}, ans={}", nowTime % DateUtil.ONE_DAY_SECONDS, dataCenter, workId, sequence, ans);
        }
        return Long.parseLong(String.format("%s%011d", getDaySegment(nowTime), ans));
    }

    /**
     * 等待下一秒
     *
     * @param lastTime
     * @return
     */
    private long waitUntilNextTime(final long lastTime) {
        long result = getNowTime();
        while (result <= lastTime) {
            result = getNowTime();
        }
        return result;
    }

    private void vibrateSequenceOffset() {
        sequenceOffset = (byte) (~sequenceOffset & 1);
    }

    private long waitToIncrDiffIfNeed(final long nowTime){
        if(lastTime <= nowTime){
            return nowTime;
        }
        long diff = lastTime - nowTime;
        AsyncUtil.sleep(diff);
        return getNowTime();
    }

    /**
     * 获取当前时间
     */
    private long getNowTime(){
        return System.currentTimeMillis() / 1000;
    }


    private static String getDaySegment(long time) {
        LocalDateTime localDate = DateUtil.time2LocalTime(time * 1000L);
        return String.format("%02d%03d", localDate.getYear() % 100, localDate.getDayOfYear());
    }
}
