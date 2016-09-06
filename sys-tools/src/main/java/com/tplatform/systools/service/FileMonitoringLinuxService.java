package com.tplatform.systools.service;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyListener;
import net.contentobjects.jnotify.macosx.JNotifyAdapterMacOSX;
import org.tplatform.framework.log.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;

/**
 * linux系统文件检测
 * Created by tianyi on 16/9/6.
 */
public class FileMonitoringLinuxService {
  /**
   * jnotify动态库 - 32位
   */
  static final String NATIVE_LIBRARIES_32BIT = "/sys-tools/src/main/resources/native/osx/";
  /**
   * jnotify动态库 - 64位
   */
  static final String NATIVE_LIBRARIES_64BIT = "/sys-tools/src/main/resources/native/osx/";

  public static void main(String[] args) {
    Logger.d("-----------Jnotify test ---------");

    Properties sysProps = System.getProperties();
    String osArch = (String) sysProps.get("os.arch");
    String osName = (String) sysProps.get("os.name");
    String userDir = (String) sysProps.getProperty("user.dir");
    Logger.d("os.arch: " + osArch);
    Logger.d("os.name: " + osName);
    Logger.d("userDir: " + userDir);
    Logger.d("java.class.path: " + sysProps.get("java.class.path"));

    // 直接调用Jnotify时， 会发生异常：java.lang.UnsatisfiedLinkError: no jnotify_64bit in java.library.path
    // 这是由于Jnotify使用JNI技术来加载dll文件，如果在类路径下没有发现相应的文件，就会抛出此异常。
    // 因此可以通过指定程序的启动参数: java -Djava.library.path=/path/to/dll，
    // 或者是通过修改JVM运行时的系统变量的方式来指定dll文件的路径，如下：

    // 判断系统是32bit还是64bit，决定调用对应的dll文件
    String jnotifyDir = NATIVE_LIBRARIES_64BIT;
    if (!osArch.contains("64")) {
      jnotifyDir = NATIVE_LIBRARIES_32BIT;
    }
    Logger.d("jnotifyDir: " + jnotifyDir);

    try {
      // 获取目录路径
      String pathToAdd = userDir + jnotifyDir ;
      boolean isAdded = false;
      final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
      usrPathsField.setAccessible(true);
      final String[] paths = (String[]) usrPathsField.get(null);
      Logger.d("usr_paths: " + Arrays.toString(paths));
      for (String path : paths) {
        if (path.equals(pathToAdd)) {
          isAdded  = true;
          break;
        }
      }
      if (!isAdded) {
        final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
        newPaths[newPaths.length - 1] = pathToAdd;
        usrPathsField.set(null, newPaths);
      }

      Logger.d("java.library.path: " + System.getProperty("java.library.path"));
      Logger.d("usr_paths: " + Arrays.toString((String[]) usrPathsField.get(null)));
      usrPathsField.setAccessible(false);
      Logger.d("类路径加载完成");

      JNotifyAdapterMacOSX jNotifyAdapterMacOSX = new JNotifyAdapterMacOSX();
      // 监听F盘下的文件事件
      jNotifyAdapterMacOSX.addWatch("/Users/tianyi/aa.txt", JNotify.FILE_ANY, true, new JNotifyListener() {
        @Override
        public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
          Logger.d("wd = " + wd + ", rootPath = " + rootPath);
          Logger.d("oldName = " + oldName + ", newName = " + newName);
        }
        @Override
        public void fileModified(int wd, String rootPath, String fileName) {
          Logger.d("fileModified");
        }
        @Override
        public void fileDeleted(int wd, String rootPath, String fileName) {
          Logger.d("fileDeleted");
        }
        @Override
        public void fileCreated(int wd, String rootPath, String fileName) {
          Logger.d("fileDeleted");
        }
      });
      Thread.sleep(1000 * 60 * 3);
    }catch (Exception e) {
      e.printStackTrace();
    }

  }
}
