package com.ybj.cbt.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

class FileUtilsTest {

  @Test
  void bufferInputStreamBufferOutputStream() {
    String src="C:\\Users\\baojian.yuan\\Desktop\\亚博发票\\发票.pdf";
    String desc="C:\\Users\\baojian.yuan\\Desktop\\desc\\1.pdf";
    FileUtils.bufferInputStreamBufferOutputStream(src,desc);
  }

  @Test
  public void testCopy() throws IOException {
    File desc=new File("C:\\Users\\baojian.yuan\\Desktop\\desc\\test.mp4");
    URL url=new URL("http://185.38.13.130//mp43/345003.mp4");
      FileUtils.copyURLToFile(url,desc );
  }

    @Test
    public void testCopyGif() throws IOException {
        File desc=new File("C:\\Users\\yuanbaojian\\Desktop\\dataTables.rowGroup.min.js");
        URL url=new URL("https://cdn.datatables.net/rowgroup/1.1.1/js/dataTables.rowGroup.min.js");
        FileUtils.copyURLToFile(url,desc );
    }

  @Test
  public void testMkdir(){
    String directoryPath="C:\\Users\\baojian.yuan\\Desktop\\dir";
    File directory=new File(directoryPath);
    directory.mkdirs();
  }

    @Test
    public void testCreateNewFile() throws IOException {
        String filePath="C:\\Users\\baojian.yuan\\Desktop\\newFile.txt";
        File newFile=new File(filePath);
//        newFile.createNewFile();
        System.out.println("newFile.getParent() = " + newFile.getParent());
    }

    @Test
    public void testInstantOf() throws IOException {
        String filePath="C:\\Users\\baojian.yuan\\Desktop\\newFile.txt";
        File newFile=new File(filePath);
//        newFile.createNewFile();
        System.out.println("newFile.getParent() = " + (filePath instanceof String));
    }



}