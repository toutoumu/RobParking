<%@page import="com.dsfy.util.JspUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>用户管理</title>
<!-- 将公共资源和设置引用进来 -->
<%@include file="/common/common.jsp"%>

<script type="text/javascript" src="<%=path%>/system/user/user_manage.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true" style="border: none;">
  <div data-options="region:'north',title:'查询条件',split:false,collapsible:false" style="height: 70px; border: none;">
    <form title="查询条件" id="queryForm" class="form" method="post" novalidate="false">
      <!--  1.管理员,2.用户,3.用户 -->
      <span class="fitem">
        <label>用户类别</label>
        <input id="category1" class="easyui-combobox" name="category" readonly="readonly"
          data-options="valueField: 'label', textField: 'value'" />
      </span>
      <a id="btnQuery" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'"
        onclick="loadUser()">查询</a>
    </form>
  </div>
  <div data-options="region:'center',split:false" style="height: 100px; border: none;">
    <div id="toolbar">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">添加</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
        onclick="disableUser()">禁用</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
        onclick="enableUser()">启用</a>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true"
        onclick="loadUser()">刷新</a>
    </div>
    <table id="dg" title="用户列表" class="easyui-datagrid" style="width: 700px; height: 250px" url="get_users.php"
      toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true" fit="true">
      <thead>
        <tr>
          <th field="nickname" width="50">昵称</th>
          <th field="account" width="50">帐号</th>
          <th field="category" width="50">用户类别</th>
          <th field="locked" width="50">是否锁定</th>
        </tr>
      </thead>
    </table>
  </div>


  <!-- 添加对话框 -->
  <div id="dlgAdd" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
    buttons="#dlgAdd-buttons" modal="true">
    <div class="ftitle">用户信息</div>
    <form id="addForm" class="form" method="post" novalidate="false">
      <div class="fitem">
        <label>帐号</label>
        <input name="account" id="account" class="easyui-validatebox textbox" required="true" />
      </div>
      <div class="fitem">
        <label>昵称</label>
        <input name="nickname" class="easyui-validatebox textbox" required="true" />
      </div>
      <!--  1.管理员,2.用户,3.代理商 -->
      <span class="fitem">
        <label>用户类别</label>
        <input id="category" class="easyui-combobox" name="category" readonly="readonly"
          data-options="valueField: 'label', textField: 'value'" />
      </span>
    </form>
  </div>
  <div id="dlgAdd-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()"
      style="width: 90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
      onclick="javascript:$('#dlgAdd').dialog('close')" style="width: 90px">Cancel</a>
  </div>


  <!-- 编辑话框 -->
  <div id="dlgEdit" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
    buttons="#dlgEdit-buttons" modal="true">
    <div class="ftitle">用户信息</div>
    <form id="editForm" class="form" method="post" novalidate="false">
      <div class="fitem">
        <label>帐号</label>
        <input name="account" class="easyui-validatebox textbox" required="true" />
      </div>

      <div class="fitem">
        <label>昵称</label>
        <input name="nickname" class="easyui-validatebox textbox" required="true" invalidMessage="有效长度8-20"
          missingMessage="不能为空" />
      </div>

      <!--  1.管理员,2.用户,3.代理商 -->
      <span class="fitem">
        <label>用户类别</label>
        <input id="category2" class="easyui-combobox" name="category" readonly="readonly"
          data-options="valueField: 'label', textField: 'value'" />
      </span>

    </form>
  </div>
  <div id="dlgEdit-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveEditUser()"
      style="width: 90px">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
      onclick="javascript:$('#dlgEdit').dialog('close')" style="width: 90px">Cancel</a>
  </div>

</body>
</html>