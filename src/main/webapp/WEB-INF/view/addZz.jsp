<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="/common/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <form id="ZzAddForm" method="post">
        <table>
            <tr>
                <td>粽子品牌</td>
                <td><input type="text" name="zbrand" class="easyui-validatebox"  data-options="required:true" /></td>
            </tr>
            <tr>
                <td>粽子产地</td>
                <td><input type="text" name="zorigin" class="easyui-validatebox"  data-options="required:true" /></td>
            </tr>
            <tr>
                <td>粽子价格</td>
                <td><input type="text" name="zprice" class="easyui-validatebox"  data-options="required:true" /></td>
            </tr>
            <%--<tr>--%>
                <%--<td>粽子生产日期</td>--%>
                <%--<td><input class="Wdate" type="text" onClick="WdatePicker()" name="zdateinproduced"> </td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>粽子销售日期</td>--%>
                <%--<td><input class="Wdate" type="text" onClick="WdatePicker()" name="zsalesdate"> </td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td>粽子保质期</td>--%>
                <%--<td><input class="Wdate" type="text" onClick="WdatePicker()" name="zexpirationdate"> </td>--%>
            <%--</tr>--%>
            <tr>
                <td>粽子口味</td>
                <td>
                    <select id="zzFlavor" name="flavorid" style="width:200px;" data-options="required:true" >

                    </select>
                </td>
            </tr>
            <tr>
                <td>相册:</td>
                <td>
                    <div id="fileQueue" style="width:500px;">
                    </div>
                    <input type="file" id="iuuidname" name="iuuidname"/>
                    <div id="showImg">
                    </div>
                </td>
            </tr>
        </table>
    </form>

<script type="text/javascript">
    $(function(){
        $('#zzFlavor').combobox({
            url:sys.contextPath+'/zz/selectZzFlavorList.do',
            valueField:'flavorid',
            textField:'fname'
        });
    });


    $(function() {
        /*上传相册*/
        $("#iuuidname").uploadify({
            //前台请求后台的url 不可忽略的参数
            'uploader': "<%=request.getContextPath()%>/zz/uploadZzImg.do",
            //插件自带 不可忽略的参数
            'swf': '<%=request.getContextPath()%>/js/uploadify/uploadify.swf',
            //撤销按钮的图片路径
            //    'cancelImg' : '<%=request.getContextPath() %>/js/uploadify/uploadify-cancel.png',
            //如果为true 为自动上传 在文件后 为false 那么它就要我们自己手动点上传按钮
            'auto': true,
            //可以同时选择多个文件 默认为true 不可忽略
            'multi': true,
            //向后台传递的参数
            //'formDate':{'flag':1},
            'method': 'POST',
            //允许同时上传文件的最大数量
            'uploadLimit': 10,
            //给div的进度条加背景 不可忽略
            'queueID': 'fileQueue',
            //给上传按钮设置文字
            'buttonText': '选取相册',
            //上传后队列是否消失
            'removeCompleted': true,
            'removeTimeout': 1,
            // action中接收文件的全局变量名  与 input标签的name属性一致
            'fileObjName': 'iuuidname',
            //上传文件的类型
            'fileTypeExts': '*.jpg;*.jpge;*.gif;*.png',
            //文件类型描述
            'fileTypeDesc': '只允许上传图片',
            //struts2上传文件默认2MB
            'fileSizeLimit': '16MB',
            //   文件上传成功后调用的函数
            'onUploadSuccess': function (file, data, response) {


                //data 文件存放路径+上传文件名称
                var src = sys.contextPath + "/" + data;
                $("#showImg").append("<img src='" + src + "' style='width:100px'/>");
            },
            //   文件上传失败后调用的函数
            'onUploadError': function (file, data, response) {
                alert("上传失败");
            },
            //当用户选择的文件不符合规定时触发
            'onSelectError': function (file, errorCode, errorMsg) {
                //alert(errorCode+"---"+errorMsg);
                if (errorCode == -100) {
                    //alert("选择文件个数超过10个，上传失败");
                    this.queueData.errorMsg = "选择文件个数超过10个，上传失败";
                }
                if (errorCode == -130) {
                    this.queueData.errorMsg = "选择文件类型不匹配，上传失败";
                }
            }
        });
    });
</script>
</body>
</html>
