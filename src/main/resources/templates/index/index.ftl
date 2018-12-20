<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>🐳BlueWhale</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">
    <link rel="stylesheet" href="${ctx}/static/css/tagAll.css">
    <link rel="stylesheet" href="${ctx}/static/css/tagStyle.css">

    <!--加载meta IE兼容文件-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <style>
        body {
            background-color: #f8f9fa;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
        }

        #articleDiv a {
            color: #17a2b8;
        }

        #articleDiv a:hover {
            text-underline: none;
            color: #ff7f21;
        }

        .title-black-blod {
            color: black;
            font-weight: 500;
        }
    </style>
</head>

<body>
<#include "../common/header.ftl"/>

<div class="banner">
    <div class="layui-carousel" id="myCarousel">
        <div carousel-item>
            <div>
                <img src="${ctx}/static/img/bannel/bannel_1.jpg" width="100%" height="600px">
            </div>
            <div>
                <img src="${ctx}/static/img/bannel/bannel_2.jpg" width="100%" height="600px">
            </div>
            <div>
                <img src="${ctx}/static/img/bannel/bannel_3.jpg" width="100%" height="600px">
            </div>
            <div>
                <img src="${ctx}/static/img/bannel/bannel_4.jpg" width="100%" height="600px">
            </div>
            <div>
                <img src="${ctx}/static/img/bannel/bannel_5.jpg" width="100%" height="600px">
            </div>
        </div>
    </div>
</div>

<div class="content">
    <div class="cont w1000">
        <div class="title title-black-blod">
            <div class="layui-tab layui-tab-brief">
                <ul class="layui-tab-title" id="articleOrderNav">
                    <li class="layui-this" data-value="createTime">
                        <a href="javascript:;" class="active">
                            <i class="layui-icon layui-icon-template-1"></i>
                            时间排序
                        </a>
                    </li>
                    <li data-value="viewCount">
                        <a href="javascript:;">
                            <i class="layui-icon layui-icon-fire" style="color: red;"></i>
                            热度排序
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="layui-row article-row" style="margin-bottom: 50px;">
            <div class="layui-col-md8 layui-col-sm-12 layui-col-space10">
                <div id="articleDiv" style="min-height: 800px;">博客列表在这里啦~~</div>

                <#-- 分页 -->
                <div id="indexPage" style="text-align: center; margin-top: 50px; color: black;"></div>
            </div>

            <div class="layui-col-md4 layui-col-sm12 layui-col-xs12 hidden-xs"
                 style="margin: 20px 0 0 10px; background-color: #ffffff; color: #000; height: 500px; overflow-y: scroll;">
                <div class="layui-tab layui-tab-brief">
                    <ul class="layui-tab-title">
                        <li class="layui-this">
                            <i class="layui-icon layui-icon-note"></i>
                            标签云
                        </li>
                    </ul>
                    <div class="layui-tab-content" id="tagMainDiv">
                        <ul class="ks-cboxtags">
                            <#if tagInfoList??>
                                <#list tagInfoList as tagInfo>
                                    <li>
                                    <input type="checkbox" id="${(tagInfo.tagName)!}" data-id="${(tagInfo.tagId)!}" value="${(tagInfo.tagName)!}">
                                <label for="${(tagInfo.tagName)!}">${(tagInfo.tagName)!}</label>
                                    </li>
                                </#list>
                            <#else>
                                暂无标签数据哟~
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#include "../common/footer.ftl"/>
</body>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>

<script type="text/javascript">
    layui.config({
        base: '${ctx}/static/js/util/'
    }).use(['element', 'laypage', 'jquery', 'menu', 'carousel'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        var $ = layui.$;
        var menu = layui.menu;
        var carousel = layui.carousel;

        var param = {
            'pageNum': 1,
            'pageSize': 10,
            'orderBy': 'createTime'
        };

        console.log(param);
        laypage.render({
            elem: 'indexPage',
            count: '${count!}',
            jump: function (obj, first) {
                param = {
                    'pageNum': obj.curr,
                    'pageSize': obj.limit,
                    'orderBy': 'createTime'
                };

                loadArticlePage(param);
            }
        });

        menu.init();

        //首页轮播图
        carousel.render({
            elem: '#myCarousel',
            height: '600px',
            width: '100%',
            interval: 3000
        });

        //按照时间或热度排行
        $("#articleOrderNav").find("li").click(function () {
            var $this = $(this);
            param['orderBy'] = $this.attr("data-value");
            console.log("4" + param);
            loadArticlePage(param);
        });

        //点击标签重新加载列表
        $("#tagMainDiv").find("input[type='checkbox']").change(function () {
            var $cks = $("#tagMainDiv").find("input[type='checkbox']:checked");
            var tagIds = [];
            $.each($cks, function (index, ck) {
                tagIds.push($(ck).attr('data-id'));
            });

            console.log("2" + param);
            param['tagIdStr'] = tagIds.join(",");
            console.log("3" + param);
            loadArticlePage(param);
        });

        function loadArticlePage(param) {
            $.ajax({
                url: '${ctx}/listArticles',
                data: param,
                async: false,
                type: 'POST',
                dataType: 'JSON',
                success: function (data) {
                    var articles = data.data;
                    console.log(articles);
                    $("#articleDiv").html(function () {
                        var contentArr = [];
                        layui.each(articles, function (index, item) {
                            contentArr.push('<div style="height:135px; margin: 20px 30px 5px 0px; background-color: #ffffff">\n' +
                                '                                <div class="layui-col-md4 layui-col-sm6 layui-col-xs6">\n' +
                                '                                    <img src="./static/images/bg-01.jpg" height="130px;" width="95%">\n' +
                                '                                </div>\n' +
                                '                                <div class="layui-col-md8 layui-col-sm6 layui-col-xs6" style="text-align: left;">\n' +
                                '                                    <div class="layui-row grid-demo">\n' +
                                '                                        <div class="layui-col-md12" style="height: 30px; line-height: 24px;">\n' +
                                '                                            <h2>\n' +
                                '                                                <a href="${ctx}/article/displayArticle?articleId=' + item["articleId"] + '">' + item["title"] + '</a>\n' +
                                '                                            </h2>\n' +
                                '                                        </div>\n' +
                                '                                        <div class="layui-col-md12" style=" height:80px; overflow: hidden;">\n' +
                                '                                            ' + item["description"] + '\n' +
                                '                                        </div>\n' +
                                '                                        <div class="layui-col-md12" style="line-height: 25px;">\n' +
                                '                                            <div class="layui-row">\n' +
                                '                                                <div class="layui-col-md8 layui-col-xs6 layui-col-sm6">\n' +
                                '                                                    <i class="layui-icon layui-icon-username" style="color: orange;"></i>\n' +
                                '                                                    <span>${(loginUser.userName)!}</span>\n' +
                                '                                                    &nbsp;\n' +
                                '                                                    <span style="color: #777">|</span> &nbsp;\n' +
                                '                                                    <i class="fa fa-book" style="color: orange;"></i>\n' +
                                '                                                    <span>javaScript,java,mysql</span>\n' +
                                '                                                </div>\n' +
                                '                                                <div class="layui-col-md4 layui-col-xs6 layui-col-sm6">\n' +
                                '                                                    <i class="fa fa-eye" style="color: orange;">&nbsp;<span style="color:#777;">' + item["viewCount"] + '</span></i>\n' +
                                '                                                    &nbsp;\n' +
                                '                                                    <span style="color: #777">|</span> &nbsp;\n' +
                                '                                                    <a href="${ctx}/leacots">\n' +
                                '                                                        <i class="layui-icon layui-icon-reply-fill" style="color: orange;">&nbsp;<span style="color:#777;">' + item["commentCount"] + '</span></i>\n' +
                                '                                                    </a>\n' +
                                '                                                </div>\n' +
                                '                                            </div>\n' +
                                '                                        </div>\n' +
                                '                                    </div>\n' +
                                '                                </div>\n' +
                                '                            </div>');
                        });

                        if (contentArr) {
                            return contentArr.join('');
                        }

                        return "暂无数据~";
                    });
                }
            });
        }
    })
</script>

</html>