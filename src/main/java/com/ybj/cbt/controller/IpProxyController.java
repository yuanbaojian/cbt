package com.ybj.cbt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/IpProxy")
public class IpProxyController {


    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("IpProxy/IpList");
        return modelAndView;
    }

//    @RequestMapping(value = "list",method = RequestMethod.GET)
//    public List<Doc> getAuditFileList(
//                                @RequestParam String docType
//                                ,@RequestParam String docName
//                                , @RequestParam String startDate
//                                , @RequestParam String endDate) throws ParseException {
//        List<Doc> documentList=auditFileServiceI.getAuditFileListByCondition(docName, startDate,endDate,docType );
//        return documentList;
//    }
//
//
//    @RequestMapping(value="/checkNameRepeated", method = RequestMethod.GET)
//    public JsonResult checkNameRepeated(@RequestParam(value = "docId") String docId
//                                    , @RequestParam(value = "docName") String docName
//                                    , @RequestParam(value = "docType") String docType
//    ) throws IOException {
//        JsonResult result = new JsonResult();
//        User user=(User) SecurityUtils.getSubject().getPrincipal();
//        try {
//            result.setCode(Constants.ERROR_CODE_NONE);
//            Boolean ifRepeated=auditFileServiceI.checkNameRepeated(docId,docName, docType);
//            if(ifRepeated){
//                result.setCode(Constants.CODE_NAME_REPEATED);
//            } else{
//                result.setCode(Constants.ERROR_CODE_NONE);
//            }
//        }catch (CustomGenericException ex1) {
//            result.setCode(ex1.getErrCode());
//            result.put("errmsg", ex1.getErrMsg());
//        }
//        return result;
//    }
//
//    @RequestMapping(value="/file/{docType}", method = RequestMethod.POST)
//    public  JsonResult insert(MultipartHttpServletRequest request
//                        , @PathVariable String docType
//            , @RequestParam(value="auditFile",required=true) MultipartFile auditFile
//            , Doc document) throws IOException {
//        User user=(User) SecurityUtils.getSubject().getPrincipal();
//        JsonResult result = new JsonResult();
//        result.put("code",Constants.ERROR_CODE_NONE);
//        try{
//            auditFileServiceI.setAttribute(document, auditFile,user, docType, true);
//            auditFileServiceI.insertDocument(document);
//            String filePathInDB = auditFileServiceI.storeAuditFile(document.getDocId(), auditFile);
//            auditFileServiceI.updateFilePath(document.getDocId(), filePathInDB);
//             result.setCode(Constants.ERROR_CODE_NONE);
//        } catch (CustomGenericException ex1) {
//            result.put("code", ex1.getErrCode());
//            result.put("errmsg", ex1.getErrMsg());
//        }
//        return  result;
//    }
//
//    @RequestMapping(value="/update", method = RequestMethod.POST)
//    public  JsonResult update(
//             @RequestParam(value="auditFile",required=false) MultipartFile auditFile
//            , Doc document) throws IOException {
//        User user=(User) SecurityUtils.getSubject().getPrincipal();
//        JsonResult result = new JsonResult();
//        result.put("code",Constants.ERROR_CODE_NONE);
//        try{
//            auditFileServiceI.setAttribute(document,auditFile,user,null,false);
//            if(auditFile!=null){
//                String filePathInDB = auditFileServiceI.storeAuditFile(document.getDocId(), auditFile);
//                document.setFilePath(filePathInDB);
//            }
//            auditFileServiceI.updateAuditFile(document);
//        } catch (CustomGenericException ex1) {
//            result.put("code", ex1.getErrCode());
//            result.put("errmsg", ex1.getErrMsg());
//        }
//        return  result;
//    }
//
//
//    @RequestMapping(value="/file/{deletedId}", method = RequestMethod.DELETE)
//    public void remove(HttpServletRequest req, HttpServletResponse response, @PathVariable(value = "deletedId") String docId) throws IOException{
//        JsonResult result = new JsonResult();
//        try {
//            auditFileServiceI.deleteAuditFileRecordByDocId(docId);
//            auditFileServiceI.deleteFileByDocId(docId);
//            result.setCode(Constants.ERROR_CODE_NONE);
//        } catch (Exception ex1) {
//            result.setCode(Constants.ERROR_CODE_BUSINESS);
//            result.put("errmsg", ex1.getMessage());
//        }
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        response.getWriter().print(JSON.toJSONString(result));
//    }
//
//    @RequestMapping(value = "/download", method = RequestMethod.POST)
//    public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "docId") String docId) throws IOException {
//        String serverPath = Global.getDataVaultUrl(request, "");
//        String filePathInDB = auditFileServiceI.getPathInDBByDocId(docId);
//        String fullPath=serverPath+filePathInDB;
//        FileUtils.DownloadRemoteFile(fullPath, response);
//    }
//
//    @RequestMapping(value = "/getFilePathByDocId/{docId}", method = RequestMethod.GET)
//    public JsonResult getFilePath( @PathVariable(value = "docId") String docId) throws IOException {
//        String filePathInDB = auditFileServiceI.getPathInDBByDocId(docId);
//       JsonResult jsonResult=new JsonResult();
//       jsonResult.put("filePath", filePathInDB);
//       return jsonResult;
//    }


}
