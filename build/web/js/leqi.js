var httpURL = "http://localhost:8080/LeQiEnglish";// "http://www.leqienglish.com";
var pageSize = 20;
var userId = -1;
$(document).ready(function() {

    //createTip();
    //checkLogin();
    //isneedback();

});
/**
 * 是否需要回退按钮
 */
function isneedback() {
    var url = document.URL;
    var index = url.search("index.html");
    if (index == -1) {
        if (url.search(".html") > -1) {
            var fistPage = $("<a href='#' id='firstPage' onclick='gobackfirstPage();return false;' style='color:#EE0000;font-size:12px'>返回首页</a>");
            $('#middleLeftDiv').append(fistPage);
        }

    }
}
function getShare(url, text) {

    var share = "<div id='bdshare' class='bdshare_t bds_tools get-codes-bdshare' data='{\"url\":"
            + url
            + ",\"text\":"
            + text
            + "}'>"
            + "<span class='bds_more'>more</span>"
            + "<a class='bds_qzone'></a>"
            + "<a class='bds_tsina'></a>"
            + "<a class='bds_tqq'></a>"
            + "<a class='bds_renren'></a><a class='bds_t163'></a><a class='shareCount'></a></div>";
    return share;
}
function addTable(url, isturnPage, callback) {
    /**
     * try {
     * netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserRead"); }
     * catch (e) { alert("Permission UniversalBrowserRead denied."); }
     */

    $.ajax({
        url: url,
        dataType: "text",
        type: "POST",
        success: function(msg) {
            $("body:first").append(msg);
            if (callback != null) {
                callback();
            }

            setContent2IdByUrl(httpURL + "/header.html", "header");
            setContent2IdByUrl(httpURL + "/bottom.html", "bottom_page");
            if (isturnPage) {
                turnPage(1, pageSize);
            }
        }
    });
}


function turnPage(page, size) {
    $.ajax({
        url: httpURL + "/edit/content_getContentList!getContentList.do",
        data: "currentPage=" + page + "&size=" + size,
        type: "POST",
        dataType: "json",
        success: function(result) {
            currentPage = result.currentPage;
            size = result.pageSize;
            allPage = result.allPage;
            allCount = result.allCount;
            var list = result.list;
            setPage(size, currentPage, allPage, allCount);
            createTable(list, size, currentPage, allCount);
        }
    });
}

function setContent2IdByUrl(url, id) {
    $.ajax({
        url: url,
        success: function(msg) {
            $("#" + id).html(msg);

        }
    });
}


function checkLogin() {
    $.ajax({
        url: httpURL + "/user/user_findUser!findUser.do",
        dataType: "json",
        type: "POST",
        success: function(msg) {
            if (msg.msg == "ok") {
                createLoginTip(msg);
                userId = msg.id;
            }

        }
    });

}

function loginopen() {
    showWindow("登录", httpURL + "/user/login.html", 400);

}

function registopen() {
    window.location.href = httpURL + "/user/adduser.html";
}

/**
 * 获取参数值
 * 
 * @param name
 * @returns
 */
function GetQueryString(name)

{

    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");

    var r = window.location.search.substr(1).match(reg);

    if (r != null)
        return unescape(r[2]);
    return null;

}

function resetHeaderUrl() {
    $('#index').attr("href", httpURL + "/index.html");
    $('#learne').attr("href", httpURL + "/learne.html");
    $('#grammar').attr("href", httpURL + "/grammars.html");
    $('#odow').attr("href", httpURL + "/ODOW.html");
}

function resetTitle() {

    $.ajax({
        url: "/edit/content!getTitleTip.do",
        dataType: "json",
        success: function(result) {
            if (result.title != "error") {
                var str = $("TITLE").html();
                $("TITLE").html(str + "---" + result.title);
                $("#leiqisoftTitle").html(result.title);
            }
        },
    });
}

/** *page** */
var pageSize;
function setPage(size, page, allpage, allcount) {
    if (allpage <= 1) {
        return;
    }
    pageSize = size;
    var pagestr = "";
    if (page > 1) {
        pagestr = createPage(1, "首页");
    }
    for (var i = 3; i > 0; i--) {
        if (page - i >= 1) {
            pagestr += createPage((page - i), (page - i));
        }
    }

    pagestr += createCurrentPage(page, page);

    for (var i = 1; i <= 3; i++) {
        if (page + i <= allpage) {
            pagestr += createPage((page + i), (page + i));

        }
    }

    if (page != allpage) {
        pagestr += createPage(allpage, "末页");
    }

    $("#pages").html(pagestr);

}

function createPage(page, value) {
    return "&nbsp;<a href='#' onclick='turnpage(" + page + ");return false;'>"
            + value + "</a>&nbsp;";
}

function createCurrentPage(page, value) {
    return "&nbsp;<strong>" + page + "</strong>&nbsp;";
}

function turnpage(page) {
    turnPage(page, pageSize);
}
/** **end path** */

/** 生成顶部登录注册提示栏* */
function createTip() {

    if ($(".userstatuStyle").length > 0) {
        return;
    }
    var div = $("<div class='userstatuStyle'></div>");
    var leftDiv = $("<div style='width:15%;float:left;position:relative; '>&nbsp;</div>");
    var rightDiv = $("<div  style='width:15%;float:left;position:relative; '>&nbsp;</div>");
    var middleLeftDiv = $("<div id='middleLeftDiv'  style='width:35%;float:left;position:relative; '>&nbsp;</div>");
    var middleRightDiv = $("<div id='tipinformation' style='width:35%;float:left;position:relative;'></div>");
    div.append(leftDiv);
    div.append(middleLeftDiv);

    var unlogin = $("<span id='unlogin' style='color:#EE0000;font-size:12px' >你还未登录!</span>");
    middleRightDiv.append(unlogin);
    middleRightDiv
            .append($("<span   style='font-size:10;color:#0;line-height:10px' >|</span>"));
    var login = $("<a href='#' class='userstatuStylefont' onclick='loginopen();return false'>登录</a>");
    middleRightDiv.append(login);

    middleRightDiv
            .append($("<span  style='font-size:10;color:#0;line-height:10px'  >|</span>"));
    var regist = $("<a href='#'  class='userstatuStylefont'  onclick='registopen();return false'>注册</a>");
    middleRightDiv.append(regist);
    middleRightDiv
            .append($("<span  style='font-size:10;color:#0;line-height:10px'  >|</span>"));
    var help = $("<a href='#'  class='userstatuStylefont'  onclick='loginopen();return false'>帮助</a>");
    middleRightDiv.append(help);
    middleRightDiv.append($("<span>&nbsp;</span>"));
    var search = $("<input type='search' placeholder='搜索' autosave='aoao.org.cn' results='8' >");
    middleRightDiv.append(search);

    div.append(middleRightDiv);
    div.append(rightDiv);
    $("body:first").append(div);
}

function createLoginTip(user) {
    var midDiv = $("#tipinformation").html("");
    var userinfo = $("<span id='unlogin' style='color:#EE0000;font-size:12px' >"
            + user.username + "</span>");
    midDiv.append(userinfo);
    midDiv
            .append($("<span   style='font-size:10;color:#0;line-height:10px' >|</span>"));
    var write = $("<a href='#' class='userstatuStylefont' onclick='useWrite();return false'>写文章</a>");
    midDiv.append(write);
    midDiv
            .append($("<span   style='font-size:10;color:#0;line-height:10px' >|</span>"));
    var loginout = $("<a href='#' class='userstatuStylefont' onclick='loginout();return false'>退出</a>");
    midDiv.append(loginout);
    midDiv
            .append($("<span  style='font-size:10;color:#0;line-height:10px'  >|</span>"));
    var help = $("<a href='#'  class='userstatuStylefont'  onclick='loginopen();return false'>帮助</a>");
    midDiv.append(help);
    midDiv
            .append($("<span  style='font-size:10;color:#0;line-height:10px'  >|</span>"));
    var search = $("<input type='search' placeholder='搜索' autosave='aoao.org.cn' results='8' >");
    midDiv.append(search);
}

function gobackfirstPage() {
    window.location.href = httpURL + "/index.html";
}

function useWrite() {
    showWindow("写文章", httpURL + "/edit/useredit.html", 700);
}

function createLoginOut() {
    var middleRightDiv = $("#tipinformation").html("");
    var unlogin = $("<span id='unlogin' style='color:#EE0000;font-size:12px' >你还未登录!</span>");
    middleRightDiv.append(unlogin);
    middleRightDiv
            .append($("<span   style='font-size:10;color:#0;line-height:10px' >|</span>"));
    var login = $("<a href='#' class='userstatuStylefont' onclick='loginopen();return false'>登录</a>");
    middleRightDiv.append(login);

    middleRightDiv
            .append($("<span  style='font-size:10;color:#0;line-height:10px'  >|</span>"));
    var regist = $("<a href='#'  class='userstatuStylefont'  onclick='registopen();return false'>注册</a>");
    middleRightDiv.append(regist);
    middleRightDiv
            .append($("<span  style='font-size:10;color:#0;line-height:10px'  >|</span>"));
    var help = $("<a href='#'  class='userstatuStylefont'  onclick='loginopen();return false'>帮助</a>");
    middleRightDiv.append(help);
    middleRightDiv.append($("<span>&nbsp;</span>"));
    var search = $("<input type='search' placeholder='搜索' autosave='aoao.org.cn' results='8' />");
    middleRightDiv.append(search);
}
function closeWin() {
    sd_remove();
    return false;
}

/**
 * 退出
 */
function loginout() {
    createLoginOut();
    $.ajax({
        url: httpURL + "/user/user_loginOut!loginOut.do",
        type: "POST",
        dataType: "json"

    });

}
function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    password = hex_md5(password);
    loginUserNameAndPassword(username, password);
}

function loginUserNameAndPassword(username, password) {
    $.ajax({
        url: httpURL + "/user/user_login!login.do",
        data: {
            "userName": username,
            "password": password
        },
        type: "POST",
        dataType: "json",
        success: function(result) {
            if (result.msg == 'ok') {
                userId = result.id;
                createLoginTip(result);
                turnPage(1, pageSize);
                sd_remove();
            } else {
                alert("用户名或密码错误！！");

            }
        }
    });
}

function createTable(list, currentPage, pageSize, allCount) {

    var startNum = allCount - (pageSize - 1) * currentPage;

    var tbody = $('#contentList');
    tbody.empty();
    for (var i = 0; i < list.length; i++) {
        var content = list[i];
        var tra = $('<tr></tr>');
        var tba = $('<td width="100%"></td>');
        var hasImage = false;
        var colspan = 1;
        //alert(content.iconPath);
        if (typeof (content.iconPath) != "undefined") {
            hasImage = true;
            colspan = 2;
        }

        var newTable = $("<table class='tableContentList'></table>");

        var title = "<h3 class='title'>" + (startNum - i) + "." + content.title
                + "</h3>";
        var newtitletr = $("<tr><td align='left' colspan='" + colspan + "'><a  href='#' onclick='reader("
                + content.id + "," + content.type + ");return false;'>" + title
                + "</a></td></tr>");
        newTable.append(newtitletr);
        var newcontenttr = $("<tr></tr>");
        if (colspan == 2) {
            var imageTd = $("<td><img width='80' height='60' src='" + content.iconPath + "'></td>");
            newcontenttr.append(imageTd);
        }
        newcontenttr.append($("<td>" + "   " + content.content + "</td>"));

        newTable.append(newcontenttr);
        var down = "";
        //alert(content.userId);
        if (content.userId == userId) {
            down = "<a href='#'>编辑</a>&nbsp;<a href='#' onclick='deleteContent("
                    + content.id
                    + ","
                    + content.userId
                    + ");return false;'>删除</a> ";
        }
        down += "评论(" + content.recomment + ")  "

                + "<a href='#' onclick='reader(" + content.id + "," + content.type
                + ");return false;'>阅读</a> (" + content.reader + ")"
                + "<a href='#' onclick='reader(" + content.id + ","
                + content.type + ")'>查看全文>></a>";
        var newdowntr = $("<tr><td align='right' colspan='" + colspan + "'>" + down + "</td></tr>");
        newTable.append(newdowntr);
        tba.append(newTable);

        tra.append(tba);
        tbody.append(tra);
    }
}

function deleteContent(id, userId) {

    $.ajax({
        url: httpURL
                + "/edit/content_deleteContent!deleteContent.do",
        data: {
            "id": id,
            "userId": userId
        },
        type: "POST",
        dataType: "json",
        success: function(result) {
            if (result.msg == 'ok') {
                window.location.href = httpURL + "/index.html";
            } else {
                alert("error");
            }
        }
    });
}



function reader(id, type) {
    window.location.href = httpURL + "/content/readContent.html?type=" + type + "&id=" + id;
}
var keyStr = "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789+/" +
        "=";

function encode64(input) {
    input = escape(input);
    var output = "";
    var chr1, chr2, chr3 = "";
    var enc1, enc2, enc3, enc4 = "";
    var i = 0;

    do {
        chr1 = input.charCodeAt(i++);
        chr2 = input.charCodeAt(i++);
        chr3 = input.charCodeAt(i++);

        enc1 = chr1 >> 2;
        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        enc4 = chr3 & 63;

        if (isNaN(chr2)) {
            enc3 = enc4 = 64;
        } else if (isNaN(chr3)) {
            enc4 = 64;
        }

        output = output +
                keyStr.charAt(enc1) +
                keyStr.charAt(enc2) +
                keyStr.charAt(enc3) +
                keyStr.charAt(enc4);
        chr1 = chr2 = chr3 = "";
        enc1 = enc2 = enc3 = enc4 = "";
    } while (i < input.length);

    return output;
}

function decode64(input) {
    var output = "";
    var chr1, chr2, chr3 = "";
    var enc1, enc2, enc3, enc4 = "";
    var i = 0;

    // remove all characters that are not A-Z, a-z, 0-9, +, /, or =  
    var base64test = /[^A-Za-z0-9\+\/\=]/g;
    if (base64test.exec(input)) {
        alert("There were invalid base64 characters in the input text.\n" +
                "Valid base64 characters are A-Z, a-z, 0-9, '+', '/', and '='\n" +
                "Expect errors in decoding.");
    }
    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

    do {
        enc1 = keyStr.indexOf(input.charAt(i++));
        enc2 = keyStr.indexOf(input.charAt(i++));
        enc3 = keyStr.indexOf(input.charAt(i++));
        enc4 = keyStr.indexOf(input.charAt(i++));

        chr1 = (enc1 << 2) | (enc2 >> 4);
        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
        chr3 = ((enc3 & 3) << 6) | enc4;

        output = output + String.fromCharCode(chr1);

        if (enc3 != 64) {
            output = output + String.fromCharCode(chr2);
        }
        if (enc4 != 64) {
            output = output + String.fromCharCode(chr3);
        }

        chr1 = chr2 = chr3 = "";
        enc1 = enc2 = enc3 = enc4 = "";

    } while (i < input.length);

    return unescape(output);
}

function checkCode(piccode) {
    var repiccode = false;

    $.ajax({
        url: httpURL + "/code/check/"+piccode+"?message=" + $.cookie("JSESSIONID"),
        type: 'post',
        dataType: "json",
        async: false,
        success: function(msg) {
            msg = msg.value;
            if(msg=="true"){
                repiccode = true;
            }else{
                repiccode = false;
            }
        }
    });
    if (repiccode) {

        return true;
    } else {
        alert("验证码错误！！");
      
        return false;
    }

  
}

function loadimag(image){
     var timenow = new Date().getTime();       
     image.attr("src",httpURL+"/code/fetch?d="+timenow);
    
}