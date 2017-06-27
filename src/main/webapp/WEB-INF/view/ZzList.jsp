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

<div class="easyui-panel">
    <form id="f" method="post">
        <table>
            <tr>
                <td>粽子品牌:</td>
                <td><input type="text" id="zbrand" name="zbrand" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>
                    <a id="btn" href="javascript:search()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                </td>
            </tr>
        </table>
    </form>
</div>


<table id="userDataGrid"></table>
<div id="tb">
    <a href="javascript:showZzForm()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" >新增</a>
    <a href="javascript:exportZzList()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >导出Excel</a>
    <a href="javascript:exportZzWhereList()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >条件导出Excel</a>
    <a href="javascript:editZzInline()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >开始编辑</a>
    <a href="javascript:updateZzInline()" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_green',plain:true" >修改</a>
    <a href="javascript:cancleEditZzInline()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >取消修改</a>
</div>

<script type="text/javascript">
    var userDatagrid = null;
    var userFromDialog = null;
    var editRow = undefined;

    function editZzInline() {
        //获取当前被选中的行
        var rowSelected =  $("#userDataGrid").datagrid('getSelected');
        //当选中行不为空时，当前行开启 行编辑
        if (rowSelected) {
            if (editRow != undefined) {
                $("#userDataGrid").datagrid("endEdit", editRow);
            }
            //当无编辑行时
            if (editRow == undefined) {
                //获取到当前选择行的下标
                var index = $("#userDataGrid").datagrid("getRowIndex", rowSelected);
                $("#userDataGrid").datagrid("beginEdit", index);
                editRow = index;
            }
        }
    }
    function updateZzInline() {
        $("#userDataGrid").datagrid("endEdit", editRow);
    }

    function cancleEditZzInline(){
        editRow = undefined;
        $("#userDataGrid").datagrid("rejectChanges");
    }

    function exportZzWhereList(){
        $("#f").attr("action","<%=request.getContextPath()%>/zz/exprotZzWhereList.do");
        $("#f").submit();
    }

    function exportZzList(){
        window.location.href=sys.contextPath+"/zz/exportZzList.do";
    }


    function showZzForm(){
        userFromDialog =  $("<div/>").dialog({
            title: '新增用户',
            width: 600,
            height: 400,
            closed: false,
            cache: false,
            href: sys.contextPath+'/zz/showZzForm.do',
            modal: true,
            buttons:[{
                text:'保存',
                iconCls:'ext-icon-anchor',
                handler:function(){

                    //form表单验证通过，在ajax提交后台
                    if ($("#ZzAddForm").form('validate')){
                        $.ajax({
                            url:sys.contextPath+'/zz/saveZzForm.do',
                            type:'POST',
                            async:true,
                            data:$("#ZzAddForm").serialize(),
                            dataType:'json',
                            success:function(data){
                                $.messager.alert('信息',data.msg,'info')
                                userFromDialog.dialog('destroy');
                                userDatagrid.datagrid('reload');
                            },
                            error:function(){
                                $.messager.alert('信息','ajax失败','error');
                            }
                        });
                    }

                }
            },{
                text:'关闭',
                iconCls:'icon-remove',
                handler:function(){
                    //销毁当前dialog
                    userFromDialog.dialog('destroy');
                }
            }],
            onClose:function(){
                //销毁当前dialog
                userFromDialog.dialog('destroy');
            }
        });
    }


    $(function() {
        userDatagrid = $("#userDataGrid").datagrid({
            url: '<%=request.getContextPath()%>/zz/queryZz.do',
            method: 'post',
            rownumbers: true,
            pageNumber: 1,
            pageSize: 2,
            pageList: [2, 4, 6, 8],
            striped: true,
            pagination: true,
            singleSelect: true,
            idField: 'id',
            loadMsg: '候着。。。',
            toolbar: '#tb',
            //排序
            sortName:'id',
            sortOrder:'desc',
            columns: [
                [
                    // 对应User实体类的私有属性名
                    {field:'id',title:'ID',width:120,checkbox:true},
                    {field: 'zbrand', title: '粽子品牌', width: 120,
                        editor:{
                            type:'validatebox',
                            options:{
                                required:true,
                                validType:'minLength[4]'
                            }
                        }
                    },
                    {field: 'zorigin', title: '粽子产地', width: 120,
                        editor:{
                            type:'validatebox',
                            options:{
                                required:true,
                                validType:'zorigin'
                            }
                        }
                    },
                    {field: 'zprice', title: '粽子价格', width: 120,
                        editor:{
                            type:'validatebox',
                            options:{
                                required:true,
                                validType:'zprice'
                            }
                        }
                    },
                    {field: 'zdateinproduced', title: '粽子生产日期', width: 120},
                    //field:'level' 自定义，但是不能重复
                    {
                        field: 'flavorid', title: '粽子口味', width: 120,
                        editor:{
                            type:'combobox',
                            options:{
                                url:sys.contextPath+'/zz/selectZzFlavorList.do',
                                valueField:'flavorid',
                                textField:'fname',
                                required:true,
                            }
                        },
                        formatter: function (value, row, index) {
                            if (row.flavor) {
                                return row.flavor.fname;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        title: '操作',
                        field: 'action',
                        width: '250',
                        formatter: function (value, row) {
                            //外单内双 格式 html代码书写格式
                            var str = '';
                            str += '<a class="linkbutton" href="#" onclick="deleteZz(\'' + row.id + '\')">删除</a>';
                            return str;
                        }
                    }
                ]
            ],
            onAfterEdit: function (index, row, changes) {
                //触发修改
                console.info(row);
                console.info(typeof(row));

                var rowStr = JSON.stringify(row); //可以将json对象转换成json对符串
                console.info(rowStr);
                console.info(typeof (rowStr));
                $.ajax({
                    url:sys.contextPath+'/zz/updateZz.do',
                    type:'post',
                    data:{'user':rowStr},
                    dataType:'json',
                    success:function (data) {
                        $.messager.alert('信息',data.msg,'info');
                    },
                    error:function () {
                        $.messager.alert('信息','ajax失败','error');
                    }
                })

                editRow = undefined;
            },
            onDblClickRow: function (index, row) {
                //双击开启编辑行
                if (editRow != undefined) {
                    $("#userDataGrid").datagrid("endEdit", editRow);
                }
                if (editRow == undefined) {
                    $("#userDataGrid").datagrid("beginEdit", index);
                    editRow = index;
                }
            }
        });
    });

    function search(){
        $("#userDataGrid").datagrid('load',{
            "whereMap['zbrand']":$("#zbrand").val().trim(),
        })
    }
</script>

</body>
</html>
