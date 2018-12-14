<div class="demoTable">
    <div class="layui-inline" style="margin-right: 10px;">
        <input style="width: 250px;" class="layui-input" name="article.keyword" id="keyword" autocomplete="off"
               placeholder="标题/描述/内容/标签">
    </div>

    <div class="layui-form layui-inline" style="margin-right: 10px;">
        <div class="layui-inline">
            <select name="article.personalFlag" id="personalFlag">
                <option value="" selected>私有设置</option>
                <option value="0">公开</option>
                <option value="1">私有</option>
            </select>
        </div>
    </div>

    发布时间：
    <div class="layui-form layui-inline" style="margin-right: 10px;">
        <div class="layui-inline">
            <input type="text" name="article.publishStartDate" id="publishStartDate" class="layui-input" id="publishStartDate">
        </div>
        -
        <div class="layui-inline">
            <input type="text" name="article.publishEndDate" id="publishEndDate" class="layui-input" id="publishEndDate"
                   value="${.now?string('yyyy-MM-dd')}">
        </div>
    </div>

    <button class="layui-btn" data-type="reload" id="search">搜索</button>

    <button class="layui-btn layui-btn-normal" data-type="export" id="btnExport">导出</button>

    <div class="layui-row" style="margin-top: 20px;">
        <button class="layui-btn layui-btn-sm" id="btnAddArticle">新增文章</button>
        <button class="layui-btn layui-btn-sm" id="btnPreviewArticle">预览</button>
        <#--<button class="layui-btn layui-btn-sm" id="btnUpdateArticle">修改</button>-->
        <button class="layui-btn layui-btn-sm" id="btnPublishArticle">发布</button>
        <button class="layui-btn layui-btn-sm" id="btnDeleteArticle">删除</button>
    </div>

    <div class="layui-tab" style="margin-top: 10px;">
        <ul class="layui-tab-title" lay-filter="openTypeTab" >
            <li class="layui-this" data-type="ALL">所有文章</li>
            <li data-type="ARTICLE_PUBLIC">公开</li>
            <li data-type="ARTICLE_PRIVATE">私有</li>
        </ul>

        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <table class="layui-hide" id="allArticleListTb" lay-filter="article"></table>
            </div>
            <div class="layui-tab-item">
                <table class="layui-hide" id="articlePublicListTb" lay-filter="article"></table>
            </div>
            <div class="layui-tab-item">
                <table class="layui-hide" id="articlePrivateListTb" lay-filter="article"></table>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/static/js/jquery.min.js"></script>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>

<!--时间格式化-->
<script id="createTime" type="text/html">
    {{#
    var date = new Date();
    date.setTime(d.createTime);
    return date.Format("yyyy-MM-dd hh:mm:ss");
    }}
</script>

<script>
    //加载弹出层组件
    layui.config({
        dir:'${ctx}/static/layui/'
    }).use(['layer', 'form', 'element', 'table'], function () {
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;
        var $ = layui.$;

        var table_data = [];
        //第一个实例
        table.render({
            elem: '#allArticleListTb',
            height: 500,
            url: '${ctx}/article/listArticles',
            page: true,
            cols:[[
                {checkbox: true, fiexed: true, unresize: true},
                {field: 'title', title: '标题'},
                {field: 'author', title: '作者'},
                {field: 'categoryId', title: '分类'},
                {field: 'imageCover', title: '封面'},
                {field: 'description', title: '描述'},
                {field: 'createName', title: '创建人'},
                {field: 'createTime', title: '创建时间', templet: "#createTimeTb"},
                {field: 'lasModifyName', title: '修改人'},
                {field: 'lastModifyTime', title: '修改时间', templet: "#lastModifyTimeTb"}
            ]],
            id:'allArticleListTb',
            limits:[5, 10],
            where:{
                'keyword':$("#keyword").val()
            },
            request:{
                pageName:"pageNum",
                limitName:"pageSize"
            }, done: function (res, curr, count) {
                table_data = res.data;
                console.log("数据渲染成功！");
            }
        });

        //搜索文章
        $("#search").click(function () {
            var keyword=$("#keyword").val();
            var personalFlag=$("#personalFlag").find("option:selected").val();
            var publishStartDate=$("#publishStartDate").val();
            var publishEndDate=$("#publishEndDate").val();
            if ((!publishStartDate&&publishEndDate) ||(publishStartDate&&!publishEndDate)){
                alert("请选择一个查询时间范围！");
                return false;
            }
            if(publishStartDate>publishEndDate){
                alert("开始时间不能大于结束时间！");
                return false;
            }
            table.reload("allArticleListTb",{
                page:{
                    curr:1
                },
                //method:'get',
                where:{
                    'keyword':keyword,
                    'personalFlag':personalFlag,
                    'publishStartDate':publishStartDate,
                    'publishEndDate':publishEndDate
                }
            });
        });

        //存储选中行的userId
        var selectedArticleIds = [];
        table.on('checkbox(article)', function (obj) {
            if (obj.checked === true) {
                if (obj.type === 'one') {
                    selectedArticleIds.push(obj.data.articleId);
                } else {
                    for (var i = 0; i < table_data.length; i++) {
                        selectedArticleIds.push(table_data[i].articleId);
                    }
                }
            } else {
                if (obj.type === 'one') {
                    for (var i = 0; i < selectedArticleIds.length; i++) {
                        if (selectedArticleIds[i] === obj.data.articleId) {
                            selectedArticleIds.remove(i);
                        }
                    }
                } else {
                    selectedArticleIds = [];
                }
            }

            console.log("-->>");
            console.log(selectedArticleIds);
        });

        //新增文章
        $("#btnAddArticle").on('click', function () {
            window.open("${ctx}/article/addArticle");
        });

        //预览文章
        $("#btnPreviewArticle").on('click',function () {
            if(selectedArticleIds.length==0){
                alert("请选择想要预览的文章！");
                return false;
            }
            if (selectedArticleIds.length!=1){
                alert("预览时只能选择一条记录！");
                return false;
            }
            $(".manage-content").load("${ctx}/article/previewArticle?articleId="+selectedArticleIds[0]);

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