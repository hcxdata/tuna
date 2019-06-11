package com.hobin.crm.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class FileHelper {

	public static String bashPath() {
		String user_dir = System.getProperty("user.dir");
		return user_dir.substring(0, user_dir.lastIndexOf(File.separator));
	}

	private static String getEntryName(File source, File file) throws IOException {
		int index = source.getAbsolutePath().length() + 1;
		String path = file.getCanonicalPath();
		return path.substring(index);
	}

	public static void addFilesToZip(File source, File destination) throws IOException, ArchiveException {
		OutputStream archiveStream = new FileOutputStream(destination);
		ArchiveOutputStream archive = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP,
				archiveStream);
		Collection<File> fileList = FileUtils.listFiles(source, null, true);
		for (File file : fileList) {
			String entryName = getEntryName(source, file);
			ZipArchiveEntry entry = new ZipArchiveEntry(entryName);
			archive.putArchiveEntry(entry);
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
			IOUtils.copy(input, archive);
			input.close();
			archive.closeArchiveEntry();
		}
		archive.finish();
		archiveStream.close();
	}

	public static void main(String[] args) throws Exception {
		File source = new File("/Users/icanon/Documents/workspace/wcp_demo/aho-corasick/src");
		File destination = new File("/Users/icanon/Documents/workspace/wcp_demo/aho-corasick/resources.zip");
		destination.delete();
		addFilesToZip(source, destination);
	}

}
