function addTreeNode(){
alert("sad");
}




//action_url

var path1="/m";



//根据节点级别 控制Tabs是否显示隐藏
//rsType资源类型,资源rsid,
function Judge_lv(rsType) {

    //默认节点&根节点
    close_all_tabs();

    if(rsType=="0"){
        $('#easyui-tab').tabs('add',{
            title:"虚拟化",
            href:p+path1,
            tools:[{
                iconCls:'icon-mini-refresh',
                handler:function(){
                    alert('refresh');
                }
            }]
        });
        $('#easyui-tab').tabs('add',{
            title:"非虚拟化",
            href:"/index3.jsp"
        });

    }

    //地域节点
    if(rsType=="1"){



    }

    //数据中心
    if(rsType=="2"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"日志",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"物理机资源使用率",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"虚拟机资源使用率",
            href:p+path1
        });
    }

    //集群
    if(rsType=="3"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"日志",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"物理机资源使用率",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"虚拟机资源使用率",
            href:p+path1
        });
    }

    //主机
    if(rsType=="4"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"CPU",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"内存",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"存储",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"网络",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"日志",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"虚拟机资源使用率",
            href:p+path1
        });
    }

    //虚拟机
    if(rsType=="5"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"CPU",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"内存",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"磁盘",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"硬件",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"网络",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"日志",
            href:p+path1
        });
    }

    //存储
    if(rsType=="6"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });
    }

    //存储设备
    if(rsType=="7"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });
    }

    //网络
    if(rsType=="8"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });

    }

    //网络域
    if(rsType=="9"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });

    }

    //子网络域
    if(rsType=="10"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });

    }

    //vlan
    if(rsType=="11"){
        $('#easyui-tab').tabs('add',{
            title:"摘要",
            href:p+path1
        });
        $('#easyui-tab').tabs('add',{
            title:"IP信息",
            href:p+path1
        });

    }

    //默认选中第一个选项卡
    $(".tabs li").each(function(i, n){
        if(i==0){
            var title = $(n).text();
            $('#easyui-tab').tabs('select',title);
        }
    });



    $('#easyui-tab').tabs({
        border:false,
        onSelect:function(title){
            //alert(title+' is selected');

        }
    });
}

//关闭所有Tabs
function close_all_tabs(){
    $(".tabs li").each(function(i, n){
        var title = $(n).text();
        $('#easyui-tab').tabs('close',title);
    });
}