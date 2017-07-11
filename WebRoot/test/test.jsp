<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>登录页面</title>
<%@include file="/common/common.jsp"%>
<script type="text/javaScript" src="test.js"></script>
</head>

<body>
  <form id="form" action="#" method="post">
    <ul>
      <li>
        姓 名：
        <input class="easyui-textbox" name="account" />
      </li>
      <li>
        密 码：
        <input class="easyui-textbox" name="a.password" />
      </li>
      <li>
        验证码：
        <input class="easyui-textbox" name="a.validateCode" />
        &nbsp;&nbsp;
        <img id="validateCodeImg" src="<%=basePath%>/LoginController/validateCode.do" />
        <a href="#" onclick="reloadValidateCode()">看不清？</a>
      </li>
    </ul>
  </form>
  <span class="fitem">
    <label>城市</label>
    <input id="city" class="easyui-combobox" data-options="valueField:'id',textField:'name'" required="true">
  </span>
  <a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">JsonRequest</a>
  <a id="btn2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">easyui</a>
  <a id="btn3" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">easyui</a>
  <a id="btn4" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">easyui</a>

</body>
</html>
