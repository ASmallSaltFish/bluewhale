<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>管理控制台</title>
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">博客控制台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="${ctx}/index">博客首页</a></li>
            <#--<li class="layui-nav-item"><a href="">商品管理</a></li>-->
            <#--<li class="layui-nav-item"><a href="">用户</a></li>-->
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="http://favorites.ren" target="_blank">云收藏</a></dd>
                    <dd><a href="http://www.github.com" target="_blank">github</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${(loginUser.userName)!}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                    <dd><a href="${ctx}/logout">退了</a></dd>
                </dl>
            </li>
            <#--<li class="layui-nav-item"><a href="${ctx}/logout">退了</a></li>-->
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">博客管理</a>
                    <dl class="layui-nav-child blog-manage-nav">
                        <dd><a href="javascript:;" id="article-manage" data-type="tabAdd">文章管理</a></dd>
                        <dd><a href="javascript:;" id="category-manage" data-type="tabAdd">类别管理</a></dd>
                        <dd><a href="javascript:;" id="tag-manage" data-type="tabAdd">书签管理</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="padding: 15px;">
        <div class="layui-tab" lay-allowclose="true" lay-filter="mainTb">
            <ul class="layui-tab-title" id="mainTbUl"></ul>
            <div class="layui-tab-content" style="height: 150px;"></div>
        </div>
    </div>

    <div class="layui-footer">
        ©bluewhale - ${(loginUser.signature)!}
    </div>
</div>
<script src="${ctx}/static/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
        var $ = layui.jquery;

        //第一次加载时，默认打开第一个菜单列表
        firstLoad();

        //菜单点击事件
        $('.blog-manage-nav').find("a").click(function () {
            var $this = $(this);
            var dataType = $this.attr("data-type");
            if ($this.attr("id") === "article-manage") {
                var content;
                $.ajax({
                    url: '${ctx}/admin/articleManage',
                    type: 'GET',
                    async: true,
                    success: function (data) {
                        content = data;
                        active[dataType] ? active[dataType].call(this, $this, content) : '';
                    }
                });
            } else if ($this.attr("id") === "category-manage") {
                var content;
                $.ajax({
                    url: '${ctx}/admin/categoryManage',
                    type: 'GET',
                    async: true,
                    success: function (data) {
                        content = data;
                        active[dataType] ? active[dataType].call(this, $this, content) : '';
                    }
                });
                active[dataType] ? active[dataType].call(this, $this, content) : '';
            } else if ($this.attr("id") === "tag-manage") {
                var content;
                $.ajax({
                    url: '${ctx}/admin/tagManage',
                    type: 'GET',
                    async: true,
                    success: function (data) {
                        content = data;
                        active[dataType] ? active[dataType].call(this, $this, content) : '';
                    }
                });
            }
        });

        // 触发事件
        var active = {
            tabAdd: function (othis, content) {
                //清除所有tab
                clearAllTab();

                var tabName = othis.text();
                var tabId = othis.attr("id");
                var $tabli = $("li[lay-id='" + tabId + "']");
                //如果已经存在
                if ($tabli.length > 0) {
                    element.tabDelete('mainTb', id);
                }

                //新增一个Tab项
                element.tabAdd('mainTb', {
                    title: tabName,
                    content: content,
                    id: tabId
                });
                $("li[lay-id='" + tabId + "']").click();
            }
        };


        function firstLoad() {
            var $articleManage = $("#article-manage");
            var content = "";
            $.ajax({
                url: '${ctx}/admin/articleManage',
                type: 'GET',
                async: true,
                success: function (data) {
                    content = data;
                    active['tabAdd'].call(this, $articleManage, content);
                }
            });

            $articleManage.parents("dd").eq(0).addClass("layui-this");
        }

        function clearAllTab() {
            $("#mainTbUl").html("");
            $(".layui-tab-content").html("");
        }
    });
</script>
</body>
</html>