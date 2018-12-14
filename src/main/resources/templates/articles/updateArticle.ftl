<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8"/>
    <title>写博客啦~</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/layui/css/layui.css">

    <link rel="stylesheet" href="${ctx}/static/css/editormd.css"/>

    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon"/>
</head>

<body>
<div class="layui-row" style="margin-top: 20px;">
    <div id="articleInfoForm">
        <div class="layui-col-md12">
            <form class="layui-form" id="articleForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">文章标题</label>
                    <div class="layui-input-inline">
                        <input type="text" name="title" required lay-verify="required" value="${(article.title)!}"
                               autocomplete="off" class="layui-input">
                        <input type="text" name="articleId" style="display: none;" value="${(article.articleId)!}">
                    </div>
                    <div class="layui-input-inline">
                        <button class="layui-btn" type="button" id="btnSave">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="layout">
    <div id="test-editormd">
        <textarea>${(article.content)!}</textarea>
    </div>
</div>
<script src="${ctx}/static/js/jquery.min.js"></script>
<script src="${ctx}/static/js/editormd.js"></script>

<script>
    //加载弹出层组件
    layui.use(['layer', 'form', 'element'], function () {
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var $ = layui.$;

        var editor = editormd("test-editormd", {
            width: "90%",
            height: 720,
            path: '${ctx}/static/lib/',
            onfullscreen: function () {
                alert("onfullscreen");
                console.log("onfullscreen =>", this, this.id, this.settings);
            },
            onfullscreenExit: function () {
                alert("onfullscreenExit");
                console.log("onfullscreenExit =>", this, this.id, this.settings);
            }
        });

        //保存文章
        $("#btnSave").on('click', function () {
            var $articleForm = $("#articleForm");
            var articleId=$articleForm.find("input[name='articleId']").val();
            var title = $articleForm.find("input[name='title']").val();
            var content = editor.getValue();
            var previewContent = editor.getPreviewedHTML();
            var param = {
                'articleId':articleId,
                'title': title,
                'content': content,
                'previewContent': previewContent
            };

            $.post('${ctx}/article/updateArticle', param, function (data) {
                if (data && data.status === "SUCCESS") {
                    layer.msg('更新成功！', {icon: 1, time: 3000}, function () {
                        window.location.href = '${ctx}/admin/index';
                    });
                } else {
                    layer.msg(data.msg || '更新出现错误！', {icon: 2, time: 3000});
                }
            });
        })
    })
</script>
</body>

</html>