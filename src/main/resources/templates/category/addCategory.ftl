<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8"/>
    <title>添加类别</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/layui/css/layui.css">

</head>

<body>

<div class="layui-row" style="margin-top: 20px;">
    <div id="articleInfoForm" style="margin-left: 30px;">
        <div class="layui-col-md12">
            <form class="layui-form" id="articleForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">类别名称</label>
                    <div class="layui-input-inline">
                        <input type="text" name="categoryName" required lay-verify="required" placeholder="取个名字吧~"
                               autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <button class="layui-btn" type="button" id="btnSave">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="${ctx}/static/js/jquery.min.js"></script>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>

<script>
    //加载弹出层组件
    layui.use(['layer', 'form', 'element'], function () {
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var $ = layui.$;

        //保存文章
        $("#btnSave").on('click', function () {
            debugger
            var $articleForm = $("#articleForm");
            var categoryName = $articleForm.find("input[name='categoryName']").val();
            var param = {
                'categoryName': categoryName
            };

            $.post('${ctx}/category/saveCategory', param, function (data) {
                if (data && data.status === "SUCCESS") {
                    layer.msg('保存成功！', {icon: 1, time: 3000}, function () {
                        window.location.href = '${ctx}/admin/index';
                    });
                } else {
                    layer.msg(data.msg || '保存出现错误！', {icon: 2, time: 3000});
                }
            });
        })
    })
</script>
</body>

</html>