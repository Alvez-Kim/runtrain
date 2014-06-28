package pac.testcase.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

public class TestZipUtil {

	// public static void main(String[] args){
	// try {
	// //new ZipUtil().decompressZip(new
	// File("d://img.zip"),"img/pic20140626.jpg","d://");
	// new ZipUtil().decompressZip(new File("d://img.zip"),"flight.log","d://");
	// //new File("d://flight.log").delete();
	// //ZipUtil.compress(new File("D://测试压缩文件"),new File("d://img.zip"));
	// // ZipUtil.compress(new File[]{new
	// File("F:/testZIP/testzip.txt"),new File("d://ftp"),new
	// File("e://ftp")},new File("d://压缩文件.zip"));
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public static void compress(File[] files, File zipFile) throws IOException {
//		if (CollectionUtil.isEmpty(files)) {
//			return;
//		}
		ZipArchiveOutputStream out = new ZipArchiveOutputStream(zipFile);
		out.setUseZip64(Zip64Mode.AsNeeded);
		for (File file : files) {
			if (file == null) {
				continue;
			}
			compressOneFile(file, out, "");
		}
		if (out != null) {
			out.close();
		}
	}

	public static void compress(File srcFile, File destFile) throws IOException {
		ZipArchiveOutputStream out = null;
		try {
			out = new ZipArchiveOutputStream(new BufferedOutputStream(
					new FileOutputStream(destFile), 1024));
			compressOneFile(srcFile, out, "");
		} finally {
			out.close();
		}
	}

	private static void compressOneFile(File srcFile,
			ZipArchiveOutputStream out, String dir) throws IOException {
		if (srcFile.isDirectory()) {
			ZipArchiveEntry entry = new ZipArchiveEntry(dir + srcFile.getName()
					+ "/");
			out.putArchiveEntry(entry);
			out.closeArchiveEntry();
			String[] subFiles = srcFile.list();
			for (String subFile : subFiles) {
				compressOneFile(new File(srcFile.getPath() + "/" + subFile),
						out, (dir + srcFile.getName() + "/"));
			}
		} else {
			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(srcFile));
				ZipArchiveEntry entry = new ZipArchiveEntry(srcFile, dir
						+ srcFile.getName());
				out.putArchiveEntry(entry);
				IOUtils.copy(is, out);
				out.closeArchiveEntry();
			} finally {
				if (is != null)
					is.close();
			}
		}
	}

	public void decompressZip(File zipFile, String dir) throws IOException {
		ZipFile zf = new ZipFile(zipFile);
		try {
			for (Enumeration<ZipArchiveEntry> entries = zf.getEntries(); entries
					.hasMoreElements();) {
				ZipArchiveEntry ze = entries.nextElement();
				File targetFile = new File(dir, ze.getName());
				if (ze.getName().lastIndexOf("/") == (ze.getName().length() - 1)) {
					continue;
				}
				if (!targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}

				InputStream i = zf.getInputStream(ze);
				OutputStream o = null;
				try {
					o = new FileOutputStream(targetFile);
					IOUtils.copy(i, o);
				} finally {
					if (i != null) {
						i.close();
					}
					if (o != null) {
						o.close();
					}
				}
			}
		} finally {
			zf.close();
		}
	}

	public void decompressZip(File zipFile, String fileName, String dir)
			throws IOException {
		File targetFile = new File(dir, fileName);
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}

		ZipFile zf = new ZipFile(zipFile);
		Enumeration<ZipArchiveEntry> zips = zf.getEntries();
		ZipArchiveEntry zip = null;
		while (zips.hasMoreElements()) {
			zip = zips.nextElement();
			if (fileName.equals(zip.getName())) {
				OutputStream o = null;
				InputStream i = zf.getInputStream(zip);
				try {
					o = new FileOutputStream(targetFile);
					IOUtils.copy(i, o);
				} finally {
					if (i != null) {
						i.close();
					}
					if (o != null) {
						o.close();
					}
				}
			}
		}
	}

	public ZipArchiveEntry readZip(File zipFile, String fileName)
			throws IOException {
		ZipFile zf = new ZipFile(zipFile);
		Enumeration<ZipArchiveEntry> zips = zf.getEntries();
		ZipArchiveEntry zip = null;
		while (zips.hasMoreElements()) {
			zip = zips.nextElement();
			if (fileName.equals(zip.getName())) {
				return zip;
			}
		}
		
		return null;
	}

	public Enumeration<ZipArchiveEntry> readZip(File zipFile)
			throws IOException {
		ZipFile zf = new ZipFile(zipFile);
		Enumeration<ZipArchiveEntry> zips = zf.getEntries();
		zf.close();
		return zips;
	}
}