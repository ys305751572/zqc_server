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
    <input type="hidden" id="mian_module" value="内容管理">
    <input type="hidden" id="child_module" value="banner广告列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">编辑用户信息</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data"
              class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${banner.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>位置</label>
                        <select class="select" id="position" name="position">
                            <option
                                    <c:if test="${banner.position=='0'}">selected="selected" </c:if> value="0">首页
                            </option>
                            <option
                                    <c:if test="${banner.position=='1'}">selected="selected" </c:if> value="1">商城
                            </option>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>开始时间</label>
                        <input type="text" id="startDate" name="startDate" value="<date:date value="${banner.startDate}" format="yyyy-MM-dd"></date:date>"
                               class="input-sm form-control form_datetime validate[required]" placeholder="..." >
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>结束时间</label>
                        <input type="text" id="endDate1" name="endDate1" value="<date:date value="${banner.endDate}" format="yyyy-MM-dd"></date:date>"
                               class="input-sm form-control form_datetime validate[required]" placeholder="..." >
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <img src="${banner.imageUrl}">
                            </div>
                            <div>
                                <span class="btn btn-file btn-alt btn-sm">
                                    <span class="fileupload-new">选择图片</span>
                                    <span class="fileupload-exists">更改</span>
                                    <input id="imageFile" name="imageFile" type="file"/>
                                </span>
                                <a href="#" class="btn fileupload-exists btn-sm" data-dismiss="fileupload">移除</a>
                            </div>
                        </div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="$user.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
                        <button type="button" class="btn btn-info btn-sm m-t-10" onclick="history.go(-1);">返回</button>
                    </div>
                </div>
            </div>
        </form>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>

<script>
    $user = {
        v: {
            list: [],
            chart: null,
            dTable: null
        },
        fn: {
            init: function () {
                if($("#startDate").val()==""){
                    $("#endDate1").attr("disabled",true);
                }

                $("#startDate").change(function(){
                    if($("#startDate").val()!=""){
                        $("#endDate1").attr("disabled",false);
                    }else{
                        $("#endDate1").attr("disabled",true);
                    }
                });

                $("#endDate1").change(function(){
                    if($("#endDate1").val()<$("#startDate").val()){
                        $leoman.notify('结束日期不能小于开始日期', "error");
                        $("#endDate1").val("");
                    }
                });
            },
            save: function () {
                if(!$("#formId").validationEngine("validate")) return;
                $("#formId").ajaxSubmit({
                    url: "${contextPath}/admin/banner/save",
                    type: "POST",
                    success: function (result) {
                        if (result.status) {
                            $common.fn.notify("操作成功", "success");
                            window.location.href = "${contextPath}/admin/banner/index";
                        } else {
                            $common.fn.notify("操作失败", "error");
                        }
                    }
                });
            }
        }
    }
    $(function () {
        $user.fn.init();
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

