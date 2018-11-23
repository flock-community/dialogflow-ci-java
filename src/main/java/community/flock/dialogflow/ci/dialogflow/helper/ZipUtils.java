package community.flock.dialogflow.ci.dialogflow.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {    
	public static void unZip(String dir, byte[] compressedData) throws IOException {
		File file = new File(dir);
		file.mkdirs();
		
	    ZipInputStream zi = null;
	    try {
	        zi = new ZipInputStream(new ByteArrayInputStream(compressedData));
	        ZipEntry zipEntry = null;
	        while ((zipEntry = zi.getNextEntry()) != null) {
	        	Path path = Paths.get(file.getAbsolutePath(), zipEntry.getName().replaceAll(" ", "\\ "));
	        	Files.createDirectories(path.getParent());
	        	Files.copy(zi, path, StandardCopyOption.REPLACE_EXISTING);
	        }
	    } finally {
	        if (zi != null) {
	            zi.close();
	        }
	    }
	}
	
	public static List<ZipEntry> getZipEntries(byte[] compressedData) throws IOException {
		List<ZipEntry> entries = new ArrayList<ZipEntry>();
		ZipInputStream zi = null;
	    try {
	        zi = new ZipInputStream(new ByteArrayInputStream(compressedData));
	        ZipEntry zipEntry = null;
	        while ((zipEntry = zi.getNextEntry()) != null) {
	        	entries.add(zipEntry);
	        }
	    } finally {
	        if (zi != null) {
	            zi.close();
	        }
	    }
	    return entries;
	}

    public static byte[] zip(String dir) {
    	List<String> fileList = generateFileList(dir, new File(dir));
    	
        byte[] buffer = new byte[1024];
        String source = new File(dir).getName();
        ByteArrayOutputStream bos = null;
        ZipOutputStream zos = null;
        try {
            bos = new ByteArrayOutputStream();
            zos = new ZipOutputStream(bos);
            FileInputStream in = null;

            for (String file: fileList) {
                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(dir + File.separator + file);
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }

            zos.closeEntry();
            System.out.println("Folder successfully compressed");

            return bos.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    
    private static List<String> generateFileList(String dir, File node) {
    	List <String> fileList = new ArrayList <String>();
        // add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(dir, node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename: subNote) {
                fileList.addAll(generateFileList(dir, new File(node, filename)));
            }
        }
        return fileList;
    }

    private static String generateZipEntry(String dir, String file) {
        return file.substring(dir.length() + 1, file.length());
    }
}