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
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">添加活动</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data"
              class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>名称:</label>
                        <input type="text" id="name" name="name" value=""
                               class="input-sm form-control validate[required]" placeholder="...">
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>关卡:</label>
                        <input type="text" id="checkpoint" name="checkpoint" value=""
                               class="input-sm form-control validate[required]" placeholder="...">
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>奖励积分:</label>
                        <input type="text" id="rewardIntegral" name="rewardIntegral" value=""
                               class="input-sm form-control validate[required]" placeholder="..." onkeyup="value=value.replace(/[^0-9]/g,'')">
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>奖励益米:</label>
                        <input type="text" id="rewardYm" name="rewardYm" value=""
                               class="input-sm form-control validate[required]" placeholder="..." onkeyup="value=value.replace(/[^0-9]/g,'')">
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>详细描述</label>
                        <div class="wysiwye-editor" id="detail"></div>
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

            },
            save: function () {
                var isCheck = true;
                if($("#name").val()==""){
                    $leoman.notify('名称不能为空', "error");
                    isCheck=false;
                }
                if($("#checkpoint").val()==""){
                    $leoman.notify('关卡不能为空', "error");
                    isCheck=false;
                }

                if($("#rewardIntegral").val()==""){
                    $leoman.notify('奖励积分人数不能为空', "error");
                    isCheck=false;
                }
                if($("#rewardYm").val()==""){
                    $leoman.notify('奖励益米人数不能为空', "error");
                    isCheck=false;
                }
                if(isCheck){
                    var code = $('.wysiwye-editor').code();
                    $("#fromId").ajaxSubmit({
                        url: "${contextPath}/admin/task/ndxs/save",
                        type: "POST",
                        data: {
                            "detail": code
                        },
                        success: function (result) {
                            if (!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/task/ndxs/index";
                        }
                    });
                }
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
        forceParse: 0
    });
</script>
</body>
</html>

