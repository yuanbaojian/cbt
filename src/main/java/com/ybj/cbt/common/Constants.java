package com.ybj.cbt.common;

import java.util.Arrays;
import java.util.List;

/**
 * 常量设置
 * @author 
 *
 */
public class Constants {


	public static String HTTP_URL = "https://www.xicidaili.com/wt/";
	public static String HTTPS_URL = "https://www.xicidaili.com/wn/";

    /// begin-----------证书维护模块依赖内容------------------------
	public static String USER_DATA="user";
	public static String CERTIFICATE_PATH="certificate";
	public static String CERTIFICATE_MSG001 = "该类证书已经存在,请先删除或重新上传!";
   ////end-----------证书维护模块依赖内容------------------------  
	
	/// begin-----------试题库管理模块依赖内容------------------------
	public static String IMAGE_PATH="examLibraryImages";
//	试题的审核状态
	public static String EXAM_AUDIT_ITEM_1 = "校对";
	public static String EXAM_AUDIT_ITEM_2 = "审核";
	public static String EXAM_AUDIT_ITEM_3 = "批准";
//  批量导入试题成功的反馈码
	public static int BATCH_SUCCESS = 0;
//  试题编号唯一性验证
	public static int EXAM_ONLY_WRONG = 0;
	public static int EXAM_ONLY_RIGHT = 1;
	////end-----------试题库管理模块依赖内容------------------------  
	/// begin-----------试卷模块依赖内容------------------------
	public static String EXAM_PAPER="examPaper";
	////end-----------试卷模块依赖内容------------------------  
	//// begin-----------3DHandbook项目的依赖内容------------------------
	public static String PLANE_MODEL = "planeModel";
	public static String PM_MANAGE_MSG001 = "机型有关联数据,不可删除";
	public static String PM_MANAGE_MSG002 = "该机型已存在,请修改名称或重新上传!";
	public static String THUMBNAIL_PATH = "thumbnail";
	public static String REPORT_PATH="userReport";
	public static String COURSE_PATH = "course";
	//// end-----------3DHandbook项目的依赖内容------------------------



	//begin素材模块
	public static String ANNEX_PATH="materialLibrary";
    public static String UNDEFINED="undefined";
    public static List<String> OFFICE_SUFFIX= Arrays.asList(".doc",".docx", ".ppt", ".pptx",".xls",".xlsx",
            "doc","docx", "ppt", "pptx","xls","xlsx");
    public static List<String> ARCHIVE_SUFFIX= Arrays.asList(".zip",".rar",
            "zip","rar");
	public static final String UNITYWEB_FOLDERNAME = "APP";
	public static final String NEW_UNITYWEB_FOLDERNAME = "NEWAPP";
	public static final int ZERO =0 ;
	//end素材模块

	//begin 培训计划模块
	public static String PLAN_PATH="plan";
	public static final String THEORY_TRAINING = "理论培训";
	public static final String HANDS_ON_TRAINING ="实习培训";
	public static final Integer THEORY_CLASS = 0;
	//end 培训计划模块

	//begin 教学内容
	public static final Integer COACH_SIGNED_ID = 1;
	public static final Integer COACH_UNSIGNED_ID = 0;
	public static final int ONE = 1 ;
	public static final int COACH_TIME_OVERLAP = 3;
	public static final int CLASS_TIME_OUT_OF_PLAN = 4;
	public static final int CLASS_TIMEOUT_OF_ARRANGED =5 ;
	public static final Long NULL_TEACH_CONTENT_ID = Long.valueOf(-1);
	public static final String AM = "AM";
	public static final String PM = "PM";
	//end 教学内容

	//begin 审核文件
	public static final String AUDIT_FILE = "auditFile";
	public static final int CODE_NAME_REPEATED = 1;
	public static final Long NULL_DOC_ID = Long.valueOf(-1);
	//end  审核文件

	//begin 课程状态
	public static String COURSE_RELEASED="4";
	//end   课程状态

	//begin 组织结构管理
	public static String belongToNoOrg="belongToNoOrg";
	//end   组织结构管理


	// 系统名称
	public static String SYSTEMNAME = "MBD工装管理系统";
	// 管理员名称
	public static String ADMIN_USER = "admin";
	// 总部
	public static String ZONGBU = "总部";
	
	// 30分钟内密码最大错误次数
	public static final int ALLOW_FAIL_COUNT = 5;
	// 密码过期时间
	public static final int LOCK_TIME = 30;
	// 默认密码
	public static final String PASSWORD = "123456";
	
	// 审核状态-与PM相关
	public static final String AUDIT_STATUS_0 = "0";  // In work       设计中
	public static final String AUDIT_STATUS_1 = "1";  // Under review  审查中
	public static final String AUDIT_STATUS_2 = "2";  // Approved      审核通过
	public static final String AUDIT_STATUS_3 = "3";  // Release       已发布
	public static final String AUDIT_STATUS_4 = "4";  // Abolished     已废除
	
	// 发布状态-web端
	public static final short RELEASE_STATUS_0 = 0;  // 未发布
	public static final short RELEASE_STATUS_1 = 1;  // 待审核
	public static final short RELEASE_STATUS_2 = 2;  // 已发布
	public static final short RELEASE_STATUS_3 = 3;  // 已驳回
	// 错误编码
	public static int ERROR_CODE_NONE = 0;
	public static int ERROR_CODE_SYSTEM = 10000;
	public static int ERROR_CODE_BUSINESS = 10001;
	
	// 电子仓库根节点
	public static String DATAVALUT_PATH = "dataVault";
	// 图框名称
	public static String FRAME_TEMPLATE = "FrameTemplate";
	// 电子仓库根目录
	public static String STOREHOUSE_PATH = "storehouse_path";  
	// 电子仓库中标准的根目录
	public static String STANDARD_PATH = "standard"; 
	// 电子仓库中工装的艮目录
	public static String FRAME_TEMPLATE_PATH = "frame_template";  
	// 电子仓库中工装模板的根目录
	public static String TOOLING_TEMPLATE_PATH = "tooling_template";
	// 电子仓库中标准件的根目录
	public static String STANDARDINSTANCE_PATH = "standard_component";  
	// 电子仓库中特征模板库的根目录
	public static String FEATURE_PATH = "feature";
	// 电子仓库中成品件的根目录
	public static String FINISHED_PRODUCT_PATH = "finished_product";
	// 电子仓库中材料的根目录
	public static String MATERIAL_PATH = "material"; 
	// 电子仓库中材料标准文档的根目录
	public static String STANDARD_DOC_PATH = "srandard_doc"; 
	
	// 电子仓库中紧固件XML的根目录
	public static String FASTENER_XML_PATH = "fastener_xml";
	// 紧固件打包下载的类型
	public static String STANDARD_TYPE_FOR_DOWNLOAD = "standard_type";
	// 紧固件打包下载的目录
	public static String FASTENER_DIRECTORY = "Fasteners";
	// 紧固件打包下载的ProjectInfo XML文件名
	public static String PROJECTINFO_XML_FILE_NAME = "projectInfo_xml_name";
	
	public static String FILE_CONNECT=".";
	
	// 接口信息
	public static final String INTERFACE_INFO="InterfaceInfo"; 
	// 电子仓库中临时文件的根目录
	public static String TEMP_PATH = "temp"; 
	// 产品文件的根目录
	public static String PRODUCT_PATH = "product"; 
	// CATDrawing文件的后缀名
	public static final String FRAME_FUFFIX = ".CATDrawing";
    // CATPart文件的后缀名	
	public static final String CATPART_FUFFIX = ".CATPart";
	// CATPart文件的后缀名	
	public static final String CATPRODUCT_FUFFIX = ".CATProduct";
	// ZIP文件后缀名
	public static final String ZIP_FUFFIX = ".zip";
	
	// MBD模板名称
	public static String MBD_TEMPALTE = "MBDTemplate";
	
	
	public static String MODEL = "Model";
	
	// 标准名称
	public static String STANDARD = "Standard";
	
	// 特征模板名称
	public static String FEATURE = "Feature";
	
	// 注释名称
	public static String ANNOTATION = "Annotation";
	
	public static String COURSE = "Course";
	
	public static String PLAN = "Plan";
	
	public static String EXAM = "Exam";
	
	public static String EXAM_PAPER_CAP = "ExamPaper";
	
	public static String EXAM_LIB = "ExamLib";
	
	// 材料名称
	public static String MATERIAL = "Material";
	// 工装模板名称
	public static String TOOLING_TEMPLATE = "ToolingTemplate";
	// 成品件名称
	public static String FINISHED_PRODUCT = "Finished";
	
	// 以下为message
	public static String ANNOTATION_MANAGE_MSG001 = "注释有关联,不可删除!";
	public static String ANNOTATION_MANAGE_MSG002 = "该分类下已存在注释，不可新建子分类！";
	public static String FEATURE_MANAGE_MSG001 = "特征模板有关联,不可删除!";
	public static String FEATURE_MANAGE_MSG002 = "该分类下已存在特征模板，不可新建子分类！";
	public static String FINISHED_MANAGE_MSG001 = "成品件有关联,不可删除!";
	public static String FINISHED_MANAGE_MSG002 = "该分类下已存在成品件，不可新建子分类！";
	public static String BOOKTAG_MSG001 = "您输入的分类名称在数据库中已存在，请重新输入";
	public static String BOOKTAG_MSG002 = "分类存在关联，删除失败！";
	public static String DOCUMENT_MSG001 = "您输入的文档名称在数据库中已经存在，请重新输入";
	public static String DOCUMENT_MSG002 = "该文档已与工装关联,不可删除!";
	public static String LOGIN_MSG001 = "用户名或密码不正确,请重新输入";
	public static String MATERIAL_MANAGE_MSG001 = "该分类已关联标材料,不可删除!";
	public static String MATERIAL_MANAGE_MSG002 = "该分类下已存在材料，不可新建子分类！";
	public static String MATERIAL_MANAGE_MSG003 = "标准文档删除失败！";
	public static String MBD_MSG001 = "MBD模板有关联,不可删除!";
	public static String MBD_MSG002 = "您输入的模板名称和模板编码在数据库中已经存在，请重新输入";
	public static String MBD_MSG003 = "您输入的模板名称在数据库中已经存在，请重新输入";
	public static String MBD_MSG004 = "您输入的模板编码在数据库中已经存在，请重新输入";
	public static String MBD_MSG005 = "该分类下已存在MBD模板，不可新建子分类！";
	public static String ORG_STRUC_MSG001 = "用户有关联,不可删除!";
	public static String ORG_STRUC_MSG002 = "该记录有关联，不可删除!";
	public static String ORG_STRUC_MSG004 = "系统初始化管理员用户不可删除!";
	public static String ORG_STRUC_MSG005 = "系统初始化总部不可删除!";
	public static String ORG_STRUC_MSG007 = "产品有关联,不可删除!";
	public static String TOOLING_TEMPLATE_MSG001 = "工装模板有关联,不可删除!";
	public static String FINISHED_PRODUCT_MSG001 = "成品件有关联,不可删除!";
	public static String PARTS_MSG001 = "零部件有关联，不可删除!";
	public static String PRODUCTSTRUCTURE_MANAGE_MSG001 = "该分类已关联装配模型,不可删除!";
	public static String PRODUCTSTRUCTURE_MANAGE_MSG002 = "您输入的分类名称在当前分类下已经存在，请重新输入";
	public static String PRODUCTSTRUCTURE_MANAGE_MSG003 = "您选择的总图在当前分类下已存在，请重新选择!";
	public static String ROLE_MSG001 = "您输入的角色名称在数据库中已经存在，请重新输入";
	public static String STANDARD_MANAGE_MSG001 = "该分类已关联标准件,不可删除!";
	public static String STANDARD_MANAGE_MSG002 = "该分类下已存在标准，不可新建子分类！";
	public static String SYSTEM_MSG001 = "您没有权限访问该功能，请切换成有该功能权限的用户重新登录系统";
	public static String TRAINNING_MSG001 = "您输入的属性名称已经存在，请重新输入";
	public static String TRAINNING_MSG003 = "该节点不可移动，只有分类和工装总图可以移动！";
	public static String TRAINNING_MSG004 = "工装总图只能移动至分类节点！";
	public static String TRAINNING_MSG005 = "您输入的工装图号在所在产品中已经存在，请重新输入";
	public static String USER_MSG001 = "您输入的登录名在数据库中已经存在，请重新输入";
	
	public static String ATAFOLDER_MANAGE_MSG001 = "该分类已存在下级分类,不可删除!";
	public static String ATAFOLDER_MANAGE_MSG002 = "该分类已关联素材或课程,不可删除!";
	public static String ATAFOLDER_MANAGE_MSG003 = "该分类名称已经被使用!";
	
	public static String COURSE_MANAGE_MSG001 = "该课程编号已经被使用!";
	public static String COURSE_MANAGE_MSG002 = "新建课程失败!";
	public static String COURSE_MANAGE_MSG003 = "课程删除失败!";
	public static String COURSE_MANAGE_MSG004 = "该章节编号已经被使用!";
	public static String COURSE_MANAGE_MSG005 = "章节删除失败!";
	public static String COURSE_MANAGE_MSG006 = "关联素材已经存在!";
	public static String COURSE_MANAGE_MSG007 = "上传素材文件失败!";
	public static String COURSE_MANAGE_MSG008 = "素材已被三维章节使用，不能删除!";
	
	public static String AUDIT_ITEM_0 = "未发布";
	public static String AUDIT_ITEM_1 = "校对";
	public static String AUDIT_ITEM_2 = "审核";
	public static String AUDIT_ITEM_3 = "批准";
	public static String AUDIT_ITEM_4 = "发布";
	
	public static String ACTION_Y = "同意";
	public static String ACTION_N = "否决";
	public static String ACTION_U = "取消发布";
	public static String ACTION_C = "提交";
	
	public static Integer STATUS_0 = 0;
	public static Integer STATUS_1 = 1;
	public static Integer STATUS_2 = 2;
	public static Integer STATUS_3 = 3;
	public static Integer STATUS_4 = 4;
	public static Integer STATUS_5 = 5;
	public static Integer STATUS_6 = 6;
	public static Integer STATUS_7 = 7;
	
	public static String MESSAGE_ALL_COUNT = "AllMessagesCount";
	public static String MESSAGE_COURSE_COUNT = "CourseCount";
	public static String MESSAGE_PLAN_COUNT = "PlanCount";
	public static String MESSAGE_EXAM_COUNT = "ExamCount";
	public static String MESSAGE_EXAM_PAPER_COUNT = "ExamPaperCount";
	public static String MESSAGE_EXAM_LIB_COUNT = "ExamLibCount";
	
	public static Integer CHAPTER_TYPE_3D = 2;
	public static String CHAPTER_TYPE_3D_APP = "APP";
	public static String CHAPTER_TYPE_3D_BUILD = "build.json";
	public static String CHAPTER_TYPE_3D_UNITY = "unity.json";
	public static String CHAPTER_DEFAULT_NAME = "请修改章节名称";
	
	public static Integer ANNEX_TYPE_2D = 0;
	public static Integer ANNEX_TYPE_3D = 1;
	
	public static Integer ANNEX_KEY_TEACH = 0;
	public static Integer ANNEX_KEY_STUDY = 1;
	
	public static String JSON_TEACH_KEY = "Teaching";
	public static String JSON_STUDY_KEY = "Practise";
	public static String JSON_STUDY_ID = "ID";
	public static String JSON_STUDY_NAME = "Name";
	public static String JSON_STUDY_TEXT = "Text";
	public static String JSON_STUDY_BUTTON = "Button";
	public static String JSON_STUDY_RB = "RightButton";
	public static String JSON_STUDY_MODEL = "Model";
	public static String JSON_STUDY_ANIM = "Animation";
}