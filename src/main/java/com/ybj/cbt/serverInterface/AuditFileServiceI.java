package com.ybj.cbt.serverInterface;

import com.ybj.cbt.model.Doc;
import com.ybj.cbt.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface AuditFileServiceI {


    /** 根据条件查询审核文件结果集
     * @param name
     * @param startDate
     * @param endDate
     * @param docType
     * @return
     */
    List<Doc> getAuditFileListByCondition(String name, String startDate, String endDate, String docType) throws ParseException;

    /***
     * @Description  对doucument对象赋值
     * @param document
     * @param auditFile
     * @param user
     * @param docType
     * @param ifInsert
     * @author baojian.yuan
     * @date 2019/10/15
     */
    void setAttribute(Doc document, MultipartFile auditFile, User user, String docType, boolean ifInsert);

    /***
     * @Description 插入文件
     * @param document
     * @return void
     * @author baojian.yuan
     * @date 2019/10/15
     */
    void insertDocument(Doc document);

    /***
     * @Description 存储文件
     * @param dir  除了主键ID  文件名  之外的文件夹路径   eg  C:/a/file/audit/internal/
     * @param fullpath  文件全路径
     * @param auditFile
     * @return void
     * @author baojian.yuan
     * @date 2019/10/15
     */
    void storeFile(String dir, String fullpath, MultipartFile auditFile) throws IOException;

    /***
     * @Description   存储审核文件
     * @param docId  文件主键
     * @param auditFile 文件内容
     * @return void
     * @author baojian.yuan
     * @date 2019/10/15
     */
    String storeAuditFile(Long docId, MultipartFile auditFile) throws IOException;

    /***
     * @Description 根据ID 更新其 文件路径
     * @param docId
     * @param fullPath
     * @return void
     * @author baojian.yuan
     * @date 2019/10/15
     */
    void updateFilePath(Long docId, String fullPath);

    /***
     * @Description  根据docID查找filePath
     * @param docId
     * @return java.lang.String
     * @author baojian.yuan
     * @date 2019/10/15
     */
    String getPathInDBByDocId(String docId);

    /***
     * @Description 删除文件
     * @param docId
     * @return void
     * @author baojian.yuan
     * @date 2019/10/15
     */
    void deleteFileByDocId(String docId) throws IOException;

    void deleteAuditFileRecordByDocId(String docId);

    /***
     * @Description  更新审核文件对象
     * @param document
     * @return void
     * @author baojian.yuan
     * @date 2019/10/16
     */
    void updateAuditFile(Doc document);

    /***
     * @Description  检测名称是否重复
     * @param docId
     * @param docName
     * @param docType
     * @return java.lang.Boolean
     * @author baojian.yuan
     * @date 2019/10/16
     */
    Boolean checkNameRepeated(String docId, String docName, String docType);
}
