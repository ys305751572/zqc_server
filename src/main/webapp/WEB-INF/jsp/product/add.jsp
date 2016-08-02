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
        <h1 class="page-title"></h1>
        <form id="fromId" name="formName" method="post" enctype="multipart/form-data"
              class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${product.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>商品名称:</label>
                        <input type="text" id="name" name="name" value="${product.name}"
                               class="input-sm form-control validate[required]" placeholder="...">
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>简短描述:</label>
                            <input type="text" id="shortDesc" name="shortDesc" value="${product.shortDesc}"
                                   class="input-sm form-control validate[required]" placeholder="...">
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>礼品类型:</label>
                        <c:if test="${product.type eq null}">
                        <select class="select" id="type" name="type">
                            <option value="0">实物</option>
                            <option value="1">众筹</option>
                            <option value="2">广告位</option>
                        </select>
                        </c:if>
                        <c:if test="${product.type ne null}">
                            <select class="select" id="type" name="type" disabled>
                                <c:if test="${product.type eq 0}"><option value="0">实物</option></c:if>
                                <c:if test="${product.type eq 1}"><option value="1">众筹</option></c:if>
                                <c:if test="${product.type eq 2}"><option value="2">广告位</option></c:if>
                            </select>
                        </c:if>
                    </div>

                    <hr class="whiter m-t-20"/>
                    <div id="change">
                        <c:if test="${product.type eq 0}">
                            <div class="col-md-6 m-b-15">
                                <label>所需益米:</label>
                                <input type="text" id="ym" name="ym" value="${product.ym}"
                                       class="input-sm form-control validate[required]" placeholder="...">
                            </div>
                            <div class="col-md-3 m-b-15">
                                <label>兑换有效期开始时间：</label>
                                <input type="text" id="sDate" value="<date:date format='yyyy-MM-dd HH:mm:ss' value='${product.validStartDate}'></date:date>" name="sDate" class="input-sm form_datetime form-control " placeholder="..." >
                                <input type="hidden" id="validStartDate" value="" name="validStartDate">
                            </div>

                            <div class="col-md-3 m-b-15">
                                <label>结束时间</label>
                                <input type="text" id="eDate" value="<date:date format='yyyy-MM-dd HH:mm:ss' value='${product.validEndDate}'></date:date>" name="eDate" class="input-sm form_datetime form-control " placeholder="..." >
                                <input type="hidden" id="validEndDate" value="" name="validEndDate">
                            </div>
                            <div class="col-md-6 m-b-15">
                                <label>兑换地点:</label>
                                <input type="text" id="address" name="address" value="${product.address}"
                                       class="input-sm form-control validate[required]" placeholder="...">
                            </div>
                        </c:if>
                        <c:if test="${product.type eq 1}">
                            <div class="col-md-6 m-b-15">
                                    <label>所需人数:</label>
                                    <input type="text" id="nums" name="nums" value="${product.nums}"
                                           class="input-sm form-control validate[required]" placeholder="...">
                            </div>
                            <div class="col-md-6 m-b-15" id="a">
                                    <label>单个售价:</label>
                                    <input type="text" id="dgym" name="ym" value="${product.ym}"
                                           class="input-sm form-control validate[required]" placeholder="...">
                            </div>
                        </c:if>
                        <c:if test="${product.type eq 2}">
                            <c:forEach items="${productAdsList}" var="v">
                                <div class="col-md-1 m-b-15" name='index'>
                                    <div>
                                        <label>展示周期:</label>
                                        <input type="text" id="days" name="days" value="${v.days}"
                                               class="input-sm form-control validate[required]" placeholder="...">
                                    </div>
                                    <div>
                                        <label>所需益米:</label>
                                        <input type="text" id="adsYm" name="adsYm" value="${v.ym}"
                                               class="input-sm form-control validate[required]" placeholder="...">
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div id="button">
                        <c:if test="${product.type eq 2}">
                            <div class="col-md-12 m-b-15"  >
                                <a href="javascript:void(0);" class="btn btn-sm btn-alt m-r-5" onclick="$product_add.fn.addym();">新增</a>
                                <a href="javascript:void(0);" class="btn btn-sm btn-alt m-r-5" onclick="$product_add.fn.delym();">删除</a>
                            </div>
                        </c:if>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-3 m-b-15">
                        <label>封面：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <img src="${product.coverUrl}">
                            </div>
                            <div>
                                <span class="btn btn-file btn-alt btn-sm">
                                    <span class="fileupload-new">选择图片</span>
                                    <span class="fileupload-exists">更改</span>
                                    <input id="imageFile" name="imageFile" type="file"/>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>详情图片：</label>
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-preview thumbnail form-control">
                                <img src="${product.detailImageUrl}">
                            </div>
                            <div>
                                <span class="btn btn-file btn-alt btn-sm">
                                    <span class="fileupload-new">选择图片</span>
                                    <span class="fileupload-exists">更改</span>
                                    <input id="imageFile2" name="imageFile2" type="file"/>
                                </span>
                            </div>
                        </div>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>详细描述</label>
                        <div class="wysiwye-editor" id="detail">${product.detailDesc}</div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" onclick="$product_add.fn.save();" class="btn btn-info btn-sm m-t-10">提交</button>
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
    $product_add = {
        v: {
            list: [],
            days: [],
            adsYm: [],
            chart: null,
            dTable: null
        },
        fn: {
            init: function () {
                var id = $("#id").val();
                if(id=="" || id==null){
                    $product_add.fn.change(0);
                }

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
                //时间控件
                $('.form_datetime').datetimepicker({
                    language: 'zh-CN',
                    weekStart: 1,
                    todayBtn: 1,
                    autoclose: 1,
                    todayHighlight: 1,
                    startView: 2,
                    forceParse: 0
                });

            },
            addym: function(){
                var html =  "";
                html += "     <div class='col-md-1 m-b-15' name='index'>                                                                   ";
                html += "         <div>                                                                                                    ";
                html += "             <label>展示周期:</label>                                                                              ";
                html += "             <input type='text' id='days' name='days' value='' class='input-sm form-control validate[required]' > ";
                html += "         </div>                                                                                                   ";
                html += "         <div>                                                                                                    ";
                html += "             <label>所需益米:</label>                                                                              ";
                html += "             <input type='text' id='adsYm' name='adsYm' value='' class='input-sm form-control validate[required]' >   ";
                html += "         </div>                                                                                                   ";
                html += "     </div>                                                                                                       ";
                $("#change").append(html);
            },
            delym:function(){
                if($("#change div[name=index]").length==1){
                    $leoman.notify('至少需要填写一个周期和所需益米', "error");
                    return false;
                }
                $("#change div[name=index]").last().remove();
            },
            change: function(data) {
                $("#change").empty();
                $("#button").empty();
                var html =  "";
                //实体
                if(data==0){
                    html += " <div class='col-md-6 m-b-15'>																								";
                    html += "     <label>所需益米:</label>                                                                                              ";
                    html += "     <input type='text' id='ym' name='ym' value='' class='input-sm form-control validate[required]' placeholder='...'>     ";
                    html += " </div>                                                                                                                    ";
                    html += " <div class='col-md-3 m-b-15'>                                                                                             ";
                    html += "     <label>兑换有效期开始时间：</label>                                                                                   ";
                    html += "     <input type='text' id='sDate' value='' name='sDate' class='input-sm form_datetime form-control ' placeholder='...' >  ";
                    html += "     <input type='hidden' id='validStartDate' value='' name='validStartDate'>                                              ";
                    html += " </div>                                                                                                                    ";
                    html += "                                                                                                                           ";
                    html += " <div class='col-md-3 m-b-15'>                                                                                             ";
                    html += "     <label>结束时间</label>                                                                                               ";
                    html += "     <input type='text' id='eDate' value='' name='eDate' class='input-sm form_datetime form-control ' placeholder='...' >  ";
                    html += "     <input type='hidden' id='validEndDate' value='' name='validEndDate'>                                                  ";
                    html += " </div>                                                                                                                    ";
                    html += " <div class='col-md-6 m-b-15'>                                                                                             ";
                    html += "     <label>兑换地点:</label>                                                                                              ";
                    html += "     <input type='text' id='address' name='address' value='' class='input-sm form-control validate[required]' placeholder='...'>";
                    html += " </div>                                                                                                                    ";
                }
                //众筹
                if(data==1){
                    html += "<div class='col-md-6 m-b-15'>																								 ";
                    html += "        <label>所需人数:</label>                                                                                            ";
                    html += "        <input type='text' id='nums' name='ym' value='' class='input-sm form-control validate[required]' placeholder='...'> ";
                    html += "</div>                                                                                                                      ";
                    html += "<div class='col-md-6 m-b-15' id='a'>                                                                                        ";
                    html += "        <label>单个售价:</label>                                                                                            ";
                    html += "        <input type='text' id='dgym' name='ym' value='' class='input-sm form-control validate[required]' placeholder='...'> ";
                    html += "</div>                                                                                                                      ";
                }
                //广告
                if(data==2){
                    var but = "";
                    html += "     <div class='col-md-1 m-b-15' name='index'>                                                                    ";
                    html += "         <div>                                                                                                     ";
                    html += "             <label>展示周期:</label>                                                                              ";
                    html += "             <input type='text' id='days' name='days' value='' class='input-sm form-control validate[required]'>    ";
                    html += "         </div>                                                                                                     ";
                    html += "         <div>                                                                                                      ";
                    html += "             <label>所需益米:</label>                                                                               ";
                    html += "             <input type='text' id='adsYm' name='adsYm' value='' class='input-sm form-control validate[required]' >   ";
                    html += "         </div>                                                                                                     ";
                    html += "     </div>                                                                                                        ";
                    but += " <div class='col-md-12 m-b-15'  >                                                                                    ";
                    but += "     <a href='javascript:void(0);' class='btn btn-sm btn-alt m-r-5' onclick='$product_add.fn.addym()'>新增</a>       ";
                    but += "     <a href='javascript:void(0);' class='btn btn-sm btn-alt m-r-5' onclick='$product_add.fn.delym();'>删除</a>       ";
                    but += " </div>                                                                                                               ";
                }
                $("#change").append(html);
                $("#button").append(but);
            },
            save: function () {
                var isCheck = true;
                var type = $("#type").val();
                if($("#name").val()==""){
                    $leoman.notify('名称不能为空', "error");
                    isCheck=false;
                }
                if($("#shortDesc").val()==""){
                    $leoman.notify('简短描述不能为空', "error");
                    isCheck=false;
                }

                if($('.fileupload-preview img').size()<1 || $('.fileupload-preview img').width()==0){
                    $leoman.notify('图片不能为空', "error");
                    isCheck=false;
                }
                if($('.note-editable').text()==""){
                    $leoman.notify('详细描述不能为空', "error");
                    isCheck=false;
                }
                if(type==0){
                    if($("#ym").val()==""){
                        $leoman.notify('所需益米不能为空', "error");
                        isCheck=false;
                    }
                    if($("#sDate").val()==""){
                        $leoman.notify('兑换有效期开始时间不能为空', "error");
                        isCheck=false;
                    }
                    if($("#eDate").val()==""){
                        $leoman.notify('结束时间不能为空', "error");
                        isCheck=false;
                    }
                    if($("#address").val()==""){
                        $leoman.notify('兑换地点不能为空', "error");
                        isCheck=false;
                    }
                }
                if(type==1){
                    if($("#nums").val()==""){
                        $leoman.notify('所需人数不能为空', "error");
                        isCheck=false;
                    }
                    if($("#dgym").val()==""){
                        $leoman.notify('单个售价不能为空', "error");
                        isCheck=false;
                    }
                }
                if(type==2){

                    $product_add.v.days = [];
                    $("#change input[name=days]").each(function(){
                        if($(this).val()==""){
                            $leoman.notify('展示周期不能为空', "error");
                            isCheck=false;
                            return false;
                        }else {
                            $product_add.v.days.push($(this).val());
                        }
                    });

                    $product_add.v.adsYm = [];
                    $("#change input[name=adsYm]").each(function(){
                        if($(this).val()==""){
                            $leoman.notify('所需益米不能为空', "error");
                            isCheck=false;
                            return false;
                        }else {
                            $product_add.v.adsYm.push($(this).val());
                        }
                    });
                }

                if(isCheck){
                    var nums = $("nums").val();
                    $("#type").val(type);
                    var code = $('.wysiwye-editor').code();
                    //实体
                    if(type==0){
                        var startDate = this.transdate($("#sDate").val());
                        $("#validStartDate").val(startDate);
                        var endDate = this.transdate($("#eDate").val());
                        $("#validEndDate").val(endDate);
                    }
                    //众筹
                    var dgym = $("#dgym").val();

                    $("#fromId").ajaxSubmit({
                        url: "${contextPath}/admin/product/save",
                        type: "POST",
                        data: {
                            "detail": code,
                            "dgym": dgym,
                            "adsDays": JSON.stringify($product_add.v.days),
                            "adsYms": JSON.stringify($product_add.v.adsYm)
                        },
                        success: function (result) {
                            if (!result.status) {
                                $common.fn.notify(result.msg);
                                return;
                            }
                            window.location.href = "${contextPath}/admin/product/index";
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
            }
        }
    };
    $(function () {
        $product_add.fn.init();
        $("#type").change(function(){
            var type = $("#type").val();
            $product_add.fn.change(type);
        });

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

