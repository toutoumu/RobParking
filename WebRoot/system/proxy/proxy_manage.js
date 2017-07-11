var userType = [{
    label : '1',
    value : '管理员'
}, {
    label : '2',
    value : '用户'
}, {
    label : '3',
    value : '代理商'
}];

var currentRow = null;
$(document).ready(function() {
    $('#category').combobox('loadData', userType);
    $('#category1').combobox('loadData', userType);
    $('#category2').combobox('loadData', userType);
    $('#category1').combobox('setValue', 3);
    // 加载代理商
    //loadUser();
});

/**
 * 加载代理商
 */
function loadUser() {
    $.postRequest({
        url : "/RobParking/UserController/getUser.do",
        data : {
            data : {
                user : {
                    //查询代理商
                    category : $('#category1').combobox('getValue')
                }
            }
        },
        success : function(data, parameter) {
            // 绑定数据
            if (data.user) {
                $('#dg').datagrid('loadData', data.user);
            } else {
                $('#dg').datagrid('clear');
            }
        }
    });
}

/**
 * 弹出添加代理商
 */
function newUser() {
    $('#dlgAdd').dialog('open').dialog('setTitle', '添加代理商');
    $('#addForm').form('clear');
    $('#category').combobox('setValue', 3);
}

/**
 * 编辑代理商
 */
function editUser() {
    var row = $('#dg').datagrid('getSelected');
    currentRow = row;
    if (!row) {
        $.messager.alert('温馨提示', '请选择代理商信息', 'info');
        return;
    }
    $('#dlgEdit').dialog('open').dialog('setTitle', '编辑代理商');
    $('#editForm').form('load', row);
}

/**
 * 保存代理商
 */
function saveUser() {
    var isValid = $('#addForm').form('validate');
    if (!isValid) {
        $.messager.alert('温馨提示', '请填写表单', 'info');
        return;
    }
    // 匹配手机号码的正则表达式
    var isMobile = /^(?:13\d|15\d|18\d|17\d)\d{5}(\d{3}|\*{3})$/;
    var phoneNumber = $('#account').val();
    if (!isMobile.test(phoneNumber)) {
        $.messager.alert('温馨提示', '请正确填写电话号码，例如:13488888888', 'info');
        return false;
    }
    $.postRequest({
        url : "/RobParking/UserController/addUser.do",
        data : {
            data : {
                user : $('#addForm').formToObject()
            }
        },
        success : function(data, parameter) {
            $('#dlgAdd').dialog('close');
            loadUser();
        }
    });
}

/**
 * 保存代理商修改
 */
function saveEditUser() {
    var isValid = $('#editForm').form('validate');
    if (!isValid) {
        $.messager.alert('温馨提示', '请填写表单', 'info');
        return;
    }
    $.postRequest({
        url : "/RobParking/UserController/editUser.do",
        data : {
            data : {
                user : $('#editForm').formToObject(currentRow)
            }
        },
        success : function(data, parameter) {
            $('#dlgEdit').dialog('close');
            loadUser();
        }
    });
}

function disableUser() {
    var row = $('#dg').datagrid('getSelected');
    if (!row) {
        $.messager.alert('温馨提示', '请选择代理商信息', 'info');
        return;
    }
    if (row.locked) {
        $.messager.alert('温馨提示', '该用户已经禁用', 'info');
        return;
    }
    $.messager.confirm('Confirm', '确定要禁用?', function(r) {
        if (r) {
            $.postRequest({
                url : "/RobParking/UserController/disableUser.do",
                data : {
                    data : {
                        user : $('#editForm').formToObject($('#dg').datagrid('getSelected'))
                    }
                },
                success : function(data, parameter) {
                    loadUser();
                }
            });
        }
    });
}

function enableUser() {
    var row = $('#dg').datagrid('getSelected');
    if (!row) {
        $.messager.alert('温馨提示', '请选择代理商信息', 'info');
        return;
    }
    if (!row.locked) {
        $.messager.alert('温馨提示', '该用户已经启用', 'info');
        return;
    }
    $.messager.confirm('Confirm', '确定要启用?', function(r) {
        if (r) {
            $.postRequest({
                url : "/RobParking/UserController/enableUser.do",
                data : {
                    data : {
                        user : $('#editForm').formToObject($('#dg').datagrid('getSelected'))
                    }
                },
                success : function(data, parameter) {
                    loadUser();
                }
            });
        }
    });
}

