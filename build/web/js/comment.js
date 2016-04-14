var commenturl="http://www.leqienglish.com";//"http://localhost:8080";
var recommentCount = 0;
var recommentPageSize = 20;


function getComment(){
	  
        getCommentAjax(1,recommentPageSize);
}
     
function getCommentAjax(page,size){
	if (typeof(size) == "undefined"){
		size = recommentPageSize;
	}
      var commentTable = $("#comment");
      commentTable.empty();
        $.ajax({
        url:commenturl+"/edit/content!getComment.do",
        data:"currentPage="+page+"&size="+size+"&id="+id,
        dataType:"json",
        type:"POST",
        success:function(msg){
           var allCount = msg.allCount;
           recommentCount = allCount;
           var allPage = msg.allPage;
           var commentTable = $("#comment");
         
        	   setPage(recommentPageSize,page,allPage,allCount);
           
          
           var div = $("<div class='recommentClass'>网友评论，共"+allCount+"条   <a href='#here'>点击评论</a></div>");
           commentTable.append(div);
           for(var i = 0 ; i < msg.list.length ; i++){
             var comment = msg.list[i];
             var classname = i%2==0?"recommentClassWhait":"recommentClassGray";
             div = $("<div id='recomment"+i+"' class='"+classname+"'></div>");
             var name =$("<div ><b>"+comment.username+"</b>发表于"+comment.createDate+" ，<a href='#' onclick='addreComment("+comment.id+",\"recomment"+i+"\");return false;' >回复此评论</a></div>");
             var content = $("<div style='color:blue'></div><br/>");
             if(comment.reCommentId>1){
                getsubComment(content,comment.reCommentId,i+"");
             }
             content.html(comment.content);
             div.append(name);
             div.append(content);
             commentTable.append(div);
             }
           
        }
      });
     }
     var subcontent;
     
     function getsubComment(obj,rid,count){
        $.ajax({
          url:commenturl+"/edit/content!getCommentById.do",
          type:"post",
          data:"id="+rid+"&timetamp="+Math.random(),
          dataType:"json",
          success:function(msg){
             //var rand = 
             if(msg.msg!="ok"){
                  var div = $("<div class='recommentDIV'></div>");
                  var name =$("<div class='recommentTitleDIV'>引用<b>"+msg.username+"</b>的评论</div>");
                  var connent = $("<div class='recommentContentDIV' id='comm"+count+"com'></div>");
                  var html = $("<span >"+msg.content+"</span>");
                  connent.append(html);
                  div.append(name);
                  div.append(connent);
                 // obj.prepend(connent);
                 var subcontent=obj.find("div#comm"+count);
                 if(subcontent.length>0){
                   subcontent.prepend(div);
                 }else{
                   obj.prepend(div);
                 }
                 
                  if(msg.reCommentId>1){
                    getsubComment(obj, msg.reCommentId,count+"com");
                  }
             }
          }
          
        });
     }
     
     var recomment;
     var lastDiv;
     
     function addreComment(cid,commId){
        var div = $("#"+commId);
        
       // var comment = $("#editComment");
       // 
        if(recomment==null){
         
          $.ajax({
           url:commenturl+"/content/recomment.html",
           dataType:"html",
           type:"post",
           success:function(msg){
               recomment = $("<div></div>");
               recomment.html(msg);
               addClick(cid);
               div.append(recomment);
           }
          });
        }else{
         // recomment.remove();
          lastDiv.children("table#recommenttest").remove();
          recomment.find("input#reusername").val("");
		  recomment.find("input#reemail").val("");
		  recomment.find("textarea#remessage").val("");
          div.append(recomment);
          addClick(cid);
        }
        lastDiv = div;
        return false;
        
     }
     
     function addClick(cid){
     recomment.find("input#recommentButton").click(function(){
			       var username = recomment.find("input#reusername").val();
			       var email = recomment.find("input#reemail").val();
			       var message = recomment.find("textarea#remessage").val();
			        $.ajax({
			            url:"../edit/content_addComment!addComment.do",
			            data:"id="+id+"&email="+email+"&username="+username+"&content="+message+"&reCommentId="+cid,
			            dataType:"json",
			            type:"POST",
			            success:function(msg){
			               if(msg.msg=="ok"){
			                 // alert("ok");
			                  updateReComment();//更新评论数
			                  getComment();
			               }else{
			                  alert("error");
			               }
			            },
			            error:function(){
			               alert("error");
			            }
			            });
			       
               
               });
     }
    
     
     function addComment(commentId){
        var email = $("#email").val();
        var username = $("#username").val();
        var comment = $("#messageComment").val();
        
         $.ajax({
            url:commenturl+"/edit/content_addComment!addComment.do",
            data:"id="+id+"&email="+email+"&username="+username+"&content="+comment+"&reCommentId="+commentId,
            dataType:"json",
            type:"POST",
            success:function(msg){
               if(msg.msg=="ok"){
                  updateReComment();//更新评论数
                  getComment();
                   $("#email").val("");
                   $("#username").val("");
                   $("#message").val("");
               }else{
                  alert("error");
               }
            },
            error:function(){
               alert("error");
            }
            });
     }
     
      var scrollH = 0;
      $(window).scroll(function(){
        var bot = 50;
      // alert($(window).scrollTop()+"::"+$(document).height()+"::"+$(window).height());
       if((100 + $(window).scrollTop()) >= ($(document).height() - $(window).height()) && $(window).scrollTop() > scrollH){
       //   document.getElementById("dialog").style.display="";
       }else{
          //document.getElementById("dialog").style.display="none";
       }
     });
      
     
      function updateReComment(){
          recommentCount+=1;
           $.ajax({
              url:commenturl+"/edit/content!updateRecomment.do",
              data:"id="+id+"&reCommentId="+recommentCount,
              type:"post"
           });
       }
      
      function turnPage(page,pageSize){
    	  var commentTable = $("#comment");
    	  commentTable.html("");
    	  getCommentAjax(page,pageSize);
      }
     