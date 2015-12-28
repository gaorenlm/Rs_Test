<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统一资源树</title>
    <meta charset="UTF-8">

    <%--Jquery1.11公共类库--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery_easyui/jquery.min.js"></script>

    <%--EasyUI依赖资源--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery_easyui/themes/gray/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery_easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery_easyui/jquery.easyui.min.js"></script>

    <%--Ztree依赖资源--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/zTree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/zTree/js/jquery.ztree.exedit-3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/zTree/js/jquery.ztree.excheck-3.5.js"></script>

    <%--资源操作JS--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/operation_function.js"></script>

    //邮件菜单样式
    <style type="text/css">
        ul{ margin:0; padding:0;}
        div#rMenu {
            position: absolute;
            visibility: hidden;
            background-color: #9dbcd9;
            text-align: left;
            padding: 0px 1px 1px 1px;
        }

        div#rMenu ul li {
            background-color: white;
            cursor: pointer;
            list-style: none outside none;
            margin-top: 1px;
            padding: 2px 5px 2px 12px;
            width: 100px;
        }
    </style>

    <%--Ztree设置--%>
    <script type="application/javascript">

        //查询用的对象
        var lastValue = "", nodeList = [], fontCss = {};
        //输入框对象
        var key;
        //菜单对象
        var rMenu;

        var p="${pageContext.request.contextPath}";

        //Ztree设置
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            }
//            , async: {
//                enable: true,
//                url: "/a",
//                autoParam: ["id"],
//                dataFilter: filter
//            }

            , view: {
                fontCss: getFontCss,
                dblClickExpand: false
            }, callback: {
                //beforeExpand: beforeExpand,
                //onExpand: onExpand,
                onClick: onClick,
                onRightClick: OnRightClick
            }
        };

       //异步加载返回后的值过滤一下
       function filter(treeId, parentNode, childNodes) {
           if (!childNodes) return null;
           for (var i=0, l=childNodes.length; i<l; i++) {
               childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
           }
           return childNodes;
       }

       $(document).ready(function(){


           $("#key2").click(function(){
               var treeObj = $.fn.zTree.getZTreeObj("rs_tree");
               treeObj.expandAll(true);
           });


           //加载全部数据
//           $.post("/a", function (data, status) {
//                       if(status=='success'){
//                           var zNodes=$.parseJSON(data);
//                           $.fn.zTree.init($("#rs_tree"), setting,zNodes);
//                           $.fn.zTree.getZTreeObj("rs_tree").expandAll(false);
//                       }else{
//                           alert("初始化失败");
//                       }
//                   });

           $.ajax({
               type : "POST",
               url : "/a",
               dataType : "json",
               success : function(data){
                    $.fn.zTree.init($("#rs_tree"), setting,data);
                    $.fn.zTree.getZTreeObj("rs_tree").expandAll(false);
               }
           });







           rMenu = $("#rMenu");
           key = $("#key");
           key.bind("propertychange", searchNode).bind("input", searchNode).bind("blur", blurKey);
       });

        function OnRightClick(event, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("rs_tree");
            if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
                zTree.cancelSelectedNode();
                showRMenu("root", event.clientX, event.clientY);
            } else if (treeNode && !treeNode.noR) {
                zTree.selectNode(treeNode);
                showRMenu("node", event.clientX, event.clientY);
            }
        }

        function showRMenu(type, x, y) {
            $("#rMenu ul").show();
            if (type=="root") {
                $("#m_del").hide();
                $("#m_check").hide();
                $("#m_unCheck").hide();
            } else {
                $("#m_del").show();
                $("#m_check").show();
                $("#m_unCheck").show();
            }
            rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

            $("body").bind("mousedown", onBodyMouseDown);
        }

        function onBodyMouseDown(event){
            if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
                rMenu.css({"visibility" : "hidden"});
            }
        }



        var result = '';
        var resultArray = [];
        function expandNodes(nodes) {
            var zTree = $.fn.zTree.getZTreeObj("rs_tree");
            result = '1,'+result;
            if (!nodes) return;
            if (nodes.length == undefined) {
                zTree.reAsyncChildNodes(nodes, "refresh");
                zTree.expandNode(nodes, true, false, false);
            } else {
                for (var i=0, l=nodes.length; i<l; i++) {
                    if(result.search(nodes[i].id) != -1 && nodes[i].id != resultArray[0]){
                        zTree.expandNode(nodes[i], true, false, false);
                        if (nodes[i].isParent && nodes[i].zAsync) {
                            if(!node[i].open){
                                expandNodes(nodes[i].children);
                            }
                        }
                    } else {
                        if (nodes[i].id == resultArray[0]) {
                            nodes[i].highlight = true;
                            zTree.updateNode(nodes[i]);
                            zTree.selectNode(nodes[i]);
                            //查询到节点后，清空查询数据
                            result = '';
                            resultArray = [];
                        }
                    }
                }
            }
        }


        //只展开一级树
//        var curExpandNode = null;
//        function beforeExpand(treeId, treeNode) {
//            var pNode = curExpandNode ? curExpandNode.getParentNode() : null;
//            var treeNodeP = treeNode.parentTId ? treeNode.getParentNode() : null;
//            var zTree = $.fn.zTree.getZTreeObj("rs_tree");
//            for (var i = 0, l = !treeNodeP ? 0 : treeNodeP.children.length; i < l; i++) {
//                if (treeNode !== treeNodeP.children[i]) {
//                    zTree.expandNode(treeNodeP.children[i], false);
//                }
//            }
//            while (pNode) {
//                if (pNode === treeNode) {
//                    break;
//                }
//                pNode = pNode.getParentNode();
//            }
//            if (!pNode) {
//                singlePath(treeNode);
//            }
//
//        }
//        function singlePath(newNode) {
//            if (newNode === curExpandNode) return;
//
//            var zTree = $.fn.zTree.getZTreeObj("rs_tree"),
//                    rootNodes, tmpRoot, tmpTId, i, j, n;
//
//            if (!curExpandNode) {
//                tmpRoot = newNode;
//                while (tmpRoot) {
//                    tmpTId = tmpRoot.tId;
//                    tmpRoot = tmpRoot.getParentNode();
//                }
//                rootNodes = zTree.getNodes();
//                for (i = 0, j = rootNodes.length; i < j; i++) {
//                    n = rootNodes[i];
//                    if (n.tId != tmpTId) {
//                        zTree.expandNode(n, false);
//                    }
//                }
//            } else if (curExpandNode && curExpandNode.open) {
//                if (newNode.parentTId === curExpandNode.parentTId) {
//                    zTree.expandNode(curExpandNode, false);
//                } else {
//                    var newParents = [];
//                    while (newNode) {
//                        newNode = newNode.getParentNode();
//                        if (newNode === curExpandNode) {
//                            newParents = null;
//                            break;
//                        } else if (newNode) {
//                            newParents.push(newNode);
//                        }
//                    }
//                    if (newParents != null) {
//                        var oldNode = curExpandNode;
//                        var oldParents = [];
//                        while (oldNode) {
//                            oldNode = oldNode.getParentNode();
//                            if (oldNode) {
//                                oldParents.push(oldNode);
//                            }
//                        }
//                        if (newParents.length > 0) {
//                            zTree.expandNode(oldParents[Math.abs(oldParents.length - newParents.length) - 1], false);
//                        } else {
//                            zTree.expandNode(oldParents[oldParents.length - 1], false);
//                        }
//                    }
//                }
//            }
//            curExpandNode = newNode;
//        }
//        function onExpand(event, treeId, treeNode) {
//            curExpandNode = treeNode;
//        }






        //节点单击事件方法
        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("rs_tree");


            //$("#summary_id").load("${pageContext.request.contextPath}/m");

            /** @namespace treeNode.rsType 资源节点类型 */
            //控制Tabs成员  显示/隐藏
            Judge_lv(treeNode.rsType)
        }

        //节点刷新
        function re_zTree() {
            var treeObj = $.fn.zTree.getZTreeObj("rs_tree");
            treeObj.reAsyncChildNodes(null, "refresh");
        }

        //搜索到的节点改变样式
        function getFontCss(treeId, treeNode) {
            return (!!treeNode.highlight) ? {color: "#A60000", "font-weight": "bold"} : {
                color: "#333",
                "font-weight": "normal"
            };
        }

        //查找节点
        function searchNode(e) {
            var zTree = $.fn.zTree.getZTreeObj("rs_tree");
            var value = $.trim(key.get(0).value);
            if (lastValue === value) return;
            lastValue = value;
            if (value === "") return;
            updateNodes(false);
            nodeList = zTree.getNodesByParamFuzzy("name", value);
            updateNodes(true);
        }

        //焦点移出事件
        function blurKey(e) {
            //re_zTree();
        }

        //更新节点样式
        function updateNodes(highlight) {
            var zTree = $.fn.zTree.getZTreeObj("rs_tree");
            for( var i=0, l=nodeList.length; i<l; i++) {
                nodeList[i].highlight = highlight;
                zTree.updateNode(nodeList[i]);



                if(nodeList[i].isParent){
                    zTree.expandNode(nodeList[i], true, true, false);
                }else{
                    //var node = zTree.getNodeByTId(nodeList[i].pId);

                }
                //
                //更新之后展开

            }
        }

        //增加右键菜单滑动效果
        $(function(){
            //为右键菜单增加鼠标划过效果
            $("#rMenu > ul > li").mouseenter(function(){
                $(this).css("background-color","#C1CDCD");
            });
            $("#div_snapshot > ul > li").mouseenter(function(){
                $(this).css("background-color","#C1CDCD");
            });
            $("#rMenu > ul > li").mouseleave(function(){
                $(this).css("background-color","white");
            });
            $("#div_snapshot > ul > li").mouseleave(function(){
                $(this).css("background-color","white");
            });
        })

    </script>





</head>
<body class="easyui-layout">
<%--右侧树--%>
<div data-options="region:'west',split:true,title:'资源池'" style="width:250px;">
    <label>
        快速查询：<input id="key" type="text" value="" style="width:180px;">
        <input id="key2" type="button" value="aaa">
    </label>
    <ul id="rs_tree" class="ztree"></ul>
    <%--这里考虑用动态生成-----------------------------------------------------------------------------------------------%>
    <jsp:include page="rMenu.jsp" flush="true" />
</div>


<%--工作区--%>
<div id="center" data-options="region:'center',title:''">
    <div id="easyui-tab" class="easyui-tabs">

    </div>
</div>
</body>
</html>
