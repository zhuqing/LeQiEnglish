package com.leqienglish.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;

import java.io.OutputStream;
import java.util.Enumeration;
//import org.apache.tools.zip.ZipEntry;
//import org.apache.tools.zip.ZipFile;
/**
 * 处理文件的工具类
 * @author guona
 */
public class FileUtil {

    /**
     * 文件存放的路径
     */
    private static String direction;
    /**
     * 当前写文件的路径
     */
    private static String currentPath;
    /**
     * 当前文件写的次数
     */
    private static int writeCount = 0;
    /**
     * 文件大小的限制 7MB
     */
    private static int fileSizeLimit = 1024 * 1024 * 7;
    /**
     * 写文件次数的限制
     */
    private static int fileWriteCountLimit = 1000;

    public final static String PROTECT_NAME = "duoduoroom";

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static void readFileByBytes(String fileName) {
        File file = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            FileUtil.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static String getRadomName(String fileName) {
        int index = fileName.lastIndexOf(".");
        return UUID.randomUUID().toString()
                + fileName.substring(index, fileName.length());

    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
//public static void unZip(String zipfile, String destDir) {
//		destDir = destDir.endsWith("//") ? destDir : destDir + "//";
//
//		byte b[] = new byte[1024];
//
//		int length;
//		ZipFile zipFile;
//
//		try {
//
//			zipFile = new ZipFile(new File(zipfile));
//
//			Enumeration enumeration = zipFile.getEntries();
//
//			ZipEntry zipEntry = null;
//			while (enumeration.hasMoreElements()) {
//
//				zipEntry = (ZipEntry) enumeration.nextElement();
//
//				File loadFile = new File(destDir + zipEntry.getName());
//
//				if (zipEntry.isDirectory()) {
//
//					// 这段都可以不要，因为每次都貌似从最底层开始遍历的
//
//					loadFile.mkdirs();
//
//				} else {
//
//					if (!loadFile.getParentFile().exists())
//
//						loadFile.getParentFile().mkdirs();
//
//					OutputStream outputStream = new FileOutputStream(loadFile);
//
//					InputStream inputStream = zipFile.getInputStream(zipEntry);
//
//					while ((length = inputStream.read(b)) > 0)
//
//						outputStream.write(b, 0, length);
//
//				}
//
//			}
//
//			System.out.println(" 文件解压成功 ");
//
//		} catch (IOException e) {
//
//			// TODO Auto-generated catch block
//
//			e.printStackTrace();
//
//		}
//
//	}
    /**
     * 随机读取文件内容
     */
    public static void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            System.out.println("随机读取一段文件内容：");
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 显示输入流中还剩的字节数
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件的大小
     *
     * @param path
     * @return
     */
    public static long getFileSize(String path) {
        if (path == null) {
            return -1L;
        }

        try {
            File file = new File(path);
            return file.length();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return -1L;
    }

    /**
     * 获取文件的大小
     *
     * @param path
     * @return
     */
    private static int fileSize(String path) {
        File file = new File(path);
        try {
            FileInputStream files = new FileInputStream(file);
            return files.available();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取文件的路径
     *
     * @return
     * @throws IOException
     */
    public static String getPath() throws IOException {

//        if (FileUtil.currentPath == null) {
//            FileUtil.currentPath = createNewFile();
//            FileUtil.writeCount = 0;
//
//            return FileUtil.currentPath;
//        }
//
//        if (FileUtil.writeCount < FileUtil.fileWriteCountLimit) {
//            return FileUtil.currentPath;
//        }
//
//        if (fileSize(currentPath) > FileUtil.fileSizeLimit) {
//            FileUtil.currentPath = createNewFile();
//            FileUtil.writeCount = 0;
//        }
        return createNewFile();
    }

    /**
     * 创建新文件并返回路径
     *
     * @return
     * @throws IOException
     */
    private static String createNewFile() throws IOException {

        String filePath = getDirPath() + UUID.randomUUID().toString();
        //System.out.println(filePath + "txt");
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        return filePath;
    }

    /**
     * 获取创建文件夹的路径
     *
     * @return
     */
    public static String getDirPath() {

        if (direction == null) {// 获取当前环境下的路径
            String fule = FileUtil.class.getClassLoader().getResource("/")
                    .toString();
            int beginIndex = fule.indexOf("/");
            int endIndex = fule.indexOf("WEB-INF");
            direction = fule.substring(beginIndex, endIndex) + "file/";
            System.out.println(direction + "createNewFile direction==null");
        }

        String dirPath = direction + createSubDir();
        System.out.println(dirPath);
        File file = new File(dirPath);
        file.mkdirs();

        return dirPath;
    }

    /**
     * 创建当前日期的文件夹名称
     *
     * @return
     */
    private static String createSubDir() {
        String dir = "";
        dir += Calendar.getInstance().get(Calendar.YEAR);
        dir += (Calendar.getInstance().get(Calendar.MONTH) + 1);
        dir += Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/";
        System.out.println("dir " + dir);
        return dir;
    }

    /**
     * 根据文件路径读取文件
     *
     * @param filePath
     * @param startIndex
     * @param length
     * @return
     * @throws IOException
     */
    public static String readFile(String filePath, int startIndex, Long length)
            throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return "";
        }
        FileChannel fc = new FileInputStream(file).getChannel();
        ByteBuffer byb = ByteBuffer.allocate(length.intValue());
        fc.read(byb, startIndex);
        fc.close();

        String content = new String(byb.array());
        //int startindex = content.indexOf(":");
        return content;
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFile(String filePath) {

        BufferedReader reader = null;
        StringBuffer sbuffer = new StringBuffer();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filePath), "UTF-8"));
            String tempString = null;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                // System.out.println("line " + line + ": " + tempString);
                sbuffer.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return sbuffer.toString();
    }

    /**
     * 将流保存到指定的文件
     *
     * @param is
     * @param filePath
     * @return
     * @throws IOException
     */
    public static boolean saveFile(InputStream is, String filePath) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(filePath);

            byte[] data = new byte[1024];
            int len = 0;
            while ((len = is.read(data)) != -1) {
                fos.write(data, 0, len);
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Properties getProperties(String fileName) throws Exception {
        String filePath = FileUtil.class.getResource("/").getPath();
        System.err.println(filePath);
       // filePath = filePath.substring(6, filePath.length());
        System.err.println(filePath);
        InputStream inputStream = new FileInputStream(filePath + "/" + fileName);
        Properties properties = new Properties();
        properties.load(inputStream);
        inputStream.close(); //关闭流  
        return properties;
    }

    /**
     *
     * @return
     */
    public static String removeUnChar(String word) {
        if (word == null) {
            return null;
        }
        word = word.toLowerCase();
        // if(word.matches("^[a-z]")){
        word = word.replaceAll("[^a-z]", "");
        // }
        return word;
    }

    /**
     * 获取相对路径
     *
     * @param filepath
     * @return
     */
    public static String getRelationPath(String filepath) {
        int index = filepath.indexOf(PROTECT_NAME);
        if (index < 0) {
            return filepath;
        }

        String relationPath = filepath.substring(index + PROTECT_NAME.length() + 1);

        return relationPath;

    }

    public static void main(String[] args) {

        try {
            //	createNewFile();
            //System.out.println(FileUtil.getRadomName("asdfadf.pig"));
//			Content content =new Content();
//			content.setId(0L);
//			content.setIconPath("iconPath");
//			String json = EntityAndJSON.toJSON(content);
//			Object jsonO = EntityAndJSON.json2Object(json, Content.class);
            //System.out.println(JSONUtil.property2Json("url.properties"));
            // System.out.println(getRelationPath("F:\\1tomcat-6.0.18_zjh\\webapps\\duoduoroom\\WEB-INF\\lib\\javaee.jar"));
            //JSONUtil.property2Json("url.properties");
            //System.out.println(json);
            System.out.println(removeUnChar("'''word..&"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
