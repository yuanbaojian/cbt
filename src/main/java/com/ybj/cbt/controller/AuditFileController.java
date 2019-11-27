package com.ybj.cbt.controller;


import com.ybj.cbt.common.Constants;
import com.ybj.cbt.common.JsonResult;
import com.ybj.cbt.model.Doc;
import com.ybj.cbt.model.User;
import com.ybj.cbt.serverInterface.AuditFileServiceI;
import com.ybj.cbt.utils.FileUtils;
import com.ybj.cbt.utils.Global;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;



@RestController
@RequestMapping("/auditFile")
public class AuditFileController {

    @Autowired
    AuditFileServiceI auditFileServiceI;

    @RequestMapping(value = "/index" ,method = RequestMethod.POST)
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("auditFile/auditFileList");
        return  modelAndView;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public List<Doc> getAuditFileList(
                                @RequestParam String docType
                                ,@RequestParam String docName
                                , @RequestParam String startDate
                                , @RequestParam String endDate) throws ParseException {
        List<Doc> documentList=auditFileServiceI.getAuditFileListByCondition(docName, startDate,endDate,docType );
        return documentList;
    }


    @RequestMapping(value="/checkNameRepeated", method = RequestMethod.GET)
    public JsonResult checkNameRepeated(@RequestParam(value = "docId") String docId
                                    , @RequestParam(value = "docName") String docName
                                    , @RequestParam(value = "docType") String docType
    ) throws IOException {
        JsonResult result = new JsonResult();
        User user=(User) SecurityUtils.getSubject().getPrincipal();
        result.setCode(Constants.ERROR_CODE_NONE);
        Boolean ifRepeated=auditFileServiceI.checkNameRepeated(docId,docName, docType);
        if(ifRepeated){
            result.setCode(Constants.CODE_NAME_REPEATED);
        } else{
            result.setCode(Constants.ERROR_CODE_NONE);
        }
        return result;
    }

    @RequestMapping(value="/file/{docType}", method = RequestMethod.POST)
    public  JsonResult insert(MultipartHttpServletRequest request
                        , @PathVariable String docType
            , @RequestParam(value="auditFile",required=true) MultipartFile auditFile
            , Doc document) throws IOException {
        User user=(User) SecurityUtils.getSubject().getPrincipal();
        JsonResult result = new JsonResult();
        result.put("code",Constants.ERROR_CODE_NONE);
        document=auditFileServiceI.setAttribute(document, auditFile,user, docType, true);
       // auditFileServiceI.insertDocument(document);
        auditFileServiceI.storeAuditFile(Long.valueOf(1),auditFile);
        result.setCode(Constants.ERROR_CODE_NONE);
        return  result;
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public  JsonResult update(
             @RequestParam(value="auditFile",required=false) MultipartFile auditFile
            , Doc document) throws IOException {
        User user=(User) SecurityUtils.getSubject().getPrincipal();
        JsonResult result = new JsonResult();
        result.put("code",Constants.ERROR_CODE_NONE);
        auditFileServiceI.setAttribute(document,auditFile,user,null,false);
        auditFileServiceI.updateAuditFile(document);
        auditFileServiceI.storeAuditFile(document.getDocId(),auditFile);
        return  result;
    }


    @RequestMapping(value="/file/{deletedId}", method = RequestMethod.DELETE)
    public JsonResult remove(HttpServletRequest req, HttpServletResponse response, @PathVariable(value = "deletedId") String docId) throws IOException{
        JsonResult result = new JsonResult();
        try {
            auditFileServiceI.deleteAuditFileRecordByDocId(docId);
            auditFileServiceI.deleteFileByDocId(docId);
            result.setCode(Constants.ERROR_CODE_NONE);
        } catch (Exception ex1) {
            result.setCode(Constants.ERROR_CODE_BUSINESS);
            result.put("errmsg", ex1.getMessage());
        }
       return result;
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "docId") String docId) throws IOException {
        String serverPath = Global.getDataVaultUrl(request, "");
        String filePathInDB = auditFileServiceI.getPathInDBByDocId(docId);
        String fullPath=serverPath+filePathInDB;
        FileUtils.remoteFileDownload(fullPath, response);
    }

    @RequestMapping(value = "/getFilePathByDocId/{docId}", method = RequestMethod.GET)
    public JsonResult getFilePath( @PathVariable(value = "docId") String docId) throws IOException {
        String filePathInDB = auditFileServiceI.getPathInDBByDocId(docId);
       JsonResult jsonResult=new JsonResult();
       jsonResult.put("filePath", filePathInDB);
       return jsonResult;
    }




}
