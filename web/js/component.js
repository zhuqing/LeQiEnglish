/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Title extends React.Component{
    render(){
          return <div> <a target = '_blank' class = 'gridview_border_url' href = {this.props.url} > {this.props.text} </a></div > ;
            }
}

class SubTitle extends React.Component{
     render()
     {
       return  <div> <span> update: </span>{this.props.time}<span class="with-100"> </span > </div>;
     }
}

class ImageView extends React.Component{
    render(){
       var imageStyle={
           width:this.props.width,
           height:this.props.height
       };
       var divStyle={
           width:(this.props.width+20)
       };
        return <div> <img  style = {imageStyle} src = {this.props.src}/></div>;
    }
} 

class GridCell extends React.Component{
    render(){
        var title = this.props.data.title;
        var url = httpURL+'/content/listen.html?id='+this.props.data.id;
        var imageWidth = this.props.width-20;
        var dataTime = getDate(this.props.data.createTime,"yyyy-MM-dd");
        var gridCellStype={
            width:this.props.width,
            height:this.props.height,
            left:this.props.left,
            top:this.props.top
            
        };
        return  <div class="gridview_gridCell" style ={gridCellStype} >
                <Title title={title} url={url}/>
                <div class = 'div_blank' />
                <SubTitle time={dataTime}/>
                <div class = 'div_blank' />
                <ImageView width={imageWidth} height="130px" src="http://cimg1.tuicool.com/bm2I7j.jpg"/>
                </div>;
    }
}

class GridView extends  React.Component{
    getInitialState(){
        return {
            datas:{},
            page:1,
            pageSize,
            allPage,
            allCount
        };
    }
    componentDidMount(){
       this.serverRequest = $.post(this.props.url,function(result){
       var pageData = result.value.page.value;
       this.setState({
        datas: result.value.value.value,
        page:pageData.currentPage,
        pageSize: pageData.pageSize,
        allPage:pageData.allPage,
        allCount:pageData.allCount
      });
        }.bind(this));
    }
    
    componentWillUnmount() {
    this.serverRequest.abort();
  }
    render(){
        var i = 0;
        var gradwidth = 280;
var gradheight = 280;
var padding = 20;
var leftpadding = 0;
var parentLeft=0;
var parentTop = 0;

      var width = 900;

    var count = Math.floor(width / (gradwidth + padding));
    padding = padding*count*1.0/(count+1);
    leftpadding = (width-(padding+gradwidth)*count)*0.5;
    var remaindar = 1;
    if (count === 0) {
        gradwidth = width;
        count = 1;
    }
    parentLeft = 20;
    parentTop = 20;
    var rows = this.state.datas.length/count+1;
    var layoutX = leftpadding+parentLeft;
    var layoutY = parentTop;
    var cells = new Array();
    for (var i = 0; i < rows; i++) {
        for (var j = 0; j < count; j++) {
            if(i*count+j>=dataArray.length){
                continue;
            }
            var cell = $("<div class='gridview_border' style='width:" + gradwidth + "px;height:" + (gradheight+2) + "px;left:" + layoutX + "px;top:" + layoutY + "px'></div>");
      
            var gridCell = createGridCell(gradwidth,gradheight,dataArray[i*count+j].value);
          cells[i*rows+j]=<GridCell data={} />
            cell.append(gridCell);
            content.append(cell);
            layoutX +=gradwidth+padding;
        }
        layoutX = leftpadding+parentLeft;
        layoutY+=gradheight+padding;
    }
        return ;
    }
}

var data={title:"titleTest",createTime:"111111111111",id:"123"};
 ReactDOM.render(
                    <GridCell width="300px" height="300px" data={data}/> ,
                    document.getElementById('content_content')
                    );

//var GridCell = React.creatClass({
//render:function(){
//return (
//        <div style = "width:{this.props.width}px;height:{this.props.heigh}px; padding:12px 12px 12px 12px;  background-color: #ffffff" >
//        < Title url = {this.props.httpURL+'/content/listen.html?id='+this.props.data.id} text = "{this.props.data.title}" / >
//        < div class = 'div_blank' / >
//        < div class = "gridview_border_subtitle" > < span > update: < /span>{getDate(this.props.data.createTime,"yyyy-MM-dd")}<span style="width:100%"> </span > < /div>
//        < div class = 'div_blank' / >
//        < div style = 'height:130px;' > < img  style = 'width:"+(gradwidth-24)+"px;height:130px' src = "{ this.props.httpURL}/{this.props.data.iconPath}" / >
//        </div>
//
//        );
//        }
//});
//        var GridView = React.creatClass({
//        render:function(){
//        return (
//        {this.props.dataArray.map(function(data) {
//        return < GridCell data = {data} httpURL = {httpURL} / > ;
//        })}
//        );
//        }
//        });

