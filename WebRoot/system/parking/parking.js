$(document).ready(function() {
	$('#btnQuery').click(loadParking);
});

/**
 * 用来缓存当前正在编辑的数据
 */
var currentRow = null;

/**
 * 加载停车场数据
 */
function loadParking() {
	var isValid = $('#queryForm').form('validate');
	if (!isValid) {
		$.messager.alert('温馨提示', '请选择城市信息！', 'info');
		return;
	}
	// 获取下拉框的值
	var regionId = $('#city').combobox('getValue');
	$.postRequest({
		url : "/RobParking/ParkingController/byCondition.do",
		data : {
			data : {
				parking : $('#queryForm').formToObject()
			}
		},
		success : function(data, parameter) {
			// 绑定数据
			$('#dg').datagrid('loadData', data.parking);
		}
	});
}

/**
 * 选择项改变事件
 * 
 * @param {Object}
 *            rec
 */
function citySelect(rec) {
	// alert(rec.id);
}

/**
 * 添加停车场
 */
function newParking() {
	$('#dlgAdd').dialog('open').dialog('setTitle', '添加停车场');
	$('#addForm').form('clear');
}

/**
 * 编辑停车场
 */
function editParking() {
	var row = $('#dg').datagrid('getSelected');
	currentRow = row;
	if (row) {
		$('#dlgEdit').dialog('open').dialog('setTitle', '编辑');
		$('#editForm').form('load', row);
		return;
	}
	$.messager.alert('温馨提示', '请选择车库信息', 'info');
}

/**
 * 添加停车场确定按钮事件
 */
function saveParking() {
	var isValid = $('#addForm').form('validate');
	if (!isValid) {
		$.messager.alert('温馨提示', '请填写...', 'info');
		return;
	}
	$.postRequest({
		url : "/RobParking/ParkingController/add.do",
		data : {
			data : {
				parking : $('#addForm').formToObject()
			}
		},
		success : function(data, parameter) {
			$('#dlgAdd').dialog('close');
			loadParking();
		}
	});

}

/**
 * 编辑保存事件
 */
function saveEditParking() {
	var isValid = $('#editForm').form('validate');
	if (!isValid) {
		$.messager.alert('温馨提示', '请填写...', 'info');
		return;
	}
	$.postRequest({
		url : "/RobParking/ParkingController/edit.do",
		data : {
			data : {
				parking : $('#editForm').formToObject(currentRow)
			}
		},
		success : function(data, parameter) {
			$('#dlgEdit').dialog('close');
			loadParking();
		}
	});
}

function showExamine() {
	var row = $('#dg').datagrid('getSelected');
	if (!row) {
		$.messager.alert('温馨提示', '请选择车库信息', 'info');
		return;
	}

	if (row.passState == 2) {
		$.messager.show({
			title : '温馨提示',
			msg : '该数据已经通过审核。',
			timeout : 2000,
			showType : 'slide'
		});
		return;
	}
	$('#examineForm').form('load', row);
	$('#dlgExamine').dialog('open').dialog('setTitle', '审核');
}

function examineParking() {
	var row = $('#dg').datagrid('getSelected');
	if (!row) {
		$.messager.alert('温馨提示', '请选择车库信息', 'info');
		return;
	}

	if (row.passState == 2) {
		$.messager.show({
			title : '温馨提示',
			msg : '该数据已经通过审核。',
			timeout : 2000,
			showType : 'slide'
		});
		return;
	}

	$.messager.confirm('Confirm', '你确定要通过审核?', function(r) {
		if (r) {
			$('#dlgExamine').dialog('close').dialog('setTitle', '审核');
			$.postRequest({
				url : "/RobParking/ParkingController/pass.do",
				data : {
					parameter : {
						id : row.id
					}
				},
				success : function(data, parameter) {
					loadParking();
				}
			});
		}
	});
}
/**
 * 格式化是否已经审核 0.未知, 1.未审核,2.已审核
 * 
 * @param {Object}
 *            value
 * @param {Object}
 *            row
 * @param {Object}
 *            index
 */
function format(value, row, index) {
	if (value == 0) {
		return "未知";
	}
	if (value == 1) {
		return "未审核";
	}
	if (value == 2) {
		return "已审核";
	}
}
