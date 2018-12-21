<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8"/>
    <title>新增文章填写表单</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/layui/css/layui.css">
</head>

<body>

<div class="layui-row" style="margin-top: 20px;">
    <div class="layui-col-xs11">
        <form class="layui-form" id="addArticleForm">
            <div class="layui-form-item">
                <label class="layui-form-label">文章标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required lay-verify="required" placeholder="请输入文章标题"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">文章简介</label>
                <div class="layui-input-block">
                    <textarea name="description" placeholder="请输入文章简介" class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否公开</label>
                <div class="layui-input-block">
                    <input type="radio" name="personalFlag" value="0" title="否" checked>
                    <input type="radio" name="personalFlag" value="1" title="是">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">选择类别</label>
                <div class="layui-input-block">
                    <select name="categoryName" id="categorySel" lay-verify="required">
                        <option value=""></option>
                        <#list categoryList as category>
                            <option value="${(category.categoryId)!}">${(category.categoryName)!}</option>
                        </#list>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">选择标签</label>
                <div class="layui-input-block">
                    <#if tagList??>
                        <#list tagList as tag>
                            <input type="checkbox" name="tagName" value="${(tag.tagId)!}" title="${tag.tagName}">
                        </#list>
                    <#else>
                        暂无标签~
                    </#if>
                </div>
            </div>

            <div class="layui-form-item" style="text-align: center; margin-top: 65px;">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" type="button" id="btnEnter" style="margin-right: 30px;">
                        确定
                    </button>
                    <button type="reset" class="layui-btn layui-btn-primary" style="margin-left: 20px;">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery-3.2.1.min.js"></script>
<script>
    //加载弹出层组件
    layui.config({
        dir: '${ctx}/static/layui/'
    }).use(['layer', 'form', 'element', 'upload'], function () {
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var parent$ = window.parent.layui.jquery;
        var $ = layui.$;
        var upload = layui.upload;

        //保存文章
        $("#btnEnter").click(function () {
            debugger
            var $articleForm = $("#addArticleForm");
            var title = $articleForm.find("input[name='title']").val();
            var description = $articleForm.find("textarea[name='description']").val();
            var personalFlag = $articleForm.find("input[name='personalFlag']:checked").val();
            console.log(personalFlag);
            var tagIds = [];
            var $tags = $("input[name='tagName']:checked");
            $.each($tags, function (index, tag) {
                tagIds.push($(tag).val());
            });

            console.log(tagIds);
            var categoryId = $("#categorySel").val();
            console.log(categoryId);

            var param = {
                title: title,
                description: description,
                personalFlag: personalFlag,
                tagIdStr: tagIds.join(","),
                categoryId: categoryId
            };

            $.post('${ctx}/article/saveArticle', param, function (data) {
                if (data && data.status === "SUCCESS") {
                    layer.msg('保存成功！', {icon: 1, time: 3000}, function () {
                        window.open("${ctx}/article/addArticle?articleId=" + data.data['articleId'], "_blank");
                        parent.layer.close(parent.layer.getFrameIndex(window.name));
                    });
                } else {
                    layer.msg(data.msg || '保存出现错误！', {icon: 2, time: 3000});
                }
            });
        });
    })
</script>
</body>

</html>