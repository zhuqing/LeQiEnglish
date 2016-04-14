
/**
 * 在div里创建table
 * @param {type} div
 * @param {type} datas 数据
 * @param {type} startIndex 起始索引
 * @returns {undefined}
 */
function createTable(div, datas, startIndex) {
    div.html("");
    var table = $("<table width='100%'></table>")
    for (var i = 0; i < datas.length; i++) {
        var item = datas[i];
        var tr = $("<tr/>");
        var indexTd = $("<td width='10px'>" + (startIndex + i) + "</td>");
        var titleTd = $("<td>" + item.title + "</td>");
        var dateStr = getDate(data.createTime, "yyyy-MM-dd");
        var dataTd = $("<td width='30px'>" + dateStr + "</td>");

        tr.append(indexTd);
        tr.append(titleTd);
        tr.append(dataTd);
        table.append(tr);
    }
    div.append(table);
}




