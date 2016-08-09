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
    <input type="hidden" value="账号管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">编辑用户信息</h1>
        <form action="${contextPath}/admin/userinfo/save" id="formId" name="formName" method="post" enctype="multipart/form-data"
              class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${user.id}">
                <input type="hidden" id="userId" name="userId" value="${user.userId}">
                <input type="hidden" id="status" name="status" value="${user.status}">
                <input type="hidden" id="sign" name="sign" value="${user.sign}">
                <input type="hidden" id="createDate" name="createDate" value="${user.createDate}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>账号</label>
                        <input type="text" id="mobile" name="mobile" value="${user.mobile}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>昵称</label>
                        <input type="text" id="nickname" name="nickname" value="${user.nickname}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>身份证号</label>
                        <input type="text" id="idCard" name="idCard" value="${user.idCard}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>等级</label>
                        <input type="text" id="level" name="level" value="${user.level}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>性别</label>
                        <select class="select" id="gender" name="gender" disabled>
                            <option
                                    <c:if test="${doctor.gender=='0'}">selected="selected" </c:if> value="0">男
                            </option>
                            <option
                                    <c:if test="${doctor.gender=='1'}">selected="selected" </c:if> value="1">女
                            </option>
                        </select>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>积分</label>
                        <input type="text" id="integral" name="integral" value="${user.integral}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>益米</label>
                        <input type="text" id="ym" name="ym" value="${user.ym}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <input type="hidden" id="imageIds" name="imageIds" value="">
                        <label>荣誉证书</label>
                        <p></p>
                        <p></p>
                        <c:forEach var="_image" items="${list}">
                            <a href="${_image.caUrl}" data-rel="gallery"  class="pirobox_gall img-popup" title="Lovely evening in Noreway">
                                <img src="${_image.caUrl}" alt="">
                            </a>
                        </c:forEach>
                    </div>
                    <%--<hr class="whiter m-t-20"/>--%>
                    <%--<div class="col-md-12 m-b-15" style="margin-top: 10px" ;>--%>
                        <%--<label>详细描述</label>--%>
                        <%--<div class="wysiwye-editor" id="detail" name="detail">${doctor.detail}</div>--%>
                    <%--</div>--%>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <%--<button type="button" onclick="$user.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>--%>
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
                $user.fn.initImage();
            },
            save: function () {
                if($("#imageIds")==""||$("#imageIds")==null){
                    $common.fn.notify("缩略图不能为空!");
                    return false;
                }
                if(!$("#formId").validationEngine("validate")) return;

                if($(".glyphicon-hand-down").length==0){ // 没有图片的情况
                    $("#formId").ajaxSubmit({
                        dataType: "json",
                        success: function (result) {
                            if(result.status) {
                                window.location.href = "${contextPath}/admin/userinfo/index";
                            }

                        }
                    });
                }else{ // 有图片的情况
                    $(".fileinput-upload-button").trigger("click");
                }
            },
            deleteImage : function() {

            },

            initialPreview:function(){
                var imgPreViews = [];
                <c:forEach var="_image" items="${list}" >
                var img =  "<img src='${_image.caUrl}' style ='height:160px'>"
                imgPreViews.push(img);
                </c:forEach>
                return imgPreViews;
            },
            initialPreviewConfig:function(){
                var imgPreViewsConf = [];
                <c:forEach var="_image" items="${list}" >
                var conf = {
                    caption: "",
                    width: "120px",
                    url: "${contextPath}/admin/userinfo/deleteCa?id=${_image.id}",
                    key: ${_image.id}
                }
                imgPreViewsConf.push(conf);
                </c:forEach>
                return imgPreViewsConf;

            },
            initImage:function(){
                var $input = $("#the_file");
                $input.fileinput({
                    language: 'zh',
                    uploadUrl: "${contextPath}/admin/userinfo/uploadCa", // server upload action
                    uploadAsync: false,
                    showUpload: true, // hide upload button
                    showRemove: false, // hide remove button
                    overwriteInitial: false,
                    minFileCount: 1,
                    maxFileCount: 3,
                    initialPreview: $user.fn.initialPreview(),
                    initialPreviewConfig: $user.fn.initialPreviewConfig(),
                    msgFilesTooMany:"只能上传三张图片",
                    allowedFileTypes:['image']
                }).on('filebatchuploadsuccess', function(event, data, previewId, index) {
                    var response = data.response;
                    if(response.status){
                        var imageIds = "";
                        $.each(response.data,function(index,data){
                            imageIds+=data.id+",";
                        })
                        if(imageIds.length>0){
                            imageIds =  imageIds.substr(0,imageIds.length-1);
                        }
                        $("#imageIds").val(imageIds);
                        $("#formId").ajaxSubmit({
                            dataType: "json",
                            success: function (result) {
                                if(result.status) {
                                    window.location.href = "${contextPath}/admin/userinfo/index";
                                }

                            }
                        });
                    }
                });
            },
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

