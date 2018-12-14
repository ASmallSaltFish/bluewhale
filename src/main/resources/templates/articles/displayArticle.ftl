<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>markdown预览样例</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">
    <link rel="stylesheet" href="${ctx}/static/css/style.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/editormd.css"/>
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon"/>

    <style>
        .article-wrap {
            margin-top: 100px;
        }

        .editormd-preview {
            width: 100%;
        }

        /* 这里可以更改markdown字体和背景 */
        .editormd-preview-container {
            text-align: left;
            font-size: 15px;
            line-height: 1.6;
            padding: 20px;
            overflow: auto;
            width: 100%;
            background-color: #fff;
            font-family: '微软雅黑';
        }
    </style>
</head>

<body>

<#include "../common/header.ftl"/>

<div class="layui-row">
    <div class="editormd-preview article-wrap" style="display: block;">
        <h2>${(article.title)!}</h2>
        <div class="markdown-body editormd-preview-container" previewcontainer="true" style="padding: 20px 20px 50px 40px;">
            <div class="layui-col-md-8 layui-col-md-offset2">
                ${(article.previewContent)!}
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx}/static/js/util/'
    }).use(['element', 'laypage', 'jquery', 'menu'], function () {
        var element = layui.element;
        var $ = layui.$;
    });
</script>
</html>