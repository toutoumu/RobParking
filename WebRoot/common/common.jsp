<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 这里存放所有公共的js和css -->
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="找车位">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="/Housekeeping/mobile/favicon.ico">

<!-- 所有样式写在这里 -->
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/common.css">

<!-- 所有公用js文件写在这里 -->
<!-- jQuery基础包 -->
<script type="text/javascript" src="<%=path%>/resources/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/jquery-easyui/jquery.json.js"></script>
<script type="text/javascript" src="<%=path%>/resources/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- 对jQuery的扩展 -->
<script type="text/javascript" src="<%=path%>/resources/jquery-easyui/jquery.extends.js"></script>
<script type="text/javascript">
<!-- 定义路径-->
	
<%="var path ='" + path + "';"%>
	
</script>

