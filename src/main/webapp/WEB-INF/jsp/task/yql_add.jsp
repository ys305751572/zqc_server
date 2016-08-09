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
    <input type="hidden" value="任务管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">添加活动</h1>
        <form id="formId" name="formName" method="post" enctype="multipart/form-data"
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
                        <label>活动类型:</label>
                        <select id="joinType" name="joinType" class="select">
                            <option value="">活动类型</option>
                            <option value="0">个人</option>
                            <option value="1">团体</option>
                        </select>
                    </div>
                    <div class="col-md-3 m-b-15">
                        <label>活动开始时间：</label>
                        <input type="text" id="sDate" value="" name="sDate" class="input-sm form_datetime form-control validate[required] " placeholder="..." >
                        <input type="hidden" id="startDate" value="" name="startDate">
                    </div>
                    <div class="col-md-3 m-b-15">
                        <label>结束时间</label>
                        <input type="text" id="eDate" value="" name="eDate" class="input-sm form_datetime form-control validate[required] " placeholder="..." >
                        <input type="hidden" id="endDate" value="" name="endDate">
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>活动地点:</label>
                        <input type="text" id="address" name="address" value=""
                               class="input-sm form-control validate[required]" placeholder="...">
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>主办方:</label>
                        <input type="text" id="organizers" name="organizers" value=""
                               class="input-sm form-control validate[required]" placeholder="...">
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>所需人数:</label>
                        <input type="text" id="nums" name="nums" value=""
                               class="input-sm form-control validate[required]" placeholder="..." onkeyup="value=value.replace(/[^0-9]/g,'')">
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
                        <label>封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <img src="">
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
                $user.fn.initImage();
                if($("#sDate").val()==""){
                    $("#eDate").attr("disabled",true);
                }

                $("#sDate").change(function(){
                    if($("#sDate").val()!=""){
                        $("#eDate").attr("disabled",false);
                    }else{
                        $("#eDate").attr("disabled",true);
                    }
                });

                $("#eDate").change(function(){
                    if($("#eDate").val()<$("#sDate").val()){
                        $leoman.notify('结束日期不能小于开始日期', "error");
                        $("#eDate").val("");
                    }
                });
            },
            save: function () {
                if(!$("#formId").validationEngine("validate")) {
                    return;
                }
                var isCheck = true;
                if($('.fileupload-preview img').size()<1 || $('.fileupload-preview img').width()==0){
                    $leoman.notify('图片不能为空', "error");
                    isCheck=false;
                }
                if($('.note-editable').text()==""){
                    $leoman.notify('详细描述不能为空', "error");
                    isCheck=false;
                }
                if(isCheck){
                    var code = $('.wysiwye-editor').code();
                    var startDate = this.transdate($("#sDate").val());
                    $("#startDate").val(startDate);
                    var endDate = this.transdate($("#eDate").val());
                    $("#endDate").val(endDate);
                    $("#formId").ajaxSubmit({
                        url: "${contextPath}/admin/task/yql/save",
                        type: "POST",
                        data: {
                            "detail": code
                        },
                        success: function (result) {
                            if (!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/task/yql/index";
                        }
                    });
                }

            },
            transdate :function(endTime){
                var date=new Date();
                date.setFullYear(endTime.substring(0,4));
                date.setMonth(endTime.substring(5,7)-1);
                date.setDate(endTime.substring(8,10));
                date.setHours(endTime.substring(11,13));
                date.setMinutes(endTime.substring(14,16));
                date.setSeconds(endTime.substring(17,19));
                return Date.parse(date);
            },
            initImage : function() {
                $('#file-fr').fileinput({
                    language: 'zh',
                    uploadAsync: false,
                    showUpload: false, // hide upload button
                    showRemove: false, // hide remove button
                    uploadUrl: '#',
                    minFileCount: 1,
                    maxFileCount: 3,
                    msgFilesTooMany:"只能上传三张图片",
                    allowedFileExtensions : ['jpg', 'png'],
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
        forceParse: 0
    });
</script>
</body>
</html>

