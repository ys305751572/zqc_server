<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<div class="modal fade" id="form_detail" tabindex="-1" role="dialog">+
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">详细描述</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div>
                        <textarea id="detail" name="detail" class="form-control overflow" style="height: 400px" wrap="virtual" readonly="readonly"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-body" align="center">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</html>
