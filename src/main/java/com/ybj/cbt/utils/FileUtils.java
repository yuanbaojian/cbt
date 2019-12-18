
package com.ybj.cbt.utils;

import com.ybj.cbt.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件操作工具类
 * 实现文件的创建、删除、复制、压缩、解压以及目录的创建、删除、复制、压缩解压等功能
 * @author kuaiduoli
 * @version 2017-03-2
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
	
	private static Logger log = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 复制单个文件，如果目标文件存在，则不覆盖
	 * @param srcFileName 待复制的文件名
	 * @param descFileName 目标文件名
	 * @return 如果复制成功，则返回true，否则返回false
	 */
	public static boolean copyFile(String srcFileName, String descFileName) {
		return FileUtils.copyFileCover(srcFileName, descFileName, false);
	}

	/**
	 * 复制单个文件
	 * @param srcFileName 待复制的文件名
	 * @param descFileName 目标文件名
	 * @param coverlay 如果目标文件已存在，是否覆盖
	 * @return 如果复制成功，则返回true，否则返回false
	 */
	public static boolean copyFileCover(String srcFileName,
			String descFileName, boolean coverlay) {
		File srcFile = new File(srcFileName);
		// 判断源文件是否存在
		if (!srcFile.exists()) {
			log.debug("复制文件失败，源文件 " + srcFileName + " 不存在!");
			return false;
		}
		// 判断源文件是否是合法的文件
		else if (!srcFile.isFile()) {
			log.debug("复制文件失败，" + srcFileName + " 不是一个文件!");
			return false;
		}
		File descFile = new File(descFileName);
		// 判断目标文件是否存在
		if (descFile.exists()) {
			// 如果目标文件存在，并且允许覆盖
			if (coverlay) {
				log.debug("目标文件已存在，准备删除!");
				if (!FileUtils.delFile(descFileName)) {
					log.debug("删除目标文件 " + descFileName + " 失败!");
					return false;
				}
			} else {
				log.debug("复制文件失败，目标文件 " + descFileName + " 已存在!");
				return false;
			}
		} else {
			if (!descFile.getParentFile().exists()) {
				// 如果目标文件所在的目录不存在，则创建目录
				log.debug("目标文件所在的目录不存在，创建目录!");
				// 创建目标文件所在的目录
				if (!descFile.getParentFile().mkdirs()) {
					log.debug("创建目标文件所在的目录失败!");
					return false;
				}
			}
		}

		// 准备复制文件
		// 读取的位数
		int readByte = 0;
		InputStream ins = null;
		OutputStream outs = null;
		try {
			// 打开源文件
			ins = new FileInputStream(srcFile);
			// 打开目标文件的输出流
			outs = new FileOutputStream(descFile);
			byte[] buf = new byte[1024];
			// 一次读取1024个字节，当readByte为-1时表示文件已经读取完毕
			while ((readByte = ins.read(buf)) != -1) {
				// 将读取的字节流写入到输出流
				outs.write(buf, 0, readByte);
			}
			log.debug("复制单个文件 " + srcFileName + " 到" + descFileName
					+ "成功!");
			return true;
		} catch (Exception e) {
			log.debug("复制文件失败：" + e.getMessage());
			return false;
		} finally {
			// 关闭输入输出流，首先关闭输出流，然后再关闭输入流
			if (outs != null) {
				try {
					outs.close();
				} catch (IOException oute) {
					oute.printStackTrace();
				}
			}
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException ine) {
					ine.printStackTrace();
				}
			}
		}
	}

	/**
	 * 复制整个目录的内容，如果目标目录存在，则不覆盖
	 * @param srcDirName 源目录名
	 * @param descDirName 目标目录名
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copyDirectory(String srcDirName, String descDirName) {
		return FileUtils.copyDirectoryCover(srcDirName, descDirName,
				false);
	}

	/**
	 * 删除文件夹的所有文件和文件夹（lizhengyang add）
	 * 
	 * @param
	 * @return
	 */
	public static void delAll(File flie) throws IOException {
		if (!flie.exists()) {
			return;
		}
		// 保存中间结果
		boolean rslt = true;
		// 先尝试直接删除
		if (!(rslt = flie.delete())) {
			// 若文件夹非空枚举、递归删除里面内容
			File[] subs = flie.listFiles();
			for (int i = 0; i <= subs.length - 1; i++) {
				if (subs[i].isDirectory()) {
					// 递归删除子文件夹内容
					delAll(subs[i]);
				}
				rslt = subs[i].delete();
			}
			// 删除此文件夹本身
			rslt = flie.delete();
		}
		if (!rslt) {
			throw new IOException("无法删除:" + flie.getName());
		}
		return;
	}

	/***
	 * @Description 拷贝一个目录下的文件到另一个文件夹下
	 * @param srcDirPath
	 * @param descDirPath
	 * @return void
	 * @author baojian.yuan
	 * @date 2019/9/4
	 */
	public static void cpoyFileUnderDirectoryToDirectory(String srcDirPath, String descDirPath) {

	}


	/**
	 * 复制整个目录的内容
	 *
	 * @param srcDirName  源目录名
	 * @param descDirName 目标目录名
	 * @param coverlay    如果目标目录存在，是否覆盖
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copyDirectoryCover(String srcDirName,
											 String descDirName, boolean coverlay) {
		File srcDir = new File(srcDirName);
		// 判断源目录是否存在
		if (!srcDir.exists()) {
			log.debug("复制目录失败，源目录 " + srcDirName + " 不存在!");
			return false;
		}
		// 判断源目录是否是目录
		else if (!srcDir.isDirectory()) {
			log.debug("复制目录失败，" + srcDirName + " 不是一个目录!");
			return false;
		}
		// 如果目标文件夹名不以文件分隔符结尾，自动添加文件分隔符
		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		// 如果目标文件夹存在
		if (descDir.exists()) {
			if (coverlay) {
				// 允许覆盖目标目录
				log.debug("目标目录已存在，准备删除!");
				if (!FileUtils.delFile(descDirNames)) {
					log.debug("删除目录 " + descDirNames + " 失败!");
					return false;
				}
			} else {
				log.debug("目标目录复制失败，目标目录 " + descDirNames + " 已存在!");
				return false;
			}
		} else {
			// 创建目标目录
			log.debug("目标目录不存在，准备创建!");
			if (!descDir.mkdirs()) {
				log.debug("创建目标目录失败!");
				return false;
			}

		}

		boolean flag = true;
		// 列出源目录下的所有文件名和子目录名
		File[] files = srcDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 如果是一个单个文件，则直接复制
			if (files[i].isFile()) {
				flag = FileUtils.copyFile(files[i].getAbsolutePath(),
						descDirName + files[i].getName());
				// 如果拷贝文件失败，则退出循环
				if (!flag) {
					break;
				}
			}
			// 如果是子目录，则继续复制目录
			if (files[i].isDirectory()) {
				flag = FileUtils.copyDirectory(files[i]
						.getAbsolutePath(), descDirName + files[i].getName());
				// 如果拷贝目录失败，则退出循环
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			log.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 失败!");
			return false;
		}
		log.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 成功!");
		return true;

	}

	/**
	 * 
	 * 删除文件，可以删除单个文件或文件夹
	 * 
	 * @param fileName 被删除的文件名
	 * @return 如果删除成功，则返回true，否是返回false
	 */
	public static boolean delFile(String fileName) {
 		File file = new File(fileName);
		if (!file.exists()) {
			log.debug(fileName + " 文件不存在!");
			return true;
		} else {
			if (file.isFile()) {
				return FileUtils.deleteFile(fileName);
			} else {
				return FileUtils.deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 
	 * 删除单个文件
	 * 
	 * @param fileName 被删除的文件名
	 * @return 如果删除成功，则返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				log.debug("删除文件 " + fileName + " 成功!");
				return true;
			} else {
				log.debug("删除文件 " + fileName + " 失败!");
				return false;
			}
		} else {
			log.debug(fileName + " 文件不存在!");
			return true;
		}
	}

	/**
	 * 
	 * 删除目录及目录下的文件
	 * 
	 * @param dirName 被删除的目录所在的文件路径
	 * @return 如果目录删除成功，则返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dirName) {
		String dirNames = dirName;
		if (!dirNames.endsWith(File.separator)) {
			dirNames = dirNames + File.separator;
		}
		File dirFile = new File(dirNames);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			log.debug(dirNames + " 目录不存在!");
			return true;
		}
		boolean flag = true;
		// 列出全部文件及子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = FileUtils.deleteFile(files[i].getAbsolutePath());
				// 如果删除文件失败，则退出循环
				if (!flag) {
					break;
				}
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = FileUtils.deleteDirectory(files[i]
						.getAbsolutePath());
				// 如果删除子目录失败，则退出循环
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			log.debug("删除目录失败!");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			log.debug("删除目录 " + dirName + " 成功!");
			return true;
		} else {
			log.debug("删除目录 " + dirName + " 失败!");
			return false;
		}

	}

	/**
	 * 创建单个空白文件 <br>
	 * 文件存在时，不创建新的空白文件
	 *
	 * @param descFileName 文件名，包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createFile(String descFileName) {
		File file = new File(descFileName);
		if (file.exists()) {
			log.debug("文件 " + descFileName + " 已存在!");
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
			log.debug(descFileName + " 为目录，不能创建目录!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			// 如果文件所在的目录不存在，则创建目录
			if (!file.getParentFile().mkdirs()) {
				log.debug("创建文件所在的目录失败!");
				return false;
			}
		}

		// 创建文件
		try {
			if (file.createNewFile()) {
				log.debug(descFileName + " 文件创建成功!");
				return true;
			} else {
				log.debug(descFileName + " 文件创建失败!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(descFileName + " 文件创建失败!");
			return false;
		}

	}

	/**
	 * 创建目录
	 *
	 * @param dirPath 目录名
	 *                如果创建成功，则返回true，否则返回false
	 *                如果文件存在， 就不创建
	 */
	public static boolean createDirectory(String dirPath) {
		File file = new File(dirPath);
		Boolean result = true;
		try {
			file.mkdirs();
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	/**
	 * 写入文件
	 *
	 * @param
	 */
	public static void writeToFile(String fileName, String content, boolean append) {
		try {
			FileUtils.write(new File(fileName), content, "utf-8", append);
			log.debug("文件 " + fileName + " 写入成功!");
		} catch (IOException e) {
			log.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
		}
	}

	/**
	 * 写入文件
	 *
	 * @param
	 */
	public static void writeToFile(String fileName, String content, String encoding, boolean append) {
		try {
			FileUtils.write(new File(fileName), content, encoding, append);
			log.debug("文件 " + fileName + " 写入成功!");
		} catch (IOException e) {
			log.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
		}
	}


	/**
	 * 获取待压缩文件在ZIP文件中entry的名字，即相对于跟目录的相对路径名
	 *
	 * @param
	 * @param file entry文件名
	 * @return
	 */
	private static String getEntryName(String dirPath, File file) {
		String dirPaths = dirPath;
		if (!dirPaths.endsWith(File.separator)) {
			dirPaths = dirPaths + File.separator;
		}
		String filePath = file.getAbsolutePath();
		// 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储
		if (file.isDirectory()) {
			filePath += "/";
		}
		int index = filePath.indexOf(dirPaths);

		return filePath.substring(index + dirPaths.length());
	}


	/**
	 * 根据传递的新名字  重命名某个文件夹下的文件
	 *
	 * @param dir
	 * @param name
	 */
	public static void renameFile(String dir, String name) {

		File fileDir = new File(dir);
		for (File file : fileDir.listFiles()) {

			String fileName = file.getName();
			int dotIndex = fileName.indexOf(".");
			String prefix = fileName.substring(0, dotIndex + 1);
			String suffix = ".unityweb";

			if (fileName.endsWith(".unityweb")) {
				fileName = fileName.replace(prefix, name + Constants.FILE_CONNECT);
			}
			File newFile = new File(dir + File.separator + fileName);
			file.renameTo(newFile);

		}
	}

//	/**
//	 * 修复路径，将 \\ 或 / 等替换为 File.separator
//	 * @param path
//	 * @return
//	 */
//	public static String path(String path){
//		String p = StringUtils.replace(path, "\\", "/");
//		p = StringUtils.join(StringUtils.split(p, "/"), "/");
//		if (!StringUtils.startsWithAny(p, "/") && StringUtils.startsWithAny(path, "\\", "/")){
//			p += "/";
//		}
//		if (!StringUtils.endsWithAny(p, "/") && StringUtils.endsWithAny(path, "\\", "/")){
//			p = p + "/";
//		}
//		return p;
//	}


	/**
	 * 修改替换json文件部分内容
	 *
	 * @throws IOException
	 */
//	public static void replaceFileContent(String filePath, String folderName) throws IOException {
//		String jsonString = JsonUtils.readJsonFile(filePath);
//
//		JSONObject jsonObject = JSONObject.fromObject(jsonString);
//		//取出json对象对对应的value
//		String dataUrl = jsonObject.getString("dataUrl");
//		String asmCodeUrl = jsonObject.getString("asmCodeUrl");
//		String asmMemoryUrl = jsonObject.getString("asmMemoryUrl");
//		String asmFrameworkUrl = jsonObject.getString("asmFrameworkUrl");
//		//修改json文件对应的值
//		dataUrl = dataUrl.replace(dataUrl.substring(0, dataUrl.indexOf(".")), folderName);
//		asmCodeUrl = asmCodeUrl.replace(asmCodeUrl.substring(0, asmCodeUrl.indexOf(".")), folderName);
//		asmMemoryUrl = asmMemoryUrl.replace(asmMemoryUrl.substring(0, asmMemoryUrl.indexOf(".")), folderName);
//		asmFrameworkUrl = asmFrameworkUrl.replace(asmFrameworkUrl.substring(0, asmFrameworkUrl.indexOf(".")), folderName);
//		//修改jsonObject的值
//		jsonObject.put("dataUrl", dataUrl);
//		jsonObject.put("asmCodeUrl", asmCodeUrl);
//		jsonObject.put("asmMemoryUrl", asmMemoryUrl);
//		jsonObject.put("asmFrameworkUrl", asmFrameworkUrl);
//		//重新写入
//		jsonString = JSON.toJSONString(jsonObject);
//		JsonUtils.writeJsonFile(jsonString, filePath);
//
//	}filePath

//	/**  下载远程服务器文件
//	 * @param request
//	 * @param response
//	 * @param filePath  eg: planeModel\666\materialLibrary\1210\ExcelUtils.java
//	 * @param fileName  eg: ExcelUtils
//	 * @param extName   eg: java
//	 * @throws IOException
//	 */
//	public static void remoteFileDownload(HttpServletRequest request, HttpServletResponse response, @RequestParam("filePath") String filePath, @RequestParam("fileName") String fileName, @RequestParam("extName") String extName) throws IOException {
//		String serverPath = Global.getDataVaultUrl(request, "");
//		String fileSrc = serverPath + filePath;
//		fileSrc = fileSrc.replace("\\", "/");
//		int backslashIndex=fileSrc.lastIndexOf("/");
//		//进行转码, 防止中文名出错
//		fileSrc=fileSrc.substring(0,backslashIndex+1	)+  URLEncoder.encode( fileSrc.substring(backslashIndex+1), "utf-8");
//		//将 “+” 还原成空格。  万一原来文件有“+” 怎么办
//		fileSrc=fileSrc.replaceAll("\\+", "%20");
//		URL url = new URL(fileSrc);
//		BufferedInputStream in = null;
//		in = new BufferedInputStream(url.openStream());
//		response.reset();
//		response.setContentType("application/x-msdownload; charset=UTF-8");
//		response.setHeader("Content-Disposition",
//				"attachment;filename=\"" + URLEncoder.encode(fileName+"."+extName, "UTF-8") + "\"");
//		OutputStream out = response.getOutputStream();
//		byte[] content = new byte[1024];
//		int length = 0;
//		int i;
//		while ((i = in.read()) != -1) {
//			response.getOutputStream().write(i);
//		}
//		in.close();
//		response.getOutputStream().close();
//	}
	public static boolean checkFileExisted(String fileSrc) throws IOException {
		boolean ifExisted;
		//检测文件是否存在
		URL url = new URL(fileSrc);
		//打开请求连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		//result.put("pdf", "notReady");
		int responseCode = connection.getResponseCode();
		ifExisted = responseCode != 404;
		return ifExisted;


	}


	/***
	 * @Description 下载服务器文件
	 * @param fileSrc   完整文件路径， eg:  localhost:8081/a/file/planeModel\666\materialLibrary\1210\ExcelUtils.java
	 * @param response
	 * @return void
	 * @author baojian.yuan
	 * @date 2019/9/3
	 */
	public static void DownloadRemoteFile(String fileSrc, HttpServletResponse response) throws IOException {
		fileSrc = fileSrc.replace("\\", "/");
		int backslashIndex = fileSrc.lastIndexOf("/");
		int lastCommaIndex = fileSrc.lastIndexOf(".");
		String fileName = fileSrc.substring(backslashIndex + 1);
		String extName = fileSrc.substring(lastCommaIndex + 1);
		//进行转码, 防止中文名出错
		fileSrc = fileSrc.substring(0, backslashIndex + 1) + URLEncoder.encode(fileName, "utf-8");
		//将 “+” 还原成空格。  万一原来文件有“+” 怎么办
		fileSrc = fileSrc.replaceAll("\\+", "%20");
		URL url = new URL(fileSrc);
		BufferedInputStream in = null;
		in = new BufferedInputStream(url.openStream());
		response.reset();
		response.setContentType("application/x-msdownload; charset=UTF-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20") + "\"");
		OutputStream out = response.getOutputStream();
		byte[] content = new byte[1024];
		int length = 0;
		int i;
		while ((i = in.read()) != -1) {
			response.getOutputStream().write(i);
		}
		in.close();
		response.getOutputStream().close();
	}

	/**
	 * 把源文件夹下的文件拷贝到目标文件夹
	 * 递归
	 *
	 * @param folderPath 传递过来的目标文件夹地址
	 */
	public static void moveFileToDirectoryFor3D(String folderPath) throws IOException {
		File desc = new File(folderPath);
		File[] directoriesUnderDesc = getDirectoryList(folderPath);
		for (File src : directoriesUnderDesc) {
			FileUtils.copyDirectory(src, desc);
			FileUtils.delAll(src);
		}
		while (getDirectoryList(folderPath).length != Constants.ZERO) {
			moveFileToDirectoryFor3D(folderPath);
		}
	}

	/***
	 * @Description 获得某个文件夹下的所有文件夹对象
	 * @param folderPath
	 * @return java.io.File[]
	 * @author baojian.yuan
	 * @date 2019/9/17
	 */
	public static File[] getDirectoryList(String folderPath) {
		FileFilter directoryFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};
		File desc = new File(folderPath);
		File[] directoriesUnderDesc = desc.listFiles(directoryFilter);
		return directoriesUnderDesc;
	}

	/***
	 * @Description 判定某个文件夹下是否包含某个文件夹
	 * @param folderPath
	 * @param folderName
	 * @return java.lang.Boolean
	 * @author baojian.yuan
	 * @date 2019/9/17
	 */
	public static Boolean judgeDirectoryExists(String folderPath, String folderName) {
		boolean result = false;
		File[] directoriesUnderDesc = FileUtils.getDirectoryList(folderPath);
		for (File dir : directoriesUnderDesc) {
			//如果存在APP文件夹，解压到NEWAPP文件夹
			if (folderName.equals(dir.getName())) {
				result = true;
			}
		}
		return result;
	}

	/***  将上传的multiparFile类型的文件，写入到目标文件中
	 * @param multipartFile
	 * @param descFilePath
	 * @return void
	 * @author baojian.yuan
	 * @date 2019/11/22
	 */
	public static void multipartFileToFIle(MultipartFile multipartFile, String descFilePath) throws IOException {
		File descFile = new File(descFilePath);
		multipartFile.transferTo(descFile);
	}


	/***   删除所有文件文件夹， 包括自身
	 * @param pathOrFile  字符串类型/ 文件类型
	 * @return void
	 * @author baojian.yuan
	 * @date 2019/11/22
	 */
	public static <T> Boolean deleteAll(T pathOrFile) throws IOException {
		Boolean result = true;
		File file = null;
		if (pathOrFile instanceof String) {
			file = new File((String) pathOrFile);
		} else {
			file = (File) pathOrFile;
		}
		if (file.exists()) {
			if (!(result = file.delete())) {
				File[] subs = file.listFiles();
				for (int i = 0; i <= subs.length - 1; i++) {
					if (subs[i].isDirectory()) {
						deleteAll(subs[i]);
					}
					result = subs[i].delete();
				}
				result = file.delete();
			}
		}
		if (!result) {
			throw new IOException("无法删除:" + file.getName());
		}
		return result;
	}

	/** 通过response下载本地文件
	 * @param response
	 * @param path
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void  downloadLocalFile(HttpServletResponse response , String path) throws IOException, URISyntaxException {
		Path file= Paths.get(path);
		if(Files.exists(file)){
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.addHeader("Content-Disposition", "attachment; filename="+  URLEncoder.encode(FileNameUtils.getName(path), "UTF-8") );
			try
			{
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}



