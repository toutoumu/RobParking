<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>登录页面</title>
<%@include file="/common/common.jsp"%>
<script type="text/javascript">
	function reloadValidateCode() {
<%="var basePath ='" + basePath + "'"%>
	var src = path + "/LoginController/validateCode.do?data=" + new Date()
				+ Math.floor(Math.random() * 24);
		$("#validateCodeImg").attr("src", src);
		return false;
	}
</script>
</head>

<body>
  <form action="<%=basePath%>/login.do" method="post">
    <ul>
      <li>
        姓 名：
        <input type="text" name="account" />
      </li>
      <li>
        密 码：
        <input type="text" name="password" />
      </li>
      <li>
        验证码：
        <input type="text" name="validateCode" />
        &nbsp;&nbsp;
        <img id="validateCodeImg" src="<%=basePath%>/LoginController/validateCode.do" />
        &nbsp;&nbsp;
        <a href="#" onclick="reloadValidateCode()">看不清？</a>
      </li>
      <li>
        <input type="submit" value="确认" />
      </li>
    </ul>
  </form>
  <a href="#" onclick="reloadValidateCode()">看不清？</a>
</body>
</html>
