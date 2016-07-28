<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no">
    <meta charset="UTF-8">
    <meta name="description" content="Violate Responsive Admin Template">
    <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">
    <title>Super Admin Responsive Template</title>
    <!-- CSS -->
    <%@ include file="../inc/new/css.jsp" %>
</head>
<body id="skin-cloth">
<%@ include file="../inc/new/header.jsp" %>
<div class="clearfix"></div>
<section id="main" class="p-relative" role="main">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <form action="${contextPath}/admin/velocity/analysis" method="post" enctype="multipart/form-data">
            <div class="block-area" id="search">
                <div class="fileupload fileupload-new row" data-provides="fileupload">
                    <div class="input-group col-md-6">
                        <div class="uneditable-input form-control">
                            <i class="fa fa-file m-r-5 fileupload-exists"></i>
                            <span class="fileupload-preview"></span>
                        </div>
                        <div class="input-group-btn">
                            <span class="btn btn-file btn-alt btn-sm">
                                <span class="fileupload-new">Select file</span>
                                <span class="fileupload-exists">Change</span>
                                <input type="file" id="file" name="file"/>
                            </span>
                        </div>
                        <a href="#" class="btn btn-sm btn-gr-gray fileupload-exists" data-dismiss="fileupload">Remove</a>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="submit" class="btn btn-info btn-sm m-t-10">开始分析</button>
                        <input type="hidden" id="srcJava" name="srcJava" value = "${srcJava}" />
                    </div>
                </div>

            </div>
        </form>
        <hr class="whiter m-t-20"/>

        <!-- Tab -->
        <div class="block-area" id="tabs">
            <%--<textarea class="form-control auto-size m-b-10" placeholder="This is a default Textarea..." disabled>${log11.message}</textarea>--%>
            <div class="tab-container tile">
                <ul class="nav tab nav-tabs">
                    <li class="active"><a href="#home1">列表</a></li>
                    <li><a href="#profile1">新增</a></li>
                    <li><a href="#messages1">详情</a></li>
                </ul>

                <div class="tab-content">
                    <div class="tab-pane active" id="home1">
                        <table class="table tile">
                            <thead>
                            <tr>
                                <th>字段</th>
                                <th>显示名称</th>
                                <th>类型</th>
                                <th>表达式</th>
                                <th>是否必填</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${fields}" var="field">
                                    <tr>
                                        <td>${field.name}</td>
                                        <td><input type="text" class="form-control input-sm validate[required]" id="listname${field.name}" placeholder="..."></td>
                                        <td>
                                            <select class="select superselect" id="listselect${field.name}" onchange="_userInfo.fn.select(this)">
                                                <option value="defaultText">Default Text</option>
                                                <option value="image">Image</option>
                                                <option value="richText">Rich Text</option>
                                                <option value="select">Select</option>
                                                <option value="radio">Radio</option>
                                                <option value="checkBox">CheckBox</option>
                                                <option value="date">Date</option>
                                            </select>
                                        </td>
                                        <td>
                                            <select class="select subselect" id="listsubselect${field.name}">
                                               <c:forEach items="${list}" var="rule">
                                                   <option value="${rule.regex}">${rule.ruleName}</option>
                                               </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <div class="make-switch switch-mini" data-on="info" data-off="success">
                                                <input id="listcheckbox${field.name}" type="checkbox">
                                            </div>
                                        </td>
                                        <td>
                                            <button title='删除' class='btn btn-primary' onclick='_userInfo.fn.deleteTr(this);'><i class='fa fa-times'></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="tab-pane" id="profile1">
                        <table class="table tile">
                            <thead>
                            <tr>
                                <th>字段</th>
                                <th>显示名称</th>
                                <th>类型</th>
                                <th>表达式</th>
                                <th>是否必填</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${fields}" var="field">
                                <tr>
                                    <td>${field.name}</td>
                                    <td><input type="text" class="form-control input-sm validate[required]" id="listname${field.name}" placeholder="..."></td>
                                    <td>
                                        <select class="select superselect" id="addselect${field.name}" onchange="_userInfo.fn.select(this)">
                                            <option value="defaultText">Default Text</option>
                                            <option value="image">Image</option>
                                            <option value="richText">Rich Text</option>
                                            <option value="select">Select</option>
                                            <option value="radio">Radio</option>
                                            <option value="checkBox">CheckBox</option>
                                            <option value="date">Date</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select class="select subselect" id="addsubselect${field.name}">
                                            <c:forEach items="${list}" var="rule">
                                                <option value="${rule.regex}">${rule.ruleName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <div class="make-switch switch-mini" data-on="info" data-off="success">
                                            <input id="addcheckbox${field.name}" type="checkbox">
                                        </div>
                                    </td>
                                    <td>
                                        <button title='删除' class='btn btn-primary' onclick='_userInfo.fn.deleteTr(this);'><i class='fa fa-times'></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="tab-pane" id="messages1">
                        <table class="table tile">
                            <thead>
                            <tr>
                                <th>字段</th>
                                <th>显示名称</th>
                                <th>类型</th>
                                <th>表达式</th>
                                <th>是否必填</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${fields}" var="field">
                                <tr>
                                    <td>${field.name}</td>
                                    <td><input type="text" class="form-control input-sm validate[required]" id="listname${field.name}" placeholder="..."></td>
                                    <td>
                                        <select class="select superselect" id="detailselect${field.name}" onchange="_userInfo.fn.select(this)">
                                            <option value="defaultText">Default Text</option>
                                            <option value="image">Image</option>
                                            <option value="richText">Rich Text</option>
                                            <option value="select">Select</option>
                                            <option value="radio">Radio</option>
                                            <option value="checkBox">CheckBox</option>
                                            <option value="date">Date</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select class="select subselect" id="detailsubselect${field.name}">
                                            <c:forEach items="${list}" var="rule">
                                                <option value="${rule.regex}">${rule.ruleName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <div class="make-switch switch-mini" data-on="info" data-off="success">
                                            <input id="detailcheckbox${field.name}" type="checkbox">
                                        </div>
                                    </td>
                                    <td>
                                        <button title='删除' class='btn btn-primary' onclick='_userInfo.fn.deleteTr(this);'><i class='fa fa-times'></i></button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-5">
                    <button type="button" onclick="_userInfo.fn.generate();;" class="btn btn-info btn-sm m-t-10">代码生成</button>
                </div>
            </div>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>

<script>
    _userInfo = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                var options3 = _userInfo.fn.findDD();
            },
            select: function (item) {
                var selectval = $(item).val();

                var subselect = $(item).parent().next().find("select");
                subselect.empty();

                var options = new Array();
                if (selectval == 'defaultText') {
                     _userInfo.fn.findDefaultText(item);
                    return;
                }
                else if (selectval == 'image') {
                    options.push("<option value='singular'>单图</option>");
                    options.push("<option value='plural'>多图</option>");
                }
                else if (selectval == 'richText') {
                }
                else if (selectval == 'select') {
                    _userInfo.fn.findDD(item);
                    return;
                }
                else if (selectval == 'radio') {
                    options = _userInfo.fn.findDD(item);
                    return;
                }
                else if (selectval == 'checkBox') {
                    options = _userInfo.fn.findDD(item);
                    return;
                }
                else if (selectval == 'date') {

                }
                subselect.append(options);
                subselect.selectpicker('refresh');


            },
            findDD: function (item) {
                $.ajax({
                    url: "${contextPath}/admin/velocity/ddList",
                    type: "POST",
                    success: function (result) {
                        var options = new Array();
                        var data = eval(result.data);
                        for (var i = 0; i < data.length; i++) {
                            var option = "<option value = " + data[i].id + "> " + data[i].name + " </option>";
                            options.push(option);
                        }
                        var subselect = $(item).parent().next().find("select");
                        subselect.empty();

                        subselect.append(options);
                        subselect.selectpicker('refresh');
                    }
                });
            },
            findDefaultText : function(item) {
                $.ajax({
                    url: "${contextPath}/admin/velocity/defaultText",
                    type: "POST",
                    success: function (result) {
                        var options = new Array();
                        console.log(result);
                        var data = eval(result.data);
                        for (var i = 0; i < data.length; i++) {
                            var option = "<option value = " + data[i].regex + "> " + data[i].ruleName + " </option>";
                            options.push(option);
                        }

                        var subselect = $(item).parent().next().find("select");
                        subselect.empty();

                        subselect.append(options);
                        subselect.selectpicker('refresh');
                    }
                });
            },
            download : function() {
                window.location.href = "${contextPath}/admin/velocity/download";
            },
            generate : function() {
                var allmodels = new Array();

                $(".tab-content").find(".tab-pane").each(function(i,val){
                    var tabname;
                    if(i == 0 ) {
                        tabname = "list";
                    }
                    else if(i == 1) {
                        tabname = "add";
                    }
                    else if(i == 2) {
                        tabname = "detail";
                    }
                    var models = new Array();
                    $(this).find("tbody").find("tr").each(function(i,val) {
                        var model;
                        var name = $(this).find("td").eq(0).text();
                        var c1,c2,c3,c4,c5;
                        $(this).find("td").each(function(i,val) {
                            if(i == 0) {
                                // 字段
                                c1 = name;
                            }
                            if(i == 1) {
                                // 显示名字
                                c2 = $("#" + tabname + "name" + name).val();
                            }
                            if(i == 2) {
                                // 类型
                                c3 = $("#" + tabname + "select" + name).val();
                            }
                            if(i == 3) {
                                // 表达式
                                c4 = $("#" + tabname + "subselect" + name).val();
                            }
                            if(i == 4) {
                                // 是否必填
                                c5 = $("#" + tabname + "checkbox" + name).is(":checked");
                            }

                        });
                        model = {
                            "c1" : c1,
                            "c2" : c2,
                            "c3" : c3,
                            "c4" : c4,
                            "c5" : c5
                        }
                        models.push(model);
                    });
                    var tabModel = JSON.stringify(models);
                    allmodels.push(tabModel);
                });

                $.ajax({
                    url : "${contextPath}/admin/velocity/generate",
                    type : "POST",
                    dataType : "JSON",
                    data : {
                        "tabString" : JSON.stringify(allmodels),
                        "srcJava" :$("#srcJava").val()
                    },
                    success : function(result) {
                        if(result.status) {
//                           _userInfo.fn.download();
                        }
                    }
                });
            },
            deleteTr : function(td) {
                $(td).parent('td').parent('tr').remove();
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        _userInfo.v.dTable.ajax.reload(null, false);
                    } else {
                        _userInfo.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        _userInfo.fn.init();

    })
</script>
<script>
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: "2",
        forceParse: 0,
        showMeridian: 1,
        format: 'yyyy-mm-dd'
    });
</script>
</body>
</html>

