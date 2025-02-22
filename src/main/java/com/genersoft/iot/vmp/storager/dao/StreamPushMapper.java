package com.genersoft.iot.vmp.storager.dao;

import com.genersoft.iot.vmp.media.zlm.dto.StreamPushItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StreamPushMapper {

    @Insert("INSERT INTO stream_push (app, stream, totalReaderCount, originType, originTypeStr, " +
            "createStamp, aliveSecond) VALUES" +
            "('${app}', '${stream}', '${totalReaderCount}', '${originType}', '${originTypeStr}', " +
            "'${createStamp}', '${aliveSecond}' )")
    int add(StreamPushItem streamPushItem);

    @Update("UPDATE stream_push " +
            "SET app=#{app}," +
            "stream=#{stream}," +
            "totalReaderCount=#{totalReaderCount}, " +
            "originType=#{originType}," +
            "originTypeStr=#{originTypeStr}, " +
            "createStamp=#{createStamp}, " +
            "aliveSecond=#{aliveSecond} " +
            "WHERE app=#{app} AND stream=#{stream}")
    int update(StreamPushItem streamPushItem);

    @Delete("DELETE FROM stream_push WHERE app=#{app} AND stream=#{stream}")
    int del(String app, String stream);

    @Select("SELECT st.*, pgs.gbId, pgs.status, pgs.name, pgs.longitude, pgs.latitude FROM stream_push st LEFT JOIN gb_stream pgs on st.app = pgs.app AND st.stream = pgs.stream")
    List<StreamPushItem> selectAll();

    @Select("SELECT st.*, pgs.gbId, pgs.status, pgs.name, pgs.longitude, pgs.latitude FROM stream_push st LEFT JOIN gb_stream pgs on st.app = pgs.app AND st.stream = pgs.stream WHERE st.enable=${enable}")
    List<StreamPushItem> selectForEnable(boolean enable);

    @Select("SELECT st.*, pgs.gbId, pgs.status, pgs.name, pgs.longitude, pgs.latitude FROM stream_push st LEFT JOIN gb_stream pgs on st.app = pgs.app AND st.stream = pgs.stream WHERE st.app=#{app} AND st.stream=#{stream}")
    StreamPushItem selectOne(String app, String stream);

    @Insert("<script>"  +
            "INSERT INTO stream_push (app, stream, totalReaderCount, originType, originTypeStr, " +
            "createStamp, aliveSecond) " +
            "VALUES <foreach collection='streamPushItems' item='item' index='index' >" +
            "( '${item.app}', '${item.stream}', '${item.totalReaderCount}', '${item.originType}', " +
            "'${item.originTypeStr}','${item.createStamp}', '${item.aliveSecond}' )" +
            " </foreach>" +
            "</script>")
    void addAll(List<StreamPushItem> streamPushItems);

    @Delete("DELETE FROM stream_push")
    void clear();

}
