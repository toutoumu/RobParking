package com.dsfy.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsfy.entity.http.JsonResponse;
import com.dsfy.exception.JsonException;

@Controller
public class FileUpload {

	private static DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	// 内存缓存大小
	private static final int yourMaxMemorySize = 1024 * 1024 * 30;

	// 上传最大文件大小
	private static final long yourMaxRequestSize = 1024 * 1024 * 30;

	// 文件上传目录
	private static final String dir = "/upload";

	@ResponseBody
	@RequestMapping(value = "/FileUpload.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public JsonResponse doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		JsonResponse jsonResponse = new JsonResponse();
		List<String> fileNames = new ArrayList<String>();
		// 1. 检查请求是否含有上传文件
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new JsonException("只能处理multipart/form-data");
		}

		// 2. 设置保存上传文件的目录
		String uploadDir = request.getServletContext().getRealPath("/upload");
		if (uploadDir == null) {
			throw new JsonException("无法访问存储目录");
		}
		File fUploadDir = new File(uploadDir);
		if (!fUploadDir.exists()) {
			if (!fUploadDir.mkdir()) {
				throw new JsonException("无法创建存储目录");
			}
		}

		// 3. 设置临时文件目录
		String tempDirectory = request.getServletContext().getRealPath(dir);
		if (tempDirectory == null) {
			throw new JsonException("无法访问临时存储目录");
		}
		File fTempDirectory = new File(uploadDir);
		if (!fTempDirectory.exists()) {
			if (!fUploadDir.mkdir()) {
				throw new JsonException("无法创建临时存储目录");
			}
		}

		// 4. 解析上传数据
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置缓存和缓存目录
		factory.setSizeThreshold(yourMaxMemorySize);
		factory.setRepository(fTempDirectory);

		// 新建一个文件上传句柄
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置最大上传大小
		upload.setSizeMax(yourMaxRequestSize);
		//如果这个文件真的很大，你可能会希望向用户报告到底传了多少到服务端，让用户了解上传的过程
		upload.setProgressListener(new FileUploadProgressListener());

		List<?> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			throw new JsonException("解析请求数据失败", e1);
		}

		//一旦解析完成，你需要进一步处理item的列表。
		Iterator<?> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			//如果是表单数据
			if (item.isFormField()) {
				// processFormField(item);
			}//如果是文件数据
			else {
				try {
					int dotIndex = item.getName().lastIndexOf(".");
					String suffix = "";
					if (dotIndex != -1) {
						suffix = "." + item.getName().substring(dotIndex + 1);
					}
					String fileName = format.format(new Date()) + (int) ((Math.random() * 9 + 1) * 100000) + suffix;
					fileNames.add(fileName);
					File uploadedFile = new File(uploadDir, fileName);
					item.write(uploadedFile);
					item.delete();
				} catch (Exception e) {
					throw new JsonException("文件上传失败", e);
				}
			}
		}
		String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/" + dir;
		jsonResponse.setSuccess(true);
		jsonResponse.setParameter("uploadDir", uploadDir);
		jsonResponse.setParameter("url", path);
		jsonResponse.setData("files", fileNames);
		jsonResponse.setMessage("文件上传成功");
		return jsonResponse;
	}

	class FileUploadProgressListener implements ProgressListener {
		@Override
		public void update(long pBytesRead, long pContentLength, int pItems) {
			System.out.println("We are currently reading item " + pItems);
			if (pContentLength == -1) {
				System.out.println("So far, " + pBytesRead + " bytes have been read.");
			} else {
				System.out.println("So far, " + pBytesRead + " of " + pContentLength + " bytes have been read.");
			}
		}
	}

	/*
	 * 将一段字符串追加到一个结果字符串中.如果结果字符串的初始内容不为空,在追加当前这段字符串之前先追加一个
	 * (,).再组合sql语句的查询语句是,经常要用到类似的方法,第一条件前没有"and"而后面的条件前都有
	 * and做连词,如果没有选择第一个条件,第二个条件就变成第一个,一次类推
	 */
	@SuppressWarnings("unused")
	private void makeUplist(StringBuffer result, String fragment) {
		if (fragment != null) {
			if (result.length() != 0) {
				result.append(",");
			}
			result.append(fragment);
		}
	}

}
