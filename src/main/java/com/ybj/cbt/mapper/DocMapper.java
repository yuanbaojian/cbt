package com.ybj.cbt.mapper;

import com.ybj.cbt.model.Doc;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DocMapper {
    int deleteByPrimaryKey(Long docId);

    int insert(Doc record);

    int insertSelective(Doc record);

    Doc selectByPrimaryKey(Long docId);

    int updateByPrimaryKeySelective(Doc record);

    int updateByPrimaryKey(Doc record);

    List<Doc> selectDocumentListByCondition(@Param(value = "docName") String name
            , @Param(value = "startDate") Date startDate
            , @Param(value = "endDate") Date endDate
            , @Param(value = "docType") Long docType);

    void updateFilePathByDocId(@Param(value = "docId") Long docId, @Param(value = "fullPath") String fullPath);

    String selectFilePathByDocId(@Param(value = "docId") Long docId);

    void deleteAuditByDocId(@Param(value = "docId") Long docId);

    int selectCountByIDAndName(@Param(value = "docId") Long docId, @Param(value = "docName") String docName, @Param(value = "docType") Long docType);
}