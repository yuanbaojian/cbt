//package com.ybj.cbt.serverImplement;
//
//import com.atoz.cbtsys.common.Constants;
//import com.atoz.cbtsys.common.utils.DateUtils;
//import com.atoz.cbtsys.common.utils.FileUtils;
//import com.atoz.cbtsys.common.utils.StringUtils;
//import com.atoz.cbtsys.dao.DocMapper;
//import com.atoz.cbtsys.model.Doc;
//import com.atoz.cbtsys.model.User;
//import com.atoz.cbtsys.server.util.PropertiesUtil;
//import com.atoz.cbtsys.service.AuditFileServiceI;
//import com.ybj.cbt.common.Constants;
//import com.ybj.cbt.mapper.DocMapper;
//import com.ybj.cbt.model.Doc;
//import com.ybj.cbt.model.User;
//import com.ybj.cbt.serverInterface.AuditFileServiceI;
//import com.ybj.cbt.utils.DateUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.DecimalFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class AuditFileServiceImpl implements AuditFileServiceI {
//
//    @Autowired
//    DocMapper documentMapper;
//
//    @Override
//    public List<Doc> getAuditFileListByCondition(String name, String startDateString, String endDateString, String docType) throws ParseException {
//        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
//        Date startDate=null;
//        Date endDate=null;
//        if( startDateString.length()!=0){
//            startDate = fmt.parse(startDateString);
//        }
//        if( endDateString.length()!=0){
//            endDate = fmt.parse(endDateString);
//            endDate= DateUtils.addDays(endDate, Constants.ONE);
//        }
//        List<Doc> documentList=documentMapper.selectDocumentListByCondition(name, startDate,endDate, Long.valueOf(docType));
//        return documentList;
//    }
//
//    @Override
//    public void setAttribute(Doc document, MultipartFile auditFile, User user, String docType, boolean ifInsert) {
//        if (auditFile != null && auditFile.getSize() >0){
//            Double fileSizeDouble= (auditFile.getSize()/1024.00/1024.00);
//            DecimalFormat df = new DecimalFormat("#.##");
//            String FilesizeLength = df.format(fileSizeDouble);
//            document.setFileSize(auditFile.getSize());
//            document.setFileSizeLength(FilesizeLength);
//            final String originalName = auditFile.getOriginalFilename();
//            int index = originalName.lastIndexOf(".");
//            // 文件名（排掉扩展名）
//            final String tempName = originalName.substring(0, index);
//            // 扩展名，类别  eg: png
//            String extName = originalName.substring(index + 1);
//            document.setFileType(extName);
//            document.setFileName(tempName);
//            document.setDocType(docType==null? null: Integer.valueOf(docType));
//        }
//        if(ifInsert){
//            document.setCreatedBy(Long.valueOf(user.getUserId()));
//            document.setCreateTime(new Date());
//        }
//        document.setUpdatedBy(Long.valueOf(user.getUserId()));
//        document.setUpdateTime(new Date());
//    }
//
//    @Override
//    public void insertDocument(Doc document) {
//        documentMapper.insertSelective(document);
//    }
//
//    @Override
//    public void storeFile(String dir, String fullpath, MultipartFile auditFile) throws IOException {
//        File fileParentDirectory = new File(dir);
//        if (!fileParentDirectory.exists()) {
//            fileParentDirectory.mkdirs();
//        }
//        File serverFile = new File(fullpath);
//        if (!serverFile.exists()) {
//            try {
//                serverFile.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        auditFile.transferTo(serverFile);
//    }
//
//    @Override
//    public String storeAuditFile(Long docId, MultipartFile auditFile) throws IOException {
//        Doc oldDoc=documentMapper.selectByPrimaryKey(docId);
//        if(oldDoc.getFilePath()!=null){
//            String oldFilePath=PropertiesUtil.getMsgsMap().get("storehouse_path") + File.separator +oldDoc.getFilePath();
//            FileUtils.deleteFile(oldFilePath);
//        }
//        String fullPath= StringUtils.joinWithFileSeparator(PropertiesUtil.getMsgsMap().get("storehouse_path"),Constants.AUDIT_FILE,docId,auditFile.getOriginalFilename());
//        FileUtils.createFile(fullPath);
//        FileUtils.multipartFileToFIle(auditFile, fullPath);
//        String pathInDb=StringUtils.joinWithFileSeparator(Constants.AUDIT_FILE,docId,auditFile.getOriginalFilename());
//        return pathInDb;
//    }
//
//
//    @Override
//    public void updateFilePath(Long docId, String fullPath) {
//        documentMapper.updateFilePathByDocId(docId,fullPath);
//    }
//
//    @Override
//    public String getPathInDBByDocId(String docId) {
//        String filePath=documentMapper.selectFilePathByDocId(Long.valueOf(docId));
//        return filePath;
//    }
//
//    @Override
//    public void deleteFileByDocId(String docId) throws IOException {
//        String dir=StringUtils.joinWithFileSeparator(PropertiesUtil.getMsgsMap().get("storehouse_path"),Constants.AUDIT_FILE,docId);
//        FileUtils.deleteAll(dir);
//    }
//
//    @Override
//    public void deleteAuditFileRecordByDocId(String docId) {
//        documentMapper.deleteAuditByDocId(Long.valueOf(docId));
//    }
//
//    @Override
//    public void updateAuditFile(Doc document) {
//        documentMapper.updateByPrimaryKeySelective(document);
//    }
//
//    @Override
//    public Boolean checkNameRepeated(String docIdString, String docName, String docType) {
//        Long docId;
//        if(docIdString.isEmpty()){
//            docId=Constants.NULL_DOC_ID;
//        } else{
//            docId=Long.valueOf(docIdString);
//        }
//        int repeatedNumber=documentMapper.selectCountByIDAndName(docId,docName, Long.valueOf(docType));
//        Boolean result=true;
//        result= repeatedNumber != Constants.ZERO;
//        return result;
//    }
//}
