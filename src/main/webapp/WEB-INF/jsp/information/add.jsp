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
        <h1 class="page-title">资讯编辑</h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data"
              class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${information.id}">
                <input type="hidden" id="infoType" value="${information.type}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>资讯标题：</label>
                        <input type="text" id="title" name="title" value="${information.title}"
                               class="input-sm form-control validate[required]" placeholder="...">
                    </div>
                    <div class="col-md-12 m-b-15">
                        <label>资讯封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <img src="${information.avater}">
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
                    <div class="col-md-6 m-b-15">
                        <label>资讯简介：</label>
                        <textarea cols="40" rows="6" id="introduction" name="introduction" class="form-control"
                                  placeholder="...">${information.introduction}</textarea>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-6 m-b-15">
                        <label>资讯类型：</label>
                        <div class="radio">
                            <label onclick="information.fn.show(0)">
                                <input type="radio" name="type" value="0" <c:if test="${information.type == 0}">checked</c:if> onclick="information.fn.show()">纯文本
                            </label>
                        </div>
                        <div class="radio">
                            <label onclick="information.fn.show(1)">
                                <input type="radio" name="type" value="1" <c:if test="${information.type == 1}">checked</c:if> onclick="information.fn.show()">网页
                            </label>
                        </div>
                    </div>
                    <div class="col-md-12 m-b-15" style="display: none" id="type1">
                        <hr class="whiter m-t-20"/>
                        <label>资讯详情</label>
                        <div class="wysiwye-editor">${information.description}</div>
                    </div>
                    <div class="col-md-12 m-b-15" style="display: none;" id="type2">
                        <hr class="whiter m-t-20"/>
                        <label>资讯详情</label>
                        <input type="text" value="${information.description}"
                               name="description" class="input-sm form-control validate[required]" placeholder="请输入资讯url">
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="information.fn.save();" class="btn btn-info btn-sm m-t-10">提交
                        </button>
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
    information = {
        v: {
            list: [],
            chart: null,
            dTable: null
        },
        fn: {
            init: function () {
                var infoType = $('#infoType').val();
                var type = 0;
                if (null != infoType && infoType != '') {
                    type = infoType;
                }
                information.fn.show(type);
            },
            show: function (type) {
                if (type == 0) {
                    $("#type2").val("");
                    $("#type1").css('display', '');
                    $("#type2").css('display', 'none');
                } else {
                    $("#type1").val("");
                    $("#type1").css('display', 'none');
                    $("#type2").css('display', '');
                }

                $('#infoType').val('');
            },
            checkData: function () {
                var flag = true;
                var title = $('#title').val();
                var imageFile = $('#imageFile').val();
                var introduction = $('#introduction').val();
                var code = $('.wysiwye-editor').code();

                if (null == title || title == '') {
                    $common.fn.notify("请输入资讯标题", "error");
                    flag = false;
                    return;
                }

                if (null == imageFile || imageFile == '') {
                    $common.fn.notify("请上传资讯封面", "error");
                    flag = false;
                    return;
                }

                if (null == introduction || introduction == '') {
                    $common.fn.notify("请输入资讯简介", "error");
                    flag = false;
                    return;
                }

                if (null == code || code == '') {
                    $common.fn.notify("请输入资讯详情", "error");
                    flag = false;
                    return;
                }

                return flag;
            },
            save: function () {
                if (information.fn.checkData()) {
                    var code = $('.wysiwye-editor').code();
                    var type = $(".description").val();
                    $("#fromId").ajaxSubmit({
                        url: "${contextPath}/admin/information/save",
                        type: "POST",
                        data: {
                            "detail": code,
                            "description": type,
                        },
                        success: function (result) {
                            if (result == 1) {
                                $common.fn.notify("操作成功", "success");
                                window.location.href = "${contextPath}/admin/information/index";
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                }

            }
        }
    }
    $(function () {
        information.fn.init();
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

