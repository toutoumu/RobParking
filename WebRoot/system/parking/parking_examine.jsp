<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>停车场审核</title>
<!-- 将公共资源和设置引用进来 -->
<%@include file="/common/common.jsp"%>
<script type="text/javascript" src="<%=path%>/system/parking/parking_examine.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true" style="border: none;">
  <div data-options="region:'north',title:'查询条件',split:false,collapsible:false" style="height: 70px; border: none;">
    <form title="查询条件" id="queryForm" method="post" style="margin: 5px 0;" novalidate="false">
      <span class="fitem">
        <label>省份</label>
        <input class="easyui-combobox"
          data-options="    
        valueField:'id',
        textField:'name',
        url:'/RobParking/RegionService/getByParent.do?id=0',    
        onSelect:function(rec){  
            var url = '/RobParking/RegionService/getByParent.do?id='+rec.id;    
            $('#city').combobox('clear');    
            $('#city').combobox('reload', url);    
        },
        onLoadSuccess: function (val) {  
             $(this).combobox('select', val[0]['id']);
          }"
          required="true" />
      </span>
      <span class="fitem">
        <label>城市</label>
        <input id="city" name="regionId" class="easyui-combobox"
          data-options="valueField:'id',textField:'name',onSelect:citySelect" required="true" />
      </span>

      <!--  0.未知, 1.未审核,2.已审核 -->
      <span class="fitem">
        <label>审核状态&nbsp;</label> <select id="cc" class="easyui-combobox" name="passState" style="width: 200px;">
          <option value="0">全部</option>
          <option value="1">未审核</option>
          <option value="2">已审核</option>
        </select>
      </span>
      <a id="btnQuery" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
    </form>
  </div>
  <div data-options="region:'center',split:false" style="height: 100px; border: none;">
    <div id="toolbar">
      <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newParking()">添加</a> -->
      <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editParking()">编辑</a> -->
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
        onclick="showExamine()">查看并审核</a>
    </div>
    <table id="dg" title="停车场列表" class="easyui-datagrid" style="width: 100%; height: 400px; border: none;"
      toolbar="#toolbar" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true" fit="true">
      <thead>
        <tr>
          <!-- 显示停车场名称，停车场的地址，所在城市，总车位数，可用车位数 -->
          <th field="name" width="50">停车场名称</th>
          <th field="address" width="50">地址</th>
          <th field="region" width="50">城市</th>
          <th field="total" width="50">总车位数</th>
          <th field="remain" width="50">可用车位数</th>
          <th field="passState" formatter="format" width="50">审核状态</th>
        </tr>
      </thead>
    </table>
  </div>

  <!-- 编辑对话框 -->
  <div id="dlgEdit" class="easyui-dialog" style="width: 400px; height: 300px; padding: 10px 20px" closed="true"
    buttons="#dlgEdit-buttons" modal="true">
    <div class="ftitle">车库信息</div>
    <form id="editForm" method="post" novalidate>
      <div class="fitem">
        <label>停车场名称:</label>
        <input name="name" class="easyui-validatebox textbox" required="true" />
      </div>
      <div class="fitem">
        <label>城市:</label>
        <input name="region" class="easyui-validatebox textbox" required="true" />
      </div>
      <div class="fitem">
        <label>地址:</label>
        <input name="address" class="easyui-validatebox textbox" required="true" />
      </div>
      <div class="fitem">
        <label>车位总数:</label>
        <input name="total" class="easyui-numberbox" data-options="min:1,precision:0" required="true" />
      </div>
      <div class="fitem">
        <label>管理员名称:</label>
        <input name="mamager" class="easyui-validatebox textbox" required="true" />
      </div>
      <div class="fitem">
        <label>联系方式:</label>
        <input name="phoneNumber" class="easyui-validatebox textbox" required="true" />
      </div>
    </form>
  </div>
  <div id="dlgEdit-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="examineParking()"
      style="width: 90px">通过审核</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
      onclick="javascript:$('#dlgEdit').dialog('close')" style="width: 90px">取消</a>
  </div>
</body>
</html>