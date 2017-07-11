$(document).ready(function() {
    $('#btnQuery').click(loadParking);
});

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
                //{ regionId : regionId }
            }
        },
        success : function(data, parameter) {
            // 绑定数据
            $('#dg').datagrid('loadData', data.parking);
        }
    });
}

/**
 *选择项改变事件
 * @param {Object} rec
 */
function citySelect(rec) {
    // alert(rec.id);
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
    $('#editForm').form('load', row);
    $('#dlgEdit').dialog('open').dialog('setTitle', '审核');
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
            $('#dlgEdit').dialog('close').dialog('setTitle', '审核');
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
 * 格式化是否已经审核
 * 0.未知, 1.未审核,2.已审核
 * @param {Object} value
 * @param {Object} row
 * @param {Object} index
 */
function format(value, row, index) {
    if (value == 0) {
        return "未知";
    }
    if (value == 1) {
        return '未审核';
    }
    if (value == 2) {
        return "已审核";
    }
}
