package com.system.backend.facedatabase;

import java.io.File;

public class Functions {
	public static boolean deleteFile(File dirFile) {
	    // 如果dir对应的文件不存在，则退出
	    if (!dirFile.exists()) {
	        return false;
	    }

	    if (dirFile.isFile()) {
	        return dirFile.delete();
	    } 
	    else {
	        for (File file : dirFile.listFiles()) {
	            deleteFile(file);
	        }
	    }
	    return dirFile.delete();
	}
}
