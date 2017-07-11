$(document).ready(function() {
    // 加载下拉框数据
    $.ajax({
        url : '/RobParking/RegionService/getByParent.do?id=1',
        dataType : 'json',
        type : "GET",
        success : function(data) {
            $("#city").combobox("loadData", data);
        }
    });

    $('#btn1').click(function() {
        $.postRequest({
            url : "/RobParking/User_ParkingController/getParkingByUser.do",
            data : {
                header : {
                },
                parameter : {
                    key : "value"
                },
                data : {
                    User_Parking : {
                        userId : 1,
                        parkingId : 3
                    }//$("#form").formToObject()
                }
            },
            success : function(data, parameter) {
                alert(data);
            },
            error : function(message) {
                alert(message);
            }
        });
    });
    return;
    // 上面最简洁的写法如下
    $.postRequest({
        url : "/RobParking/JsonRequest.do",
        data : {
            parameter : {
                key : "value"
            },
            data : {
                user : $("#form").formToObject()
            }
        },
        success : function(data, parameter) {
            alert(data);
        }
    });
});

/**
 * 重新加载验证码
 */
function reloadValidateCode() {
    // path变量是页面生成是由系统定义的
    var src = path + "/LoginController/validateCode.do?data=" + new Date() + Math.floor(Math.random() * 24);
    $("#validateCodeImg").attr("src", src);
    return false;
}