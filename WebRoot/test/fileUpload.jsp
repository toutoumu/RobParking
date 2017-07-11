<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>文件上传</title>
</head>
<body>
  <div dojoType="dijit.TitlePane" title="图片上传" style="margin: 0px; padding: 0px;">
    <form enctype="multipart/form-data" action="/RobParking/FileUpload.do" method="post">
      <input id="file" type="file" name="file1" />
      <input type="submit" value="上传" />
    </form>
  </div>
</body>
</html>
