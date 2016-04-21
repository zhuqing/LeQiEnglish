var array = new Array("热度", "评论", "分享", "点赞", "收藏");
var gradwidth = 280;
var gradheight = 280;
var padding = 20;
var leftpadding = 0;
var parentLeft=0;
var parentTop = 0;
function createGridView(content, dataArray) {
    content.empty();
    var width = content.width();

    var count = Math.floor(width / (gradwidth + padding));
    padding = padding*count*1.0/(count+1);
    leftpadding = (width-(padding+gradwidth)*count)*0.5;
    var remaindar = 1;
    if (count === 0) {
        gradwidth = width;
        count = 1;
    }
    parentLeft = content.position().left;
    parentTop = content.position().top;
    var rows = dataArray.length/count+1;
    var layoutX = leftpadding+parentLeft;
    var layoutY = parentTop;
    for (var i = 0; i < rows; i++) {
        for (var j = 0; j < count; j++) {
            if(i*count+j>=dataArray.length){
                continue;
            }
            var cell = $("<div class='gridview_border' style='width:" + gradwidth + "px;height:" + (gradheight+2) + "px;left:" + layoutX + "px;top:" + layoutY + "px'></div>");
      
            var gridCell = createGridCell(gradwidth,gradheight,dataArray[i*count+j].value);
          
            cell.append(gridCell);
            content.append(cell);
            layoutX +=gradwidth+padding;
        }
        layoutX = leftpadding+parentLeft;
        layoutY+=gradheight+padding;
    }
    //alert(layoutY-parentTop-gradheight-padding);
    content.height(layoutY-parentTop-gradheight-padding);
}

/**
 * 创建Grid的单元
 * src='" + data.iconPath + "'
 */
function createGridCell(width, height, data) {
          
    var border = $("<div style='width:"+width+"px;height:"+height+"px; padding:12px 12px 12px 12px;  background-color: #ffffff'></div>");
     //var path = encode64(JSON.stringify(data));
    var title = $("<div ><a target='_blank' class='gridview_border_url' href='"+httpURL+"/content/listen.html?id="+data.id+"'>" + data.title + "</a></div>");
    border.append(title);
       border.append($("<div class='div_blank'/>"));
      
       var dateStr= getDate(data.createTime,"yyyy-MM-dd");
        
    var subtitle=$("<div class='gridview_border_subtitle'><span >update:</span>"+dateStr+"<span style='width:100%'> </span></div>");
    border.append(subtitle);
    border.append($("<div class='div_blank'/>"));
    var imagePath = httpURL+"/"+data.iconPath;
     var image = $("<div style='height:130px;'><img  style='width:"+(gradwidth-24)+"px;height:130px' src='"+imagePath+"'/></tr>");
    border.append(image);
    //var text = $("<div style='height:70px;top:150px; position:absolute'>" + data.content + "</div>");
   // border.append(text);

     /// border.append(createButtonBar(array, data.id));
    return border;
}

/**
 * 创建下面的buttonBar
 */
function createButtonBar(arrayData, id) {
    var buttonBar = $("<div class='gridview_bottom_div' style='width:100%;top:"+(gradheight-20)+"px;'></div>");
    for (var i = 0; i < arrayData.length; i++) {
        var button = $("<input cursor='hand'  class='gridview_button' style='width:"+(gradwidth/arrayData.length)+"px' type='button' value='"+arrayData[i]+"' onclick='buttonClick_gridView('" + arrayData[i] + "'," + id + ")' />");
        buttonBar.append(button);
    }

    return buttonBar;
}

function buttonClick_gridView(type,id){
    
}

function titleOnclick_gridView(type,id){
   // window.
       // window.location.
     window.location.href = httpURL+"/content/listen.html?type="+type+"&id="+id;
}