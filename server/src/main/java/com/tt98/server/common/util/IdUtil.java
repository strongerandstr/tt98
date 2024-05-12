package com.tt98.server.common.util;

import com.tt98.server.common.snowflake.SnowflakeIdGenerator;
import com.tt98.server.common.snowflake.SnowflakeProducer;

public class IdUtil {
    /**
     * 默认的id生成器
     */
    public static SnowflakeProducer DEFAULT_ID_PRODUCER = new SnowflakeProducer(new SnowflakeIdGenerator());

    /**
     * 生成全局id
     *
     * @return
     */
    public static Long genId() {
        return DEFAULT_ID_PRODUCER.genId();
    }

    /**
     * 生成字符串格式全局id
     *
     * @return
     */
    public static String genStrId() {
        return CompressUtil.int2str(genId());
    }

    public static void main(String[] args) {
        System.out.println(IdUtil.genStrId());
        Long id = IdUtil.genId();
        System.out.println(id + " = " + CompressUtil.int2str(id));
        System.out.println(IdUtil.genId() + "->" + IdUtil.genStrId());
        AsyncUtil.sleep(2000);
        System.out.println(IdUtil.genId() + "->" + IdUtil.genStrId());

        System.out.println("-----");

        SnowflakeProducer producer = new SnowflakeProducer(new SnowflakeIdGenerator());
        id = producer.genId();
        System.out.println("id: " + id + " -> " + CompressUtil.int2str(id));
        AsyncUtil.sleep(3000L);
        id = producer.genId();
        System.out.println("id: " + id + " -> " + CompressUtil.int2str(id));
    }
}
