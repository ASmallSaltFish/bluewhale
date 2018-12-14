<div class="demoTable" style="margin-top: 20px;">
    <div class="layui-inline" style="margin-right: 10px;">
        <input style="width: 250px;" class="layui-input" name="article.keyword" id="keyword" autocomplete="off"
               placeholder="标签名称">
    </div>

    <button class="layui-btn" data-type="reload" id="search">搜索</button>

    <div class="layui-row" style="margin-top: 20px;">
        <input type="button" class="layui-btn layui-btn-sm" id="btnAddTag" value="新增"/>
        <input type="button" class="layui-btn layui-btn-sm" id="btnUpdateTag" value="修改"/>
        <input type="button" class="layui-btn layui-btn-danger layui-btn-sm" id="btnDeleteTag" value="删除"/>
    </div>

    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <table class="layui-hide" id="allTagInfoTb" lay-filter="tagInfo"></table>
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
            elem: '#allTagInfoTb',
            height: 500,
            url: '${ctx}/tag/listTagPage',
            page: true,
            cols: [[
                {checkbox: true, fiexed: true, unresize: true},
                {field: 'tagName', title: '标签名称'},
                {field: 'createTime', title: '创建时间', templet: "#createTimeTb"},
                {field: 'lastModifyTime', title: '修改时间', templet: "#lastModifyTimeTb"}
            ]],
            id: 'allTagInfoTb',
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
            table.reload("allTagInfoTb", {
                page: {
                    curr: 1
                },
                where: {
                    'keyword': keyword
                }
            });
        });

        //存储选中行的userId
        var selectedTagIds = [];
        table.on('checkbox(article)', function (obj) {
            if (obj.checked === true) {
                if (obj.type === 'one') {
                    selectedTagIds.push(obj.data.articleId);
                } else {
                    for (var i = 0; i < table_data.length; i++) {
                        selectedTagIds.push(table_data[i].articleId);
                    }
                }
            } else {
                if (obj.type === 'one') {
                    for (var i = 0; i < selectedTagIds.length; i++) {
                        if (selectedTagIds[i] === obj.data.articleId) {
                            selectedTagIds.remove(i);
                        }
                    }
                } else {
                    selectedTagIds = [];
                }
            }

            console.log("-->>");
            console.log(selectedTagIds);
        });

        //新增文章
        $("#btnAddArticle").on('click', function () {
            window.open("${ctx}/article/addArticle");
        });


        //数组添加remove方法
        Array.prototype.remove = function (dx) {
            if (isNaN(dx) || dx < this.length) {
                return false;
            }

            for (var i = 0, n = 0; i < this.length; i++) {
                if (this[i] != this[dx]) {
                    this[n++] = this[i];
                }
            }

            this.length -= 1;
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