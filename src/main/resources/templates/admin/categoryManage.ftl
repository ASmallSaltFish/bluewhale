<div class="demoTable" style="margin-top: 20px;">
    <div class="layui-inline" style="margin-right: 10px;">
        <input style="width: 250px;" class="layui-input" name="article.keyword" id="keyword" autocomplete="off"
               placeholder="类别名称">
    </div>

    <button class="layui-btn" data-type="reload" id="search">搜索</button>

    <div class="layui-row" style="margin-top: 20px;">
        <input type="button" class="layui-btn layui-btn-sm" id="btnAddCategory" value="新增"/>
        <input type="button" class="layui-btn layui-btn-sm" id="btnUpdateCategory" value="修改"/>
        <input type="button" class="layui-btn layui-btn-danger layui-btn-sm" id="btnDeleteCategory" value="删除"/>
    </div>

    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <table class="layui-hide" id="allCategoryInfoTb" lay-filter="categoryInfo"></table>
        </div>
    </div>
</div>

<script src="${ctx}/static/js/jquery.min.js"></script>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>

<script>
    //加载弹出层组件
    layui.config({
        dir: '${ctx}/static/layui/'
    }).use(['layer', 'form', 'element', 'table'], function () {
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;
        var $ = layui.$;

        var table_data = [];
        //第一个实例
        table.render({
            elem: '#allCategoryInfoTb',
            height: 500,
            url: '${ctx}/category/listCategoryPage',
            page: true,
            cols: [[
                {checkbox: true, fiexed: true, unresize: true},
                {field: 'categoryName', title: '类别名称'},
                {field: 'createTime', title: '创建时间', templet: "#createTimeTb"},
                {field: 'lastModifyTime', title: '修改时间', templet: "#lastModifyTimeTb"}
            ]],
            id: 'allCategoryInfoTb',
            limits: [5, 10],
            where: {
                'keyword': $("#keyword").val()
            },
            method: 'POST',
            request: {
                pageName: "pageNum",
                limitName: "pageSize"
            }, done: function (res, curr, count) {
                table_data = res.data;
                console.log("数据渲染成功！");
            }
        });

        //搜索文章
        $("#search").click(function () {
            var keyword = $("#keyword").val();
            table.reload("allCategoryInfoTb", {
                page: {
                    curr: 1
                },
                where: {
                    'keyword': keyword
                }
            });
        });

        //存储选中行的userId
        var selectedCategoryIds = [];
        table.on('checkbox(categoryInfo)', function (obj) {
            if (obj.checked === true) {
                if (obj.type === 'one') {
                    selectedCategoryIds.push(obj.data.categoryId);
                } else {
                    for (var i = 0; i < table_data.length; i++) {
                        selectedCategoryIds.push(table_data[i].categoryId);
                    }
                }
            } else {
                if (obj.type === 'one') {
                    for (var i = 0; i < selectedCategoryIds.length; i++) {
                        if (selectedCategoryIds[i] === obj.data.categoryId) {
                            selectedCategoryIds.remove(selectedCategoryIds[i]);
                        }
                    }
                } else {
                    selectedCategoryIds = [];
                }
            }

            console.log("-->>");
            console.log(selectedCategoryIds);
        });

        //新增类别
        $("#btnAddCategory").on('click', function () {
            //window.open("${ctx}/category/addCategory");
            var $btn = $("#btnAddCategory");
            $.ajax({
                url: '${ctx}/category/addCategory',
                type: 'GET',
                async: true,
                success: function (data) {
                    var tabName = "类别-" + $btn.val();
                    var tabId = $btn.attr("id");
                    var $tabli = $("li[lay-id='" + tabId + "']");
                    //如果已经存在
                    if ($tabli.length === 0) {
                        //新增一个Tab项
                        element.tabAdd('mainTb', {
                            title: tabName,
                            content: data,
                            id: tabId
                        });
                    }

                    $("li[lay-id='" + tabId + "']").click();
                }
            });
        });

        //修改类别
        $("#btnUpdateCategory").on('click',function () {
            if (selectedCategoryIds.length == 0) {
                layer.msg('请选择想要修改的类别！', {icon: 2});
                return false;
            }
            if (selectedCategoryIds.length != 1) {
                layer.msg('修改时只能选择一条记录！', {icon: 2});
                return false;
            }

            var $btn = $("#btnUpdateCategory");
            $.ajax({
                url: '${ctx}/category/updateCategory?categoryId=' + selectedCategoryIds[0],
                type: 'GET',
                async: true,
                success: function (data) {
                    var tabName = "类别-" + $btn.val();
                    var tabId = $btn.attr("id");
                    var $tabli = $("li[lay-id='" + tabId + "']");
                    //如果已经存在
                    if ($tabli.length === 0) {
                        //新增一个Tab项
                        element.tabAdd('mainTb', {
                            title: tabName,
                            content: data,
                            id: tabId
                        });
                    }

                    $("li[lay-id='" + tabId + "']").click();
                }
            });
        });

        //删除类别
        $("#btnDeleteCategory").on('click',function () {
            if(selectedCategoryIds==null||selectedCategoryIds.length==0){
                layer.msg('请选择想要删除的类别！', {icon: 2});
                return false;
            }
            alert("您确定要删除选中的类别吗？");
            $.ajax({
                type:'POST',
                url:'${ctx}/category/deleteCategory',
                contentType: "application/x-www-form-urlencoded",
                data: {"selectedCategoryIds":selectedCategoryIds},
                success : function(data) {
                    console.log(data);
                    if (data && data.status === "SUCCESS") {
                        layer.msg('删除成功！', {icon: 1, time: 3000}, function () {
                            window.location.href = '${ctx}/admin/index';
                        });
                    } else {
                        layer.msg(data.msg || '删除出现错误！', {icon: 2, time: 3000});
                    }
                }
            });
        });

        //数组添加remove方法
        Array.prototype.remove = function (val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        }
    });
</script>

<script type="text/html" id="createTimeTb">
    {{# if(d.createTime!==null){ }}
    <div>{{layui.util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm:ss')}}</div>
    {{# } }}
</script>

<script type="text/html" id="lastModifyTimeTb">
    {{# if(d.lastModifyTime!==null){ }}
    <div>{{layui.util.toDateString(d.lastModifyTime, 'yyyy-MM-dd HH:mm:ss')}}</div>
    {{# } }}
</script>
</body>

</html>